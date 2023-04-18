package com.example.config

import org.flywaydb.core.Flyway
import Config._

object MigrationDB {
  def migrateDatabase(): Unit = {
    val flyway = Flyway.configure
      .dataSource(
        instance.DB_URL,
        instance.DB_USER,
        instance.DB_PASSWORD
      )
      .load()

    flyway.migrate()
  }
}
