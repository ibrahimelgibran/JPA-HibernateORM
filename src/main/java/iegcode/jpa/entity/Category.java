package iegcode.jpa.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Calendar;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private String id;

    private String name;

    private String description;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at")
    private Calendar createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    public Calendar getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Calendar createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
