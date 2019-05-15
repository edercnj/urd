package com.br.kerberus.urd.entity;

import java.util.Objects;

public class NetworkCredential {

    private String username;
    private String password;

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public NetworkCredential(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NetworkCredential)) return false;
        NetworkCredential that = (NetworkCredential) o;
        return username.equals(that.username) &&
                password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "NetworkCredential{" +
                "username:'" + username + '\'' +
                ", password:'" + password + '\'' +
                '}';
    }
}

