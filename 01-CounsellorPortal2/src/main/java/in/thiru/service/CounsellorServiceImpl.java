package in.thiru.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.thiru.dtos.CounsellorDTO;
import in.thiru.entity.CounsellorEntity;
import in.thiru.repo.CounsellorRepo;

@Service
public class CounsellorServiceImpl implements ICounsellorService {

	@Autowired
	private CounsellorRepo counsellorRepo;

	@Override
	public CounsellorDTO login(CounsellorDTO counsDTO) {

		CounsellorEntity counsellortEntity = counsellorRepo.findByEmailAndPassword(counsDTO.getEmail(),
				counsDTO.getPassword());

		if (counsellortEntity != null) {
			CounsellorDTO dto = new CounsellorDTO();
			BeanUtils.copyProperties(counsellortEntity, dto);
			return dto;
		}

		return null;

	}

	@Override
	public boolean uniqueEmailCheck(String email) {
		
		return counsellorRepo.findByEmail(email) == null;

//		CounsellorEntity findByEmail = counsellorRepo.findByEmail(email);
//
//		if (findByEmail == null) {
//			return true;
//		}
//
//		return false;
	}

	@Override
	public boolean registerCounsellor(CounsellorDTO counsDTO) {

		/*
		 * CounsellorEntity centity=new CounsellorEntity();
		 * centity.setEmail(counsDTO.getEmail()); centity.setName(counsDTO.getName());
		 * centity.setPassword(counsDTO.getPassword());
		 * centity.setPhno(counsDTO.getPhno());
		 */

		CounsellorEntity centity = new CounsellorEntity();

		BeanUtils.copyProperties(counsDTO, centity);

		CounsellorEntity saved = counsellorRepo.save(centity);

		//if id generated means it is inserted.
		if (saved != null) {
			return true;
		}
		return false;
	}

}
