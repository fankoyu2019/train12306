package com.fanko.train.business.dto;

import java.util.Date;
import java.util.List;

public class ConfirmOrderDelayMQDto {
    private Long id;
    private List<Long> seatId;
    private List<String> sell;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 日期
     */
    private Date date;
    /**
     * 车次编号
     */
    private String trainCode;
    /*
     * 订单中车票数量*/
    private Integer count;
    /*
     * 座位类型
     * */
    private String seatType;

    /*订票起始站*/
    private Integer startIndex;
    /*订票终点站*/
    private Integer endIndex;
    private List<Integer> minStartIndex;
    private List<Integer> maxStartIndex;
    private List<Integer> minEndIndex;
    private List<Integer> maxEndIndex;

    public List<Long> getSeatId() {
        return seatId;
    }

    public void setSeatId(List<Long> seatId) {
        this.seatId = seatId;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public List<Integer> getMinStartIndex() {
        return minStartIndex;
    }

    public void setMinStartIndex(List<Integer> minStartIndex) {
        this.minStartIndex = minStartIndex;
    }

    public List<Integer> getMaxStartIndex() {
        return maxStartIndex;
    }

    public void setMaxStartIndex(List<Integer> maxStartIndex) {
        this.maxStartIndex = maxStartIndex;
    }

    public List<Integer> getMinEndIndex() {
        return minEndIndex;
    }

    public void setMinEndIndex(List<Integer> minEndIndex) {
        this.minEndIndex = minEndIndex;
    }

    public List<Integer> getMaxEndIndex() {
        return maxEndIndex;
    }

    public void setMaxEndIndex(List<Integer> maxEndIndex) {
        this.maxEndIndex = maxEndIndex;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ConfirmOrderDelayMQDto{");
        sb.append("id=").append(id);
        sb.append(", seatId=").append(seatId);
        sb.append(", date=").append(date);
        sb.append(", trainCode='").append(trainCode).append('\'');
        sb.append(", count=").append(count);
        sb.append(", seatType='").append(seatType).append('\'');
        sb.append(", startIndex=").append(startIndex);
        sb.append(", endIndex=").append(endIndex);
        sb.append(", minStartIndex=").append(minStartIndex);
        sb.append(", maxStartIndex=").append(maxStartIndex);
        sb.append(", minEndIndex=").append(minEndIndex);
        sb.append(", maxEndIndex=").append(maxEndIndex);
        sb.append('}');
        return sb.toString();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    public void setSell(List<String> sell) {
        this.sell = sell;
    }

    public List<String> getSell() {
        return sell;
    }
}
