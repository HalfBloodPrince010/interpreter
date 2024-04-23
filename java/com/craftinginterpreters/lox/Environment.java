package com.craftinginterpreters.lox;

import java.util.HashMap;
import java.util.Map;

class Environment {
    private final Map<String, Object> values = new HashMap<>();

    void define(String name, Object value) {
        values.put(name, value);
    }

    Object get(Token name) {
        String variableName = name.lexeme;
        if(values.containsKey(variableName)) {
            return values.get(variableName);
        }

        throw new RuntimeError(name, "Undefined Variable '" + variableName + "'.");
    }
}
