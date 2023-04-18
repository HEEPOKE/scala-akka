package com.example.routes

import akka.actor.typed.ActorSystem
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import scala.concurrent.ExecutionContextExecutor

object AppRouter {
  def apply()(implicit system: ActorSystem[_], ec: ExecutionContextExecutor): Route = {
    path("") {
      get {
        complete("HUI")
      }
    }
  }
}


