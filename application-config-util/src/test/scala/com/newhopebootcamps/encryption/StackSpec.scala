package com.newhopebootcamps.encryption

//import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.flatspec._

import scala.collection.mutable.Stack

class StackSpec extends AnyFlatSpec {

  behavior of "A Stack"

  it should "pop values in last-in-first-out order" in {
    val stack = new Stack[Int]
    stack.push(1)
    stack.push(2)
    assert(stack.pop() === 2)
    assert(stack.pop() === 1)
  }

  it should "throw NoSuchElementException if an empty stack is popped" in {
    val emptyStack = new Stack[String]
    intercept[NoSuchElementException] {
      emptyStack.pop()
    }
  }
}