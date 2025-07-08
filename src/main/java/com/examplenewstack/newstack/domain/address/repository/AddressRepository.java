package com.examplenewstack.newstack.domain.address.repository;

import com.examplenewstack.newstack.domain.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
