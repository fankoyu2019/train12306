package com.fanko.train.member.controller.admin;

import com.fanko.train.common.context.LoginMemberContext;
import com.fanko.train.common.req.MemberTicketReq;
import com.fanko.train.common.resp.CommonResp;
import com.fanko.train.common.resp.PageResp;
import com.fanko.train.member.req.TicketQueryReq;
import com.fanko.train.member.req.TicketSaveReq;
import com.fanko.train.member.resp.TicketQueryResp;
import com.fanko.train.member.service.TicketService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/ticket")
public class TicketAdminController {
    @Resource
    private TicketService ticketService;


    @GetMapping("/query-list")
    public CommonResp<PageResp<TicketQueryResp>> queryList(@Valid TicketQueryReq req) {
        PageResp<TicketQueryResp> list = ticketService.queryList(req);
        return new CommonResp<>(list);
    }

}
