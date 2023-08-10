package com.bosonit.formacion.backend.trip.repository;

import com.bosonit.formacion.backend.trip.domain.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, String > {
}
