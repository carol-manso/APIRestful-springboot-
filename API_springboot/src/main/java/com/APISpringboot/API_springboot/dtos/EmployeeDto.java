package com.APISpringboot.API_springboot.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Optional;



public class EmployeeDto {
    private Long id;
    private String name;
    private String email;
    private Optional<String> senha = Optional.empty();
    private Optional<String> hourValue = Optional.empty();
    private Optional<String> workHours = Optional.empty();
    private Optional<String> lunchHours = Optional.empty();

    public EmployeeDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @NotEmpty(message = "Name can not be empty")
    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty(message = "Email can not be empty")
    @Email(message = "Invalid email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Optional<String> getSenha() {
        return senha;
    }

    public void setSenha(Optional<String> senha) {
        this.senha = senha;
    }

    public Optional<String> getHourValue() {
        return hourValue;
    }

    public void setHourValue(Optional<String> hourValue) {
        this.hourValue = hourValue;
    }

    public Optional<String> getWorkHours() {
        return workHours;
    }

    public void setWorkHours(Optional<String> workHours) {
        this.workHours = workHours;
    }

    public Optional<String> getLunchHours() {
        return lunchHours;
    }

    public void setLunchHours(Optional<String> lunchHours) {
        this.lunchHours = lunchHours;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", senha=" + senha +
                ", hourValue=" + hourValue +
                ", workHours=" + workHours +
                ", lunchHours=" + lunchHours +
                '}';
    }
}
