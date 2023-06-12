package com.cybersolution.fazeal.logistics.dto;

import com.cybersolution.fazeal.logistics.constants.AppConstants;
import com.cybersolution.fazeal.logistics.constants.DrivingMethod;
import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class VehicleDetailsDTO {

    @NotNull(message = AppConstants.USER_ID_REQUIRED)
    private Long userId;
    private String type;
    private String licensePlateNumber;
    private String brand;
    private String modelName;
    private String capacity;
    private String fuelType;
    private String mileAge;
    private String year;
    private String status;
    @NotNull(message = AppConstants.DRIVING_METHOD_REQUIRED)
    private DrivingMethod drivingMethod;
    private String color;

}
