package com.example.jooq.service

import com.example.jooq.domain.Account
import com.example.jooq.domain.IAccountRepository
import com.example.jooq.domain.AccountRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountService(
    private val accountJpaRepository: IAccountRepository,
    private val accountJooqRepository: AccountRepository
) {

    @Transactional(readOnly = true)
    fun getAccountById(id: Long): Account? {
        return accountJpaRepository.findById(id).orElse(null)
    }

    @Transactional(readOnly = true)
    fun getAccountByUsername(username: String): Account? {
        return accountJpaRepository.findByUsername(username)
    }

    @Transactional(readOnly = true)
    fun getAccountByEmail(email: String): Account? {
        return accountJooqRepository.findByEmail(email)
    }

    @Transactional
    fun createAccount(account: Account): Account {
        return accountJpaRepository.save(account)
    }

    @Transactional
    fun updateAccount(account: Account): Account {
        return accountJpaRepository.save(account)
    }

    @Transactional
    fun deleteAccount(id: Long) {
        accountJpaRepository.deleteById(id)
    }

    @Transactional(readOnly = true)
    fun getActiveAccounts(): List<Account> {
        return accountJooqRepository.findActiveAccounts()
    }
}
