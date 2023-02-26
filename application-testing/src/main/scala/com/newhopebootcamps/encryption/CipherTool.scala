package com.newhopebootcamps.encryption


object CipherTool {
  val passwd = "12345678"
/*  def main(args: Array[String]): Unit = {
    test02
  }*/

  def test02(): Unit = {
    val result = EncryptionUtil.encrypt(passwd)
    println("Encrypted = " + result)

    val pwd = EncryptionUtil.decrypt(result)
    println("passwd : $pwd")
  }

}
