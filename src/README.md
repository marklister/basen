#BaseN

A simple (initially 5 classes) library for working with Hex / Decimal / Binary data in the Scala
REPL or in a program.

Crypto primitives can be applied to Hex / Decimal / Binary data.

The library was developed to complete Dan Boneth's Crypto I course on Coursera.

Provided under the Apache 2 Licence.  Please don't add exercise specific code if you use it for the same purpose.

## How to use it

### SBT

If you have sbt installed on your path create a new directory, then add the following to your build.sbt:

```
resolvers += "org.catch22" at "http://marklister.github.io/basen/"

libraryDependencies += "org.catch22" %% "basen" % "1.0.0"

initialCommands in console := """
  import org.catch22.BaseN._
  import org.catch22.CryptoOps._
  """

scalaVersion:="2.10.3"
```

### GIT

Simply cloning the project will give you a working environment.  Run ```sbt console```

