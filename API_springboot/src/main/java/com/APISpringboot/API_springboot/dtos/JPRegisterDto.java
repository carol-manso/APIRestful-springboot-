package com.APISpringboot.API_springboot.dtos;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class JPRegisterDto {
    private long id;
    private String email;
    private String name;
    private String password;
    private String cpf;
    private String socialReason;
    private String cnpj;

    public JPRegisterDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NotEmpty(message = "Email cannot be emnpty")
    @Email(message = "Invalid email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @NotEmpty(message = "Name cannot be emnpty")
    @Length(min = 3, max = 200, message = "Name need to be between 3 and 200 characters long")
    public String getName() {
        return name; 
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty(message = "Password cannot be empty")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotEmpty(message = "CPF cannot be emnpty")
    @CPF(message = "Invalid CPF")
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @NotEmpty(message = "Social Reason cannot be emnpty")
    @Length(min = 5, max = 200, message = "Name need to be between 5 and 200 characters long")
    public String getSocialReason() {
        return socialReason;
    }

    public void setSocialReason(String socialReason) {
        this.socialReason = socialReason;
    }

    @NotEmpty(message = "CNPJ cannot be emnpty")
    @CPF(message = "Invalid CPJ")
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return "JPRegisterDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", cpf='" + cpf + '\'' +
                ", socialReason='" + socialReason + '\'' +
                ", cnpj='" + cnpj + '\'' +
                '}';
    }
}
