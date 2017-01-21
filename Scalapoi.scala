package scalapoi

import shapeless._

trait PoiEncoder[A] {
  val width: Int
  def encode(value: A): List[String]
  //   def decode(value: T): List[String]
}

object PoiEncoder {
  // "summoner method"
  def apply[A](implicit enc: PoiEncoder[A]) = enc

  def pure[A](w: Int)(func: A => List[String]) =
    new PoiEncoder[A] {
      val width = w
      def encode(value: A): List[String] = func(value)
    }

  // this is basically a CSV writer right now
  def writePoi[A](values: List[A])(implicit P: PoiEncoder[A]) =
    values.map(v => P.encode(v).mkString(",")).mkString("\n")
}

// the following code is designed to derive a PoiEncoder instance for flat case classes
object implicits {
  implicit val stringEncoder: PoiEncoder[String] = PoiEncoder.pure(1)(s => List(s))
  implicit val intEncoder: PoiEncoder[Int] = PoiEncoder.pure(1)(i => List(i.toString))
  implicit val booleanEncoder: PoiEncoder[Boolean] =
    PoiEncoder.pure(1)(b => if (b) List("true") else List("false"))

  implicit val hnilEncoder: PoiEncoder[HNil] = PoiEncoder.pure(0)(hnil => Nil)

  implicit def hlistEncoder[H, T <: HList](
    implicit
    hEncoder: Lazy[PoiEncoder[H]],
    tEncoder: PoiEncoder[T]
  ): PoiEncoder[H :: T] =
    PoiEncoder.pure(hEncoder.value.width + tEncoder.width) {
      case h :: t => hEncoder.value.encode(h) ++ tEncoder.encode(t)
    }

  implicit val cnilEncoder: PoiEncoder[CNil] =
    PoiEncoder.pure(0)(cnil => throw new Exception("unreachable"))

  implicit def coproductEncoder[H, T <: Coproduct](
    implicit
    hEncoder: Lazy[PoiEncoder[H]],
    tEncoder: PoiEncoder[T]
  ): PoiEncoder[H :+: T] = PoiEncoder.pure(hEncoder.value.width + tEncoder.width) {
    case Inl(h) => hEncoder.value.encode(h) ++ List.fill(tEncoder.width)("")
    case Inr(t) => List.fill(hEncoder.value.width)("") ++ tEncoder.encode(t)
  }

  implicit def genericEncoder[A, R](
    implicit
    gen: Generic.Aux[A, R],
    enc: Lazy[PoiEncoder[R]]
  ): PoiEncoder[A] = PoiEncoder.pure(enc.value.width)(a => enc.value.encode(gen.to(a)))
}
