package iegcode.jpa.entity;

import jakarta.persistence.*;

@Entity // untuk ke DB
@Table(name = "customers")
public class Customer {

    @Id // primary key
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "primary_email")
    private String primaryEmail;

    private Boolean married;

    private Byte age;
    @Enumerated(EnumType.STRING)
    private CustomerType type;

    @Transient // untuk meng ignore full name hilang saat di test tapi ada
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getMarried() {
        return married;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
