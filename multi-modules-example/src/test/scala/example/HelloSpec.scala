package example

import com.newhopebootcamps.task.Log4jUsage

class HelloSpec extends munit.FunSuite {
  test("say hello") {
    assertEquals(Log4jUsage.greeting, "hello")
  }
}
