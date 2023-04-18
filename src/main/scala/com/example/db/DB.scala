package com.example.db

import slick.jdbc.PostgresProfile.api._
import com.example.config.Config

object DatabaseConnection {
  val config = Config.instance
  val db = Database.forConfig(config.DB_URL, config.DB_USER, config.DB_PASSWORD, driver = config.DB_DRIVER)
}
