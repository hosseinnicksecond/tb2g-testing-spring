package org.springframework.samples.petclinic.sfg;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {BaseConfig.class,LauralConfig.class,YanniConfig.class})
public class HearingInterpreterTest {

    @Autowired
    HearingInterpreter hearingInterpreter;


    @org.junit.Test
    public void whatIHear() {
        String word=hearingInterpreter.WhatIHear();

        assertEquals("Yanni",word);
    }
}
