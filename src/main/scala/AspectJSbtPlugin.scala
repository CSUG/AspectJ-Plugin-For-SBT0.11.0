package name.fujohnwang

import sbt._, Keys._

object AspectJSbtPlugin extends Plugin with Creator {

  val AspectJConfiguration = config("aspectj").extend(Compile)
  val aspectjVersion = SettingKey[String]("aspectj-version")
  val aspectDirectory = SettingKey[File]("aspect_directory")
  val aspectjClasspath = TaskKey[Classpath]("aspectj-classpath")

  lazy val weaveTask: Setting[_] = TaskKey[Unit]("weave") <<= (streams, classDirectory in Compile, aspectDirectory, aspectjClasspath) map {
    (s, clazzDir, aspectDir, cp) =>
      s.log.info("run ajc to weave...")

      val weaver = new org.aspectj.tools.ajc.Main
      val classpathOption = Seq("-classpath", cp.files.absString)
      val baseOptions = Seq[String]("-inpath", clazzDir.absolutePath, "-sourceroots", aspectDir.absolutePath, "-d", clazzDir.absolutePath, "-1.5")
      val msghandler = new org.aspectj.bridge.MessageHandler
      weaver.run((baseOptions ++ classpathOption).toArray, msghandler)
      msghandler.getMessages(null, true).foreach(m => s.log.info(m.getMessage))
  }

  override lazy val settings = inConfig(AspectJConfiguration)(Seq(
    weaveTask,
    aspectjVersion := "1.6.11.RELEASE",
    aspectDirectory <<= sourceDirectory(_ / "main" / "aspectj"),
    managedClasspath <<= (configuration, classpathTypes, update) map Classpaths.managedJars,
    dependencyClasspath <<= fullClasspath in Compile,
    aspectjClasspath <<= (managedClasspath, dependencyClasspath) map {
      _ ++ _
    },
    resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/",
    libraryDependencies <+= (aspectjVersion in AspectJConfiguration)("org.aspectj" % "aspectjtools" % _),
    libraryDependencies <+= (aspectjVersion in AspectJConfiguration)("org.aspectj" % "aspectjrt" % _)
  ))
}

