package ru.reeson2003.telega.controller.model;

import lombok.Data;

@Data
public class AddCommand {

    private String script;

    private Object state;

}
