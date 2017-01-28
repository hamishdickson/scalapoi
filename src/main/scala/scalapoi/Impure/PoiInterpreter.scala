package scalapoi
package interpreter

import scalapoi.PoiAdt._
import Document._

import cats.arrow.FunctionK
import cats.{Id, ~>}

object PoiInterpreter {
  /*
  * first interpreter
  */
  def impureInterpreter: PoiAdtT ~> Id  =
    new (PoiAdtT ~> Id) {

      def apply[A](fa: PoiAdtT[A]): Id[A] =
        fa match {
          case CreateDocument(name) =>
            Document.create(name)
          case _ => ???
        }
    }
}
