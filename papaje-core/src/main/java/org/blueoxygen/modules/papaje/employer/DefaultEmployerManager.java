package org.blueoxygen.modules.papaje.employer;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DefaultEmployerManager implements EmployerManager {

	@Inject
	private EmployerRepository employerRepository;
	
	@Override
	public Employer findByUserId(String userId) {
		return employerRepository.findByPersonalInfoUserId(userId);
	}

	@Override
	@Transactional
	public Employer saveEmployer(Employer employer) {
		if(StringUtils.isBlank(employer.getId())) {
			employer.setId(null);
			return employerRepository.save(employer);
		} else {
			Employer e = employerRepository.findById(employer.getId());
			e.setPersonalInfo(employer.getPersonalInfo());
			e.setCompany(employer.getCompany());

			return e;
		}
	}

}
