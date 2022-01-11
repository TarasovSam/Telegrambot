package com.github.telegrambot.command;

import com.github.telegrambot.repository.entity.TelegramUser;
import com.github.telegrambot.service.SendBotMessageService;
import com.github.telegrambot.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Start {@link Command}.
 */

public class StartCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public final static String START_MESSAGE = "Привет! Я TelegramBot. Я помогу тебе быть в курсе последних " +
            "статей тех авторов, которые тебе интересны. Я ещё маленький и только начинаю расти :)";

    //Здесь не будем добавлять сервис через получение из Application Context (создадим в CommandContainer), чтобы не получить циклическую зависимость и не сломать приложение.

    public StartCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();

        telegramUserService.findByChatId(chatId).ifPresentOrElse(
                user -> {
                    user.setActive(true);
                    telegramUserService.save(user);
                },
                () -> {
                    TelegramUser telegramUser = new TelegramUser();
                    telegramUser.setActive(true);
                    telegramUser.setChatId(chatId);
                    telegramUserService.save(telegramUser);
                }
        );

        sendBotMessageService.sendMessage(chatId, START_MESSAGE);
    }
}
