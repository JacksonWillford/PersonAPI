package com.yackson.personapi.model;

import com.yackson.personapi.model.input.CityInput;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class City {
    private String id;
    private String name;
    private double latitude;
    private double longitude;
    private int population;
    private TimeZone timeZone;

    public City(String id, String name, String latitude, String longitude, String population)
    {
        this.id = id;
        this.name = name;
        this.latitude = Double.parseDouble(latitude);
        this.longitude = Double.parseDouble(longitude);
        this.population = Integer.parseInt(population);
    }

    protected static City getNew(CityInput cityInput) {
        return new City(
                cityInput.getId(),
                cityInput.getName(),
                cityInput.getLatitude(),
                cityInput.getLongitude(),
                cityInput.getPopulation()
        );
    }
}
