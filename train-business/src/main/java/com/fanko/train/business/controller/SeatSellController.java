package com.fanko.train.business.controller;

import com.fanko.train.business.req.SeatSellReq;
import com.fanko.train.business.resp.SeatSellResp;
import com.fanko.train.business.service.DailyTrainSeatService;
import com.fanko.train.common.resp.CommonResp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seat-sell")
public class SeatSellController {

    @Autowired
    DailyTrainSeatService dailyTrainSeatService;

    @GetMapping("/query")
    public CommonResp<List<SeatSellResp>> query(@Valid SeatSellReq req){
        List<SeatSellResp> seatList = dailyTrainSeatService.querySeatSell(req);
        return new CommonResp<>(seatList);
    }

}
