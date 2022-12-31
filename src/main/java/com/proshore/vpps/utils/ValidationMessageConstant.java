package com.proshore.vpps.utils;

public final class ValidationMessageConstant {

    private ValidationMessageConstant() {
    }

    public static final String NAME_REQUIRED = "Required: Name is required";
    public static final String POSTCODE_REQUIRED = "Required: Postcode is required";
    public static final String CAPACITY_REQUIRED = "Required: Capacity is required";
    public static final String NAME_FORMAT = "Name should contain only characters";
    public static final String POSTCODE_FORMAT = "Postcode should contain only positive numerics";
    public static final String CAPACITY_FORMAT = "Capacity should contain only positive numerics";
    public static final String EMPTY_LIST_ERROR_MESSAGE = "Battery list is empty";
}
