package com.odev.repairapp.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class Defect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String requiredTime;
    private double cost;
    private double price;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @ManyToOne
    private Device device;

    @ManyToMany(mappedBy = "defects")
    private List<RepairOrder> repairOrders;

    @Override
    public String toString() {
        return "Defect{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", requiredTime='" + requiredTime + '\'' +
                ", cost=" + cost +
                ", price=" + price +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", device=" + device +
                ", repairOrders=" + repairOrders +
                '}';
    }
}
