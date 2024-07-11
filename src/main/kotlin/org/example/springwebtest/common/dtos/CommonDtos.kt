package org.example.springwebtest.common.dtos

data class CommonQueryResponse<T> (
        val code: String,
        val message: String,
        val data: T
)
