// Generated by delombok at Fri Feb 12 21:32:09 UTC 2021
// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.microsoft.aad.msal4j;

enum CredentialTypeEnum {
    ACCESS_TOKEN("AccessToken"), REFRESH_TOKEN("RefreshToken"), ID_TOKEN("IdToken");
    private final String value;

    @java.lang.SuppressWarnings("all")
    public String value() {
        return this.value;
    }

    @java.lang.SuppressWarnings("all")
    private CredentialTypeEnum(final String value) {
        this.value = value;
    }
}
