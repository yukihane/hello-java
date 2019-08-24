package com.github.yukihane.linebotsample.controller;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@LineMessageHandler
public class MyController {

    @EventMapping
    public TextMessage handleTextMessageEvent(final MessageEvent<TextMessageContent> event) throws Exception {
        System.out.println("event: " + event);
        return new TextMessage(event.getMessage().getText());
    }
}
