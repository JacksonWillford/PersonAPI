package com.yackson.personapi.model;

import com.yackson.personapi.model.input.CityInput;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Admin {
    private static final String NONE = "none";
    private String name;
    List<City> cities = new ArrayList<>();

    protected static Admin getNew(CityInput cityInput) {
        return new Admin(
                getOrNone(cityInput.getAdmin()),
                new ArrayList<>(List.of(City.getNew(cityInput)))
        );
    }

    private static String getOrNone(String adminString) {
        if (adminString != null) {
            return adminString;
        }
        return NONE;
    }
}
