package hospitalbook.hongikhospitalbook.Service;

import hospitalbook.hongikhospitalbook.domain.*;
import hospitalbook.hongikhospitalbook.repository.DepartmentRepository;
import hospitalbook.hongikhospitalbook.repository.DoctorRepository;
import hospitalbook.hongikhospitalbook.repository.HospitalRepository;
import hospitalbook.hongikhospitalbook.repository.ReservationRepository;
import hospitalbook.hongikhospitalbook.service.DepartmentService;
import hospitalbook.hongikhospitalbook.service.DoctorService;
import hospitalbook.hongikhospitalbook.service.HospitalService;
import hospitalbook.hongikhospitalbook.service.ReservationService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ReservationServiceTest {
    @PersistenceContext EntityManager em;

    @Autowired ReservationRepository reservationRepository;
    @Autowired ReservationService reservationService;
    @Autowired
    HospitalService hospitalService;
    @Autowired
    HospitalRepository hospitalRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    DoctorService doctorService;
    @Autowired DepartmentService departmentService;
    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    public void 병원_정보_출력() throws  Exception{
        //given
        Hospital hospital = createHospital("서울병원");
        hospital.setAddress(new Address("서울특별시","강남","123-123"));

        Department department1 = createDepartment("외과",3);
        Department department2 = createDepartment("안과",4);
        Department department3 = createDepartment("치과",2);

        hospital.addDepartment(department1);
        hospital.addDepartment(department2);
        hospital.addDepartment(department3);

        //when
        List<Department> departments = hospitalService.getDepartments(hospital.getId());

        //then
        System.out.println("병원 = " + hospital.getName() +"주소= "+ hospital.getAddress());
        for (Department department : departments) {
            System.out.println("소속 진료과 = " + department.getName());;
        }
    }
    @Test
    public void 진료과_정보_출력() throws  Exception{
        //given
        Department department1 = createDepartment("외과",3);
        Department department2 = createDepartment("안과",4);
        Department department3 = createDepartment("치과",2);

        Doctor doctor1 = createDoctor("의사1",4);
        Doctor doctor2 = createDoctor("의사2", 5);
        Doctor doctor3 = createDoctor("의사3",6);

        department1.setDoctor(doctor1);
        department2.setDoctor(doctor2);
        department3.setDoctor(doctor3);
        //when
        List<Doctor> doctors1 = department1.getDoctors();
        List<Doctor> doctors2 = department2.getDoctors();
        List<Doctor> doctors3 = department3.getDoctors();

        //then
        System.out.println("진료과: "+ department1.getName() + " 진로실 수: "+ department1.getClinicCount());
        for (Doctor doctor : doctors1) {
            System.out.println("소속의사 = " + doctor.getName());
        }
        System.out.println("진료과: "+ department2.getName() + " 진로실 수: "+ department2.getClinicCount());
        for (Doctor doctor : doctors2) {
            System.out.println("소속의사 = " + doctor.getName());
        }
        System.out.println("진료과: "+ department3.getName() + " 진로실 수: "+ department3.getClinicCount());
        for (Doctor doctor : doctors3) {
            System.out.println("소속의사 = " + doctor.getName());
        }

    }
    @Test
    public void 예약() throws Exception{
        //given
        Hospital hospital = createHospital("서울병원");
        hospital.setAddress(new Address("서울특별시","강남","123-123"));

        Department department1 = createDepartment("외과",3);
        Department department2 = createDepartment("안과",4);
        Department department3 = createDepartment("치과",2);

        department1.setHospital(hospital);
        department2.setHospital(hospital);
        department3.setHospital(hospital);

        Patient patient1 = createPatient("김철수","Male",22);
        Patient patient2= createPatient("이영희","Female",25);
        Patient patient3= createPatient("조홍익","Male",30);

        Doctor doctor1 = createDoctor("의사1",4);
        Doctor doctor2 = createDoctor("의사2", 5);
        Doctor doctor3 = createDoctor("의사3",6);

        department1.setDoctor(doctor1);
        department2.setDoctor(doctor2);
        department3.setDoctor(doctor3);

        //when
        Reservation reservation1 = reservationService.reservation(patient1.getId(), doctor1.getId());

        Reservation reservation2 = reservationService.reservation(patient2.getId(), doctor2.getId());

        Reservation reservation3 = reservationService.reservation(patient3.getId(), doctor3.getId());

        //then
        //예약: 환자,의사,진료과,병원,시간으로 구성

        System.out.println("환자명: "+reservation1.getPatient().getName()+
                " 의사명: "+reservation1.getDoctor().getName()+
                " 진료과: "+reservation1.getDoctor().getDepartment().getName()+
                " 병원: "+ reservation1.getDoctor().getDepartment().getHospital().getName()+
                " 예약시간: "+reservation1.getReserveTime());

        System.out.println("환자명: "+reservation2.getPatient().getName()+
                " 의사명: "+reservation2.getDoctor().getName()+
                " 진료과: "+reservation2.getDoctor().getDepartment().getName()+
                " 병원: "+ reservation2.getDoctor().getDepartment().getHospital().getName()+
                " 예약시간: "+reservation2.getReserveTime());

        System.out.println("환자명: "+reservation3.getPatient().getName()+
                " 의사명: "+reservation3.getDoctor().getName()+
                " 진료과: "+reservation3.getDoctor().getDepartment().getName()+
                " 병원: "+ reservation3.getDoctor().getDepartment().getHospital().getName()+
                " 예약시간: "+reservation3.getReserveTime());

    }

    private Patient createPatient(String name, String gender, int age){
        Patient patient = new Patient();
        patient.setName(name);
        patient.setGender(gender);
        patient.setAge(age);
        em.persist(patient);
        return patient;
    }

    private Doctor createDoctor(String name,int yesrOfService){
        Doctor doctor = new Doctor();
        doctor.setName(name);
        doctor.setYearsOfService(yesrOfService);
        em.persist(doctor);
        return doctor;
    }

    private Hospital createHospital(String name){
        Hospital hospital = new Hospital();
        hospital.setName(name);
        em.persist(hospital);
        return hospital;
    }

    private Department createDepartment(String name, int clinitCount){
        Department department = new Department();
        department.setName(name);
        department.setClinicCount(clinitCount);
        em.persist(department);
        return department;
    }
}
