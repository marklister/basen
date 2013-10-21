/**
 * Scala hex / decimal binary calculator
 * (c) Mark Lister 2012 - 2013
 * Licence Apache 2.
 *
 * CryptoOps is a sort of "RichByteArray" for Crypto primitives
 */

 package org.catch22
 
 import java.security.MessageDigest
 import javax.crypto.Cipher
 import javax.crypto.spec.SecretKeySpec
 
 class CryptoOps(val underlying:Array[Byte]){
    /**
     * Return the sha256 of this CryptOps
     */
    def sha256 = digest("SHA-256")
  
    /**
     * Return the SHA-1 of this BaseN
     */
    def sha1 = digest("SHA-1")
  
    /**
     * Return the MD5 of this BaseN
     */
    def md5 = digest("MD5")

    /**
     * Return a Digest of this CryptoOps that is supported by the underlying
     * security provider of the JVM (SHA1, SHA256 and MD5 are standard)
     * Private but you can change that if you need direct access. 
     */
    private def digest(d: String):Array[Byte] = {
      val md = MessageDigest.getInstance(d)
      md.update(underlying)
      md.digest
    }
  
    /**
     * Pad this CryptoOps to a multiple of 16 bytes by:  adding n bytes of value n
     * where n is the number of bytes required to make this a multiple of 16.
     * Where the block is a multiple of 16, a full block is added.
     */
    def pkcs5Pad: CryptoOps = pkcs5Pad(16)
  
    /**
     * Pad this block in the same way as pkcs5Pad but with a selectable block size.
     */
    def pkcs5Pad(n: Int): CryptoOps = {
      val p = n - underlying.length % n
      underlying ++ (1 to p).map(i => p.toByte)
    }
  
    /**
     * Return this CryptoOps encrypted using AES in ECB mode
     * Returns an Array[Byte]
     */
    def aesEncrypt(k: Array[Byte]):Array[Byte] = {
      val c = Cipher.getInstance("AES/ECB/NoPadding")
      val key = new SecretKeySpec(k, "AES")
      c.init(Cipher.ENCRYPT_MODE, key)
      c.doFinal(underlying)
    }
  
    /**
     * Return this CryptoOps decrypted using AES in ECB Mode
     * Returns an Array[Byte]
     */
    def aesDecrypt(k: Array[Byte]) = {
      val c = Cipher.getInstance("AES/ECB/NoPadding")
      val key = new SecretKeySpec(k, "AES")
      c.init(Cipher.DECRYPT_MODE, key)
      c.doFinal(underlying)
    }
  }

 import scala.language.implicitConversions

 object CryptoOps{
    def apply(b:Array[Byte])= new CryptoOps(b)
    implicit def ArrayByteToCryptoOps(ba:Array[Byte]):CryptoOps= CryptoOps(ba)
    implicit def SeqByteToCryptoOps(sb:Seq[Byte]):CryptoOps= CryptoOps(sb.toArray)
    implicit def CryptoOpsToByteArray(co:CryptoOps):Array[Byte]= co.underlying
    implicit def CryptoOpsToSeqByte(co:CryptoOps):Seq[Byte]= co.underlying.toSeq
    implicit def BaseNToCryptoOps(bn:BaseN)=CryptoOps(bn.toByteArray)
    implicit def CryptoOpsToBaseN(co:CryptoOps)=BaseN(co.underlying)
    implicit def CryptoOpsToHex(co:CryptoOps)=new Hex(BigInt(co.underlying))
  }
