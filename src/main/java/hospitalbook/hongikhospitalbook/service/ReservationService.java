package hospitalbook.hongikhospitalbook.service;

import hospitalbook.hongikhospitalbook.domain.Doctor;
import hospitalbook.hongikhospitalbook.domain.Patient;
import hospitalbook.hongikhospitalbook.domain.Reservation;
import hospitalbook.hongikhospitalbook.repository.DoctorRepository;
import hospitalbook.hongikhospitalbook.repository.PatientRepository;
import hospitalbook.hongikhospitalbook.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    //예약: 환자,의사,진료과,병원,시간으로 구성
    //환자,의사 예약정보 조회 가능 / 환자 의사 간 ㅇㅖ약 1개만 존재

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final ReservationRepository reservationRepository;


    /**
     * 얘약
     */
    @Transactional
    public Reservation reservation(Long patientId, Long doctorId) {
        //엔티티 조회
        Patient patient = patientRepository.findById(patientId);
        Doctor doctor =  doctorRepository.findById(doctorId);

        Reservation reservation = Reservation.createReservation(patient, doctor);
        reservationRepository.save(reservation);
        return reservation;
    }

    /**
     * 예약취소
     */
    @Transactional
    public void cancelReservation(Long reservationId){
        Reservation reservation = reservationRepository.findById(reservationId);
        reservation.cancel();
    }
}