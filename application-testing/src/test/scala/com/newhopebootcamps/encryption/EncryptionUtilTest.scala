package com.newhopebootcamps.encryption

import org.scalatest.funsuite.AnyFunSuite

class EncryptionUtilTest extends AnyFunSuite {

  val passwd2 = "1234567"

  test("EncryptionUtil.encrypt") {

    assert(EncryptionUtil.encrypt("Admin@777") ==="ykV55xp7kbTXwqAepYVMYA==" )

  }

  test("EncryptionUtil.decrypt") {

    assert(EncryptionUtil.decrypt("ykV55xp7kbTXwqAepYVMYA==") === "Admin@777")

  }

  test("EncryptionUtil.decrypt() test #2") {
    //println(EncryptionUtil.encrypt(passwd2))
    assert(EncryptionUtil.decrypt("9x1+C4Ui84uVjIcQke9wSg==") === "1234567")
  }


}