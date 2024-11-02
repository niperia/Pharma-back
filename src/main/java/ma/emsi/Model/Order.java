package ma.emsi.Model;


import jakarta.persistence.*;
import ma.emsi.Model.Enum.PaymentMethod;

import java.util.Date;


@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "adresse_id")
    private Adress adress;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Transient // This will not be persisted in the database
    private Integer productId;

    @Transient // This will not be persisted in the database
    private Integer userId; // For accepting user ID in requests

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getAddressId() {
        return addressId;
    }
   // For accepting user ID in requests

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    @Transient // This will not be persisted in the database
    private Integer addressId; // For accepting user ID in requests


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "order_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Column(name = "total_amount")
    private Double totalAmount;

    // Constructors
    public Order() {}

    public Order(Cart cart, String address, PaymentMethod paymentMethod, Double totalAmount) {

        this.orderDate = new Date();
        this.totalAmount = totalAmount;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
