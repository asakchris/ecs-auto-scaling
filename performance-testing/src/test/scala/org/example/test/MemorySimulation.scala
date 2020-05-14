package org.example.test

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class MemorySimulation extends Simulation {
  val httpProtocol = http.baseUrl("http://localhost:8080/api/v1/autoscaling")

  val userGroupOne = scenario("UserGroupOne").exec(GetMemory.memory, GetMemory.memory)
  val userGroupTwo = scenario("UserGroupTwo").exec(GetMemory.memory, GetMemory.memory, GetMemory.memory)

  setUp(
    userGroupOne.inject(rampUsers(2000) during (300 seconds)),
    userGroupTwo.inject(rampUsers(2000) during (300 seconds)),
  ).protocols(httpProtocol)
}

object GetMemory {
  val memory = exec(http("GetMemory")
    .get("/memory")
    .check(status.is(200)))
    .pause(2 seconds)
}