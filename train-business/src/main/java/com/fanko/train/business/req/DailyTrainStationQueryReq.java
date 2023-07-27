package com.fanko.train.business.req;

import com.fanko.train.common.req.PageReq;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DailyTrainStationQueryReq extends PageReq {
    /**
     * 日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    /**
     * 车次编号
     */
    private String trainCode;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DailyTrainStationQueryReq{");
        sb.append("date=").append(date);
        sb.append(", trainCode='").append(trainCode).append('\'');
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
}
