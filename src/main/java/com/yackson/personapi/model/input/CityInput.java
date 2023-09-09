package com.yackson.personapi.model.input;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CityInput {
    private String id;
    private String name;
    private String country;
    @SerializedName("admin1")
    private String admin;
    @SerializedName("lat")
    private String latitude;
    @SerializedName("lon")
    private String longitude;
    @SerializedName("pop")
    private String population;

    public String getIso2() {
        return this.getCountry();
    }
}
