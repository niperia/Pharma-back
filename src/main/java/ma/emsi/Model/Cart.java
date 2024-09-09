package ma.emsi.Model;

import jakarta.persistence.*;

@Entity
@Table
public class Cart {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
}
