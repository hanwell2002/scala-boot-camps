package utils

import org.scalatest.funsuite.AnyFunSuite

object ScalaTestingStyles

class CalculatorSuite extends AnyFunSuite{
  val calculator = new Calculator
  test("ultiplication by 0 should always be 0")
  {
    assert(calculator.multiply(653278 , 0) == 0)
    assert(calculator.multiply(-653278 , 0) == 0)
    assert(calculator.multiply(0 , 0) == 0)
  }

  test("dividing by o should throw some math error"){
    assertThrows[ArithmeticException](calculator.divide(653278, 0))
  }

}


