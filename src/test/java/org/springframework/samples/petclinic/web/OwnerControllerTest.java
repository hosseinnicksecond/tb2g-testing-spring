package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-test-config.xml",
        "classpath:spring/mvc-core-config.xml"})
class OwnerControllerTest {

    @Autowired
    OwnerController ownerController;

    @Autowired
    ClinicService clinicService;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @Captor
    ArgumentCaptor<Owner> ownerArgumentCaptor;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc= MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @Test
    void findByNameFindOne() throws Exception {
        List<Owner> owners= new ArrayList<>();
        Owner owner= new Owner();
        owner.setId(2);
//        owner.setLastName("Doe");
        owners.add(owner);
        given(clinicService.findOwnerByLastName(anyString())).willReturn(owners);
        mockMvc.perform(get("/owners")
                 .param("lastName","Doe"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/2"));

        then(clinicService).should().findOwnerByLastName(stringArgumentCaptor.capture());

        assertThat(stringArgumentCaptor.getValue()).isEqualTo("Doe");
    }

    @Test
    void processFormNewOwnerValid() throws Exception {
        mockMvc.perform(post("/owners/new")
                  .param("firstName","John")
                  .param("lastName","Doe")
                  .param("Address","102 street")
                  .param("city","newYork")
                  .param("telephone","121241234"))
                .andExpect(status().is3xxRedirection());

    }

    @Test
    void processFormNewOwnerNotValid() throws Exception {
        mockMvc.perform(post("/owners/new")
                        .param("firstName","lily")
                        .param("lastName","Doe")
                        .param("telephone","11344567"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("owner"))
                .andExpect(model().attributeHasFieldErrors("owner","address"))
                .andExpect(model().attributeHasFieldErrors("owner","city"))
                .andExpect(view().name("owners/createOrUpdateOwnerForm"));
    }

    @Test
    void findByNameFindMoreThanOne() throws Exception {
        List<Owner> owners= new ArrayList<>();
        owners.add(new Owner());
        owners.add(new Owner());
        given(clinicService.findOwnerByLastName("")).willReturn(owners);
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("selections"))
                .andExpect(view().name("owners/ownersList"));

        then(clinicService).should().findOwnerByLastName(stringArgumentCaptor.capture());

        assertThat(stringArgumentCaptor.getValue()).isEqualTo("");

    }

    @Test
    void findByNameNotFoundTest() throws Exception {
        mockMvc.perform(get("/owners")
                .param("firstName","Not Found")
                 .param("lastName","Not found"))//
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"));
    }

    @Test
    void initCreationForm() throws Exception {

        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owners/createOrUpdateOwnerForm"));
    }
}