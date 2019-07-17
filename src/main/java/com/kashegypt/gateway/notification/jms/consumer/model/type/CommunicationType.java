package com.kashegypt.gateway.notification.jms.consumer.model.type;

import com.kashegypt.gateway.notification.jms.consumer.model.validator.type.ICode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum CommunicationType implements ICode {
    UNICAST(1),
    MULTICAST(2),
    DEVICEGROUP(3);

    private int id;

    CommunicationType(int id){
        this.id = id;
    }

    private static final Map<Integer, CommunicationType> map = new HashMap<>();

    static {
        Arrays.stream(CommunicationType.values()).forEach(k -> map.put(k.id, k));
    }

    public static Map<Integer, CommunicationType> getValuesMap() {
        return map;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public String getDescription() {
        return this.name();
    }

    @Override
    public int getCodeTypeId() {
        return 2;
    }
}
