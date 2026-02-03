package es.uma.taw.sceneit.entity;

import es.uma.taw.sceneit.dto.MovieDTO;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "movie")
public class Movie implements Serializable, toDTO<MovieDTO>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMovie", nullable = false)
    private Integer id;

    @Column(name = "title", length = 45)
    private String title;

    @Column(name = "homePage", length = 200)
    private String homePage;

    @Column(name = "original_title", length = 45)
    private String originalTitle;

    @Column(name = "overview", length = 500)
    private String overview;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "revenue")
    private Integer revenue;

    @Column(name = "runtime")
    private Integer runtime;

    @Column(name = "status", length = 45)
    private String status;

    @Column(name = "tagline", length = 45)
    private String tagline;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "movieIdmovie")
    private List<Cast> casts = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "crew_movie",
            joinColumns = @JoinColumn(name = "Movie_idMovie"),
            inverseJoinColumns = @JoinColumn(name = "Crew_idCrew"))
    private List<Crew> crews = new ArrayList<>();

    @OneToMany(mappedBy = "movieIdmovie")
    private List<Interaction> interactions = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "movie_gender",
            joinColumns = @JoinColumn(name = "Movie_idMovie"),
            inverseJoinColumns = @JoinColumn(name = "Gender_idGender"))
    private List<Gender> genders = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "movie_keyword",
            joinColumns = @JoinColumn(name = "Movie_idMovie"),
            inverseJoinColumns = @JoinColumn(name = "Keyword_idKeyword"))
    private List<Keyword> keywords = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "movie_language",
            joinColumns = @JoinColumn(name = "Movie_idMovie"),
            inverseJoinColumns = @JoinColumn(name = "Language_idLanguage"))
    private List<Language> languages = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "movie_production_company",
            joinColumns = @JoinColumn(name = "Movie_idMovie"),
            inverseJoinColumns = @JoinColumn(name = "Production_Company_idProduction_Company"))
    private List<ProductionCompany> productionCompanies = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "movie_production_country",
            joinColumns = @JoinColumn(name = "Movie_idMovie"),
            inverseJoinColumns = @JoinColumn(name = "Production_Country_idProduction_Country"))
    private List<ProductionCountry> productionCountries = new ArrayList<>();

    @Override
    public MovieDTO toDTO() {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(this.id);
        movieDTO.setTitle(this.title);
        movieDTO.setImage(image);
        movieDTO.setDate((String.format("%04d-%02d-%02d",
                date.getYear(),
                date.getMonthValue(),
                date.getDayOfMonth())));
        movieDTO.setRevenue(this.revenue);
        movieDTO.setRuntime(this.runtime);
        movieDTO.setStatus(this.status);
        movieDTO.setTagline(this.tagline);

        int contador = 0;
        int contadorPopularidad = 0;
        Double  suma = 0.0;
        for(Interaction i : interactions){
            if(i.getRecommended() != null){
                contador++;
                suma += i.getValoration();
                if(i.getRecommended() == 1){
                    contadorPopularidad++;
                }
            }
        }

        Double media = 0.0;
        if (contador > 0) {
            media = suma / contador;  // Cast a double para obtener media decimal
        }

        movieDTO.setVoteAverage(media);


        movieDTO.setVoteCount(contador);
        movieDTO.setHomePage(homePage);
        movieDTO.setOriginalTitle(originalTitle);
        movieDTO.setOverview(overview);
        movieDTO.setPopularity(contadorPopularidad);


        List<Integer> castsDTO = new ArrayList<>();
        for(Cast cast: casts) {
            castsDTO.add(cast.getId());
        }
        movieDTO.setCasts(castsDTO);

        List<Integer> crewsDTO = new ArrayList<>();
        for(Crew crew: crews) {
            crewsDTO.add(crew.getId());
        }
        movieDTO.setCrews(crewsDTO);

        List<Integer> gendersDTO = new ArrayList<>();
        List<String> nGendersDTO = new ArrayList<>();
        for(Gender gender: genders) {
            gendersDTO.add(gender.getId());
            nGendersDTO.add(gender.getName());
        }
        movieDTO.setGenders(gendersDTO);
        movieDTO.setNombreGenders(nGendersDTO);

        List<String> nKeywordsDTO = new ArrayList<>();
        List<Integer> keywordsDTO = new ArrayList<>();
        for(Keyword keyword: keywords) {
            keywordsDTO.add(keyword.getId());
            nKeywordsDTO.add(keyword.getName());
        }
        movieDTO.setKeywords(keywordsDTO);
        movieDTO.setNKeywords(nKeywordsDTO);

        List<String> nLanguagesDTO = new ArrayList<>();
        List<Integer> languagesDTO = new ArrayList<>();
        for(Language language: languages) {
            languagesDTO.add(language.getId());
            nLanguagesDTO.add(language.getName());
        }
        movieDTO.setLanguages(languagesDTO);
        movieDTO.setNLanguages(nLanguagesDTO);

        List<String> nProductionCompaniesDTO = new ArrayList<>();
        List<Integer> productionCompaniesDTO = new ArrayList<>();
        for(ProductionCompany productionCompany: productionCompanies) {
            productionCompaniesDTO.add(productionCompany.getId());
            nProductionCompaniesDTO.add(productionCompany.getName());
        }
        movieDTO.setProductionCompanies(productionCompaniesDTO);
        movieDTO.setNPrCompanies(nProductionCompaniesDTO);

        List<String> nProductionCountriesDTO = new ArrayList<>();
        List<Integer> productionCountriesDTO = new ArrayList<>();
        for(ProductionCountry productionCountry: productionCountries) {
            productionCountriesDTO.add(productionCountry.getId());
            nProductionCountriesDTO.add(productionCountry.getName());
        }
        movieDTO.setProductionCountries(productionCountriesDTO);
        movieDTO.setNPrCountries(nProductionCountriesDTO);


        return movieDTO;
    }

}
