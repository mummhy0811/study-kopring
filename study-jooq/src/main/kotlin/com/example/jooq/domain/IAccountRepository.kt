package com.example.jooq.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IAccountRepository : JpaRepository<Account, Long> {
    fun findByUsername(username: String): Account?
}
