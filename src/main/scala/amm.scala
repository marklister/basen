object amm extends App {

  import org.github.marklister.basen.BaseN._
  import org.github.marklister.basen.CryptoOps._

  val r = ammonite.Main().copy(predefCode =
    """import org.github.marklister.basen.BaseN._
      |import org.github.marklister.basen.CryptoOps._
      |""".stripMargin)
  r.run()
}

