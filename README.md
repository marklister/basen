# BaseN

Scala for Cryptography I on Coursera //google

A simple (initially 5 classes) library for working with Hex / Decimal / Binary data in the Scala
REPL or in a program.

Crypto primitives can be applied to Hex / Decimal / Binary data.

The library was developed to complete Dan Boneh's Cryptography I course on Coursera.

Provided under the Apache 2 Licence.  Please don't add exercise specific code if you use it for the same purpose.

## How to use it

### GIT

Simply cloning the project will give you a working environment.  Run ```sbt console``` from the cloned directory.

### Scaladoc

[Browse Scaladoc on line](http://marklister.github.io/basen/target/scala-2.10/api/#org.catch22.package)

### High level concept

Strings are implicitly converted into BaseN objects.  You invoke the `h` method to convert from Hex `d` to convert from Decimal and `b` from binary

Byte arrays are implicitly converted into Objects of type CryptoOps.

You can add subtract multiply divide, xor etc any of your BaseN objects.  You can convert them to hex decimal binary or BigInts.

You can encrypt, hash, decrypt, pkspad your converted byte arrays.

The REPL tab key might be useful.  

If you are running from the REPL imports are handled automatically.  If you are writing a program then include:

``` scala
import org.github.marklister.basen.BaseN._
import org.github.marklister.basen.CryptoOps._
```

### REPL Session

```
Welcome to Scala 2.12.2 (OpenJDK 64-Bit Server VM, Java 1.8.0_121).
Type in expressions for evaluation. Or try :help.

scala> //working with Hex / Decimal / Binary
import org.github.marklister.basen.BaseN._
import org.github.marklister.basen.CryptoOps._

scala> "255".d 
res0: org.github.marklister.basen.Decimal = 000:255 [base: 10]

scala> "ff".h
res1: org.github.marklister.basen.Hex = 00:ff [base: 16]

scala> "10".d * 2
res2: org.github.marklister.basen.BaseN = 020 [base: 10]

scala> "10101010".b xor "01010101".b
res3: org.github.marklister.basen.BaseN = 00000000:11111111 [base: 2]

scala> "2".d + "f".h
res4: org.github.marklister.basen.BaseN = 017 [base: 10]

scala> 

scala> //Crypto stuff

scala> "Hello".getBytes.h
res5: org.github.marklister.basen.Hex = 48:65:6c:6c:6f [base: 16]

scala> "Hello".getBytes.sha256.h
res6: org.github.marklister.basen.Hex = 18:5f:8d:b3:22:71:fe:25:f5:61:a6:fc:93:8b:2e:26:43:06:ec:30:4e:da:51:80:07:d1:76:48:26:38:19:69 [base: 16]

scala> val encrypted="Hello World".getBytes.pkcs5Pad.aesEncrypt("my key".getByte 
s.pkcs5Pad)
encrypted: Array[Byte] = Array(-80, 101, 29, 31, 83, -62, 3, 95, -61, -73, -17, -45, -38, -83, 78, 22)

scala> encrypted.aesDecrypt("my key".getBytes.pkcs5Pad).h.toByteArray.map(_.toCh 
ar)
res7: Array[Char] = Array(H, e, l, l, o,  , W, o, r, l, d, ?, ?, ?, ?, ?)

```

