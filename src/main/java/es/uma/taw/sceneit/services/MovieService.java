package es.uma.taw.sceneit.services;

import es.uma.taw.sceneit.dao.*;

import es.uma.taw.sceneit.dto.MovieDTO;
import es.uma.taw.sceneit.entity.*;
import es.uma.taw.sceneit.ui.FiltroPeliculas;
import es.uma.taw.sceneit.ui.FiltroStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class MovieService extends ServiceParent<Movie, MovieDTO>{
    @Autowired
    protected MovieRepository movieRepository;
    @Autowired
    protected CastRepository castRepository;
    @Autowired
    protected CrewRepository crewRepository;
    @Autowired
    protected GenderRepository genreRepository;
    @Autowired
    protected LanguageRepository languageRepository;
    @Autowired
    protected ProductionCompanyRepository productionCompanyRepository;
    @Autowired
    protected ProductionCountryRepository productionCountryRepository;
    @Autowired
    protected KeywordRepository keywordRepository;



    public List<MovieDTO> getAllUsersAsDTO() {
        List<Movie> language = movieRepository.findAll();
        return convertToDTOList(language);
    }

    public void deleteById(Integer id) {this.movieRepository.deleteById(id);}

    public MovieDTO getById(Integer id) {
        return this.movieRepository.findById(id).map(Movie::toDTO).orElse(new MovieDTO());
    }

    public List<MovieDTO> filtrar(FiltroPeliculas filtro) {
        return convertToDTOList(this.movieRepository.buscarPorFiltros(filtro.getNombre(), filtro.getGenre(), filtro.getLanguages(), filtro.getLanguages().size()));
    }

    public List<MovieDTO> filtrarStatistics(FiltroStatistics filtroStatistics) {
        return convertToDTOList(this.movieRepository.buscarPorFiltros(filtroStatistics.getNombre(), filtroStatistics.getGenre(), filtroStatistics.getLanguages(), filtroStatistics.getLanguages().size()));
    }

    public void save(MovieDTO movieDTO) {
        Movie movie = this.movieRepository.findById(movieDTO.getId()).orElse(new Movie());
        if( !movieDTO.getDate().isEmpty()){
            String[] fecha = movieDTO.getDate().split("-");
            movie.setDate(LocalDate.of(Integer.parseInt(fecha[0]), Integer.parseInt(fecha[1]), Integer.parseInt(fecha[2])));

        }else{
            movie.setDate(LocalDate.now());
        }
        movie.setHomePage(movieDTO.getHomePage());
        movie.setTitle(movieDTO.getTitle());
        movie.setImage(movieDTO.getImage());
        movie.setOriginalTitle(movieDTO.getOriginalTitle());
        movie.setOverview(movieDTO.getOverview());
        movie.setRevenue(movieDTO.getRevenue());
        movie.setRuntime(movieDTO.getRuntime());
        movie.setStatus(movieDTO.getStatus());
        movie.setTagline(movieDTO.getTagline());

        List<Cast> casts =  this.castRepository.findAllById(movieDTO.getCasts());
        List<Crew> crews = this.crewRepository.findAllById(movieDTO.getCrews());
        List<Language> languages = this.languageRepository.findAllById(movieDTO.getLanguages());
        List<Keyword> keywords = this.keywordRepository.findAllById(movieDTO.getKeywords());
        List<Gender> genders = this.genreRepository.findAllById(movieDTO.getGenders());
        List<ProductionCompany> companiesList = this.productionCompanyRepository.findAllById(movieDTO.getProductionCompanies());

        List<ProductionCountry> countriesList = this.productionCountryRepository.findAllById(movieDTO.getProductionCountries());

        movie.setProductionCompanies(companiesList);
        movie.setProductionCountries(countriesList);
        movie.setKeywords(keywords);
        movie.setGenders(genders);
        movie.setLanguages(languages);
        movie.setCrews(crews);
        for(Cast cast : casts){
            cast.setMovieIdmovie(movie);
        }

        this.movieRepository.save(movie);
    }

}
