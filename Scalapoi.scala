package scalapoi

import shapeless._

trait PoiEncoder[A] {
  def encode(value: A): List[String]
  //   def decode(value: T): List[String]
}

object PoiEncoder {
  // "summoner method"
  def apply[A](implicit enc: PoiEncoder[A]) = enc

  def instance[A](func: A => List[String]) =
    new PoiEncoder[A] {
      def encode(value: A): List[String] = func(value)
    }

  // this is basically a CSV writer right now
  def writePoi[A](values: List[A])(implicit P: PoiEncoder[A]) =
    values.map(v => P.encode(v).mkString(",")).mkString("\n")
}

// the following code is designed to derive a PoiEncoder instance for flat case classes
object implicits {
  implicit val stringEncoder: PoiEncoder[String] = PoiEncoder.instance(s => List(s))
  implicit val intEncoder: PoiEncoder[Int] = PoiEncoder.instance(i => List(i.toString))
  implicit val booleanEncoder: PoiEncoder[Boolean] =
    PoiEncoder.instance(b => if (b) List("true") else List("false"))

  implicit val hnilEncoder: PoiEncoder[HNil] = PoiEncoder.instance(hnil => Nil)

  implicit def hlistEncoder[H, T <: HList](
    implicit
    hEncoder: PoiEncoder[H],
    tEncoder: PoiEncoder[T]
  ): PoiEncoder[H :: T] =
    PoiEncoder.instance { case h :: t => hEncoder.encode(h) ++ tEncoder.encode(t) }

  implicit def genericEncoder[A, R](
    implicit
    gen: Generic.Aux[A, R],
    enc: PoiEncoder[R]
  ): PoiEncoder[A] = PoiEncoder.instance(a => enc.encode(gen.to(a)))
}
