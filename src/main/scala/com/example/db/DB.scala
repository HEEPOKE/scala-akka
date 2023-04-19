package com.example.db

import slick.jdbc.PostgresProfile.api._
import com.typesafe.config.ConfigFactory
import com.example.config.Config

object DatabaseConnection {
  val config = Config.instance
  val dbConfig = ConfigFactory.parseString(s"""
    slick.dbs.default.driver="${config.DB_DRIVER}"
    slick.dbs.default.db.url="${config.DB_URL}"
    slick.dbs.default.db.user="${config.DB_USER}"
    slick.dbs.default.db.password="${config.DB_PASSWORD}"
  """)
  val db = Database.forConfig("default", dbConfig)
}
