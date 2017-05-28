package com.projeto.sqlite.Model;

/**
 * Created by Bruni on 18/05/2017.
 */

public class LoginDados {

    private String senha;
    private String cpf;

    public String getPassword()
    {
        return senha;
    }
    public void setPassword(String senha)
    {
        this.senha = senha;
    }
    public String getEmail()
    {
        return cpf;
    }
    public void setEmail(String cpf)
    {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "LoginDados{" +
                "senha='" + senha + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
