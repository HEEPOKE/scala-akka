package com.example.config

import com.typesafe.config.ConfigFactory

case class Config(
    HOST: String,
    PORT: Int,
    DB_DRIVER: String,
    DB_URL: String,
    DB_USER: String,
    DB_PASSWORD: String,
    SYSTEM_NAME: String
)

object Config {
  private val config = ConfigFactory.load()
  private val appConfig = config.getConfig("app")

  val instance = Config(
    HOST = appConfig.getString("api.host"),
    PORT = appConfig.getInt("api.port"),
    DB_DRIVER = appConfig.getString("db.driver"),
    DB_URL = appConfig.getString("db.url"),
    DB_USER = appConfig.getString("db.user"),
    DB_PASSWORD = appConfig.getString("db.password"),
    SYSTEM_NAME = appConfig.getString("system.name")
  )
}
