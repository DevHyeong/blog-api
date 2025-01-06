package org.example.springwebtest.api.controllers


data class SuccessResponseEntity<T> (
    val code: Int,
    val data: T,
)