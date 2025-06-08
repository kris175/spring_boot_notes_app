package com.dev.hari.spring_boot_notes_app.database.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document("users")
data class User(
    @Id
    val id: ObjectId = ObjectId.get(),
    val email: String,
    val hashedPassword: String,
    val createdAt: Instant = Instant.now()
)
