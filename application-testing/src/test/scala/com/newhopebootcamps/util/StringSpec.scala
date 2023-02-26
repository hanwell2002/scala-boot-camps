package com.newhopebootcamps.util

import org.scalatest.flatspec.FixtureAnyFlatSpec

/**
 * The purpose of a test fixture is to ensure that there is a well known and fixed environment in which tests are run so that results are repeatable. Some people call this the test context.
 *
 * Examples of fixtures:
 *
 * Loading a database with a specific, known set of data
 * Erasing a hard disk and installing a known clean operating system installation
 * Copying a specific known set of files
 * Preparation of input data and set-up/creation of fake or mock objects
 *
 * fixture: 固定在建筑物或车辆中的一件设备或家具。
 */

// Can also pass fixtures into tests with FixtureAnyFlatSpec
class StringSpec extends FixtureAnyFlatSpec {
  type FixtureParam = String // Define the type of the passed fixture object

  override def withFixture(test: OneArgTest) = {
    // Shared setup (run before each test), including...
    val fixture = "a fixture object" // ...creating a fixture object
    try test(fixture) // Pass the fixture into the test
    finally {
      // Shared cleanup (run at end of each test)
    }
  }

  "The passed fixture" can "be used in the test" in { s => // Fixture passed in as s
    assert(s == "a fixture object")
  }
}
