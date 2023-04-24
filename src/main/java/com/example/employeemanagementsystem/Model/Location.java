package com.example.employeemanagementsystem.Model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Location {
    private Integer locationId;
    private String locationName;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
