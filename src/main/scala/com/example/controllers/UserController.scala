package com.example.controllers

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.example.models.UserModel
import com.example.repositories.UserRepository

import scala.concurrent.ExecutionContext.Implicits.global

class UserController {
  def addUser(user: UserModel): Route = {
    onSuccess(UserRepository.addUser(user)) { id =>
      complete((StatusCodes.Created, Map("id" -> id)))
    }
  }

  def getUser(id: Long): Route = {
    onSuccess(UserRepository.getUser(id)) {
      case Some(user) => complete(user)
      case None => complete((StatusCodes.NotFound, Map("message" -> "User not found")))
    }
  }

  def updateUser(id: Long, user: UserModel): Route = {
    onSuccess(UserRepository.updateUser(id, user.username, user.email, user.password, user.tel, user.role, user.updatedAt)) {
      case numRows if numRows > 0 => complete((StatusCodes.OK, Map("message" -> "User updated")))
      case _ => complete((StatusCodes.NotFound, Map("message" -> "User not found")))
    }
  }

  def deleteUser(id: Long): Route = {
    onSuccess(UserRepository.deleteUser(id)) {
      case numRows if numRows > 0 => complete((StatusCodes.OK, Map("message" -> "User deleted")))
      case _ => complete((StatusCodes.NotFound, Map("message" -> "User not found")))
    }
  }
}
