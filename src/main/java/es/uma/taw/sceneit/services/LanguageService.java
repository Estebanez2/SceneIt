package es.uma.taw.sceneit.services;

import es.uma.taw.sceneit.dao.LanguageRepository;
import es.uma.taw.sceneit.dto.LanguageDTO;
import es.uma.taw.sceneit.entity.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService extends ServiceParent<Language, LanguageDTO>{
    @Autowired
    protected LanguageRepository languageRepository;

    public List<LanguageDTO> getAllUsersAsDTO() {
        List<Language> language = languageRepository.findAll();
        return convertToDTOList(language);
    }

    public void delete(Integer id) {
        languageRepository.deleteById(id);
    }

    public LanguageDTO findById(Integer id) {
        return languageRepository.findById(id)
                .map(Language::toDTO)
                .orElse(new LanguageDTO());
    }

    public List<LanguageDTO> searchByName(String name) {
        return convertToDTOList(languageRepository.buscarPorNombre(name));
    }

    public void save(LanguageDTO dto) {
        Language entity = languageRepository.findById(dto.getId()).orElse(new Language());
        entity.setName(dto.getName());
        languageRepository.save(entity);
    }

}
