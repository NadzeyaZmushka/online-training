package com.epam.jwd.training.controller;

public interface Command {

    CommandResponse execute(CommandRequest request);

}
