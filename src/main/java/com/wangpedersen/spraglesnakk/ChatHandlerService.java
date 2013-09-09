package com.wangpedersen.spraglesnakk;

import org.atmosphere.config.service.ManagedService;
import org.atmosphere.cpr.AtmosphereResponse;
import org.atmosphere.handler.OnMessage;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

@ManagedService(path = "/chat")
public class ChatHandlerService extends OnMessage<String> {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onMessage(AtmosphereResponse atmosphereResponse, String message) throws IOException {
        System.out.println("****DEBUG: Message: " + message);

        ChatMessage chatMessage = mapper.readValue(message, ChatMessage.class);
        atmosphereResponse.write(mapper.writeValueAsString(chatMessage));

    }

}
