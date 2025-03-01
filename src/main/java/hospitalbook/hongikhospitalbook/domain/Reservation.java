package hospitalbook.hongikhospitalbook.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.print.Doc;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Reservation {

    @Id
    @GeneratedValue
    @Column(name = "reservation_id")
    private Long id;
    private LocalDateTime reserveTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status; //CONFIRMED, CANCEL

    //==연관관계메서드==//
    public void setPatient(Patient patient){
        this.patient = patient;
        patient.getReservations().add(this);
    }

    public void setDoctor(Doctor doctor){
        this.doctor = doctor;
        doctor.getReservations().add(this);
    }

    //==생성매서드==//
    public static Reservation createReservation(Patient patient, Doctor doctor){
        Reservation reservation = new Reservation();
        reservation.setPatient(patient);
        reservation.setDoctor(doctor);
        reservation.setReserveTime(LocalDateTime.now());
        reservation.setStatus(ReservationStatus.CONFIRMED);
        return reservation;
    }

    //==비지니스 로직==//
    public void cancel(){
        this.setStatus(ReservationStatus.CANCEL);
    }
}
