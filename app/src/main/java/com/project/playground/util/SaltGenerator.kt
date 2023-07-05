package com.project.playground.util

import java.security.SecureRandom

class SaltGenerator{
    fun generateRandomSalt(): String {
        val random = SecureRandom()
        val salt = ByteArray(16)
        random.nextBytes(salt)

        return salt.toString()
    }
}