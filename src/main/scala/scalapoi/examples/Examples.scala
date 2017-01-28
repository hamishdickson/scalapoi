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

  import scalapoi._
  import scalapoi.Adt._

  case class Thingy(s: String, i: Int, b: Boolean)
  val thingy = Thingy("foo", 123, false)

  val doc = Document("doc1")
  val sheet = Sheet("sheet1", doc)

  val createPutAndGet = for {
    _ <- createDocument("thing")
    _ <- createSheet("sheet1", doc)
    _ <- put[Thingy](thingy, sheet)
    thingys <- get[Thingy](sheet)
  } yield thingys
}
