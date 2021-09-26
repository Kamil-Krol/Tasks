package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Trello {

    @JsonProperty("board")
    private int board = 0;

    @JsonProperty("card")
    private int card = 0;
}
