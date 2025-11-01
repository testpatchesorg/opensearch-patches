/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

package org.opensearch.search;

import org.opensearch.common.annotation.DeprecatedApi;
import org.opensearch.core.common.io.stream.StreamOutput;
import org.opensearch.core.tasks.resourcetracker.ResourceStats;
import org.opensearch.tasks.Task;

import java.io.IOException;
import java.util.function.Function;

/**
 * Enum to hold the resource type
 *
 * @opensearch.api
 *
 */
@DeprecatedApi(since = "2.17.0")
@Deprecated(forRemoval = true)
public enum ResourceType {
    CPU("cpu", task -> task.getTotalResourceUtilization(ResourceStats.CPU)),
    MEMORY("memory", task -> task.getTotalResourceUtilization(ResourceStats.MEMORY));

    private final String name;
    private final Function<Task, Long> getResourceUsage;

    ResourceType(String name, Function<Task, Long> getResourceUsage) {
        this.name = name;
        this.getResourceUsage = getResourceUsage;
    }

    /**
     * The string match here is case-sensitive
     * @param s name matching the resource type name
     * @return a {@link ResourceType}
     */
    public static ResourceType fromName(String s) {
        for (ResourceType resourceType : values()) {
            if (resourceType.getName().equals(s)) {
                return resourceType;
            }
        }
        throw new IllegalArgumentException("Unknown resource type: [" + s + "]");
    }

    public static void writeTo(StreamOutput out, ResourceType resourceType) throws IOException {
        out.writeString(resourceType.getName());
    }

    public String getName() {
        return name;
    }

    /**
     * Gets the resource usage for a given resource type and task.
     *
     * @param task the task for which to calculate resource usage
     * @return the resource usage
     */
    public long getResourceUsage(Task task) {
        return getResourceUsage.apply(task);
    }
}
