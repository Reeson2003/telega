package ru.reeson2003.telega.controller.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CommandNotFoundException extends ResponseStatusException {
    public CommandNotFoundException(String commandName) {
        super(HttpStatus.NOT_FOUND, String.format("Command [%s] not found", commandName));
    }
}
