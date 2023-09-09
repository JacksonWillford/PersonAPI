package com.yackson.personapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TimeZone {
    private String id;
    private String name;
    private String offset;
}
