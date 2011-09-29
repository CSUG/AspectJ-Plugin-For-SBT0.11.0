sbtPlugin := true

name := "aspectj_sbt_plugin"

version := "0.0.1"

organization := "name.fujohnwang"

publishMavenStyle := true

scalacOptions := Seq("-deprecation", "-unchecked")

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq("org.aspectj" % "aspectjtools" % "1.6.11.RELEASE", "org.aspectj" % "aspectjrt" % "1.6.11.RELEASE","org.aspectj" % "aspectjweaver" % "1.6.11.RELEASE")

