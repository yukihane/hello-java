package com.example.spring_thymeleaf_validation.web.controller

import com.example.spring_thymeleaf_validation.business.entities.*
import com.example.spring_thymeleaf_validation.business.services.SeedStarterService
import com.example.spring_thymeleaf_validation.business.services.VarietyService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import java.util.*

@Controller
class SeedStarterMngController(
    private val varietyService: VarietyService,
    private val seedStarterService: SeedStarterService,
) {


    @ModelAttribute("allTypes")
    fun populateTypes(): List<Type> {
        return Type.ALL.toList()
    }

    @ModelAttribute("allFeatures")
    fun populateFeatures(): List<Feature> {
        return Feature.ALL.toList()
    }

    @ModelAttribute("allVarieties")
    fun populateVarieties(): List<Variety> {
        return varietyService.findAll()
    }

    @ModelAttribute("allSeedStarters")
    fun populateSeedStarters(): List<SeedStarter> {
        return seedStarterService.findAll()
    }


    @RequestMapping(value = ["/", "/seedstartermng"])
    fun showSeedstarters(seedStarter: SeedStarter): String {
        seedStarter.datePlanted = Calendar.getInstance().time
        return "seedstartermng"
    }


    @RequestMapping(value = ["/seedstartermng"], params = ["save"])
    fun saveSeedstarter(seedStarter: SeedStarter, bindingResult: BindingResult, model: ModelMap): String {
        if (bindingResult.hasErrors()) {
            return "seedstartermng"
        }
        seedStarterService.add(seedStarter)
        model.clear()
        return "redirect:/seedstartermng"
    }


    @RequestMapping(value = ["/seedstartermng"], params = ["addRow"])
    fun addRow(seedStarter: SeedStarter, bindingResult: BindingResult): String {
        seedStarter.rows.add(Row())
        return "seedstartermng"
    }


    @RequestMapping(value = ["/seedstartermng"], params = ["removeRow"])
    fun removeRow(seedStarter: SeedStarter, bindingResult: BindingResult, req: HttpServletRequest): String {
        val rowId = req.getParameter("removeRow").toInt()
        seedStarter.rows.removeAt(rowId)
        return "seedstartermng"
    }
}
