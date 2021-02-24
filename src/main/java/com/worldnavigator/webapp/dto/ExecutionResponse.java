package com.worldnavigator.webapp.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ExecutionResponse {

    @NotBlank
    private final String result;

    @JsonCreator
    public ExecutionResponse(
            @JsonProperty String result
    ) {
        this.result = result;
    }
}
