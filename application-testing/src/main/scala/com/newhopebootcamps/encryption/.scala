package com.newhopebootcamps.encryption

import java.security.SecureRandom
import java.util.Base64

import javax.crypto.Cipher
import javax.crypto.spec.{IvParameterSpec, SecretKeySpec}
import org.apache.commons.codec.binary.Hex

import scala.util.{Failure, Try}

case class CipherFailedException(msg: String, cause: Throwable)
  extends Exception(msg, cause)

object SymmetricCipher {
  val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")

  private def key16(s: String): String =
    "%16s".format(s)

  def encrypt(key: Array[Byte], input: Array[Byte]): Try[Array[Byte]] =
    Try{
      val secretBytes = new SecretKeySpec(key, "AES")
      cipher.init(Cipher.ENCRYPT_MODE, secretBytes, new SecureRandom())
      cipher.getIV ++ cipher.doFinal(input)
    } recoverWith {
      case e: Throwable =>
        e.printStackTrace()
        Failure(new CipherFailedException("Encryption failed", e))
    }

  def encrypt(key: String, input: Array[Byte]) : Try[Array[Byte]]=
    encrypt(key.getBytes, input)

  def encryptTextToBase64WithKey16(key: String, text: String) : Try[String]=
    encrypt(key16(key), text.getBytes).map(Base64.getEncoder.encodeToString)

  def decrypt(key: Array[Byte], input: Array[Byte]): Try[Array[Byte]] =
    Try{
      val secretBytes = new SecretKeySpec(key, "AES")
      cipher.init(Cipher.DECRYPT_MODE, secretBytes, new IvParameterSpec(input.slice(0, 16)))
      cipher.doFinal(input.slice(16, input.length))
    } recoverWith {
      case e: Throwable =>
        Failure(new CipherFailedException("Decryption failed", e))
    }

  def decrypt(key: String, input: Array[Byte]) : Try[Array[Byte]]=
    decrypt(key.getBytes, input)

  def decryptFromBase64ToTextWithKey16(key: String, encryptedBase64String: String) : Try[String]=
    decrypt(key16(key), Base64.getDecoder.decode(encryptedBase64String)).map(_.map(_.toChar).mkString)

  def encryptHex(key: String, s: String) : Try[String] =
    encrypt(key16(key), s.getBytes("UTF-8")).map(bytes => new String(Hex.encodeHex(bytes, false)))

  def decryptHex(key: String, s: String) : Try[String] =
    decrypt(key16(key), Hex.decodeHex(s)).map(new String(_, "UTF-8"))
}
