package com.example.simple_quiz_game.configuration

import com.example.simple_quiz_game.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserAdapter(private val user: User): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val simpleGrantedAuthority = SimpleGrantedAuthority(user.authority)
        return mutableListOf(simpleGrantedAuthority)
    }

    override fun getPassword(): String = requireNotNull(user.password)

    override fun getUsername(): String = requireNotNull(user.username)

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}