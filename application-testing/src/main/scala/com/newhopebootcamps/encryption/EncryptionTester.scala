package com.newhopebootcamps.encryption

import com.newhopebootcamps.config.ApplicationConfiguration

import java.util.UUID

object EncryptionTester extends App {

  val passwd = "Admin@777"
  val passwd2 = "1234567"

  test01

  test02


  def test01(): Unit = {

    val key = UUID.randomUUID().toString

    println("key = " + key)
    val encrypted = EncryptionUtil.encrypt(passwd)
    println("Encrypt = " + encrypted)

    //     decrypt(key: String, encryptedValue: String)
    val decrypted = EncryptionUtil.decrypt(encrypted)
    println("decrypted = " + decrypted)

  }

  def test02(): Unit = {
    ApplicationConfiguration.initialize()
    println("-----------------------------------")

    val result = EncryptionUtil.encrypt(passwd)
    println("Encrypted = " + result)

    val pwd1 = EncryptionUtil.decrypt(result)
    println(pwd1)

  }



}
