package com.example.jooq.domain

import com.example.jooq.tables.tables.Account.Companion.ACCOUNT
import org.jooq.DSLContext
import org.springframework.stereotype.Repository


@Repository
class AccountRepository(private val dsl: DSLContext) {

    fun findByEmail(email: String): Account? {
        return dsl.selectFrom(ACCOUNT)
            .where(ACCOUNT.EMAIL.eq(email))
            .fetchOneInto(Account::class.java)
    }

    fun findActiveAccounts(): List<Account> {
        return dsl.selectFrom(ACCOUNT)
            .where(ACCOUNT.ROLES.eq("USER"))
            .fetchInto(Account::class.java)
    }
}
