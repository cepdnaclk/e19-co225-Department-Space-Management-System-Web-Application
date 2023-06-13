package com.example.SharedSpaces.models.token;

import com.example.SharedSpaces.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name="token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String token;
    public TokenType tokenType = TokenType.BEARER;
    public boolean revoked;
    public boolean expired;
    public User user;

    public Token(){

    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", tokenType=" + tokenType +
                ", revoked=" + revoked +
                ", expired=" + expired +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token1 = (Token) o;
        return revoked == token1.revoked && expired == token1.expired && Objects.equals(id, token1.id) && Objects.equals(token, token1.token) && tokenType == token1.tokenType && Objects.equals(user, token1.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token, tokenType, revoked, expired, user);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public boolean isRevoked() {
        return revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Token(User user, String token, TokenType tokenType, boolean revoked, boolean expired){
        this.user = user;
        this.token = token;
        this.tokenType = tokenType;
        this.revoked = revoked;
        this.expired = expired;
    }

}

