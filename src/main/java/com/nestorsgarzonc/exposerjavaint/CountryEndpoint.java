package com.nestorsgarzonc.exposerjavaint;

import io.spring.guides.gs_producing_web_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;


@Endpoint
public class CountryEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private CountryRepository countryRepository;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountriesRequest")
    @ResponsePayload
    public GetCountriesResponse getCountries(@RequestPayload GetCountriesRequest request) {
        GetCountriesResponse response = new GetCountriesResponse();
        ArrayList<Country> countries= (ArrayList<Country>) response.getCountry();
        countries.addAll(countryRepository.countries());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getVenuesRequest")
    @ResponsePayload
    public GetVenuesResponse getVenues(@RequestPayload GetVenuesRequest request) {
        GetVenuesResponse response = new GetVenuesResponse();
        ArrayList<Venue> venues= (ArrayList<Venue>) response.getVenue();
        venues.addAll(countryRepository.venues());
        return response;
    }
}