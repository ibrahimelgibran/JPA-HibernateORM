package iegcode.jpa.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "brands")
public class Brand extends AuditableEntity<String>{
    private String name;

    private String description;

    @OneToMany(mappedBy = "brand")
    private List<Product> products;

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
