package scalapoi

object PoiAdt {
  sealed trait PoiAdtT[T]

  final case class CreateDocument(name: String) extends PoiAdtT[Unit]

  final case class CreateSheet(name: String, document: Document) extends PoiAdtT[Unit]

  /**
   * Put a single T
   */
  final case class Put[T](value: T, sheet: Sheet) extends PoiAdtT[Unit]

  /**
   * Takes a list of T and puts them into the desired sheet
   */
  final case class PutMany[T](values: List[T], sheet: Sheet) extends PoiAdtT[Unit]

  /*
   * Get the head T from a sheet
   */
  final case class Head[T](sheet: Sheet) extends PoiAdtT[Option[T]]

  /*
   * Get the Ts from a sheet
   */
  final case class Get[T](sheet: Sheet) extends PoiAdtT[List[T]]

  final case class Delete(sheet: Sheet) extends PoiAdtT[Unit]
}
