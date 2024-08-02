package com.food.ordersystem.enitites;


import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String orderNumber;
    
    /*@Column(nullable = false)
    private Date orderDate;*/
    
    @Column(nullable = false)
    private Double totalPrice;
    
    @Column(nullable = false)
    private String deliveryAddress;
    
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
  /*   @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;*/
    
    // Getters and Setters
    
    public enum DeliveryStatus {
        PENDING, SHIPPED, DELIVERED, CANCELED
    }
    
}
