package edu.spring.dogs.controllers

import edu.spring.dogs.entities.Master
import edu.spring.dogs.repositories.MasterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.view.RedirectView


@Controller
class MainController {

    @Autowired
    lateinit var masterRepository: MasterRepository

    @RequestMapping("/")
    fun indexAction(model: ModelMap):String{
        model["masters"] = masterRepository.findAll()
        return "index"
    }

    @PostMapping("/master/add")
    fun submitNewAction(@ModelAttribute master: Master): RedirectView {
        masterRepository.save(master)
        return RedirectView("/")
    }

}