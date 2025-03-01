package hospitalbook.hongikhospitalbook.service;

import hospitalbook.hongikhospitalbook.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PatientService {
    // 환자의 나이,성별 등의 신상정보를 유지하여야 한다. 환자는 개인 id로 식별된다
    private final PatientRepository patientRepository;


}
