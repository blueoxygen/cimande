package org.blueoxygen.modules.papaje.language;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class RestLanguageService implements LanguageService {

	@Inject
	private LanguageRepository languageRepository;
	
	@Override
	public Language getLanguageById(String id) {
		return languageRepository.findById(id);
	}

	@Override
	public Page<? extends Language> findLanguageByKeyword(String keyword,
			int max, int page) {
		keyword = StringUtils.defaultIfBlank(keyword, "");
		return languageRepository.findByNameContaining(keyword, new PageRequest(page, max));
	}

	@Override
	public boolean removeLanguage(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Language saveLanguage(Language language) {
		if (StringUtils.isBlank(language.getId())) {
			language = languageRepository.save(language);
		} else {
			Language l = languageRepository.findById(language.getId());
			l.setName(language.getName());
			l.setDescription(language.getDescription());
			
			language = l;
		}
		return language;
	}

}
