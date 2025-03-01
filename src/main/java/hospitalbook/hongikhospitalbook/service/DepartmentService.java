package hospitalbook.hongikhospitalbook.service;


import hospitalbook.hongikhospitalbook.domain.Department;
import hospitalbook.hongikhospitalbook.domain.Doctor;
import hospitalbook.hongikhospitalbook.repository.DepartmentRepository;
import hospitalbook.hongikhospitalbook.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DepartmentService {

    //해당 병원 내에서 이름으로 식별되며, 진료실 수 정보가 있으며, 소속 의사들을 파악할 수 있어야 한다.
    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;

    public List<Doctor> getDoctors(Long departmentId){
        Department department = departmentRepository.findById(departmentId);
        List<Doctor> doctors = doctorRepository.findByDepartment();
        return doctors;
    }
}
