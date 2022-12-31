package com.proshore.vpps.dto;

import java.util.List;

public class BatteryResponseDTO {

    private int totalBatteries;
    private int totalCapacity;
    private Double averageCapacity;
    private List<BatteryDTO> batteryDTOs;

    public BatteryResponseDTO() {
    }

    public BatteryResponseDTO(int totalBatteries, int totalCapacity, Double averageCapacity, List<BatteryDTO> batteryDTOs) {
        this.totalBatteries = totalBatteries;
        this.totalCapacity = totalCapacity;
        this.averageCapacity = averageCapacity;
        this.batteryDTOs = batteryDTOs;
    }

    public int getTotalBatteries() {
        return totalBatteries;
    }

    public void setTotalBatteries(int totalBatteries) {
        this.totalBatteries = totalBatteries;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public Double getAverageCapacity() {
        return averageCapacity;
    }

    public void setAverageCapacity(Double averageCapacity) {
        this.averageCapacity = averageCapacity;
    }

    public List<BatteryDTO> getBatteryDTOs() {
        return batteryDTOs;
    }

    public void setBatteryDTOs(List<BatteryDTO> batteryDTOs) {
        this.batteryDTOs = batteryDTOs;
    }
}
