package com.APISpringboot.API_springboot.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "company")
public class Company implements Serializable {
    //private static final long serialVersionUID = ;
    private Long id;
    private String socialReason;
    private String cnpj;
    private Date creationDate;
    private Date updateDate;
    private List<Employee> empoloyees;

    public Company(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    @Column(name = "social_reason", nullable = false)
    public String getSocialReason() {
        return socialReason;
    }

    public void setSocialReason(String socialReason) {
        this.socialReason = socialReason;
    }

    @Column(name = "cnpj", nullable = false)
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //uma empresa possui um ou mais funcionários. Quando eu carregar minha empresa, não desejo carregar todos os funcionários automaticamente. Cascade All - excluir todos os funcionarios dependentes de uma empresa se ela for excluida.
    public List<Employee> getEmpoloyees() {
        return empoloyees;
    }

    public void setEmpoloyees(List<Employee> empoloyees) {
        this.empoloyees = empoloyees;
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
    public String toString(){
        return "Empresa [id = " + id + ",social reason=" + socialReason + ",cnpj= " + this.cnpj + ", creation date = " + this.creationDate
                    + ", update date = " + this.updateDate + " ]";
    }





}
