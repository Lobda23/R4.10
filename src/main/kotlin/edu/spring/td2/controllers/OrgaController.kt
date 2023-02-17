package edu.spring.td2.controllers

import edu.spring.td2.entities.Organization
import edu.spring.td2.entities.User
import edu.spring.td2.exceptions.ElementNotFoundException
import edu.spring.td2.repositories.OrganizationRepository
import edu.spring.td2.services.OrgaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.ModelAndViewDefiningException
import org.springframework.web.servlet.view.RedirectView
import javax.lang.model.element.Element

@Controller
@RequestMapping("/orgas/")
class OrgaController {
    @Autowired
    lateinit var orgaRespository: OrganizationRepository

    @Autowired
    lateinit var orgaService: OrgaService

    @RequestMapping(path = ["","index"])
    fun indexAction(model:ModelMap):String{
        model["orgas"]=orgaRespository.findAll()
        return "/orgas/index"
    }

    @GetMapping("/new")
    fun newAction(model:ModelMap):String{
        model["orga"]=Organization()
        return "/orgas/form"
    }

    @PostMapping("/new")
    fun submitNewAction(
            @ModelAttribute orga:Organization,
            @ModelAttribute("users") users:String
    ):RedirectView{
        orgaService.addUsersFromString(users, orga)
        orgaRespository.save(orga)
        return RedirectView("/orgas/")
    }

    @GetMapping("/delete/{id}")
    fun deleteAction(@PathVariable id:Int):RedirectView{
        orgaRespository.deleteById(id)
        return RedirectView("/orgas/")
    }

    @GetMapping("/edit/{id}")
    fun editAction(model:ModelMap, @PathVariable id: Int):String{
        val orga = orgaRespository.findById(id)
        if (orga.isPresent){
            model["orga"]=orga.get()
        } else {
            model["orga"]=Organization()
        }
        return "/orgas/edit";
    }

    @GetMapping("/details/{id}")
    fun viewDetails (model: ModelMap, @PathVariable id: Int): String{
        val orga = orgaRespository.findById(id)
        if (orga.isPresent){
            model["orga"]=orga.get()
        } else {
            return throw ElementNotFoundException("t nul")
        }
        return "/orgas/details"
    }

    @ExceptionHandler
    fun errorHandlerAction(ex:Exception): ModelAndView{
        val msg=ModelAndView("/main/error")
        msg.addObject("msg", ex.message)
        msg.addObject("title", "Element non trouvé")
        return msg
    }

}