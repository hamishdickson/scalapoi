package scalapoi

import org.apache.poi.hssf.usermodel._
import utils._

object PoiAdt {
  sealed trait PoiAdtT[T]

  final case class CreateDocument(name: String) extends PoiAdtT[Workbook]

  final case class CreateSheet(name: String, wb: Workbook) extends PoiAdtT[HSSFSheet]

  /**
   * Put a single T
   */
  final case class Put[T](value: T, sheet: HSSFSheet) extends PoiAdtT[Unit]

  /**
   * Takes a list of T and puts them into the desired sheet
   */
  final case class PutMany[T](values: List[T], sheet: HSSFSheet) extends PoiAdtT[Unit]

  /*
   * Get the head T from a sheet
   */
  final case class Head[T](sheet: HSSFSheet) extends PoiAdtT[Option[T]]

  /*
   * Get the Ts from a sheet
   */
  final case class Get[T](sheet: HSSFSheet) extends PoiAdtT[List[T]]

  final case class Delete(sheet: HSSFSheet) extends PoiAdtT[Unit]
}
