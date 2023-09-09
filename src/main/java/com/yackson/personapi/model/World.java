package com.yackson.personapi.model;

import com.yackson.personapi.model.input.CityInput;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class World {
    List<Country> countries = new ArrayList<>();

    public void addNew(CityInput cityInput) {
        if (!isCityInputIso2InWorld(cityInput)) {
            this.countries.add(Country.getNew(cityInput));
        } else {
            Country country = getCountryByIso2(cityInput);
            if (!country.isCityInputAdminInCountry(cityInput)) {
                country.getAdmins().add(Admin.getNew(cityInput));
            } else {
                Admin admin = getAdminByName(cityInput, country);
                admin.getCities().add(City.getNew(cityInput));
            }
        }
    }

    private Admin getAdminByName(CityInput cityInput, Country country) {
        return country.getAdmins().stream()
                .filter(a -> a.getName().equals(cityInput.getAdmin()))
                .findFirst()
                .orElseThrow();
    }

    private Country getCountryByIso2(CityInput cityInput) {
        return this.countries.stream()
                .filter(c -> c.getIso2().equals(cityInput.getIso2()))
                .findFirst()
                .orElseThrow();
    }

    private boolean isCityInputIso2InWorld(CityInput cityInput) {
        return this.countries.stream()
                .map(Country::getIso2)
                .toList()
                .contains(cityInput.getCountry());
    }
}
