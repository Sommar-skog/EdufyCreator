package com.example.EdufyCreator.repositories;

import com.example.EdufyCreator.models.entities.Creator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//ED-143-AA
@Repository
public interface CreatorRepository extends JpaRepository<Creator, Long> {

}
