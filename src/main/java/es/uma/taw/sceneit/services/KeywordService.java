package es.uma.taw.sceneit.services;

import es.uma.taw.sceneit.dao.KeywordRepository;
import es.uma.taw.sceneit.dto.KeywordDTO;
import es.uma.taw.sceneit.entity.Keyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordService extends ServiceParent<Keyword, KeywordDTO>{
    @Autowired
    protected KeywordRepository keywordRepository;

    public List<KeywordDTO> getAllUsersAsDTO() {
        List<Keyword> keyword = keywordRepository.findAll();
        return convertToDTOList(keyword);
    }

    public void delete(Integer id) {
        keywordRepository.deleteById(id);
    }

    public KeywordDTO findById(Integer id) {
        return keywordRepository.findById(id)
                .map(Keyword::toDTO)
                .orElse(new KeywordDTO());
    }

    public List<KeywordDTO> searchByName(String name) {
        return convertToDTOList(keywordRepository.buscarPorNombre(name));
    }

    public void save(KeywordDTO dto) {
        Keyword entity = keywordRepository.findById(dto.getId()).orElse(new Keyword());
        entity.setName(dto.getName());
        keywordRepository.save(entity);
    }

}
