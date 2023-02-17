package edu.spring.td2.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class IndexController {
    @RequestMapping(path = ["",""])
    fun indexAction(model: ModelMap):String{
        return "index"
    }
}