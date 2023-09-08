package com.yackson.personapi.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.yackson.personapi.model.World;
import com.yackson.personapi.model.input.CityInput;
import com.yackson.personapi.model.input.WorldInput;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@UtilityClass
public class FileUtil {
    private static final String INPUT_PATH = "src\\main\\resources\\cities\\cities500.json";
    private static final String OUTPUT_PATH = "output-path";
    private static final Path PATH = Path.of(OUTPUT_PATH);

    public static void saveWorldOutput(World world) {
        tryCreateOutputDirectory();
        String outputFileName = getNewFileName();
        try (Writer writer = new FileWriter(Path.of(OUTPUT_PATH, outputFileName).toString(), StandardCharsets.UTF_8)) {
            log.info("Attempting to save {} to {}", outputFileName, OUTPUT_PATH);
            new GsonBuilder().setPrettyPrinting().create().toJson(world, writer);
            log.info("Successfully saved {} to {}", outputFileName, OUTPUT_PATH);
        } catch (IOException e) {
            log.error("Failed to save the output file to {}", OUTPUT_PATH);
            e.printStackTrace();
        }
    }

    public static WorldInput loadWorldInput() {
        try {
            log.info("Attempting to load the input file at {}", INPUT_PATH);
            WorldInput worldInput = new WorldInput(Arrays.asList(new Gson().fromJson(new FileReader(INPUT_PATH), CityInput[].class)));
            log.info("Successfully loaded the input file");
            return worldInput;
        } catch (FileNotFoundException e) {
            log.error("Could not find the input file at {}", INPUT_PATH);
        } catch (JsonParseException e) {
            log.error("Failed to load the input file at {} due to a JSON syntax issue!", INPUT_PATH);
        }
        throw new IllegalStateException("There was an error loading the input file.");
    }

    private static void tryCreateOutputDirectory() {
        try {
            if (!Files.exists(PATH)) {
                log.info("No output directory exists, attempting to create one at {}", OUTPUT_PATH);
                Files.createDirectory(PATH);
                log.info("Output directory created at {}", OUTPUT_PATH);
            } else {
                log.info("Output files will be saved at {}", OUTPUT_PATH);
            }
        } catch (IOException e) {
            log.error("Failed to create the output directory at {}", OUTPUT_PATH);
        }
    }

    private static String getNewFileName() {
        return String.format("world-%s.json", LocalDateTime.now().toString().replace(':', '-'));
    }
}
