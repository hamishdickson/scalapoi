name := "scalapoi"

version := "0.1"

lazy val catsVersion = "0.9.0"
lazy val poiVersion = "3.14"
lazy val monixVersion = "2.1.0"
lazy val shapelessVersion = "2.3.2"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats" % catsVersion,
  "org.apache.poi" % "poi" % poiVersion,
  "org.apache.poi" % "poi-ooxml" % poiVersion,
  "io.monix" %% "monix" % monixVersion,
  "io.monix" %% "monix-cats" % monixVersion,
  "com.chuusai" %% "shapeless" % shapelessVersion
)
