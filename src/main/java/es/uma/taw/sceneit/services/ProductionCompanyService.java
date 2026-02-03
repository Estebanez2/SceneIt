package es.uma.taw.sceneit.services;

import es.uma.taw.sceneit.dao.ProductionCompanyRepository;
import es.uma.taw.sceneit.dto.ProductionCompanyDTO;
import es.uma.taw.sceneit.entity.ProductionCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionCompanyService extends ServiceParent<ProductionCompany, ProductionCompanyDTO>{
    @Autowired
    protected ProductionCompanyRepository productionCompanyRepository;

    public List<ProductionCompanyDTO> getAllUsersAsDTO() {
        List<ProductionCompany> productionCompany = productionCompanyRepository.findAll();
        return convertToDTOList(productionCompany);
    }

    public void delete(Integer id) {
        productionCompanyRepository.deleteById(id);
    }

    public ProductionCompanyDTO findById(Integer id) {
        return productionCompanyRepository.findById(id)
                .map(ProductionCompany::toDTO)
                .orElse(new ProductionCompanyDTO());
    }

    public ProductionCompany findEntityById(Integer id) {
        return productionCompanyRepository.findById(id).orElse(null);
    }

    public List<ProductionCompanyDTO> searchByName(String name) {
        return convertToDTOList(productionCompanyRepository.buscarPorNombre(name));
    }

    public void save(ProductionCompanyDTO dto) {
        ProductionCompany entity = productionCompanyRepository.findById(dto.getId()).orElse(new ProductionCompany());
        entity.setName(dto.getName());
        productionCompanyRepository.save(entity);
    }

}
