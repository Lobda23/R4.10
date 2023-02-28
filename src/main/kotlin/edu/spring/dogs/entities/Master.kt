package edu.spring.dogs.entities

import jakarta.persistence.*

@Entity
open class Master() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id: Int = 0

    @Column(length = 30)
    open var firstName:String? = null

    @Column(length = 30)
    open var lastName:String? = null

    @OneToMany(mappedBy = "master", fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    open val dogs= mutableSetOf<Dog>()

    constructor(firstName: String?, lastName: String?): this(){
        this.firstName = firstName
        this.lastName = lastName
    }


    fun addDog(dog: Dog) {
        dogs.add(dog)
    }

    fun giveUpDog(dog:Dog) {
        dogs.remove(dog)
    }

    @PreRemove
    fun preRemove(){
        for (dog in dogs){
            dogs.remove(dog)
        }
    }
}