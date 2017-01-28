package scalapoi

trait PoiEncoder[A] {
  val width: Int
  def encode(value: A): List[PoiCell]
}

object PoiEncoder {
  // "summoner method"
  def apply[A](implicit enc: PoiEncoder[A]) = enc

  def pure[A](w: Int)(func: A => List[PoiCell]) =
    new PoiEncoder[A] {
      val width = w
      def encode(value: A): List[PoiCell] = func(value)
    }

  // todo this is basically a CSV writer right now, adapt
  def writePoi[A](values: List[A])(implicit P: PoiEncoder[A]) =
    values.map(P.encode(_))
}
