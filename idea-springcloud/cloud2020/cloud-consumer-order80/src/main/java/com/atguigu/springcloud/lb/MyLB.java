package com.atguigu.lb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements MyLoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);


    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int index = myIndex(serviceInstances.size());
        return serviceInstances.get(index);
    }

    public int myIndex(int size){
        int current;
        int next;
        do {
            current = atomicInteger.get();
            next = (current+1);
        }while (!atomicInteger.compareAndSet(current,next));
        return next % size;
    }
}
