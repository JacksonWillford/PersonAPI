package com.yackson.personapi.model.input;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CityInput {
    private String id;
    private String name;
    private String country;
    @SerializedName("admin1")
    private String admin;
    @SerializedName("lat")
    private double latitude;
    @SerializedName("long")
    private double longitude;
    @SerializedName("pop")
    private BigDecimal population;
}
