package com.fanko.train.business.req;

import com.fanko.train.common.req.PageReq;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

public class DailyTrainTicketQueryReq extends PageReq {
    /**
     * 日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    /**
     * 车次编号
     */
    private String trainCode;

    /*
     * 出发站
     * */
    private String start;
    /*
     * 到达站
     * */
    private String end;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DailyTrainTicketQueryReq{");
        sb.append("date=").append(date);
        sb.append(", trainCode='").append(trainCode).append('\'');
        sb.append(", start='").append(start).append('\'');
        sb.append(", end='").append(end).append('\'');
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

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyTrainTicketQueryReq that = (DailyTrainTicketQueryReq) o;
        return Objects.equals(date, that.date) && Objects.equals(trainCode, that.trainCode)
                && Objects.equals(start, that.start)
                && Objects.equals(end, that.end)
                && Objects.equals(((DailyTrainTicketQueryReq) o).getPage(), that.getPage())
                && Objects.equals(((DailyTrainTicketQueryReq) o).getSize(), that.getSize());
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, trainCode, start, end, getPage(), getSize());
    }
}
