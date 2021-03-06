package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    @Mock
    ClinicService service;

    @Mock
    Map<String, Object> model;

    @InjectMocks
    VetController controller;

    MockMvc mockMvc;

    List<Vet> vetList= new ArrayList<>();

    @BeforeEach
    void setUp() {

        vetList.add(new Vet());

        given(service.findVets()).willReturn(vetList);

        mockMvc= MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void controllerWebTest() throws Exception {
        mockMvc.perform(get("/vets.html"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("vets"))
                .andExpect(view().name("vets/vetList"));
    }

    @Test
    void showVetList()  {

        String view=controller.showVetList(model);

        verify(service).findVets();
        then(model).should().put(eq("vets"),any());

        assertThat("vets/vetList").isEqualTo(view);
    }

    @Test
    void showResourcesVetList() {

        Vets vets=controller.showResourcesVetList();

        then(service).should().findVets();

        assertThat(vets).isNotNull();

    }
}