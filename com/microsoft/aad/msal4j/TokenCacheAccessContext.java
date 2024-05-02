// Generated by delombok at Fri Feb 12 21:32:09 UTC 2021
// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.microsoft.aad.msal4j;

/**
 * Context in which the the token cache is accessed
 *
 * For more details, see https://aka.ms/msal4j-token-cache
 */
public class TokenCacheAccessContext implements ITokenCacheAccessContext {
    private ITokenCache tokenCache;
    private String clientId;
    private IAccount account;
    private boolean hasCacheChanged;

    @java.lang.SuppressWarnings("all")
    TokenCacheAccessContext(final ITokenCache tokenCache, final String clientId, final IAccount account, final boolean hasCacheChanged) {
        this.tokenCache = tokenCache;
        this.clientId = clientId;
        this.account = account;
        this.hasCacheChanged = hasCacheChanged;
    }


    @java.lang.SuppressWarnings("all")
    public static class TokenCacheAccessContextBuilder {
        @java.lang.SuppressWarnings("all")
        private ITokenCache tokenCache;
        @java.lang.SuppressWarnings("all")
        private String clientId;
        @java.lang.SuppressWarnings("all")
        private IAccount account;
        @java.lang.SuppressWarnings("all")
        private boolean hasCacheChanged;

        @java.lang.SuppressWarnings("all")
        TokenCacheAccessContextBuilder() {
        }

        @java.lang.SuppressWarnings("all")
        public TokenCacheAccessContextBuilder tokenCache(final ITokenCache tokenCache) {
            this.tokenCache = tokenCache;
            return this;
        }

        @java.lang.SuppressWarnings("all")
        public TokenCacheAccessContextBuilder clientId(final String clientId) {
            this.clientId = clientId;
            return this;
        }

        @java.lang.SuppressWarnings("all")
        public TokenCacheAccessContextBuilder account(final IAccount account) {
            this.account = account;
            return this;
        }

        @java.lang.SuppressWarnings("all")
        public TokenCacheAccessContextBuilder hasCacheChanged(final boolean hasCacheChanged) {
            this.hasCacheChanged = hasCacheChanged;
            return this;
        }

        @java.lang.SuppressWarnings("all")
        public TokenCacheAccessContext build() {
            return new TokenCacheAccessContext(tokenCache, clientId, account, hasCacheChanged);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        public java.lang.String toString() {
            return "TokenCacheAccessContext.TokenCacheAccessContextBuilder(tokenCache=" + this.tokenCache + ", clientId=" + this.clientId + ", account=" + this.account + ", hasCacheChanged=" + this.hasCacheChanged + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    public static TokenCacheAccessContextBuilder builder() {
        return new TokenCacheAccessContextBuilder();
    }

    @java.lang.SuppressWarnings("all")
    public ITokenCache tokenCache() {
        return this.tokenCache;
    }

    @java.lang.SuppressWarnings("all")
    public String clientId() {
        return this.clientId;
    }

    @java.lang.SuppressWarnings("all")
    public IAccount account() {
        return this.account;
    }

    @java.lang.SuppressWarnings("all")
    public boolean hasCacheChanged() {
        return this.hasCacheChanged;
    }
}