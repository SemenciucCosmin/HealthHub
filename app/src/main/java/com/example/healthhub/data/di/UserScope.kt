package com.example.healthhub.data.di

import com.example.healthhub.data.account.model.User
import org.koin.core.Koin
import org.koin.core.qualifier.named

object UserScope {
    private const val SCOPE_NAME = "USER"

    fun create(koin: Koin, user: User) {
        val currentScope = koin.getScopeOrNull(SCOPE_NAME)
        if (currentScope != null) {
            currentScope.declare(user)
        } else {
            val scope = koin.createScope(SCOPE_NAME, named(user.id.toString()))
            scope.declare(user)
        }
    }

    fun close(koin: Koin) {
        koin.getScopeOrNull(SCOPE_NAME)?.close()
    }

    fun getUser(koin: Koin): User? {
        val user: User? = koin.getScopeOrNull(SCOPE_NAME)?.get()
        return user
    }
}