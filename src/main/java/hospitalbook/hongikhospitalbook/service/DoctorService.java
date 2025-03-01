package hospitalbook.hongikhospitalbook.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DoctorService {

        //id를 부여받아 식별, 이름/소속병원,진료과,근무연수 등이 포함 -> 환자가 이거 보고 의사 선택
}
