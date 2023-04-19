package com.example.models

import java.time.LocalDateTime
import com.example.enums.Role

case class UserModel(
  id: Long,
  username: String,
  email: String,
  password: String,
  tel: String,
  role: Role.Role,
  createdAt: LocalDateTime,
  updatedAt: LocalDateTime
)
