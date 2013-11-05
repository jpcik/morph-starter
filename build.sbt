name := "morph-starter"

organization := "es.upm.fi.oeg.morph"

version := "0.0.2"

scalaVersion := "2.10.3"

crossPaths := false

libraryDependencies ++= Seq(
  "es.upm.fi.oeg.morph" % "morph-querygen" % "1.0.6",
  "es.upm.fi.oeg.morph" % "morph-r2rml-tc" % "1.0.6" intransitive,
  "org.scalatest" % "scalatest_2.10" % "2.0.RC1" % "test"
)

resolvers ++= Seq(
  "aldebaran-releases" at "http://aldebaran.dia.fi.upm.es/artifactory/sstreams-releases-local",
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
)

EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource

//javacOptions ++= Seq("-source", "1.7", "-target", "1.6")

mainClass := Some("es.upm.fi.oeg.morph.DemoQueryJava")



