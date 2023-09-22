package com.cader.govtech.selection;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

    private String sender;
    private String message;

    private MessageType type;
}
