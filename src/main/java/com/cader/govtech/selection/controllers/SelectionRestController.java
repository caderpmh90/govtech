package com.cader.govtech.selection.controllers;

import com.cader.govtech.DTO.InitiateSessionDto;
import com.cader.govtech.DTO.ValidateSessionReqDto;
import com.cader.govtech.DTO.ValidateSessionResDto;
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

    @PostMapping("/validateSession")
    public ResponseEntity<ValidateSessionResDto> validateSession(@RequestBody ValidateSessionReqDto data) {
        boolean isValid =  sessionService.validateSession(data.getSessionId());
        ValidateSessionResDto out = new ValidateSessionResDto(isValid);
        return isValid? new ResponseEntity<>(out, HttpStatus.OK): new ResponseEntity<>(out, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/removeSession/{sessionId}")
    public ResponseEntity<String> removeSession(@PathVariable String sessionId) {
        boolean removed = sessionService.removeSession(sessionId);
        return removed ?new ResponseEntity<>("Session removed successfully", HttpStatus.OK)  :new ResponseEntity<>( "Session not found", HttpStatus.NOT_FOUND) ;
    }
}
