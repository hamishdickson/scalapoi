package scalapoi
package dragons

import scalapoi.PoiAdt._

import cats.arrow.FunctionK
import cats.{Id, ~>}

object PoiInterpreter {
  /*
  * first kind of horrible interpreter
  */
  def impureInterpreter: PoiAdtT ~> Id  =
    new (PoiAdtT ~> Id) {

      def apply[A](fa: PoiAdtT[A]): Id[A] =
        fa match {
          case CreateDocument(name) =>
            Document.create(name)
          case CreateSheet(name, wb) =>
            Sheet.create(name, wb)
          case _ => ???
        }
    }
}
