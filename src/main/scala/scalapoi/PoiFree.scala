package scalapoi

object PoiFree {
  sealed trait PoiGrammerT[T]
  final case class CreateDocument(name: String) extends PoiGrammerT[Unit]
  final case class CreateSheet(name: String, document: Document) extends PoiGrammerT[Unit]
  final case class Put[T](sheet: Sheet, value: T) extends PoiGrammerT[Unit]
  final case class Get[T](sheet: Sheet) extends PoiGrammerT[Option[T]]
  final case class Delete(sheet: Sheet) extends PoiGrammerT[Unit]
}
