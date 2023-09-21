package dev.kadirkid.rickandmorty.service

import com.apollographql.apollo3.api.Error

public class ApolloResponseException(private val errors: List<Error>): Exception() {
    override val message: String
        get() = errors.map { it.message }.joinToString("\n")
}