package hospitalbook.hongikhospitalbook.repository;

import hospitalbook.hongikhospitalbook.domain.Reservation;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReservationRepository {

    private final EntityManager em;

    public void save(Reservation reservation){
        em.persist(reservation);
    }

    public Reservation findById(Long id){
        return em.find(Reservation.class, id);
    }
}
