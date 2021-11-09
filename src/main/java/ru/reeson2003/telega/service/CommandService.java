package ru.reeson2003.telega.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CommandService {

    List<String> getAllCommands();

    void addCommand(String name, String script, Object initialState);

    void editCommand(String name, String script);

    Optional<Object> runCommand(String commandName, Map<String, Object> arguments);
}
