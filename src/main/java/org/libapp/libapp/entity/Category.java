package org.libapp.libapp;

import jakarta.persistence.*;

@Entity
@Table(name = "categories", schema = "bookapp")
public class Category {
    @Id
    @Column(name = "category_id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "category_name", nullable = false)
    private String categoryName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}