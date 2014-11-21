package org.blueoxygen.modules.papaje.company;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class RestCompanyService implements CompanyService {

	@Inject
	private CompanyRepository companyRepository;
	
	@Override
	public Page<? extends Company> findCompanyByKeyword(String keyword,
			int max, int page) {
		keyword = StringUtils.defaultIfBlank(keyword, "");
		return companyRepository.findByNameContaining(keyword, new PageRequest(page, max));
	}

	@Override
	public boolean removeCompany(String id) {
		return false;
	}

	@Override
	@Transactional
	public Company saveCompany(Company company) {
		if (StringUtils.isBlank(company.getId())) {
			company = companyRepository.save(company);
		} else {
			Company c = companyRepository.findById(company.getId());
			c.setName(company.getName());
			c.setDescription(company.getDescription());
			c.setWebsite(company.getWebsite());
			c.setEmail(company.getEmail());
			c.setPhone1(company.getPhone1());
			c.setPhone2(company.getPhone2());
			c.setAddress(company.getAddress());
			
			company = c;
		}

		return company;
	}

	@Override
	public Company getCompanyById(String id) {
		return companyRepository.findById(id);
	}

}
