package edu.spring.dogs.repositories

import org.springframework.stereotype.Repository
import edu.spring.dogs.entities.Dog

@Repository
interface DogRepository {

    fun findByMasterIsNull(): Any {

    }

    fun findByNameAndMasterId(s: String, id: Any): Any {

    }

}
