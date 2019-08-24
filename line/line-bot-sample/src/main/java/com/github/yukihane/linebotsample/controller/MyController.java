package com.github.yukihane.linebotsample.controller;

import com.linecorp.bot.model.action.Action;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.template.ButtonsTemplate;
import com.linecorp.bot.model.message.template.Template;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import java.util.List;

@LineMessageHandler
public class MyController {

    @EventMapping
    public Message handleTextMessageEvent(final MessageEvent<TextMessageContent> event) throws Exception {
        System.out.println("event: " + event);
        final String text = event.getMessage().getText();

        final Action a1 = new URIAction("link", "https://qiita.com/", null);
        final String data = "データです";
        final PostbackAction a2 = new PostbackAction("postback", data);

        final List<Action> actions = List.of(a1, a2);
        final Template template = ButtonsTemplate.builder()
            .title("タイトル")
            .text("テキスト")
            .actions(actions)
            .build();
        return new TemplateMessage(text, template);
    }

    @EventMapping
    public Message handlePostBackEvent(final PostbackEvent event) throws Exception {
        final String data = event.getPostbackContent().getData();

        return new TextMessage("PostBackEvent受信: " + data);
    }
}
