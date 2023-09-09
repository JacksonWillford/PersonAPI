package com.yackson.personapi.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WorldTest {

    @Test
    void addNewTest_uniqueCity_shouldAdd() {
        // GIVEN - A city to add to the world object
        World world = WorldTestResources.getWorldWithOneEachFakeData();

        // WHEN - The city is added to the world object
        world.addNew(WorldTestResources.getNewFakeCityInput());

        // THEN - There should be two cities in the world
        int count = (int) world.getCountries().stream()
                .flatMap(country -> country.getAdmins().stream())
                .mapToLong(admin -> admin.getCities().size())
                .sum();

        Assertions.assertEquals(2, count);
    }
}
