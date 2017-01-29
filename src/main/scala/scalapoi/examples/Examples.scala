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
  val bars = List(
    Bar("one", Some(foo), 4.0),
    Bar("two", Some(foo), 4.0),
    Bar("three", None, 4.0)
  )

  val w = PoiEncoder.writePoi(bars)

  /*
  scala> w
  res1: List[List[scalapoi.PoiCell]] =
  List(
    List(StrCell(one), StrCell(two), BoolCell(false), NumCell(123.0), NumCell(4.0)),
    List(StrCell(two), StrCell(two), BoolCell(false), NumCell(123.0), NumCell(4.0)),
    List(StrCell(three), StrCell(two), BoolCell(false), NumCell(123.0), NumCell(4.0))
  )
*/

  case class Thingy(s: String, i: Int, b: Boolean)
  val thingys = List(
    Thingy("foo1", 123, false),
    Thingy("foo2", 456, true),
    Thingy("foo3", 789, false),
    Thingy("foo4", 112, true),
    Thingy("foo5", 345, false)
  )

  import scalapoi.Adt._

  val createAWbAndFill = for {
    wbk <- createDocument("things.xlsx")
    sheet <- createSheet("sheet1", wbk)
    //_ <- put[Int](1, sheet)
  } yield ()

  import scalapoi.dragons.PoiInterpreter._

  lazy val thing = createAWbAndFill.foldMap(impureInterpreter)
}
