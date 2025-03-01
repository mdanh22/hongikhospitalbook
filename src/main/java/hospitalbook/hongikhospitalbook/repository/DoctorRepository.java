package hospitalbook.hongikhospitalbook.repository;

import hospitalbook.hongikhospitalbook.domain.Doctor;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DoctorRepository {

    private final EntityManager em;

    public void save(Doctor doctor){
        em.persist(doctor);
    }

    public Doctor findById(Long id){
        return em.find(Doctor.class,id);
    }

    public List<Doctor> findAll(){
        return em.createQuery("select d from Doctor d", Doctor.class)
                .getResultList();
    }

    public List<Doctor> findByDepartment(){
        return em.createQuery("select d from Doctor d" +
                        " join fetch d.department dp", Doctor.class)
                .getResultList();
    }
}
