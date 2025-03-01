package hospitalbook.hongikhospitalbook.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Hospital {

    @Id @GeneratedValue
    @Column(name = "hospital_id")
    private Long id;

    private String name;
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "hospital",cascade = CascadeType.ALL)
    private List<Department> departments = new ArrayList<>();

    //==연관관계 매서드==//
    public void addDepartment(Department department){
        departments.add(department);
        department.setHospital(this);
    }
}
