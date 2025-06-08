package com.dev.hari.spring_boot_notes_app.controller

import com.dev.hari.spring_boot_notes_app.database.model.Note
import com.dev.hari.spring_boot_notes_app.database.repository.NoteRepository
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
@RequestMapping("/notes")
class NoteController (
    private val repository: NoteRepository
){

    data class NoteRequest(
        val id: String?,
        val title: String,
        val content: String,
        val color: Long
    )

    data class NoteResponse(
        val id: String,
        val title: String,
        val content: String,
        val color: Long,
        val createdAt: Instant
    )

    @PostMapping("/note")
    fun save(
        @RequestBody body: NoteRequest
    ) : NoteResponse {
        val note = repository.save(
            Note(
                id = body.id?.let { ObjectId(it) } ?: ObjectId.get(),
                title = body.title,
                content = body.content,
                color = body.color,
                createdAt = Instant.now(),
                ownerId = ObjectId(),
            )
        )
        return note.toResponse()
    }

    @GetMapping
    fun findByOwnerId(
        @RequestParam("ownerId") ownerId: String,
    ) : List<NoteResponse> {
        return repository.findByOwnerId(
            ownerId = ObjectId(ownerId)
        ).map { it.toResponse() }
    }
}

private fun Note.toResponse(): NoteController.NoteResponse {
    return NoteController.NoteResponse(
        id = this.id.toString(),
        title = this.title,
        content = this.content,
        color = this.color,
        createdAt = this.createdAt
    )
}