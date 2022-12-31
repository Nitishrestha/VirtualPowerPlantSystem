package com.proshore.vpps.service;

import com.proshore.vpps.dto.BatteryDTO;
import com.proshore.vpps.dto.BatteryResponseDTO;

import java.util.List;

public interface BatteryService {

    List<BatteryDTO> addBatteries(List<BatteryDTO> batteries);

    BatteryResponseDTO findAllByRange(int from, int to);
}
