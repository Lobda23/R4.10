package edu.spring.dogs.entities

import jakarta.persistence.*


@Entity
open class Dog(){
    @Column
    open lateinit var name:String

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id:Int = 0

    @ManyToOne
    @JoinColumn(name="idMaster", nullable = false)
    open var master: Master? = null

    @ManyToMany
    @JoinColumn(name = "dog_toys")
    open val toys= mutableSetOf<Toy>()

    constructor(name:String):this(){
        this.name=name
    }
}

