package com.example.models

import java.time.LocalDateTime
import com.example.enums.Role._

case class User(
  id: Long,
  username: String,
  email: String,
  password: String,
  tel: String,
  role: Role,
  createdAt: LocalDateTime,
  updatedAt: LocalDateTime
)
