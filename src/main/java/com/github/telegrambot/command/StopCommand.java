package com.github.telegrambot.command;

import com.github.telegrambot.service.SendBotMessageService;
import com.github.telegrambot.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Stop {@link Command}.
 */

public class StopCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public final static String STOP_MESSAGE = "Остановил все твои подписки \uD83D\uDE1F.\n" +
            "Ты всегда можешь вернуться нажав /start";

    public StopCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();

        sendBotMessageService.sendMessage(chatId, STOP_MESSAGE);
        telegramUserService.findByChatId(chatId)
                .ifPresent(it -> {
                    it.setActive(false);
                    telegramUserService.save(it);
                });
    }
}
