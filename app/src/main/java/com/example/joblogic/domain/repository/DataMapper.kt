package com.example.joblogic.domain.repository

interface DataMapper<E, M> {
    fun from(from: E): M
    fun to(from: M): E
}