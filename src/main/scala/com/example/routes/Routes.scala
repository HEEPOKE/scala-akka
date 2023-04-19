package com.example.routes

import akka.actor.typed.ActorSystem
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import scala.concurrent.ExecutionContextExecutor
import com.example.controllers.UserController

object AppRouter {
  def apply()(implicit
      system: ActorSystem[_],
      ec: ExecutionContextExecutor
  ): Route = {
    val userController = new UserController()

    val mainRoutes: Route = path("") {
      get {
        complete("Hello, world!")
      }
    }

    val Router: Route = pathPrefix("api") {
      pathPrefix("users") {
        path("") {
          get {
            entity(as[UserModel]) { user =>
              userController.getAllUsers()
            }
          } ~
        }
        path("add") {
          post {
            entity(as[UserModel]) { user =>
              userController.addUser(user)
            }
          }
        } ~
          path("find") {
            get {
              parameter("id".as[Long]) { id =>
                userController.getUser(id)
                userController.getUser(id)
              }
            }
          } ~
          path("update") {
            put {
              parameter("id".as[Long]) { id =>
                entity(as[UserModel]) { user =>
                  userController.updateUser(id, user)
                }
              }
            }
          } ~
          path("delete") {
            delete {
              parameter("id".as[Long]) { id =>
                userController.deleteUser(id)
              }
            }
          }
      }
    }

    mainRoutes ~ Router
  }
}
