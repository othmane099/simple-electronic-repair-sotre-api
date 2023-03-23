package com.odev.repairapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Setting {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String appName;
    private String appAddress;
    private String appPhone;
    @Column(columnDefinition = "varchar(255) default 'DA'")
    private String currencySymbol;

    @UpdateTimestamp
    private LocalDateTime updated_at;
}
