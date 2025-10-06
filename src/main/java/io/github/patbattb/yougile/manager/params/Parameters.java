package io.github.patbattb.yougile.manager.params;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Parameters(@JsonProperty(value = "manager", required = true) Manager manager, Mail mail) {
}
