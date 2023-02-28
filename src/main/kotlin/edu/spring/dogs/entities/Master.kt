package edu.spring.dogs.entities

import jakarta.persistence.*

@Entity
open class Master() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id: Int=0

    @Column(length = 30)
    open var firstname:String? = null

    @Column(length = 30)
    open var lastname:String? = null

    @OneToMany(mappedBy = "master", fetch = FetchType.EAGER, cascade = [CascadeType.DETACH , CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH])
    open val dogs= mutableSetOf<Dog>()

    constructor(firstname: String?, lastname: String?): this(){
        this.firstname = firstname
        this.lastname = lastname
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