#BaseN

A simple (initially 5 classes) library for working with Hex / Decimal / Binary data in the Scala
REPL or in a program.

Crypto primitives can be applied to Hex / Decimal / Binary data.

The library was developed to complete Dan Boneth's Crypto I course on Coursera.

Provided under the Apache 2 Licence.  Please don't add exercise specific code if you use it for the same purpose.

## How to use it

### SBT

In progress...

### GIT

Simply cloning the project will give you a working environment.  Run ```sbt console``` from the cloned directory.

### REPL Session

```
import org.catch22.BaseN._
import org.catch22.CryptoOps._
Welcome to Scala version 2.10.3 (OpenJDK Server VM, Java 1.7.0_25).
Type in expressions to have them evaluated.
Type :help for more information.

scala> //working with Hex / Decimal / Binary

scala> "255".d 
res0: org.catch22.Decimal = 000:255 [base: 10]

scala> "ff".h
res1: org.catch22.Hex = 00:ff [base: 16]

scala> "10".d * 2
res2: org.catch22.BaseN = 020 [base: 10]

scala> "10101010".b xor "01010101".b
res3: org.catch22.BaseN = 00000000:11111111 [base: 2]

scala> "2".d + "f".h
res4: org.catch22.BaseN = 017 [base: 10]

scala> 

scala> //Crypto stuff

scala> "Hello".getBytes.h
res5: org.catch22.Hex = 48:65:6c:6c:6f [base: 16]

scala> "Hello".getBytes.sha256.h
res6: org.catch22.Hex = 18:5f:8d:b3:22:71:fe:25:f5:61:a6:fc:93:8b:2e:26:43:06:ec:30:4e:da:51:80:07:d1:76:48:26:38:19:69 [base: 16]

scala> val encrypted="Hello World".getBytes.pkcs5Pad.aesEncrypt("my key".getBytes.pkcs5Pad)
encrypted: Array[Byte] = Array(-80, 101, 29, 31, 83, -62, 3, 95, -61, -73, -17, -45, -38, -83, 78, 22)

scala> encrypted.aesDecrypt("my key".getBytes.pkcs5Pad).h.toByteArray.map(_.toChar)
res7: Array[Char] = Array(H, e, l, l, o,  , W, o, r, l, d, ?, ?, ?, ?, ?)

scala> 
```