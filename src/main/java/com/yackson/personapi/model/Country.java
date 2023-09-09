package com.yackson.personapi.model;

import com.yackson.personapi.model.input.CityInput;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

@Data
@AllArgsConstructor
public class Country {
    private String name;
    private String iso2;
    private List<Admin> admins = new ArrayList<>();

    protected static Country getNew(CityInput cityInput) {
        return new Country(
                getCountryNameFromIso2(cityInput.getIso2()),
                cityInput.getIso2(),
                new ArrayList<>(List.of(Admin.getNew(cityInput)))
        );
    }

    private static String getCountryNameFromIso2(String iso2) {
        return new Locale("en", iso2).getDisplayCountry();
    }

    protected boolean isCityInputAdminInCountry(CityInput cityInput) {
        return this.admins.stream()
                .map(Admin::getName)
                .toList()
                .contains(cityInput.getAdmin());
    }
}
