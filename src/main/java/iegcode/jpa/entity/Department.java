package iegcode.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "departments")
public class Department {

    @EmbeddedId
    private DepartmentId id;

    private String name;

    public DepartmentId getId() {
        return id;
    }

    public void setId(DepartmentId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
