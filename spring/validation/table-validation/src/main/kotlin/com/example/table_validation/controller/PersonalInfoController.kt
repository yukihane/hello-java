package com.example.table_validation.controller

import com.example.table_validation.form.PersonalInfoForm
import com.example.table_validation.model.PersonalInfo
import com.example.table_validation.service.PersonalInfoService
import jakarta.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class PersonalInfoController(private val personalInfoService: PersonalInfoService) {

    @GetMapping("/")
    fun showForm(model: Model): String {
        if (!model.containsAttribute("personalInfoForm")) {
            val form = PersonalInfoForm()
            form.infoList.add(PersonalInfo())
            model.addAttribute("personalInfoForm", form)
        }
        model.addAttribute("savedList", personalInfoService.getAll())
        return "form"
    }

    @PostMapping("/submit")
    fun submitForm(
        @Valid @ModelAttribute("personalInfoForm") form: PersonalInfoForm,
        bindingResult: BindingResult,
        redirectAttributes: RedirectAttributes,
        model: Model
    ): String {
        if (bindingResult.hasErrors()) {
            model.addAttribute("savedList", personalInfoService.getAll())
            return "form"
        }
        
        personalInfoService.saveAll(form.infoList)
        
        redirectAttributes.addFlashAttribute("successMessage", "個人情報が正常に保存されました。")
        return "redirect:/"
    }

    @PostMapping("/clear")
    fun clearAll(redirectAttributes: RedirectAttributes): String {
        personalInfoService.clear()
        redirectAttributes.addFlashAttribute("successMessage", "すべての個人情報が削除されました。")
        return "redirect:/"
    }
}
