package scalapoi

object PoiAdt {
  sealed trait PoiAdtT[T]

  final case class CreateDocument(name: String) extends PoiAdtT[Unit]

  final case class CreateSheet(name: String, document: Document) extends PoiAdtT[Unit]

  final case class Put[T](sheet: Sheet, value: T) extends PoiAdtT[Unit]

  final case class Get[T](sheet: Sheet) extends PoiAdtT[Option[T]]
  
  final case class Delete(sheet: Sheet) extends PoiAdtT[Unit]
}
