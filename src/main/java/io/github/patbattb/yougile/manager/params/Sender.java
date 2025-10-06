package io.github.patbattb.yougile.manager.params;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Sender(
        @JsonProperty(value = "name", required = true) String name,
        @JsonProperty(value = "email", required = true) String email) {
}
