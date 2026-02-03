package es.uma.taw.sceneit.services;

import es.uma.taw.sceneit.dao.CrewRepository;
import es.uma.taw.sceneit.dto.CrewDTO;
import es.uma.taw.sceneit.entity.Crew;
import es.uma.taw.sceneit.entity.ProductionCompany;
import es.uma.taw.sceneit.ui.Filtro;
import es.uma.taw.sceneit.ui.FiltroStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrewService extends ServiceParent<Crew, CrewDTO> {
    @Autowired
    protected CrewRepository crewRepository;

    @Autowired
    protected PersonService personService;

    @Autowired
    protected ProductionCompanyService productionCompanyService;

    public List<CrewDTO> getAllUsersAsDTO() {
        List<Crew> crew = crewRepository.findAll();
        return convertToDTOList(crew);
    }

    public void delete(Integer id) {
        crewRepository.deleteById(id);
    }

    public CrewDTO findById(Integer id) {
        return crewRepository.findById(id)
                .map(Crew::toDTO)
                .orElse(new CrewDTO());
    }

    public List<CrewDTO> searchByName(String name) {
        return convertToDTOList(crewRepository.buscarPorNombre(name));
    }

    public List<CrewDTO> searchByJob(String name) {
        return convertToDTOList(crewRepository.buscarPorPuesto(name));
    }

    public List<CrewDTO> searchByCompany(String name) {
        return convertToDTOList(crewRepository.buscarPorEmpresa(name));
    }

    public List<CrewDTO> search(Filtro filtro) {
        List<CrewDTO> resultado;

        switch (filtro.getTipo()) {
            case "nombre":
                resultado = searchByName(filtro.getNombre());
                break;
            case "puesto":
                resultado = searchByJob(filtro.getNombre());
                break;
            case "empresa":
                resultado = searchByCompany(filtro.getNombre());
                break;
            default:
                resultado = new ArrayList<>();
                break;
        }

        return resultado;
    }

    public List<CrewDTO> searchStatistics(FiltroStatistics filtroStatistics) {
        List<CrewDTO> resultado;

        switch (filtroStatistics.getTipo()) {
            case "Nombre":
                resultado = searchByName(filtroStatistics.getNombre());
                break;
            case "Puesto":
                resultado = searchByJob(filtroStatistics.getNombre());
                break;
            case "Empresa":
                resultado = searchByCompany(filtroStatistics.getNombre());
                break;
            default:
                resultado = new ArrayList<>();
                break;
        }

        return resultado;
    }

    public List<CrewDTO> crewDeMovie(Integer id) {
        return convertToDTOList(crewRepository.crewDeMovie(id));
    }


    public void save(CrewDTO dto) {
        Crew entity = crewRepository.findById(dto.getId()).orElse(new Crew());
        entity.setJob(dto.getJob());

        entity.setPersonIdperson(personService.findEntityById(dto.getIdPersona()));

        if(dto.getIdProductCompany() != -1)
        {
            ProductionCompany pc = this.productionCompanyService.findEntityById(dto.getIdProductCompany());
            entity.setProductionCompanyIdproductionCompany(pc);
        } else{
            entity.setProductionCompanyIdproductionCompany(null);
        }

        crewRepository.save(entity);
    }

}