// Generated by delombok at Fri Feb 12 21:32:09 UTC 2021
// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.microsoft.aad.msal4j;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response returned from the STS device code endpoint containing information necessary for
 * device code flow
 */
public final class DeviceCode {
    /**
     * code which user needs to provide when authenticating at the verification URI
     */
    @JsonProperty("user_code")
    private String userCode;
    /**
     * code which should be included in the request for the access token
     */
    @JsonProperty("device_code")
    private String deviceCode;
    /**
     * URI where user can authenticate
     */
    @JsonProperty("verification_uri")
    private String verificationUri;
    /**
     * expiration time of device code in seconds.
     */
    @JsonProperty("expires_in")
    private long expiresIn;
    /**
     * interval at which the STS should be polled at
     */
    @JsonProperty("interval")
    private long interval;
    /**
     * message which should be displayed to the user.
     */
    @JsonProperty("message")
    private String message;
    private transient String correlationId = null;
    private transient String clientId = null;
    private transient String scopes = null;

    /**
     * code which user needs to provide when authenticating at the verification URI
     */
    @java.lang.SuppressWarnings("all")
    public String userCode() {
        return this.userCode;
    }

    /**
     * code which should be included in the request for the access token
     */
    @java.lang.SuppressWarnings("all")
    public String deviceCode() {
        return this.deviceCode;
    }

    /**
     * URI where user can authenticate
     */
    @java.lang.SuppressWarnings("all")
    public String verificationUri() {
        return this.verificationUri;
    }

    /**
     * expiration time of device code in seconds.
     */
    @java.lang.SuppressWarnings("all")
    public long expiresIn() {
        return this.expiresIn;
    }

    /**
     * interval at which the STS should be polled at
     */
    @java.lang.SuppressWarnings("all")
    public long interval() {
        return this.interval;
    }

    /**
     * message which should be displayed to the user.
     */
    @java.lang.SuppressWarnings("all")
    public String message() {
        return this.message;
    }

    @java.lang.SuppressWarnings("all")
    protected String correlationId() {
        return this.correlationId;
    }

    @java.lang.SuppressWarnings("all")
    protected DeviceCode correlationId(final String correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    @java.lang.SuppressWarnings("all")
    protected String clientId() {
        return this.clientId;
    }

    @java.lang.SuppressWarnings("all")
    protected DeviceCode clientId(final String clientId) {
        this.clientId = clientId;
        return this;
    }

    @java.lang.SuppressWarnings("all")
    protected String scopes() {
        return this.scopes;
    }

    @java.lang.SuppressWarnings("all")
    protected DeviceCode scopes(final String scopes) {
        this.scopes = scopes;
        return this;
    }
}
