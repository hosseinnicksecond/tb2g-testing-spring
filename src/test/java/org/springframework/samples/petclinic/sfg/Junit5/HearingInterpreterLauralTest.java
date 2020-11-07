package org.springframework.samples.petclinic.sfg.Junit5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.sfg.BaseConfig;
import org.springframework.samples.petclinic.sfg.HearingInterpreter;
import org.springframework.samples.petclinic.sfg.LauralConfig;
import org.springframework.samples.petclinic.sfg.YanniConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = {BaseConfig.class, LauralConfig.class, YanniConfig.class})
class HearingInterpreterLauralTest {

    @Autowired
    HearingInterpreter hearingInterpreter;

    @Test
    void whatIHear() {

        String word=hearingInterpreter.WhatIHear();

//        assertEquals("Laurel",word); //if LaurelConfig has Primary Bean
        assertEquals("Yanni",word);// if YanniConfig has Primary Bean
    }
}