# Time Series Storage - NoOps [![CircleCI](https://circleci.com/gh/opennms-forge/opennms-tss-plugin-noops.svg?style=svg)](https://circleci.com/gh/opennms-forge/opennms-tss-plugin-noops)

This plugin exposes a no operations implementation of the TimeSeriesStorage interface.
It doesn't store data but counts the received samples.
It can be used for performance tests of OpenNMS.
It is not meant for production use.

### Usage
* compile: ``mvn install``
* activation: Enable the timeseries integration layer: see [documentation](https://docs.opennms.org/opennms/releases/26.1.0/guide-admin/guide-admin.html#ga-opennms-operation-timeseries)
* activate in Karaf shell: ``bundle:install -s mvn:org.opennms.plugins.tss/noops/1.0.0-SNAPSHOT``
* show statistics in Karaf shell: ``opennms-tss-noops:stats``

**Configure**

The default configuration has the following settings:
```
# write latency in ms. The store() method is blocked for n seconds.
# Where latencyMin <= n <= latencyMax 
latencyMin=5
latencyMax=10
```

Change configuration via Karaf shell:
```
config:edit org.opennms.plugins.tss.noops
property-set latencyMin 5
property-set latencyMax 10
config:update
```

  
 



