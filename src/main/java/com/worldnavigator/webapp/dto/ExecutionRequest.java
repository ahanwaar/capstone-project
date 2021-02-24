package com.worldnavigator.webapp.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ExecutionRequest {

    @NotBlank
    private final String line;

    @JsonCreator
    public ExecutionRequest(
            @JsonProperty String line
    ) {
        this.line = line;
    }
}
