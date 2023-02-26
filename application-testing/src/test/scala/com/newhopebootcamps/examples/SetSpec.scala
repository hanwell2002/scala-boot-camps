package com.newhopebootcamps.examples

import org.scalatest.flatspec.AnyFlatSpec

class SetSpec extends AnyFlatSpec {
    val setEx1 = Set(1,2)

  "An empty Set" should "have size 0" in {
    assert(Set.empty.size == 0)
  }

  it should "produce NoSuchElementException when head is invoked" in {
    assertThrows[NoSuchElementException] {
      Set.empty.head
    }
  }

  "setEx1" should "have size 3" in {
    assert(setEx1.size == 2)
  }


}