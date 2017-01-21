package examples

import scalapoi._
import scalapoi.implicits._

object Examples {
  /*
  scalapoi can infer the writer method for any case class
  */
  case class Foo(s1: String, b: Boolean)
  case class Bar(s2: String, f: Foo)

  val bars = List(Bar("one", Foo("two", false)))

  val w = PoiEncoder.writePoi(bars)
}
