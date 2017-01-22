package scalapoi

object PoiFree {
  trait Sheet

  sealed trait PoiGrammerT[T]
  final case class Put[T](sheet: Sheet, value: T) extends PoiGrammerT[Unit]
  final case class Get[T](sheet: Sheet) extends PoiGrammerT[Option[T]]
  final case class Delete(sheet: Sheet) extends PoiGrammerT[Unit]

  import cats.free.Free

  type PoiGrammer[T] = Free[PoiGrammerT, T]

  import cats.free.Free.liftF

  // Put returns nothing (i.e. Unit).
  def put[T](sheet: Sheet, value: T): PoiGrammer[Unit] =
    liftF[PoiGrammerT, Unit](Put[T](sheet, value))

  // Get returns a T value.
  def get[T](sheet: Sheet): PoiGrammer[Option[T]] =
    liftF[PoiGrammerT, Option[T]](Get[T](sheet))

  // Delete returns nothing (i.e. Unit).
  def delete(sheet: Sheet): PoiGrammer[Unit] =
    liftF(Delete(sheet))
}
