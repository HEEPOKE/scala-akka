package com.example.db

import java.time.LocalDateTime
import slick.jdbc.PostgresProfile.api._

import com.example.models.UserModel
import com.example.enums.Role._

class Users(tag: Tag) extends Table[UserModel](tag, "users") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def username = column[String]("username")
  def email = column[String]("email")
  def password = column[String]("password")
  def tel = column[String]("tel")
  def role = column[Role]("role")
  def createdAt = column[LocalDateTime]("created_at")
  def updatedAt = column[LocalDateTime]("updated_at")
  
  def * = (id, username, email, password, tel, role, createdAt, updatedAt) <> (User.tupled, User.unapply)
}

object UsersTable {
  val all = TableQuery[Users]
}
