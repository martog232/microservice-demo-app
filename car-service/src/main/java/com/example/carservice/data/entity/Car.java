package com.example.carservice.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "cars")
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Car {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_id_gen")
    @SequenceGenerator(name = "car_id_gen", sequenceName = "car_id_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "user_id")
    private Long ownerId;
}
