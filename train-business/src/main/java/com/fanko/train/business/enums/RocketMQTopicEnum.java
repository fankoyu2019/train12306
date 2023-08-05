package com.fanko.train.business.enums;

public enum RocketMQTopicEnum {
    CONFIRM_ORDER("CONFIRM_ORDER","确认订单排队");
    private final String code;

    private final String desc;

    RocketMQTopicEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RocketMQTopicEnum{");
        sb.append("code='").append(code).append('\'');
        sb.append(", desc='").append(desc).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
