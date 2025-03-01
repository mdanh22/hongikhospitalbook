package hospitalbook.hongikhospitalbook.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Department {

    @Id @GeneratedValue
    @Column(name = "department_id")
    private Long id;

    private String name;
    private int clinicCount; //진료실 수

    @OneToMany(mappedBy = "department")
    private List<Doctor> doctors = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    /*//==연관관계 매서드==//
    public void setHospital(Hospital hospital){
        this.hospital = hospital;
        hospital.getDepartments().add(this);
    }*/

    //==연관관계 매서드==//
    public void setDoctor(Doctor doctor){

        this.doctors.add(doctor);
        doctor.setDepartment(this);
    }
}
