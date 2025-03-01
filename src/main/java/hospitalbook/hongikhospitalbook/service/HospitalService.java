package hospitalbook.hongikhospitalbook.service;


import hospitalbook.hongikhospitalbook.domain.Department;
import hospitalbook.hongikhospitalbook.domain.Hospital;
import hospitalbook.hongikhospitalbook.repository.DepartmentRepository;
import hospitalbook.hongikhospitalbook.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HospitalService {

    //id로식별됨, 이름,주소,병원에 설치되어 있는 진료과에 대한 정보를 제공
    private final HospitalRepository hospitalRepository;
    private final DepartmentRepository departmentRepository;


}
