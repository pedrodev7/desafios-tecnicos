package com.project.ms_authorize.service;

import com.project.ms_authorize.dto.MensageResponse;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class MensageService {

    public MensageResponse autorizar(){
        int number = ThreadLocalRandom.current().nextInt(1,11);
        System.out.println("Numero: " + number);
        if(number > 8)
            return new MensageResponse("Negado");
        return new MensageResponse("Autorizado");
    }
}
