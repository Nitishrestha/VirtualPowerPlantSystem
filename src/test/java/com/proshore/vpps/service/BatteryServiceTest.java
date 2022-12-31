package com.proshore.vpps.service;

import com.proshore.vpps.dto.BatteryDTO;
import com.proshore.vpps.dto.BatteryResponseDTO;
import com.proshore.vpps.entity.Battery;
import com.proshore.vpps.exceptionHandler.customException.EmptyListException;
import com.proshore.vpps.mapper.BatteryMapper;
import com.proshore.vpps.repository.BatteryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;


@RunWith(MockitoJUnitRunner.class)
class BatteryServiceTest {

    private BatteryDTO batteryDTO;
    private Battery battery;

    @InjectMocks
    private BatteryServiceImpl batteryService;

    @Mock
    private BatteryRepository batteryRepository;

    @Mock
    private BatteryMapper batteryMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        batteryDTO = new BatteryDTO("Battery", "1000", "2000");
        battery = new Battery("Battery", 12345, 2000);
    }

    @Test
    void shouldAddBatteries() {
        Mockito.when(batteryMapper.mapToEntity(batteryDTO)).thenReturn(battery);
        Mockito.when(batteryRepository.save(battery)).thenReturn(battery);
        List<BatteryDTO> batteryDTOs = batteryService.addBatteries(List.of(batteryDTO));
        BatteryDTO result = batteryDTOs.get(0);
        Assertions.assertEquals(batteryDTO.getName(), result.getName());
        Assertions.assertEquals(batteryDTO.getCapacity(), result.getCapacity());
        Assertions.assertEquals(batteryDTO.getPostcode(), result.getPostcode());
    }

    @Test
    void shouldFindAllBatteriesWithSummary() {
        Mockito.when(batteryRepository.findByPostCodeBetween(500, 1000)).thenReturn(List.of(battery));
        Mockito.when(batteryMapper.mapToDTOs(List.of(battery))).thenReturn(List.of(batteryDTO));
        BatteryResponseDTO batteryResponseDTO = batteryService.findAllByRange(500, 1000);
        Integer totalCapacity = Stream.of(batteryDTO).map(BatteryDTO::getCapacity).mapToInt(Integer::valueOf).sum();
        Double avgCapacity = Stream.of(batteryDTO).map(BatteryDTO::getCapacity).mapToDouble(Double::valueOf)
                .average().orElse(0.0D);
        Assertions.assertEquals(totalCapacity, batteryResponseDTO.getTotalCapacity());
        Assertions.assertEquals(avgCapacity, batteryResponseDTO.getAverageCapacity());
        Assertions.assertEquals(1, batteryResponseDTO.getTotalBatteries());
    }

    @Test
    void shouldReturnEmptyListWhenPostcodeIsOutOfRange() {
        Mockito.when(batteryRepository.findByPostCodeBetween(500, 1000)).thenReturn(Collections.emptyList());
        BatteryResponseDTO batteryResponseDTO = batteryService.findAllByRange(500, 1000);
        Assertions.assertEquals(0, batteryResponseDTO.getTotalCapacity());
        Assertions.assertEquals(0, batteryResponseDTO.getAverageCapacity());
        Assertions.assertEquals(0, batteryResponseDTO.getTotalBatteries());
    }

    @Test
    void shouldThrowExceptionWhenEmptyBatteryListAdded() {
        Assertions.assertThrows(EmptyListException.class, () -> {
            batteryService.addBatteries(Collections.emptyList());
        });
    }

}