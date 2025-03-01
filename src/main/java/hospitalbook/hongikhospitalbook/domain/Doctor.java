package hospitalbook.hongikhospitalbook.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Doctor {

    @Id @GeneratedValue
    @Column(name = "doctor_id")
    private Long id;
    private String name;
    private int yearsOfService; //근무연수

    @OneToMany(mappedBy = "doctor")
    private List<Reservation> reservations = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;


    //==연관관계 매서드==//
    /*public void setDepartment(Department department){
        this.department = department;
        department.getDoctors().add(this);
    }*/


    //==생성 매서드==//


}
