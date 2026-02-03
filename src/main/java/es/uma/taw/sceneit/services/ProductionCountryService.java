package es.uma.taw.sceneit.services;

import es.uma.taw.sceneit.dao.ProductionCountryRepository;
import es.uma.taw.sceneit.dto.ProductionCountryDTO;
import es.uma.taw.sceneit.entity.ProductionCountry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionCountryService extends ServiceParent<ProductionCountry, ProductionCountryDTO>{
    @Autowired
    protected ProductionCountryRepository productionCountryRepository;

    public List<ProductionCountryDTO> getAllUsersAsDTO() {
        List<ProductionCountry> productionCountry = productionCountryRepository.findAll();
        return convertToDTOList(productionCountry);
    }

    public void delete(Integer id) {
        productionCountryRepository.deleteById(id);
    }

    public ProductionCountryDTO findById(Integer id) {
        return productionCountryRepository.findById(id)
                .map(ProductionCountry::toDTO)
                .orElse(new ProductionCountryDTO());
    }

    public List<ProductionCountryDTO> searchByName(String name) {
        return convertToDTOList(productionCountryRepository.buscarPorNombre(name));
    }

    public void save(ProductionCountryDTO dto) {
        ProductionCountry entity = productionCountryRepository.findById(dto.getId()).orElse(new ProductionCountry());
        entity.setName(dto.getName());
        productionCountryRepository.save(entity);
    }

}
