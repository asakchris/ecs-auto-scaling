package org.example.test

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class TestSimulation extends Simulation {
  val steps = Seq(5, 10, 15, 25, 40)

  val injectionProfile = steps.flatMap(
    load => Seq(
      constantUsersPerSec(load).during(10 seconds),
      nothingFor(5 seconds)
    )
  )

  val httpProtocol = http.baseUrl("http://localhost:8080/api/v1/autoscaling")

  val scn = scenario("Get available memory")
    .exec(http("request_1")
      .get("/memory")
      .check(status.is(200)))
    .pause(1 second)
    .exec(http("request_2")
      .get("/memory")
      .check(status.is(200)))

  setUp(
    scn
      /*.inject(
        atOnceUsers(10),
        rampUsers(2) during (10 seconds)
      )*/
      .inject(injectionProfile)
      .protocols(httpProtocol))
}