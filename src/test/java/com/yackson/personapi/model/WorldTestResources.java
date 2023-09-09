package com.yackson.personapi.model;

import com.yackson.personapi.model.input.CityInput;

import java.util.ArrayList;
import java.util.List;

public class WorldTestResources {
    public static World getWorldWithOneEachFakeData() {
        return new World(
                new ArrayList<>(List.of(new Country(
                        "test",
                        "TT",
                        getAdminWithOneEachFakeData()
                )))
        );
    }

    private static List<Admin> getAdminWithOneEachFakeData() {
        return new ArrayList<>(List.of(new Admin(
                "testadmin",
                getCityOneFakeData()
        ))
        );
    }

    private static List<City> getCityOneFakeData() {
        return new ArrayList<>(List.of(new City(
                "testid",
                "testCityName",
                1234.12,
                123.23,
                3487,
                getFakeTimeZone()
        )));
    }

    private static TimeZone getFakeTimeZone() {
        return new TimeZone(
                "timeZoneId",
                "timeZoneName",
                "offset"
        );
    }

    private static List<Admin> getAdminTwoWithOneEachFakeData() {
        return new ArrayList<>(List.of(new Admin(
                "testadmin",
                getCityTwoFakeData()
        )));
    }

    private static List<City> getCityTwoFakeData() {
        return new ArrayList<>(List.of(new City(
                "testid2",
                "testCityName2",
                2222.22,
                1342223.23,
                222,
                getFakeTimeZone()
        )));
    }

    public static CityInput getNewFakeCityInput() {
        return new CityInput(
                "inputfake",
                "namefake",
                "TT",
                "testadmininput",
                "3434.234",
                "3434.22",
                "2348"
        );
    }
}
