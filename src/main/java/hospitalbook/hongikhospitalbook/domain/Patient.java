package hospitalbook.hongikhospitalbook.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Patient {
    @Id @GeneratedValue
    @Column(name = "patient_id")
    private Long id;

    private String name;
    private String gender;
    private int age;

    @OneToMany(mappedBy = "patient")
    private List<Reservation> reservations = new ArrayList<>();
}
