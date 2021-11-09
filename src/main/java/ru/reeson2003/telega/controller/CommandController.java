package ru.reeson2003.telega.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.reeson2003.telega.controller.model.CommandNotFoundException;
import ru.reeson2003.telega.service.CommandService;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/commands")
public class CommandController {

    private final CommandService commandService;

    @GetMapping
    public List<String> getCommands() {
        return commandService.getAllCommands();
    }

    @PostMapping("/exec/{command_name}")
    public Object execute(@PathVariable("command_name") String commandName, @RequestBody Map<String, Object> params) {
        return commandService.runCommand(commandName, params)
                .orElseThrow(() -> new CommandNotFoundException(commandName));
    }

    @PostMapping("/add/{command_name}")
    public void addCommand(@PathVariable("command_name") String commandName,
                           @RequestParam("script") String script,
                           @RequestParam("state") String state) {
        commandService.addCommand(commandName, script, state);
    }

    @PostMapping("/edit/{command_name}")
    public void editCommand(@PathVariable("command_name") String commandName,
                            @RequestParam("script") String script) {
        commandService.editCommand(commandName, script);
    }
}
