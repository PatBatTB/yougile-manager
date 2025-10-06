package io.github.patbattb.yougile.manager.params;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Report(
        @JsonProperty(value = "interrupted", required = true) boolean interrupted,
        @JsonProperty(value = "critical", required = true) boolean critical) {
}
