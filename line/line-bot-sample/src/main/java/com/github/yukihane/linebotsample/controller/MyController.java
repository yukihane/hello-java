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
import java.net.URI;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.List;

@LineMessageHandler
public class MyController {

    @EventMapping
    public TextMessage handleTextMessageEvent(final MessageEvent<TextMessageContent> event) throws Exception {
        System.out.println("event: " + event);
        return new TextMessage(event.getMessage().getText());
    }

    @EventMapping
    public Message handlePostBackEvent(final PostbackEvent event) throws Exception {
        final String data = event.getPostbackContent().getData();

        if ("the-postback-action".equals(data)) {
            final LocalDateTime now = LocalDateTime.now();
            final int sec = now.get(ChronoField.SECOND_OF_MINUTE);

            final Action a1 = new URIAction("link", new URI("https://qiita.com/settings/account"), null);

            if (sec % 2 == 0) {
                final List<Action> actions = List.of(a1);
                final Template template = ButtonsTemplate.builder()
                    .text("Go to qiita.com")
                    .actions(actions)
                    .build();
                return new TemplateMessage("リンクです(" + now + ")", template);
            } else {
                final String d = "データです";
                final PostbackAction a2 = new PostbackAction("postback", d);

                final List<Action> actions = List.of(a1, a2);
                final Template template = ButtonsTemplate.builder()
                    .title("タイトル")
                    .text("テキスト")
                    .actions(actions)
                    .build();
                return new TemplateMessage("その他です(" + now + ")", template);
            }

        }

        return new TextMessage("PostBackEvent受信: " + data);
    }
}
