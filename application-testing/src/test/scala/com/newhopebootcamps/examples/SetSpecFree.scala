package com.newhopebootcamps.examples

import org.scalatest.freespec.AnyFreeSpec

class SetSpecFree extends AnyFreeSpec {

  "A Set" - {   //  -2
    "when empty" - {   // -1
      "should have size 0" in {
        assert(Set.empty.size == 0)
      }

      "should produce NoSuchElementException when head is invoked" in {
        assertThrows[NoSuchElementException] {
          Set.empty.head
        }
      }


    } // -1

  }  // -2


}