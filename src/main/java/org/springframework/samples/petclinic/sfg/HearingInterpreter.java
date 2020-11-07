package org.springframework.samples.petclinic.sfg;

import org.springframework.stereotype.Service;

@Service
public class HearingInterpreter {

    private final wordProducer wordProducer;

    public HearingInterpreter(wordProducer wordProducer) {
        this.wordProducer = wordProducer;
    }

    public String WhatIHear(){
        String word=wordProducer.getWord();

        System.out.println(word);

        return word;
    }


}
