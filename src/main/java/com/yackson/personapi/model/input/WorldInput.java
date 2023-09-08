package com.yackson.personapi.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class WorldInput {
    private List<CityInput> cityInputs;
}
