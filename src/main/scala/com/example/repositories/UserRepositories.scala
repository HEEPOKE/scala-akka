package com.example.repositories

import java.time.LocalDateTime
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import slick.jdbc.PostgresProfile.api._
import com.example.db.DatabaseConnection
import com.example.db.UsersTable
import com.example.models.UserModel
import com.example.enums.Role._

object UserRepository {
  val db = DatabaseConnection.db

  def getAllUsers: Future[Seq[UserModel]] = {
    val query = UsersTable.all.result
    db.run(query)
  }

  def addUser(user: UserModel): Future[Long] = {
    val query = UsersTable.all returning UsersTable.all.map(_.id) += user
    db.run(query)
  }

  def getUser(id: Long): Future[Option[UserModel]] = {
    val query = UsersTable.all.filter(_.id === id).result.headOption
    db.run(query)
  }

  def updateUser(
      id: Long,
      username: String,
      email: String,
      tel: String,
      role: Role,
      updatedAt: LocalDateTime
  ): Future[Int] = {
     val query = UsersTable.all
      .filter(_.id === id)
      .map(u => (u.username, u.email, u.tel, u.role, u.updatedAt).mapTo[UserModel])
      .update((username, email, tel, role, updatedAt))
    db.run(query)
  }

  def deleteUser(id: Long): Future[Int] = {
    val query = UsersTable.all.filter(_.id === id).delete
    db.run(query)
  }
}
