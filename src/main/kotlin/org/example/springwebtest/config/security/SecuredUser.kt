package org.example.springwebtest.config.security

import lombok.Getter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class SecuredUser(
        val email: String,
        val password: String
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        TODO("Not yet implemented")
    }

    override fun getPassword() = password
    override fun getUsername() = email
    override fun isAccountNonExpired() = false
    override fun isAccountNonLocked() = false
    override fun isCredentialsNonExpired() = false
    override fun isEnabled() = true
}
