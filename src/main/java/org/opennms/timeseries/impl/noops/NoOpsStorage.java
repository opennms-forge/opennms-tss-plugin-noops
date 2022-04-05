/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2006-2020 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2020 The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * OpenNMS(R) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with OpenNMS(R).  If not, see:
 *      http://www.gnu.org/licenses/
 *
 * For more information contact:
 *     OpenNMS(R) Licensing <license@opennms.org>
 *     http://www.opennms.org/
 *     http://www.opennms.com/
 *******************************************************************************/

package org.opennms.timeseries.impl.noops;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.opennms.integration.api.v1.timeseries.Metric;
import org.opennms.integration.api.v1.timeseries.Sample;
import org.opennms.integration.api.v1.timeseries.TagMatcher;
import org.opennms.integration.api.v1.timeseries.TimeSeriesFetchRequest;
import org.opennms.integration.api.v1.timeseries.TimeSeriesStorage;

/**
 * Rejects but counts all written sapmples.
 * For performance tests.
 */
public class NoOpsStorage implements TimeSeriesStorage {

    private final MetricRegistry metrics = new MetricRegistry();
    private final Meter samplesWritten = metrics.meter("samplesWritten");

    @Override
    public void store(final List<Sample> samples) {
        Objects.requireNonNull(samples);
        samplesWritten.mark(samples.size());
    }

    @Override
    public List<Metric> findMetrics(Collection<TagMatcher> tagMatchers) {
        Objects.requireNonNull(tagMatchers);
        if (tagMatchers.isEmpty()) {
            throw new IllegalArgumentException("We expect at least one TagMatcher but none was given.");
        }
        return Collections.emptyList();
    }

    @Override
    public List<Sample> getTimeseries(TimeSeriesFetchRequest request) {
        Objects.requireNonNull(request);
        return Collections.emptyList();
    }

    @Override
    public void delete(Metric metric) {
        Objects.requireNonNull(metric);
    }

    @Override
    public String toString() {
        return this.getClass().getName();
    }

    public MetricRegistry getMetrics() {
        return metrics;
    }
}
