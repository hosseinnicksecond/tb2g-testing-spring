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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    @Mock
    ClinicService service;

    @Mock
    Map<String, Object> model;

    @InjectMocks
    VetController controller;

    List<Vet> vetList= new ArrayList<>();

    @BeforeEach
    void setUp() {

        vetList.add(new Vet());

        given(service.findVets()).willReturn(vetList);
    }

    @Test
    void showVetList() {

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