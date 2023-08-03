package com.fanko.train.business.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fanko.train.business.req.ConfirmOrderDoReq;
import com.fanko.train.business.req.ConfirmOrderQueryReq;
import com.fanko.train.business.resp.ConfirmOrderQueryResp;
import com.fanko.train.business.service.ConfirmOrderService;
import com.fanko.train.common.exception.BusinessException;
import com.fanko.train.common.exception.BusinessExceptionEnum;
import com.fanko.train.common.resp.CommonResp;
import com.fanko.train.common.resp.PageResp;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/confirm-order")
public class ConfirmOrderController {
    private static final Logger LOG = LoggerFactory.getLogger(ConfirmOrderController.class);
    @Resource
    private ConfirmOrderService confirmOrderService;

    @PostMapping("/do")
//    接口资源名不能与接口路径一致，会导致限流后不走到降级方法中
    @SentinelResource(value = "confirmOrderDo",blockHandler = "doConfirmBlock")
    public CommonResp<Object> doConfirm(@Valid @RequestBody ConfirmOrderDoReq req) {
        confirmOrderService.doConfirm(req);
        return new CommonResp<>();
    }
    /*
        降级方法，需包含限流方法的所有参数和BlackException参数
        * */
    public CommonResp<Object> doConfirmBlock(ConfirmOrderDoReq req, BlockException e){
        LOG.info("购票请求被限流：{}",req);
//        throw new BusinessException(BusinessExceptionEnum.CONFIRM_ORDER_FLOW_EXCEPTION);
        CommonResp<Object> commonResp = new CommonResp<>();
        commonResp.setSuccess(false);
        commonResp.setMessage(BusinessExceptionEnum.CONFIRM_ORDER_FLOW_EXCEPTION.getDesc());
        return commonResp;
    }

}
