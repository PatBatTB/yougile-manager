package io.github.patbattb.yougile.manager.params;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Smtp(
        @JsonProperty(value = "host", required = true) String host,
        @JsonProperty(value = "port", required = true) int port,
        @JsonProperty(value = "ssl", required = true) boolean ssl,
        @JsonProperty(value = "username", required = true) String username,
        @JsonProperty(value = "password", required = true) String password) {
}
