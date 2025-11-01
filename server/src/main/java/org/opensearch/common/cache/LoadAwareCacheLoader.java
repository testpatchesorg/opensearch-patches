/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

package org.opensearch.common.cache;

import org.opensearch.common.annotation.ExperimentalApi;

/**
 * Extends a cache loader with awareness of whether the data is loaded or not.
 * @param <K> Type of key.
 * @param <V> Type of value.
 *
 * @opensearch.experimental
 */
@ExperimentalApi
public interface LoadAwareCacheLoader<K, V> extends CacheLoader<K, V> {
    boolean isLoaded();
}
