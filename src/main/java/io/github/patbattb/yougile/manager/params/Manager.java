package io.github.patbattb.yougile.manager.params;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Manager(
        @JsonProperty (value = "threads", required = true) int threads,
        @JsonProperty (value = "cycle", required = true) int cycle,
        @JsonProperty (value = "terminationTimeout", required = true) int terminationTimeout) {
}
