package com.kraemer.primeiros_passos_spring_boot;

import org.springframework.stereotype.Component;

@Component
public class Remetente {

    private String email;

    private String nome;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Remetente{" +
                "email='" + email + '\'' +
                '}';
    }
}
