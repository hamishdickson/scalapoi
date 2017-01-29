package scalapoi

import cats.free.Free
import PoiAdt._
import utils._
import org.apache.poi.hssf.usermodel._

object Adt {
  type PoiGrammer[T] = Free[PoiAdtT, T]

  import cats.free.Free.liftF

  def createDocument(name: String): PoiGrammer[Workbook] =
    liftF[PoiAdtT, Workbook](CreateDocument(name))

  def createSheet(name: String, wb: Workbook): PoiGrammer[HSSFSheet] =
    liftF[PoiAdtT, HSSFSheet](CreateSheet(name, wb))

  // Put returns nothing (i.e. Unit).
  def put[T](value: T, sheet: HSSFSheet): PoiGrammer[Unit] =
    liftF[PoiAdtT, Unit](Put[T](value, sheet))

  def putMany[T](values: List[T], sheet: HSSFSheet): PoiGrammer[Unit] =
    liftF[PoiAdtT, Unit](PutMany[T](values, sheet))

  def head[T](sheet: HSSFSheet): PoiGrammer[Option[T]] =
    liftF[PoiAdtT, Option[T]](Head[T](sheet))

  // Get returns a T value.
  def get[T](sheet: HSSFSheet): PoiGrammer[List[T]] =
    liftF[PoiAdtT, List[T]](Get[T](sheet))

  // Delete returns nothing (i.e. Unit).
  def delete(sheet: HSSFSheet): PoiGrammer[Unit] =
    liftF[PoiAdtT, Unit](Delete(sheet))
}
