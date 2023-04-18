package com.example

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import scala.concurrent.ExecutionContextExecutor
import com.example.config.Config
import com.example.routes.AppRouter
import com.example.config.MigrationDB

object Main extends App {
  val config = Config.instance

  implicit val system: ActorSystem[Nothing] = ActorSystem[Nothing](
    guardianBehavior = Behaviors.empty,
    name = config.SYSTEM_NAME
  )

  implicit val ec: ExecutionContextExecutor = system.executionContext

  MigrationDB.migrateDatabase()

  val router: Route = AppRouter()

  val bindingFuture = Http().newServerAt(config.HOST, config.PORT).bind(router)

  println(s"Server online at http://${config.HOST}:${config.PORT}/")

  bindingFuture.onComplete {
    case scala.util.Success(_) => println("Server started successfully.")
    case scala.util.Failure(exception) =>
      println(s"Server could not start: ${exception.getMessage}")
  }

  sys.addShutdownHook {
    bindingFuture
      .flatMap(_.unbind())(system.executionContext)
      .onComplete(_ => system.terminate())
  }
}
