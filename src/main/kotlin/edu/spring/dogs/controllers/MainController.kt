package edu.spring.dogs.controllers

import edu.spring.dogs.entities.Dog
import edu.spring.dogs.entities.Master
import edu.spring.dogs.repositories.DogRepository
import edu.spring.dogs.repositories.MasterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.view.RedirectView
import java.net.IDN


@Controller
class MainController {

    @Autowired
    lateinit var masterRepository: MasterRepository

    @Autowired
    lateinit var dogRepository: DogRepository

    @RequestMapping("/")
    fun indexAction(model: ModelMap):String{
        model["masters"] = masterRepository.findAll()
        model["dogs"] = dogRepository.findByMasterIsNull()
        return "index"
    }

    @PostMapping("/master/add")
    fun submitNewAction(@ModelAttribute master: Master): RedirectView {
        masterRepository.save(master)
        return RedirectView("/")
    }

    @PostMapping("/master/{id}/dog")
    fun addOrRemoveDog(@ModelAttribute("dog_name") nom: String, @ModelAttribute("dog-action") button :String, @PathVariable id: Int) : RedirectView {
        if (button == "add") {
            var dog = dogRepository.save(Dog(nom))
            var master = masterRepository.findById(id).get()
            master.addDog(dog)
            dogRepository.save(dog)
        }
        if (button == "giveup"){
            var master = masterRepository.findById(id).get()
            var dog = dogRepository.findByNameAndMasterId(nom, id)
            master.giveUpDog(dog)
            dogRepository.save(dog)
        }
        return RedirectView("/")
    }

    @PostMapping("/dog/{id}/action")
    fun addNewMaster(@ModelAttribute("") nom: String, @ModelAttribute("dog-action") button :String, @PathVariable id: Int) : RedirectView {
        if (button == "add") {
            var dog = dogRepository.save(Dog(nom))
            var master = masterRepository.findById(id).get()
            master.addDog(dog)
            dogRepository.save(dog)
        }
        if (button == "giveup"){
            var master = masterRepository.findById(id).get()
            var dog = dogRepository.findByNameAndMasterId(nom, id)
            master.giveUpDog(dog)
            dogRepository.save(dog)
        }
        return RedirectView("/")
    }

}