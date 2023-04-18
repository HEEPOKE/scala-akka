package com.example.config

import java.sql.{Connection, DriverManager}
import Config._

object Database {
    def getConnection: Connection = {

    val DB_URL = instance.DB_URL
    val DB_USER = instance.DB_USER
    val DB_PASSWORD = instance.DB_PASSWORD
    DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)
  }
}
