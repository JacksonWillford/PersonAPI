package com.yackson.personapi.service.impl;

import com.yackson.personapi.model.City;
import com.yackson.personapi.model.TimeZone;
import com.yackson.personapi.model.World;
import com.yackson.personapi.model.input.WorldInput;
import com.yackson.personapi.service.IWorldService;
import com.yackson.personapi.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import net.iakovlev.timeshape.TimeZoneEngine;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class WorldServiceImpl implements IWorldService {
    private WorldInput worldInput;
    private World world = new World();

    public WorldServiceImpl() {
        buildFile();
    }

    private void buildFile() {
        this.worldInput = FileUtil.loadWorldInput();
        log.info("Building the output file");
        worldInput.getCityInputs().forEach(world::addNew);

        mapTimeZones();

        FileUtil.saveWorldOutput(world);
    }

    private void mapTimeZones() {
        TimeZoneEngine timeZoneEngine = TimeZoneEngine.initialize();
        List<City> cities = world.getCountries().stream()
                .flatMap(country -> country.getAdmins().stream())
                .flatMap(admin -> admin.getCities().stream())
                .sorted(Comparator.comparing(City::getName))
                .toList();

        int total = cities.size();

        AtomicInteger progress = new AtomicInteger(0);

        cities.parallelStream().forEach(city -> {
            timeZoneEngine.query(city.getLatitude(), city.getLongitude()).ifPresent(zoneId -> city.setTimeZone(deriveTimeZone(zoneId)));
            progress.incrementAndGet();
            log.info("{}% complete", Math.round(((double) progress.get() / total) * 10000) / 100.0);
        });
    }

    private TimeZone deriveTimeZone(ZoneId zoneId) {
        return new TimeZone(
                zoneId.getId(),
                zoneId.getDisplayName(TextStyle.FULL, Locale.US),
                "UTC" + zoneId.getRules().getOffset(Instant.now()).getId()
        );
    }
}
