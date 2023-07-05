package com.project.playground.enums

enum class AuthenticationStatements(private val statement: String) {
    USER_NOT_FOUND("User not found"),
    VALID_USER("valid user"),
    INVALID_USER("invalid user")


    ;

    override fun toString(): String {
        return statement
    }
}