package com.newhopebootcamps.stream

/**
 * @author ${user.name}
 * test with program parameters:  ABC 123 @777
 */
object CommandLineParams {

  def foo(x : Array[String]) = x.foldLeft("")((a,b) => a + b)

  def main(args : Array[String]) {
      for(n <- 0 to args.length -1 )
        println(s"-->$n: " + args(n))

    println( "Hello World!" )
    println("concat arguments = " + foo(args))
  }

}