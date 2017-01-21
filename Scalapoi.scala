package scalapoi

import shapeless._

object Poi {
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
  }

  def createEncoder[A](func: A => List[String]) =
    new PoiEncoder[A] {
      def encode(value: A): List[String] = func(value)
    }

  implicit val stringEncoder: PoiEncoder[String] = createEncoder(s => List(s))
  implicit val intEncoder: PoiEncoder[Int] = createEncoder(i => List(i.toString))
  implicit val booleanEncoder: PoiEncoder[Boolean] = createEncoder(b => if (b) List("true") else List("false"))

  implicit val hnilEncoder: PoiEncoder[HNil] = createEncoder(hnil => Nil)

  implicit def hlistEncoder[H, T <: HList](
    implicit
    hEncoder: PoiEncoder[H],
    tEncoder: PoiEncoder[T]
  ): PoiEncoder[H :: T] =
    createEncoder { case h :: t => hEncoder.encode(h) ++ tEncoder.encode(t) }

  implicit def genericEncoder[A, R](
    implicit
    gen: Generic.Aux[A, R],
    enc: PoiEncoder[R]
  ): PoiEncoder[A] = createEncoder(a => enc.encode(gen.to(a)))
}

object Helper {
  import Poi._

  // this is basically a CSV writer
  def writePoi[A](values: List[A])(implicit P: PoiEncoder[A]) =
    values.map(v => P.encode(v).mkString(",")).mkString("\n")
}
