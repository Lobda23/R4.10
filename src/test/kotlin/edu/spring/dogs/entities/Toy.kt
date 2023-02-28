package edu.spring.dogs.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.awt.Label

@Entity
open class Toy(){

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id:Int = 0

    @Column(length = 30)
    open var type: String? = null

    @Column(length = 30)
    open lateinit var label:String

    constructor(type: String?, label: String):this(){
        this.type = type
        this.label = label
    }
}