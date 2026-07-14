package com.project.ms_authorize.controller;

import com.project.ms_authorize.dto.MensageResponse;
import com.project.ms_authorize.service.MensageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authorize")
public class MensageController {
    private final MensageService mensageService;

    public MensageController(MensageService mensageService) {
        this.mensageService = mensageService;
    }

    @GetMapping
    public MensageResponse authorize(){
        return mensageService.autorizar();
    }
}
