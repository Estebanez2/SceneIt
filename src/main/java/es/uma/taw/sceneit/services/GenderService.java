package es.uma.taw.sceneit.services;

import es.uma.taw.sceneit.dao.GenderRepository;
import es.uma.taw.sceneit.dto.GenderDTO;
import es.uma.taw.sceneit.entity.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenderService extends ServiceParent<Gender, GenderDTO> {
    @Autowired
    protected GenderRepository genderRepository;

    public List<GenderDTO> getAllUsersAsDTO() {
        List<Gender> gender = genderRepository.findAll();
        return convertToDTOList(gender);
    }

    public void delete(Integer id) {
        genderRepository.deleteById(id);
    }

    public GenderDTO findById(Integer id) {
        return genderRepository.findById(id)
                .map(Gender::toDTO)
                .orElse(new GenderDTO());
    }

    public List<GenderDTO> searchByName(String name) {
        return convertToDTOList(genderRepository.buscarPorNombre(name));
    }

    public void save(GenderDTO dto) {
        Gender entity = genderRepository.findById(dto.getId()).orElse(new Gender());
        entity.setName(dto.getName());
        genderRepository.save(entity);
    }

}
