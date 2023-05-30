package com.nestorsgarzonc.exposerjavaint;

import io.spring.guides.gs_producing_web_service.Country;
import io.spring.guides.gs_producing_web_service.Currency;
import io.spring.guides.gs_producing_web_service.Venue;
import jakarta.annotation.PostConstruct;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

@Component
public class CountryRepository {
    private static final Map<String, Country> countries = new HashMap<>();

    @PostConstruct
    public void initData() {
        Country spain = new Country();
        spain.setName("Spain");
        spain.setCapital("Madrid");
        spain.setCurrency(Currency.EUR);
        spain.setPopulation(46704314);

        countries.put(spain.getName(), spain);

        Country poland = new Country();
        poland.setName("Poland");
        poland.setCapital("Warsaw");
        poland.setCurrency(Currency.PLN);
        poland.setPopulation(38186860);

        countries.put(poland.getName(), poland);

        Country uk = new Country();
        uk.setName("United Kingdom");
        uk.setCapital("London");
        uk.setCurrency(Currency.GBP);
        uk.setPopulation(63705000);

        countries.put(uk.getName(), uk);
    }

    public Country findCountry(String name) {
        Assert.notNull(name, "The country's name must not be null");
        return countries.get(name);
    }
    
    public ArrayList<Country> countries() {
        ArrayList<Country> listCountries = new ArrayList();
        countries.forEach((key, value) -> {
            listCountries.add(value);
        });
        return listCountries;
    }

    public ArrayList<Venue> venues() {
        ArrayList<Venue> venues = new ArrayList();
        String url = "https://api-gateway-apollo-4yiv26znhq-uc.a.run.app/graphql";
        Map<String, Object> map = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();
        map.put("query","query {getVenues { id location description isActive }}");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
        ResponseEntity<Data> response =restTemplate.postForEntity(url, entity, Data.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            System.out.println("ERROR ");
            System.out.println(response);
            return venues;
        }
        System.out.println(response.getBody().getData().getGetVenues());
        for (GetVenues venue :  response.getBody().getData().getGetVenues())
        {
            Venue newVenue = new Venue();
            newVenue.setDescription(venue.getDescription());
            newVenue.setId(venue.getId());
            newVenue.setLocation(venue.getLocation());
            newVenue.setIsActive(venue.getActive());
            venues.add(newVenue);
        }
        return venues;
    }

}