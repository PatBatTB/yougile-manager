package io.github.patbattb.yougile.manager.params;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Mail(
        @JsonProperty(value = "report", required = true) Report report,
        @JsonProperty(value = "from", required = true) Sender from,
        @JsonProperty(value = "recipients", required = true) List<String> recipients,
        @JsonProperty(value = "smtp", required = true) Smtp smtp) {
}
