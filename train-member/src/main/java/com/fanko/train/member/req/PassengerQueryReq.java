package com.fanko.train.member.req;

import com.fanko.train.common.req.PageReq;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class PassengerQueryReq extends PageReq {
    private Long memberId;
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PassengerQueryReq{");
        sb.append("memberId=").append(memberId);
        sb.append('}');
        return sb.toString();
    }
}