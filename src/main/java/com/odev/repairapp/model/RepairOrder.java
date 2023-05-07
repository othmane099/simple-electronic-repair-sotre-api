package com.odev.repairapp.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
@Table(indexes = @Index(columnList = "uuid"))
public class RepairOrder {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = false)
    private String tracking;
    @Column(updatable = false)
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
    private LocalDateTime completedAt;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToOne
    private Device device;
    @OneToOne
    private RepairStatus repairStatus;
    @OneToOne
    private RepairPriority repairPriority;
    @ManyToMany
    @JoinTable(
            name = "repair_order_defect",
            joinColumns = @JoinColumn(name = "repair_order_id"),
            inverseJoinColumns = @JoinColumn(name = "defect_id"))
    private List<Defect> defects;
    @OneToOne
    private User user;

    @Override
    public String toString() {
        return "RepairOrder{" +
                "uuid=" + uuid +
                '}';
    }
}
