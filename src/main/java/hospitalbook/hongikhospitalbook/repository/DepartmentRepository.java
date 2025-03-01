package hospitalbook.hongikhospitalbook.repository;

import hospitalbook.hongikhospitalbook.domain.Department;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DepartmentRepository {

    private final EntityManager em;

    public void save(Department department){
        em.persist(department);
    }

    public Department findById(Long id){
        return em.find(Department.class, id);
    }

    public List<Department> findAll(){
        return em.createQuery("select d from Department d", Department.class)
                .getResultList();
    }

    public List<Department> findByHospitalId(Long id){
        return em.createQuery("select d from Department d" +
                " join fetch d.hospital h", Department.class)
                .getResultList();
    }
}
