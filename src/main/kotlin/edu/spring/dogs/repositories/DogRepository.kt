package edu.spring.dogs.repositories

import org.springframework.stereotype.Repository
import edu.spring.dogs.entities.Dog
import edu.spring.dogs.entities.Master
import org.springframework.data.repository.CrudRepository

@Repository
interface DogRepository:CrudRepository<Dog, Int>{

    fun findByMasterIsNull(): List<Dog>

    fun findByNameAndMasterId(s: String, s1: Int): Dog ?

}
