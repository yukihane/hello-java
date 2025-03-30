package com.example.table_validation.controller

import com.example.table_validation.form.PersonalInfoForm
import com.example.table_validation.model.PersonalInfo
import com.example.table_validation.service.PersonalInfoService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.view
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl

@WebMvcTest(PersonalInfoController::class)
class PersonalInfoControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var personalInfoService: PersonalInfoService

    @Test
    fun `show form returns form view`() {
        `when`(personalInfoService.getAll()).thenReturn(emptyList())

        mockMvc.perform(get("/"))
            .andExpect(status().isOk)
            .andExpect(view().name("form"))
    }

    @Test
    fun `submit form with valid data redirects to root`() {
        val form = PersonalInfoForm(mutableListOf(PersonalInfo("Test Name", 30, "Test Address")))

        mockMvc.perform(
            post("/submit")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("infoList[0].name", "Test Name")
                .param("infoList[0].age", "30")
                .param("infoList[0].address", "Test Address")
        )
            .andExpect(status().is3xxRedirection)
            .andExpect(redirectedUrl("/"))

        verify(personalInfoService).saveAll(form.infoList)
    }
}
