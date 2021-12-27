package com.APISpringboot.API_springboot.entities;

import com.APISpringboot.API_springboot.enums.ProfileEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String cpf;
    private BigDecimal hourValue;
    private Float workHoursPerDay;
    private Float lunchHours;
    private ProfileEnum profile;
    private Date creationDate;
    private Date updateDate;
    private Company company;
    private List<Cast> casts;

    public Employee(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "cpf", nullable = false)
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Column(name = "hour_value", nullable = false)
    public BigDecimal getHourValue() {
        return hourValue;
    }

    @Transient
    public Optional<BigDecimal> getHourValueOpt(){
        return Optional.ofNullable(this.hourValue);
    }

    public void setHourValue(BigDecimal hourValue) {
        this.hourValue = hourValue;
    }

    @Column(name = "work_hours_per_day", nullable = false)
    public Float getWorkHoursPerDay() {
        return workHoursPerDay;
    }

    public void setWorkHoursPerDay(Float workHoursPerDay) {
        this.workHoursPerDay = workHoursPerDay;
    }


    @Column(name = "lunch_hours", nullable = false)
    public Float getLunchHours() {
        return lunchHours;
    }

    public void setLunchHours(Float lunchHours) {
        this.lunchHours = lunchHours;
    }

    @Enumerated(EnumType.STRING) //colocar o nome da role ao invés de sua posição
    @Column(name = "profile", nullable = false)
    public ProfileEnum getProfile() {
        return profile;
    }


    public void setProfile(ProfileEnum profile) {
        this.profile = profile;
    }

    @Column(name = "creation_date", nullable = false)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Column(name = "update_date", nullable = false)
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    //smepre que carregar os dados do meu funcionário já quero que carregue os dados da empresa.
    //uma empresa tem vários funcionários.
    @ManyToOne(fetch = FetchType.EAGER)
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    //um employee tem uma ou mais horas lançadas.
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Cast> getCasts() {
        return casts;
    }

    public void setCasts(List<Cast> casts) {
        this.casts = casts;
    }

    @PreUpdate
    public void preUpdate(){
        this.updateDate = new Date();

    }
    @PrePersist
    public void prePersist(){
        final Date current = new Date();
        this.creationDate = current;
        this.updateDate = current;

    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", cpf='" + cpf + '\'' +
                ", hourValue=" + hourValue +
                ", workHoursPerDay=" + workHoursPerDay +
                ", lunchHours=" + lunchHours +
                ", profile=" + profile +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                ", company=" + company +
                ", casts=" + casts +
                '}';
    }

}
