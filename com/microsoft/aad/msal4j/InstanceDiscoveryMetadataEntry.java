// Generated by delombok at Fri Feb 12 21:32:09 UTC 2021
// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.microsoft.aad.msal4j;

import com.fasterxml.jackson.annotation.JsonProperty;
//import lombok.*;
import java.util.*;

class InstanceDiscoveryMetadataEntry {
    @JsonProperty("preferred_network")
    String preferredNetwork;
    @JsonProperty("preferred_cache")
    String preferredCache;
    @JsonProperty("aliases")
    Set<String> aliases;


    @java.lang.SuppressWarnings("all")
    public static class InstanceDiscoveryMetadataEntryBuilder {
        @java.lang.SuppressWarnings("all")
        private String preferredNetwork;
        @java.lang.SuppressWarnings("all")
        private String preferredCache;
        @java.lang.SuppressWarnings("all")
        private Set<String> aliases;

        @java.lang.SuppressWarnings("all")
        InstanceDiscoveryMetadataEntryBuilder() {
        }

        @java.lang.SuppressWarnings("all")
        public InstanceDiscoveryMetadataEntryBuilder preferredNetwork(final String preferredNetwork) {
            this.preferredNetwork = preferredNetwork;
            return this;
        }

        @java.lang.SuppressWarnings("all")
        public InstanceDiscoveryMetadataEntryBuilder preferredCache(final String preferredCache) {
            this.preferredCache = preferredCache;
            return this;
        }

        @java.lang.SuppressWarnings("all")
        public InstanceDiscoveryMetadataEntryBuilder aliases(final Set<String> aliases) {
            this.aliases = aliases;
            return this;
        }

        @java.lang.SuppressWarnings("all")
        public InstanceDiscoveryMetadataEntry build() {
            return new InstanceDiscoveryMetadataEntry(preferredNetwork, preferredCache, aliases);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        public java.lang.String toString() {
            return "InstanceDiscoveryMetadataEntry.InstanceDiscoveryMetadataEntryBuilder(preferredNetwork=" + this.preferredNetwork + ", preferredCache=" + this.preferredCache + ", aliases=" + this.aliases + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    public static InstanceDiscoveryMetadataEntryBuilder builder() {
        return new InstanceDiscoveryMetadataEntryBuilder();
    }

    @java.lang.SuppressWarnings("all")
    String preferredNetwork() {
        return this.preferredNetwork;
    }

    @java.lang.SuppressWarnings("all")
    String preferredCache() {
        return this.preferredCache;
    }

    @java.lang.SuppressWarnings("all")
    Set<String> aliases() {
        return this.aliases;
    }

    @java.lang.SuppressWarnings("all")
    public InstanceDiscoveryMetadataEntry() {
    }

    @java.lang.SuppressWarnings("all")
    public InstanceDiscoveryMetadataEntry(final String preferredNetwork, final String preferredCache, final Set<String> aliases) {
        this.preferredNetwork = preferredNetwork;
        this.preferredCache = preferredCache;
        this.aliases = aliases;
    }
}