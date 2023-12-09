package com.byakko.service.sales.common.domain.valueobject;

public class Authority {

    private String name;

    public Authority() {
    }

    public Authority(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "name='" + name + '\'' +
                '}';
    }
}
