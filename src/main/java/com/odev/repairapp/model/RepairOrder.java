package com.odev.repairapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Table(indexes = @Index(columnList = "uuid"))
public class RepairOrder {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tracking;
    private UUID uuid;
    private boolean paymentStatus;
    private String name;
    private String email;
    private String phone;
    private String serialNumber;
    private String address;
    @Lob
    private String diagnostic;
    private Double subTotal;
    private double totalCost;
    private double prePaid;
    private LocalDateTime completed_at;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToOne
    private Device device;
    @OneToOne
    private RepairStatus repairStatus;
    @OneToOne
    private RepairPriority repairPriority;
    @OneToOne
    private User user;
}
