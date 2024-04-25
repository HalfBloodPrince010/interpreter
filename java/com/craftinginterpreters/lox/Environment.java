package com.craftinginterpreters.lox;

import java.util.HashMap;
import java.util.Map;

class Environment {
    final Environment enclosing;
    private final Map<String, Object> values = new HashMap<>();

    Environment() {
        enclosing = null;
    }

    Environment(Environment enclosing) {
        this.enclosing = enclosing;
    }

    void define(String name, Object value) {
        values.put(name, value);
    }

    Object get(Token name) {
        String variableName = name.lexeme;
        if(values.containsKey(variableName)) {
            return values.get(variableName);
        }

        if (enclosing != null) {
            return enclosing.get(name);
        }

        throw new RuntimeError(name, "Undefined Variable '" + variableName + "'.");
    }

    void assign(Token name, Object value) {
        String variableName = name.lexeme;
        if (values.containsKey(variableName)) {
            values.put(variableName, value);
            return;
        }

        if (enclosing != null) {
            enclosing.assign(name, value);
            return;
        }

        throw new RuntimeError(name, "Undefined Variable '" + variableName + "'.");
    }
}
