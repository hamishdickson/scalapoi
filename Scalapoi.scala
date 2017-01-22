package scalapoi

trait PoiEncoder[A] {
  val width: Int
  def encode(value: A): List[PoiCell]
  //   def decode(value: T): List[String]
}

object PoiEncoder {
  // "summoner method"
  def apply[A](implicit enc: PoiEncoder[A]) = enc

  def pure[A](w: Int)(func: A => List[PoiCell]) =
    new PoiEncoder[A] {
      val width = w
      def encode(value: A): List[PoiCell] = func(value)
    }

  // this is basically a CSV writer right now
  def writePoi[A](values: List[A])(implicit P: PoiEncoder[A]) =
    values.map(v => P.encode(v).mkString(",")).mkString("\n")
}
