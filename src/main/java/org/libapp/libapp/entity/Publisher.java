package org.libapp.libapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "publishers", schema = "bookapp")
public class Publisher {
    @Id
    @Column(name = "publisher_id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "publisher_name")
    private String publisherName;

    @Lob
    @Column(name = "address")
    private String address;

    @Lob
    @Column(name = "website")
    private String website;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

}