package com.newhopebootcamps.examples

/*
RefSpec (JVM only)
The RefSpec style allows you to define tests as methods, which saves one function literal per test compared
to style classes that represent tests as functions. Fewer function literals translates into faster compile times
and fewer generated class files, which can help minimize build times. As a result, using Spec can be a good choice
 in large projects where build times are a concern as well as when generating large numbers of tests programatically via static code generators.
*/

import org.scalatest.refspec.RefSpec

class SetRefSpec extends RefSpec {

  object `A Set` {
    object `when empty` {
      def `should have size 0` {
        assert(Set.empty.size == 0)
      }

      def `should produce NoSuchElementException when head is invoked` {
        assertThrows[NoSuchElementException] {
          Set.empty.head
        }
      }
    }
  }
}