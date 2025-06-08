package com.dev.hari.spring_boot_notes_app.security

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class HashEncoder {
    private val bCryptPasswordEncoder = BCryptPasswordEncoder()

    fun encode(raw: String): String = bCryptPasswordEncoder.encode(raw)

    fun matches(raw: String, hashedPassword: String): Boolean = bCryptPasswordEncoder.matches(raw, hashedPassword)
}