// Generated by delombok at Fri Feb 12 21:32:09 UTC 2021
// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.microsoft.aad.msal4j;

abstract class MsalRequest {
    AbstractMsalAuthorizationGrant msalAuthorizationGrant;
    private final AbstractClientApplicationBase application;
    private final RequestContext requestContext;
    private final java.util.concurrent.atomic.AtomicReference<java.lang.Object> headers = new java.util.concurrent.atomic.AtomicReference<java.lang.Object>();

    MsalRequest(AbstractClientApplicationBase clientApplicationBase, AbstractMsalAuthorizationGrant abstractMsalAuthorizationGrant, RequestContext requestContext) {
        this.application = clientApplicationBase;
        this.msalAuthorizationGrant = abstractMsalAuthorizationGrant;
        this.requestContext = requestContext;
        CurrentRequest currentRequest = new CurrentRequest(requestContext.publicApi());
        application.getServiceBundle().getServerSideTelemetry().setCurrentRequest(currentRequest);
    }

    @java.lang.SuppressWarnings("all")
    AbstractMsalAuthorizationGrant msalAuthorizationGrant() {
        return this.msalAuthorizationGrant;
    }

    @java.lang.SuppressWarnings("all")
    AbstractClientApplicationBase application() {
        return this.application;
    }

    @java.lang.SuppressWarnings("all")
    RequestContext requestContext() {
        return this.requestContext;
    }

    @java.lang.SuppressWarnings("all")
    public MsalRequest(final AbstractMsalAuthorizationGrant msalAuthorizationGrant, final AbstractClientApplicationBase application, final RequestContext requestContext) {
        this.msalAuthorizationGrant = msalAuthorizationGrant;
        this.application = application;
        this.requestContext = requestContext;
    }

    @java.lang.SuppressWarnings("all")
    HttpHeaders headers() {
        java.lang.Object value = this.headers.get();
        if (value == null) {
            synchronized (this.headers) {
                value = this.headers.get();
                if (value == null) {
                    final HttpHeaders actualValue = new HttpHeaders(requestContext);
                    value = actualValue == null ? this.headers : actualValue;
                    this.headers.set(value);
                }
            }
        }
        return (HttpHeaders) (value == this.headers ? null : value);
    }
}
