package com.kainv.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kainv.model.entities.personal_cabinet_schema.User.Birthday;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtUser implements UserDetails {

    private final Long id;
    private final String surname;
    private final String firstName;
    private final String patronymic;
    private final Birthday birthDate;
    private final String email;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(Long id, String surname, String firstName, String patronymic, Birthday birthDate, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.surname = surname;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public Birthday getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return null;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return false;
    }
}
