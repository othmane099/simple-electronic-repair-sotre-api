package com.odev.repairapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Setting {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String appName;
    private String appAddress;
    private String appPhone;
    @Column(columnDefinition = "string currency_symbol 'DA'")
    private String currencySymbol;
}
