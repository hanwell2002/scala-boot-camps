package com.newhopebootcamps.util

  object IntegerCalculator {
    def add(x: Int, y: Int): Int = x + y

    /**
     * developer wrote a error function!! need to fix the bug.
     * @param x
     * @param y
     * @return
     */
    def minus(x: Int, y: Int): Int = x + y                  // *** Error function

    def multiply(x: Int, y: Int): Int = x * y

    def divide(x: Int, y: Int): Int = x / y
  }
