package com.bosonit.formacion.backend.passenger.repository;

import com.bosonit.formacion.backend.passenger.domain.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PassengerRepository extends JpaRepository<Passenger, String>, PagingAndSortingRepository<Passenger, String> {

}
