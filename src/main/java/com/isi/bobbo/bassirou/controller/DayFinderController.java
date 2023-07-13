package com.isi.bobbo.bassirou.controller;

import com.isi.bobbo.bassirou.classe.DayResponse;
import com.isi.bobbo.bassirou.classe.HistoricalDayResponse;
import com.isi.bobbo.bassirou.entity.Day;
import com.isi.bobbo.bassirou.entity.SearchItem;
import com.isi.bobbo.bassirou.repository.Dayrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/dayfinder")
public class DayFinderController {


    @Autowired
    private Dayrepository dayRepository; // Interface pour interagir avec la base de données

    @GetMapping
    public DayResponse getDayOfWeek(@RequestParam("date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        // Récupérer le jour de la semaine
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        // Enregistrer la date dans la base de données
        Day day = new Day(date);
        // Enregistrer la date de recherche dans la base de données
        day.setSearchDate(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()));

        // Créer la réponse JSON spécifique au jour sans les propriétés searchDate et searchItems
        DayResponse response = new DayResponse();
        response.setDate(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        response.setDayOfWeek(dayOfWeek.getDisplayName(TextStyle.FULL, Locale.FRANCE));

        // Créer l'objet SearchItem pour les informations de recherche et de réponse
        SearchItem searchItem = new SearchItem();
        searchItem.setRequest(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        searchItem.setResponseDate(response.getDate());
        searchItem.setResponseDay(response.getDayOfWeek());

        // Ajouter l'objet SearchItem à la liste des SearchItem de la journée actuelle
        day.getSearchItems().add(searchItem);

        dayRepository.save(day);





        return response;
    }

    @GetMapping("/historique")
    public List<DayResponse> getHistorique() {
        List<Day> days = dayRepository.findAll();
        List<DayResponse> responses = new ArrayList<>();

        for (Day day : days) {
            DayResponse response = new DayResponse();
            response.setId(day.getId());
            response.setSearchDate(day.getSearchDate());
            response.setSearchItems(day.getSearchItems());

            responses.add(response);
        }

        return responses;
    }
}

