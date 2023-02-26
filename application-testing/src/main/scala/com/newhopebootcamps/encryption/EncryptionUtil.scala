package com.newhopebootcamps.encryption

import com.newhopebootcamps.config.ApplicationConfiguration

import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.{IvParameterSpec, SecretKeySpec}

object EncryptionUtil {
//  val key = ApplicationConfiguration.key
//  val initVector = ApplicationConfiguration.initVector
//
  val key = "1ad0c826-8aded5d"
  val initVector = "20522ced-fc87921"


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