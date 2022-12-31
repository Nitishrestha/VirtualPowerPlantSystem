package com.proshore.vpps.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.proshore.vpps.utils.RegExConstant;
import com.proshore.vpps.utils.ValidationMessageConstant;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Valid
public class BatteryDTO {

    @NotEmpty(message = ValidationMessageConstant.NAME_REQUIRED)
    @Pattern(regexp = RegExConstant.STRING_REGEX, message = ValidationMessageConstant.NAME_FORMAT)
    private String name;
    @NotEmpty(message = ValidationMessageConstant.POSTCODE_REQUIRED)
    @Pattern(regexp = RegExConstant.POSITIVE_INTEGER, message = ValidationMessageConstant.POSTCODE_FORMAT)
    private String postcode;
    @NotEmpty(message = ValidationMessageConstant.CAPACITY_REQUIRED)
    @Pattern(regexp = RegExConstant.POSITIVE_INTEGER, message = ValidationMessageConstant.CAPACITY_FORMAT)
    private String capacity;

    public BatteryDTO() {
    }

    public BatteryDTO(String name, String postCode, String capacity) {
        this.name = name;
        this.postcode = postCode;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
}
