package com.cader.govtech.selection.controllers;

import com.cader.govtech.selection.services.ManageSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SelectionRestController {
    private final ManageSessionService sessionService;

    @Autowired
    public SelectionRestController(ManageSessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/initiateSession")
    public String initiateSession(@RequestBody String name) {
        return sessionService.createSession();
    }
}
