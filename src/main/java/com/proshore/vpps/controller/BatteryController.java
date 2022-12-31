package com.proshore.vpps.controller;

import com.proshore.vpps.dto.BatteryDTO;
import com.proshore.vpps.dto.BatteryResponseDTO;
import com.proshore.vpps.dto.MessageResponseDTO;
import com.proshore.vpps.service.BatteryService;
import com.proshore.vpps.utils.APIConstant;
import com.proshore.vpps.utils.SuccessMessage;
import com.proshore.vpps.utils.UrlConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(UrlConstant.API_VERSION + APIConstant.BATTERY_API)
@Validated
public class BatteryController {

    private final BatteryService batteryService;

    public BatteryController(BatteryService batteryService) {
        this.batteryService = batteryService;
    }

    @PostMapping
    public ResponseEntity<MessageResponseDTO> addBatteries(@RequestBody @Valid List<BatteryDTO> batteries) {
        batteryService.addBatteries(batteries);
        return new ResponseEntity<>(new MessageResponseDTO(SuccessMessage.BATTERY_ADDED), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<BatteryResponseDTO> findAllByRange(@RequestParam int from, @RequestParam int to) {
        BatteryResponseDTO batteries = batteryService.findAllByRange(from, to);
        return new ResponseEntity<>(batteries, HttpStatus.OK);
    }
}
