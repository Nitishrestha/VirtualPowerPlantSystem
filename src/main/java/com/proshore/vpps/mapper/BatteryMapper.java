package com.proshore.vpps.mapper;

import com.proshore.vpps.dto.BatteryDTO;
import com.proshore.vpps.entity.Battery;
import org.mapstruct.Mapper;

@Mapper
public interface BatteryMapper extends BaseMapper<Battery, BatteryDTO> {
}
