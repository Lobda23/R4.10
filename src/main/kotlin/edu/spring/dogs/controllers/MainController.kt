package edu.spring.dogs.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("/")
class MainController {
    @RequestMapping(path = ["","index"])
    fun indexAction():String="index"

}