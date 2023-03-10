package utility.encryption

import utility.config.AppConfig

import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.{IvParameterSpec, SecretKeySpec}

object EncryptionUtil {
  val key = AppConfig.key
  val initVector = AppConfig.initVector
  val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")

  def decrypt(text: String): String = {
    val iv = new IvParameterSpec(initVector.getBytes("UTF-8"))
    val skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES")

    cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv)
    val original = cipher.doFinal(Base64.getDecoder.decode(text))

    new String(original)
  }

  def encrypt(text: String): String = {
    val iv = new IvParameterSpec(initVector.getBytes("UTF-8"))
    val skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES")
    cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv)

    val encrypted = cipher.doFinal(text.getBytes())
    return Base64.getEncoder().encodeToString(encrypted)
  }

}