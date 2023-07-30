package com.fanko.train.business.controller;

import com.fanko.train.business.req.TrainQueryReq;
import com.fanko.train.business.req.TrainSaveReq;
import com.fanko.train.business.resp.TrainQueryResp;
import com.fanko.train.business.service.TrainSeatService;
import com.fanko.train.business.service.TrainService;
import com.fanko.train.common.resp.CommonResp;
import com.fanko.train.common.resp.PageResp;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/train")
public class TrainController {
    @Resource
    private TrainService trainService;

    @GetMapping("/query-all")
    public CommonResp<List<TrainQueryResp>> queryList() {
        List<TrainQueryResp> list = trainService.queryAll();
        return new CommonResp<>(list);
    }
}
