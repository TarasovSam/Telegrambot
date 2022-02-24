package com.github.telegrambot.command;

//Enumeration for {@link Command}'s.

public enum CommandName {

    START ("/start"),
    STOP ("/stop"),
    HELP ("/help"),
    NO ("nocommand"),
    STAT ("/stat"),
    ADD_GROUP_SUB ("/addGroupSub"),
    LIST_GROUP_SUB ("/listGroupSub");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
