name := "BaseN"

organization :="org.github.marklister.basen"

publishTo := Some(Resolver.file("file", new File("src/site")))

initialCommands in console := """
  import org.github.marklister.basen.BaseN._
  import org.github.marklister.basen.CryptoOps._
  """

scalaVersion:="2.13.0"

version := "1.02"
