package es.uma.taw.sceneit.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MovieDTO {
    private Integer id = -1;
    private String title ="";
    private String homePage="";
    private String originalTitle="";
    private String overview="";
    private Integer popularity=-1;
    private String date= "";
    private Integer revenue = 0;
    private Integer runtime;
    private String status = "";
    private String tagline = "";
    private Double voteAverage = 0.0;
    private Integer voteCount = 0;
    private String image = "";
    private List<Integer> casts = new ArrayList<>();
    private List<Integer> crews = new ArrayList<>();
    private List<Integer> genders = new ArrayList<>();
    private List<String> nombreGenders = new ArrayList<>();
    private List<Integer> keywords = new ArrayList<>();
    private List<String> nKeywords = new ArrayList<>();
    private List<Integer> languages = new ArrayList<>();
    private List<String> nLanguages = new ArrayList<>();
    private List<Integer> productionCompanies = new ArrayList<>();
    private List<String> nPrCompanies = new ArrayList<>();
    private List<Integer> productionCountries = new ArrayList<>();
    private List<String> nPrCountries = new ArrayList<>();

    private List<Integer> interactions = new ArrayList<>();
}
