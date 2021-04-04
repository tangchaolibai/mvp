package com.atguigu.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface MyLoadBalancer {

    ServiceInstance instance(List<ServiceInstance> serviceInstances);

}
