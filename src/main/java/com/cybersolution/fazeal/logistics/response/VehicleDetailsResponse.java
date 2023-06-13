package com.cybersolution.fazeal.logistics.response;

import com.cybersolution.fazeal.logistics.constants.AppConstants;
import com.cybersolution.fazeal.logistics.constants.DrivingMethod;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class VehicleDetailsResponse {

    private Long id;
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
    private DrivingMethod drivingMethod;
    private String color;
    private String active;
    private List<String> vehicleImages;

}
