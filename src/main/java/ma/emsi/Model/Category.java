package ma.emsi.Model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name="Category")
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="name")
    private String name;
    @Column(name = "slug")
    private String slug;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "created_at")
    private Date createdAt;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "updated_at")
    private Date updatedAt;
    @PrePersist
    void createdAt() {
        this.createdAt = this.updatedAt = new Date();
    }
    @PreUpdate
    void updatedAt() {
        this.updatedAt = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
