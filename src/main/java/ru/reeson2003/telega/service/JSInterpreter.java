package ru.reeson2003.telega.service;

import lombok.extern.slf4j.Slf4j;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;
import org.springframework.stereotype.Component;
import ru.reeson2003.telega.service.model.ExecutionResult;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Component
@Slf4j
public class JSInterpreter {

    public final ExecutionResult runScript(String script, Object state, Map<String, Object> arguments) {
        Context context = Context.enter();
        ScriptableObject scope = context.initStandardObjects();
        var newState = new AtomicReference<>(state);
        var result = new AtomicReference<>();
        var resultJs = Context.javaToJS(result, scope);
        ScriptableObject.putProperty(scope, "logger", Context.javaToJS(log, scope));
        ScriptableObject.putProperty(scope, "state", Context.javaToJS(state, scope));
        arguments.forEach((name, value) -> ScriptableObject.putProperty(scope, name, Context.javaToJS(value, scope)));
        ScriptableObject.putProperty(scope, "newState", Context.javaToJS(newState, scope));
        ScriptableObject.putProperty(scope, "result", resultJs);
        context.evaluateString(scope, script, "Index", 1, null);
        return ExecutionResult.builder()
                .state(newState.get())
                .result(result.get())
                .build();
    }
}
