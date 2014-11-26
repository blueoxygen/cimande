package org.blueoxygen.modules.papaje.job;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.blueoxygen.cimande.security.SessionCredentials;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DefaultJobManager implements JobManager {

	@Inject
	private JobRepository jobRepository;
	
	@Override
	@Transactional
	public Job saveJob(Job job) {
		if (StringUtils.isBlank(job.getId())) {
			job.setId(null);
			
			return jobRepository.save(job);
		} else {
			Job j = jobRepository.findById(job.getId());
			j.setAddress(job.getAddress());
			j.setApplied(job.getApplied());
			j.setCode(job.getCode());
			j.setCompany(job.getCompany());
			j.setDescription(job.getDescription());
			j.setExperienceLevel(job.getExperienceLevel());
			j.setFunction(job.getFunction());
			j.setName(job.getName());
			j.setNotes(job.getNotes());
			j.setPersonalSpec(job.getPersonalSpec());
			j.setRequirement(job.getRequirement());
			j.setSallary(job.getSallary());
			j.setType(job.getType());
			j.setCategory(job.getCategory());
			
			return j;
		}
	}

	@Override
	@Transactional
	public boolean removeJob(Job job) {
		if (job == null)
			return false;
		
		jobRepository.delete(job);
		
		return true;
	}

	@Override
	public Job findById(String id) {
		return jobRepository.findById(id);
	}

	@Override
	public Page<Job> findByName(String q, Pageable pageable) {
		q = StringUtils.defaultIfBlank(q, "");
		return jobRepository.findByNameContainingAndLogInformationSite(q, SessionCredentials.getCurrentSite().getId(), pageable);
	}

	@Override
	public Page<Job> findByName(String q, int status, Pageable pageable) {
		q = StringUtils.defaultIfBlank(q, "");
		return jobRepository.findByNameContainingAndLogInformationActiveFlagAndLogInformationSite(
				q, status, SessionCredentials.getCurrentSite().getId(), pageable);
	}

	@Override
	public Page<Job> findByCompanyId(String companyId, Pageable pageable) {
		return jobRepository.findByCompanyIdContainingAndLogInformationSite(
				companyId, SessionCredentials.getCurrentSite().getId(), pageable);
	}

	@Override
	@Transactional
	public Job updateStatus(Job job, int status) {
		job = jobRepository.findById(job.getId());
		job.getLogInformation().setActiveFlag(status);
		
		return job;
	}

}
