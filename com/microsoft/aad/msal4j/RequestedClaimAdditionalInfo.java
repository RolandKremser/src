// Generated by delombok at Fri Feb 12 21:32:09 UTC 2021
// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.microsoft.aad.msal4j;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

/**
 * Represents the additional information that can be sent to an authorization server for a request claim in the claim request parameter
 *
 * @see <a href="https://openid.net/specs/openid-connect-core-1_0-final.html#ClaimsParameter">https://openid.net/specs/openid-connect-core-1_0-final.html#ClaimsParameter</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestedClaimAdditionalInfo {
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("essential")
    boolean essential;
    @JsonProperty("value")
    String value;
    @JsonProperty("values")
    List<String> values;

    @java.lang.SuppressWarnings("all")
    public boolean isEssential() {
        return this.essential;
    }

    @java.lang.SuppressWarnings("all")
    public String getValue() {
        return this.value;
    }

    @java.lang.SuppressWarnings("all")
    public List<String> getValues() {
        return this.values;
    }

    @java.lang.SuppressWarnings("all")
    public void setEssential(final boolean essential) {
        this.essential = essential;
    }

    @java.lang.SuppressWarnings("all")
    public void setValue(final String value) {
        this.value = value;
    }

    @java.lang.SuppressWarnings("all")
    public void setValues(final List<String> values) {
        this.values = values;
    }

    @java.lang.SuppressWarnings("all")
    public RequestedClaimAdditionalInfo(final boolean essential, final String value, final List<String> values) {
        this.essential = essential;
        this.value = value;
        this.values = values;
    }
}
