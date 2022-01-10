package com.APISpringboot.API_springboot.dtos;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Optional;

public class PPRegisterDto {
    private long id;
    private String email;
    private String name;
    private String password;
    private String cpf;
    private Optional<BigDecimal> hourValue = Optional.empty();
    private Optional<Float> hoursPerDay = Optional.empty();
    private Optional<Float> lunchHours = Optional.empty();
    private String cnpj;

    public PPRegisterDto() {
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

    public Optional<BigDecimal> getHourValue() {
        return hourValue;
    }

    public void setHourValue(Optional<BigDecimal> hourValue) {
        this.hourValue = hourValue;
    }

    public Optional<Float> getHoursPerDay() {
        return hoursPerDay;
    }

    public void setHoursPerDay(Optional<Float> hoursPerDay) {
        this.hoursPerDay = hoursPerDay;
    }

    public Optional<Float> getLunchHours() {
        return lunchHours;
    }

    public void setLunchHours(Optional<Float> lunchHours) {
        this.lunchHours = lunchHours;
    }

    @CNPJ(message = "Invalid CNPJ")
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
                '}';
    }
}
