package com.fanko.train.business.controller.admin;

import com.fanko.train.common.context.LoginMemberContext;
import com.fanko.train.common.resp.CommonResp;
import com.fanko.train.common.resp.PageResp;
import com.fanko.train.business.req.DailyTrainStationQueryReq;
import com.fanko.train.business.req.DailyTrainStationSaveReq;
import com.fanko.train.business.resp.DailyTrainStationQueryResp;
import com.fanko.train.business.service.DailyTrainStationService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/daily-train-station")
public class DailyTrainStationAdminController {
    @Resource
    private DailyTrainStationService dailyTrainStationService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody DailyTrainStationSaveReq req) {
        dailyTrainStationService.save(req);
        return new CommonResp<>();
    }
    @GetMapping("/query-list")
    public CommonResp<PageResp<DailyTrainStationQueryResp>> queryList(@Valid DailyTrainStationQueryReq req) {
        PageResp<DailyTrainStationQueryResp> list = dailyTrainStationService.queryList(req);
        return new CommonResp<>(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        dailyTrainStationService.delete(id);
        return new CommonResp<>();
    }

}
