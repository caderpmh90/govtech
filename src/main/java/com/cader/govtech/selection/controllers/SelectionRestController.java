package com.cader.govtech.selection.controllers;

import com.cader.govtech.InitiateSessionDto;
import com.cader.govtech.selection.services.ManageSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SelectionRestController {
    private final ManageSessionService sessionService;

    @Autowired
    public SelectionRestController(ManageSessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/initiateSession")
    public ResponseEntity<InitiateSessionDto> initiateSession(@RequestBody String name) {
        InitiateSessionDto out = new InitiateSessionDto();
        out.setSessionId(sessionService.createSession());
        return new ResponseEntity<>(out, HttpStatus.OK) ;
    }

    @DeleteMapping("/removeSession/{sessionId}")
    public String removeSession(@PathVariable String sessionId) {
        boolean removed = sessionService.removeSession(sessionId);
        return removed ? "Session removed successfully" : "Session not found";
    }
}
