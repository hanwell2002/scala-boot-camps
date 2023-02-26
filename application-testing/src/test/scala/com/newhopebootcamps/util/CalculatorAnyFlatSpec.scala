package com.newhopebootcamps.util

import org.scalatest.flatspec.AnyFlatSpec

/**
FlatSpec is a good first step for teams wishing to move from xUnit to BDD. Its structure is flat like xUnit,
so simple and familiar, but the test names must be written in a specification style: "X should Y," "A must B," etc.
 */
class CalculatorAnyFlatSpec extends AnyFlatSpec {

  override def withFixture(test: NoArgTest) = { // Define a shared fixture
    // Shared setup (run at beginning of each test)
    try test()
    finally {
      // Shared cleanup (run at end of each test)
    }
  }

  // Define the first test for a subject, in this case: "2 x 5"
  " multiply(2, 5)" should " return 10" in { // Name the subject, write 'should', then the rest of the test name
    assert( IntegerCalculator.multiply(2, 5) == 10) // (Can use 'must' or 'can' instead of 'should')
  }

  " 3 x 3" must " return 9" in { // Name the subject, write 'should', then the rest of the test name
    assert(IntegerCalculator.multiply(3, 3) == 3 * 3) // (Can use 'must' or 'can' instead of 'should')
  }

  " 100 / 2  " should " return 50" in { // Name the subject, write 'should', then the rest of the test name
    assert( IntegerCalculator.divide(100, 2) == 50) // (Can use 'must' or 'can' instead of 'should')
  }

  it should "produce ArithmeticException when a Integer divide by Zero" in { // Define another test for the same
    intercept[ArithmeticException] { // subject with 'it'
      IntegerCalculator.divide(100, 0)
    }
  }


  " minus(5, 5) " should " get a 0" in { // Name the subject, write 'should', then the rest of the test name
    assert(IntegerCalculator.minus(5, 5) == 0) // (Can use 'must' or 'can' instead of 'should')
  }

  " 78 + 56" should " get a 134" in { // Name the subject, write 'should', then the rest of the test name
    assert(IntegerCalculator.add(78, 56) == 78 + 56) // (Can use 'must' or 'can' instead of 'should')
  }


}

