package com.dev.hari.spring_boot_notes_app.database.repository

import com.dev.hari.spring_boot_notes_app.database.model.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, ObjectId> {
    fun findByEmail(email: String): User?
}