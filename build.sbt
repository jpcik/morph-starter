name := "morph-starter"

organization := "es.upm.fi.oeg.morph"

version := "0.0.1"

scalaVersion := "2.10.1"

crossPaths := false

libraryDependencies ++= Seq(
  //"es.upm.fi.oeg.morph" % "morph-core" % "1.0.3",
  "es.upm.fi.oeg.morph" % "morph-querygen" % "1.0.4",
  "es.upm.fi.oeg.morph" % "morph-r2rml-tc" % "1.0.3" intransitive,
  "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test",
  "org.scalacheck" % "scalacheck_2.10" % "1.10.0" % "test"
  //"com.typesafe.akka" %% "akka-actor" % "2.1.2",
  //"com.typesafe.akka" %% "akka-remote" % "2.1.2",
  //"com.typesafe.akka" %% "akka-kernel" % "2.1.2"
)

resolvers ++= Seq(
  "aldebaran-releases" at "http://aldebaran.dia.fi.upm.es/artifactory/sstreams-releases-local",
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
)

EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource

//javacOptions ++= Seq("-source", "1.7", "-target", "1.6")

mainClass := Some("es.upm.fi.oeg.morph.DemoQueryJava")



