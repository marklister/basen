/**
 * Scala hex / decimal binary calculator
 * (c) Mark Lister 2012 - 2013
 * Licence Apache 2.
 */
package org.catch22

import scala.language.implicitConversions

sealed class BaseN(val underlying: BigInt, val baseN: Int)  {
  def n(x: Int) = new BaseN(underlying, x)

  /**
   * Return this BaseN as an array of Int normalized  in the range 0 -255
   */
  def toIntArray: Array[Int] = toByteArray.map(_ + 256).map(_ % 256)

  private def toB(n: BigInt): List[Int] = {
    if (n == 0) Nil else
      (n % 2).toInt :: toB(n / 2)
  }
  
  /**
   * Return the bit representation of this BaseN
   */
  def toBits = toB(underlying).reverse

  /**
   * Return this BaseN as a Byte Array with the sign byte removed (if it exists)
   */
  def toByteArray = underlying.toByteArray
  def toUnsignedByteArray =if (underlying.toByteArray.head == 0) underlying.toByteArray.tail else underlying.toByteArray
  
  /* All the following ops are mearely wrappers for
   the equivalent ops on BigInt */
  
  def ^(that: BaseN) = BaseN(underlying.^(that.underlying), baseN)
  def xor(that: BaseN) = ^(that) //Synonym for ^
  def +(that: BaseN) = BaseN(underlying.+(that.underlying), baseN)
  def -(that: BaseN) = BaseN(underlying.-(that.underlying), baseN)
  def *(that: BaseN) = BaseN(underlying.*(that.underlying), baseN)
  def /(that: BaseN) = BaseN(underlying./(that.underlying), baseN)
  def &(that: BaseN) = BaseN(underlying.&(that.underlying), baseN)
  def |(that: BaseN) = BaseN(underlying.|(that.underlying), baseN)
  def mod(that:BaseN)= BaseN(underlying.mod(that.underlying),baseN)
  def modInverse(that:BaseN)=BaseN(underlying.modInverse(that.underlying),baseN)
  def modPow(that:BaseN,p:BaseN)=BaseN(underlying.modPow(that.underlying,p.underlying),baseN)
  
  def <<(n: Int) = BaseN(underlying.<<(n), baseN)
  def >>(n: Int) = BaseN(underlying.>>(n), baseN)
  
 implicit def toInt = underlying.toInt
 implicit def toBigInt = underlying
 
  /**
   * Return the byte wise String representation of this BaseN.  For a non byte wise 
   * orientated version use toString(16) or toRawString
   */
  override def toString = {
    val len = (scala.math.log(256) / scala.math.log(baseN) + 0.999).toInt
    def toS(i: Int) = ("0" * len + BigInt(i).toString(baseN)).takeRight(len)
    toIntArray.map(toS(_)).mkString(":") + " [base: " + baseN + "]"
  }
  
  def toString(n:Int)= underlying.toString(n)

  /**
   * Return the conventional String representation of this BaseN
   */
  def toRawString = underlying.toString(baseN)

}

object BaseN {
  def apply(b: BigInt, n: Int) = new BaseN(b, n)
  def apply(ba:Array[Byte])= new BaseN(BigInt(ba),16)

  implicit def StringToBinary(s: String): Binary = new Binary(BigInt(s, 2))
  implicit def StringToHex(s: String): Hex = new Hex(BigInt(s, 16))
  implicit def StringToDecimal(s: String): Decimal = new Decimal(BigInt(s, 10))

  implicit def BaseNToHex(x: BaseN): Hex = new Hex(x.underlying)
  implicit def BaseNToDecimal(x: BaseN): Decimal = new Decimal(x.underlying)
  implicit def BaseNToBinary(x: BaseN): Binary = new Binary(x.underlying)

  implicit def IntToBaseN(i: Int): BaseN = new BaseN(BigInt(i), 16) // to support 0xff etc
  implicit def BaseNToInt(n: BaseN): Int = n.toInt

  implicit def BigIntToBinary(b: BigInt): Binary = new Binary(b)
  implicit def BigIntToHex(b: BigInt): Hex = new Hex(b)
  implicit def BigIntToDecimal(b: BigInt): Decimal = new Decimal(b)
  implicit def ArrayByteToHex(ba: Array[Byte]): Hex = new Hex(BigInt(ba))
  implicit def BaseNToSeqByte(bn:BaseN):Seq[Byte]=bn.toByteArray.toSeq
  
  //implicit def HexToBinary (h:Hex):Binary=new Binary(h.underlying)
  //implicit def HexToDecimal (h:Hex):Decimal=new Decimal(h.underlying)
  //implicit def DecimalToHex (d:Decimal):Hex=new Hex(d.underlying)
  //implicit def DecimalToBinary (d:Decimal):Binary=new Binary(d.underlying)
  //implicit def BinaryToHex (b:Binary):Hex=new Hex(b.underlying)
  //implicit def BinaryToDecimal (b:Binary):Decimal=new Decimal(b.underlying)

}

class Hex(b: BigInt) extends BaseN(b, 16) {
  /*
   * The "h" method is unique to Hex.  Invoking the "h" method will
   * tell the compiler to attempt an implicit conversion to Hex
   */
  def h:Hex = this
}

class Decimal(b: BigInt) extends BaseN(b, 10) {
  /*
   * The "d" method is unique to Decimal.  Invoking the "d" method will
   * tell the compiler to attempt an implicit conversion to Decimal
   */
  def d:Decimal = this
}

class Binary(b: BigInt) extends BaseN(b, 2) {
  /*
   * The "b" method is unique to Decimal.  Invoking the "b" method will
   * tell the compiler to attempt an implicit conversion to Binary
   */
  def b:Binary = this
}