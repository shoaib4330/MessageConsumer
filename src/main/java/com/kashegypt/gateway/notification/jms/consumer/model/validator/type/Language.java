package com.kashegypt.gateway.notification.jms.consumer.model.validator.type;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Language implements ICode {
    ENGLISH(1),
    ARABIC(2),
    FRANCO_ARABIC(3);

    private int id;

    Language(int id){
        this.id = id;
    }

    private static final Map<Integer, Language> map = new HashMap<>();

    static {
        Arrays.stream(Language.values()).forEach(k -> map.put(k.id, k));
    }

    public static Map<Integer, Language> getValuesMap() {
        return map;
    }

    public int value(){
        return this.id;
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
