package com.github.telegrambot.command;

import com.github.telegrambot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

//Unknown {@link Command}.

public class UnknownCommand implements Command {
    private final SendBotMessageService sendBotMessageService;

    public final static String UNKNOWN_MESSAGE = "Не понимаю тебя \uD83D\uDE1F. Напиши /help, чтобы узнать что я понимаю.";

    public UnknownCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), UNKNOWN_MESSAGE);
    }
}
