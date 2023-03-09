package com.newhopebootcamps.tools

object ProgramArguments extends App{

    if (args.size == 0)
      println("Hello, you")
    else
      println("Hello, " + args(0))

}
