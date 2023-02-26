package com.newhopebootcamps.encryption

import com.newhopebootcamps.util.Calculator
import org.scalatest.funspec.AnyFunSpec

  class CalculatorAnyFunSpec extends AnyFunSpec {
    val cal = new Calculator

    describe("Calculator") {
      describe("when 10 divided by 2") {
        it("should have size 0") {
          assert(cal.divide(10, 2) == 5)
        }

        it("should throw an IndexOutOfBoundsException when a Integer divided by zero") {
           // assertThrows[java.lang.ArithmeticException] {
            assertThrows[ArithmeticException] {
               cal.divide(10, 0)
          }
        }
      }
    }
  }