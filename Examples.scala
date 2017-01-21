package examples

import scalapoi._
import scalapoi.implicits._

object Examples {
  /*
  scalapoi can infer the writer method for any case class
  */
  case class Foo(s: String, b: Boolean)

  val foos = List(Foo("another", false))

  val w = PoiEncoder.writePoi(foos)
}
