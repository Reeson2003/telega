package ru.reeson2003.telega.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reeson2003.telega.controller.model.CommandNotFoundException;
import ru.reeson2003.telega.data.ScriptSourceRepository;
import ru.reeson2003.telega.data.entity.ScriptSource;
import ru.reeson2003.telega.data.entity.State;
import ru.reeson2003.telega.service.model.ExecutionResult;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class CommandServiceImpl implements CommandService {

    private final JSInterpreter jsInterpreter;

    private final ScriptSourceRepository repository;

    @Override
    public List<String> getAllCommands() {
        return repository.findAll().stream()
                .map(ScriptSource::getId).
                collect(Collectors.toList());
    }

    @Override
    public void addCommand(String name, String script, Object initialState) {
        var scriptSource = repository.findById(name)
                .orElseGet(() -> ScriptSource.builder()
                        .id(name)
                        .source(script)
                        .state(State.builder()
                                .data(initialState)
                                .build())
                        .build());
        repository.save(scriptSource);
    }

    @Override
    public void editCommand(String name, String script) {
        var scriptSource = repository.findById(name)
                .orElseThrow(() -> new CommandNotFoundException(name));
        scriptSource.setSource(script);
        repository.save(scriptSource);
    }

    @Override
    public final Optional<Object> runCommand(String commandName, Map<String, Object> arguments) {
        return repository.findById(commandName)
                .map(scriptSource -> {
                    var result = runScript(scriptSource, arguments);
                    scriptSource.getState().setData(result.getState());
                    repository.save(scriptSource);
                    return result.getResult();
                });

    }

    private ExecutionResult runScript(ScriptSource scriptSource, Map<String, Object> arguments) {
        var script = scriptSource.getSource();
        var state = scriptSource.getState();
        return jsInterpreter.runScript(script, state.getData(), arguments);
    }
}
