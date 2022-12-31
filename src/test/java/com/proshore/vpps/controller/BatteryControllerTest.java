package com.proshore.vpps.controller;

import com.proshore.vpps.dto.BatteryDTO;
import com.proshore.vpps.dto.BatteryResponseDTO;
import com.proshore.vpps.service.BatteryService;
import com.proshore.vpps.utils.APIConstant;
import com.proshore.vpps.utils.SuccessMessage;
import com.proshore.vpps.utils.UrlConstant;
import com.proshore.vpps.utils.ValidationMessageConstant;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(BatteryController.class)
public class BatteryControllerTest {

    @MockBean
    private BatteryService batteryService;

    @Autowired
    private MockMvc mockMvc;

    private final String batteryEndPoint = UrlConstant.API_VERSION + APIConstant.BATTERY_API;

    private BatteryDTO batteryDto;
    private List<BatteryDTO> batteryDtos;
    private BatteryResponseDTO batteryResponseDTO;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.mockMvc(mockMvc);
        batteryDto = new BatteryDTO("battery", "12345", "2000");
        batteryDtos = List.of(batteryDto);
        batteryResponseDTO = new BatteryResponseDTO(1, 2000, 2000D, batteryDtos);
    }

    @Test
    void shouldAddBattery() {
        Mockito.when(batteryService.addBatteries(any(List.class))).thenReturn(batteryDtos);
        RestAssuredMockMvc
                .given()
                .contentType("application/json")
                .body(batteryDtos)
                .when()
                .post(batteryEndPoint)
                .then()
                .statusCode(200)
                .expect(jsonPath("$.message", is(SuccessMessage.BATTERY_ADDED)));
    }

    @Test
    void shouldThrowErrorWhenBatteryNameIsInvalid() {
        batteryDto.setName("Battery 1234");
        Mockito.when(batteryService.addBatteries(any(List.class))).thenReturn(List.of(batteryDto));
        RestAssuredMockMvc
                .given()
                .contentType("application/json")
                .body(batteryDtos)
                .when()
                .post(batteryEndPoint)
                .then()
                .statusCode(400)
                .expect(jsonPath("$.errors[0]", is(ValidationMessageConstant.NAME_FORMAT)));
    }

    @Test
    void shouldThrowErrorWhenBatteryPostCodeIsInvalid() {
        batteryDto.setPostcode("B1234");
        Mockito.when(batteryService.addBatteries(any(List.class))).thenReturn(List.of(batteryDto));
        RestAssuredMockMvc
                .given()
                .contentType("application/json")
                .body(batteryDtos)
                .when()
                .post(batteryEndPoint)
                .then()
                .statusCode(400)
                .expect(jsonPath("$.errors[0]", is(ValidationMessageConstant.POSTCODE_FORMAT)));
    }

    @Test
    void shouldThrowErrorWhenBatteryCapacityIsNull() {
        batteryDto.setCapacity(null);
        Mockito.when(batteryService.addBatteries(any(List.class))).thenReturn(List.of(batteryDto));
        RestAssuredMockMvc
                .given()
                .contentType("application/json")
                .body(batteryDtos)
                .when()
                .post(batteryEndPoint)
                .then()
                .statusCode(400)
                .expect(jsonPath("$.errors[0]", is(ValidationMessageConstant.CAPACITY_REQUIRED)));
    }

    @Test
    void shouldThrowErrorWhenRequiredFieldIsMissing() {
        batteryDto = new BatteryDTO();
        batteryDto.setName("batteryName");
        batteryDto.setPostcode("1234");
        batteryDtos = List.of(batteryDto);
        Mockito.when(batteryService.addBatteries(any(List.class))).thenReturn(List.of(batteryDto));
        RestAssuredMockMvc
                .given()
                .contentType("application/json")
                .body(batteryDtos)
                .when()
                .post(batteryEndPoint)
                .then()
                .statusCode(400)
                .expect(jsonPath("$.errors[0]", is(ValidationMessageConstant.CAPACITY_REQUIRED)));
    }

    @Test
    void shouldGeAllBatteries() {
        Mockito.when(batteryService.findAllByRange(anyInt(), anyInt())).thenReturn(batteryResponseDTO);
        RestAssuredMockMvc
                .given()
                .contentType("application/json")
                .param("from", 2000)
                .param("to", 2500)
                .when()
                .get(batteryEndPoint)
                .then()
                .statusCode(200)
                .expect(jsonPath("$.totalBatteries", is(1)))
                .expect(jsonPath("$.totalCapacity", is(2000)))
                .expect(jsonPath("$.averageCapacity", is(2000D)))
                .expect(jsonPath("$.batteryDTOs.size()", is(1)));
    }
}
