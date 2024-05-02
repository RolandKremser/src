// Generated by delombok at Fri Feb 12 21:32:09 UTC 2021
// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.microsoft.aad.msal4j;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Contains information about outgoing HTTP request. Should be adapted to HTTP request for HTTP
 * client of choice
 */
public class HttpRequest {
    /**
     * {@link HttpMethod}
     */
    private HttpMethod httpMethod;
    /**
     * HTTP request url
     */
    private URL url;
    /**
     * HTTP request headers
     */
    private Map<String, String> headers;
    /**
     * HTTP request body
     */
    private String body;

    HttpRequest(HttpMethod httpMethod, String url) {
        this.httpMethod = httpMethod;
        this.url = createUrlFromString(url);
    }

    HttpRequest(HttpMethod httpMethod, String url, Map<String, String> headers) {
        this.httpMethod = httpMethod;
        this.url = createUrlFromString(url);
        this.headers = headers;
    }

    HttpRequest(HttpMethod httpMethod, String url, String body) {
        this.httpMethod = httpMethod;
        this.url = createUrlFromString(url);
        this.body = body;
    }

    HttpRequest(HttpMethod httpMethod, String url, Map<String, String> headers, String body) {
        this.httpMethod = httpMethod;
        this.url = createUrlFromString(url);
        this.headers = headers;
        this.body = body;
    }

    /**
     * @param headerName Name of HTTP header name
     * @return Value of HTTP header
     */
    public String headerValue(String headerName) {
        if (headerName == null || headers == null) {
            return null;
        }
        return headers.get(headerName);
    }

    private URL createUrlFromString(String stringUrl) {
        URL url;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            throw new MsalClientException(e);
        }
        return url;
    }

    /**
     * {@link HttpMethod}
     */
    @java.lang.SuppressWarnings("all")
    public HttpMethod httpMethod() {
        return this.httpMethod;
    }

    /**
     * HTTP request url
     */
    @java.lang.SuppressWarnings("all")
    public URL url() {
        return this.url;
    }

    /**
     * HTTP request headers
     */
    @java.lang.SuppressWarnings("all")
    public Map<String, String> headers() {
        return this.headers;
    }

    /**
     * HTTP request body
     */
    @java.lang.SuppressWarnings("all")
    public String body() {
        return this.body;
    }
}
