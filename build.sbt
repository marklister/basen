name := "BaseN"

organization :="org.catch22"

publishTo := Some(Resolver.file("file", new File("src/site")))

initialCommands in console := """
  import org.catch22.BaseN._
  import org.catch22.CryptoOps._
  """

scalaVersion:="2.10.3"

crossScalaVersions := Seq("2.10.3")

version := "1.0"