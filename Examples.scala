package examples

import scalapoi._
import scalapoi.instances._

object Examples {
  /*
  scalapoi can infer the writer method for any case class
  */
  case class Foo(s1: String, b: Boolean, i: Int)
  case class Bar(s2: String, f: Option[Foo], d: Double)

  val foo = Foo("two", false, 123)
  val bars = List(Bar("one", Some(foo), 4.0))

  val w = PoiEncoder.writePoi(bars)
}
