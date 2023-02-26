package com.newhopebootcamps.dao

//import java.util.concurrent.ConcurrentHashMap
import org.scalatest._
import java.util.UUID.randomUUID

import org.scalatest.flatspec
import DbServer._


trait DbFixture extends  flatspec.FixtureAnyFlatSpec { this: FixtureSuite =>

  type FixtureParam = Db

  // Allow clients to populate the database after
  // it is created
  def populateDb(db: Db) {}

  def withFixture(test: OneArgTest) = {
    val dbName = randomUUID.toString
    val db = createDb(dbName) // create the fixture
    try {
      populateDb(db) // setup the fixture
      withFixture(test.toNoArgTest(db)) // "loan" the fixture to the test
    }
    finally removeDb(dbName) // clean up the fixture
  }
}

class TestWithSharedDbFixtureSpec extends flatspec.FixtureAnyFlatSpec with DbFixture {
  override def populateDb(db: Db) { // setup the fixture
    db.append("ScalaTest is ")
  }

  "Testing" should "be easy" in { db =>
    db.append("easy!")
    assert(db.toString === "ScalaTest is easy!")
  }

  it should "be fun" in { db =>
    db.append("fun!")
    assert(db.toString === "ScalaTest is fun!")
  }

  // This test doesn't need a Db
  "Test code" should "be clear" in { () =>
    val buf = new StringBuffer
    buf.append("ScalaTest code is ")
    buf.append("clear!")
    assert(buf.toString === "ScalaTest code is clear!")
  }
}