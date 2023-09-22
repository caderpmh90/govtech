package com.cader.govtech.selection.services;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ManageSessionService {
    private final Map<String, UUID> sessions = new HashMap<>();

    public boolean validateSession(String sessionId) {
        return sessions.containsKey(sessionId);
    }

    public String createSession() {
        UUID sessionId;
        String sessionIdStr;

        // Generate a unique UUID and check if it already exists in the map
        do {
            sessionId = UUID.randomUUID();
            sessionIdStr = sessionId.toString();
        } while (sessions.containsKey(sessionId.toString()));

        sessions.put(sessionIdStr, sessionId);
        return sessionIdStr;
    }

    public boolean removeSession(String sessionId) {
        if (sessions.containsKey(sessionId)) {
            sessions.remove(sessionId);
            return true; // Session removed successfully
        }
        return false; // Session not found
    }
}
