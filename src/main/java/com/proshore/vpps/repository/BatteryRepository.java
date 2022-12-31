package com.proshore.vpps.repository;

import com.proshore.vpps.entity.Battery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BatteryRepository extends JpaRepository<Battery, Long> {

    @Query("SELECT b FROM Battery b WHERE b.postcode BETWEEN :from AND :to")
    List<Battery> findByPostCodeBetween(Integer from, Integer to);


}
