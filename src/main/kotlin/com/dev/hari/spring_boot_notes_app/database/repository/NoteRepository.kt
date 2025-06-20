package com.dev.hari.spring_boot_notes_app.database.repository

import com.dev.hari.spring_boot_notes_app.database.model.Note
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface NoteRepository : MongoRepository<Note, ObjectId> {
    fun findByOwnerId(ownerId: ObjectId): List<Note>
}