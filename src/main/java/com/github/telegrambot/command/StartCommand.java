package com.github.telegrambot.command;

import com.github.telegrambot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

//Start {@link Command}.

public class StartCommand implements Command {
    private final SendBotMessageService sendBotMessageService;

    public final static String START_MESSAGE = "Привет! Я TelegramBot. Я помогу тебе быть в курсе последних " +
            "статей тех авторов, которые тебе интересны. Я ещё маленький и только начинаю расти :)";

    //Здесь не будем добавлять сервис через получение из Application Context (создадим в CommandContainer), чтобы не получить циклическую зависимость и не сломать приложение.

    public StartCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }
}
