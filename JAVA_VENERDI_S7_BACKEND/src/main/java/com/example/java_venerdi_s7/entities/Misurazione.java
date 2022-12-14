package com.example.java_venerdi_s7.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "misurazioni")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Misurazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sonda_id")
    private Sonda sonda;

    private int smokeLevel;
}
