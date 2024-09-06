package com.example.jooq.controller

import com.example.jooq.domain.Account
import com.example.jooq.service.AccountService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/accounts")
class AccountController(
    private val accountService: AccountService
) {

    @GetMapping("/{id}")
    fun getAccountById(@PathVariable id: Long): ResponseEntity<Account> {
        val account = accountService.getAccountById(id)
        return if (account != null) {
            ResponseEntity.ok(account)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/username/{username}")
    fun getAccountByUsername(@PathVariable username: String): ResponseEntity<Account> {
        val account = accountService.getAccountByUsername(username)
        return if (account != null) {
            ResponseEntity.ok(account)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/email/{email}")
    fun getAccountByEmail(@PathVariable email: String): ResponseEntity<Account> {
        val account = accountService.getAccountByEmail(email)
        return if (account != null) {
            ResponseEntity.ok(account)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/active")
    fun getActiveAccounts(): ResponseEntity<List<Account>> {
        val accounts = accountService.getActiveAccounts()
        return ResponseEntity.ok(accounts)
    }

    @PostMapping
    fun createAccount(@RequestBody account: Account): ResponseEntity<Account> {
        val createdAccount = accountService.createAccount(account)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount)
    }

    @PutMapping
    fun updateAccount(@RequestBody account: Account): ResponseEntity<Account> {
        val updatedAccount = accountService.updateAccount(account)
        return ResponseEntity.ok(updatedAccount)
    }

    @DeleteMapping("/{id}")
    fun deleteAccount(@PathVariable id: Long): ResponseEntity<Void> {
        accountService.deleteAccount(id)
        return ResponseEntity.noContent().build()
    }
}
