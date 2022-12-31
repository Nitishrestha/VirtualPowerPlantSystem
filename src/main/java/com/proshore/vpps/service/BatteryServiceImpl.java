package com.proshore.vpps.service;

import com.proshore.vpps.dto.BatteryDTO;
import com.proshore.vpps.dto.BatteryResponseDTO;
import com.proshore.vpps.entity.Battery;
import com.proshore.vpps.exceptionHandler.customException.EmptyListException;
import com.proshore.vpps.mapper.BatteryMapper;
import com.proshore.vpps.repository.BatteryRepository;
import com.proshore.vpps.utils.ValidationMessageConstant;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatteryServiceImpl implements BatteryService {

    private final BatteryMapper batteryMapper = Mappers.getMapper(BatteryMapper.class);
    private final BatteryRepository batteryRepository;

    public BatteryServiceImpl(BatteryRepository batteryRepository) {
        this.batteryRepository = batteryRepository;
    }

    @Override
    public List<BatteryDTO> addBatteries(List<BatteryDTO> batteries) {
        if (batteries.isEmpty()) {
            throw new EmptyListException(ValidationMessageConstant.EMPTY_LIST_ERROR_MESSAGE);
        }
        List<Battery> batteryList = batteryMapper.mapToEntities(batteries);
        batteryRepository.saveAll(batteryList);
        return batteries;
    }

    @Override
    public BatteryResponseDTO findAllByRange(int from, int to) {
        List<Battery> batteries = batteryRepository.findByPostCodeBetween(from, to);
        if (batteries.isEmpty()) {
            return new BatteryResponseDTO(0, 0, 0.0, Collections.emptyList());
        }
        List<BatteryDTO> batteryDTOs = batteryMapper
                .mapToDTOs(batteries).stream()
                .sorted(Comparator.comparing(BatteryDTO::getName))
                .collect(Collectors.toList());
        double avgCapacity = batteryDTOs.stream().map(BatteryDTO::getCapacity).mapToDouble(Double::valueOf)
                .average().orElse(0.0D);
        int totalCapacity = batteryDTOs.stream().map(BatteryDTO::getCapacity).mapToInt(Integer::valueOf).sum();
        return new BatteryResponseDTO(batteryDTOs.size(), totalCapacity, avgCapacity, batteryDTOs);
    }
}
