package com.example.demo.domain;


import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto Increment > 데이터가 추가 될 때 마다,자동으로 증가 하는 어노테이션.
    @Column(name = "id", updatable = false) //고유해야 하니까
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

}
