package scalapoi

import cats.free.Free
import scalapoi.PoiAdt._

object Adt {
  type PoiGrammer[T] = Free[PoiAdtT, T]

  import cats.free.Free.liftF

  def createDocument(name: String): PoiGrammer[Unit] =
    liftF[PoiAdtT, Unit](CreateDocument(name))

  def createSheet(name: String, doc: Document): PoiGrammer[Unit] =
    liftF[PoiAdtT, Unit](CreateSheet(name, doc))

  // Put returns nothing (i.e. Unit).
  def put[T](value: T, sheet: Sheet): PoiGrammer[Unit] =
    liftF[PoiAdtT, Unit](Put[T](value, sheet))

  def putMany[T](values: List[T], sheet: Sheet): PoiGrammer[Unit] =
    liftF[PoiAdtT, Unit](PutMany[T](values, sheet))

  def head[T](sheet: Sheet): PoiGrammer[Option[T]] =
    liftF[PoiAdtT, Option[T]](Head[T](sheet))

  // Get returns a T value.
  def get[T](sheet: Sheet): PoiGrammer[List[T]] =
    liftF[PoiAdtT, List[T]](Get[T](sheet))

  // Delete returns nothing (i.e. Unit).
  def delete(sheet: Sheet): PoiGrammer[Unit] =
    liftF[PoiAdtT, Unit](Delete(sheet))
}
