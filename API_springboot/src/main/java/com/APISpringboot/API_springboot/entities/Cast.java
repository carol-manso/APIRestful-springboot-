package com.APISpringboot.API_springboot.entities;

import com.APISpringboot.API_springboot.enums.TypeEnum;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cast")
public class Cast {

    private Long id;
    private Date date;
    private String location;
    private Date creationDate;
    private Date updateDate;
    private TypeEnum type;
    private Employee employee;



    private String description;

    public  Cast(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //certifica que quer gravar data E hora
    @Temporal(TemporalType.TIMESTAMP)
    @Column (name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column (name = "location", nullable = false)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Column (name = "creation_date", nullable = false)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Column (name = "update_date", nullable = false)
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    //vários lançamentos pertencem a um mesmo funcionário.
    @ManyToOne(fetch = FetchType.EAGER)
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "Cast{" +
                "id=" + id +
                ", date=" + date +
                ", location='" + location + '\'' +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                ", type=" + type +
                ", employee=" + employee +
                ", description=" + description +
                '}';
    }


}
