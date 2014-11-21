package org.blueoxygen.modules.papaje.job;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobManager {
	Job saveJob(Job job);
	
	boolean removeJob(Job job);
	
	Job findById(String id);
	
	Job updateStatus(Job job, int status);
	
	Page<Job> findByName(String q, Pageable pageable);
	
	Page<Job> findByName(String q, int status, Pageable pageable);
	
	Page<Job> findByCompanyId(String companyId, Pageable pageable);
}
