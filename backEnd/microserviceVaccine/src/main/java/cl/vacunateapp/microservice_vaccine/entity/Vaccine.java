package cl.vacunateapp.microservice_vaccine.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@Table(name = "VACCINE")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "AMOUNT")
    private Integer amount;
}
