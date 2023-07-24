package com.fanko.train.business.enums;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;

public enum TrainTypeEnum {
    G("1", "高铁", new BigDecimal("1.2")),
    D("2", "动车", new BigDecimal("1")),
    K("3", "快速", new BigDecimal("0.8"));
    private String code;
    private String desc;

    /**
     * 票价比例，例：1.1，则票价 = 1.1 * 每公里单价（SeatTypeEnum.price） * 公里（station.km）
     */
    private BigDecimal priceRate;

    TrainTypeEnum(String code, String desc, BigDecimal priceRate) {
        this.code = code;
        this.desc = desc;
        this.priceRate = priceRate;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TrainTypeEnum{");
        sb.append("code='").append(code).append('\'');
        sb.append(", desc='").append(desc).append('\'');
        sb.append(", priceRate=").append(priceRate);
        sb.append('}');
        return sb.toString();
    }

    public static List<HashMap<String, String>> getEnumList() {
        List<HashMap<String, String>> list = new ArrayList<>();
        for (TrainTypeEnum anEnum : EnumSet.allOf(TrainTypeEnum.class)) {
            HashMap<String, String> map = new HashMap<>();
            map.put("code", anEnum.code);
            map.put("desc", anEnum.desc);
            list.add(map);
        }
        return list;
    }

    public BigDecimal getPriceRate() {
        return priceRate;
    }

    public void setPriceRate(BigDecimal priceRate) {
        this.priceRate = priceRate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
