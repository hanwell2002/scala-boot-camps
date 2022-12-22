package application

// function Currying
/**
 def add(x:Int,y:Int)=x+y
  // now transform function definition to :
 def add(x:Int)(y:Int) = x + y
  //even:
 def add2(x: Int) = (y: Int) => x + y;

 */

object CurryingFunctionDemo {
  def main(args: Array[String]) {
    val str1: String = "Hello, "
    val str2: String = "World!"
    println("str1 + str2 = " + strcat(str1)(str2))

    val z = add(1)(2)
    println(z)

    // Partially Applied function.
    val sum = add(3) _;
    println(sum(7));

    val z2 = add2(2)(3)
    println(z2)

    val sum2 = add2(78);
    println(sum2(56))


  }

  def strcat(s1: String)(s2: String) = {
    s1 + s2
  }
  def add(x:Int)(y: Int):Int = {
    x + y
  }
  def add2(x: Int) = (y: Int) => x + y;
}