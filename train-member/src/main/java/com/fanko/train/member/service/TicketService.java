package com.fanko.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.fanko.train.common.req.MemberTicketReq;
import com.fanko.train.common.resp.PageResp;
import com.fanko.train.common.util.SnowUtil;
import com.fanko.train.member.domain.Ticket;
import com.fanko.train.member.domain.TicketExample;
import com.fanko.train.member.mapper.TicketMapper;
import com.fanko.train.member.req.TicketQueryReq;
import com.fanko.train.member.req.TicketSaveReq;
import com.fanko.train.member.resp.TicketQueryResp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.seata.core.context.RootContext;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private static final Logger LOG = LoggerFactory.getLogger(TicketService.class);
    @Resource
    private TicketMapper ticketMapper;

    public void save(MemberTicketReq req) throws Exception {
        LOG.info("seata全局事务ID：{}", RootContext.getXID());
        DateTime now = DateTime.now();
        Ticket ticket = BeanUtil.copyProperties(req, Ticket.class);
        ticket.setId(SnowUtil.getSnowflakeNextId());
        ticket.setCreateTime(now);
        ticket.setUpdateTime(now);
        ticketMapper.insert(ticket);
//         模仿被调用方出现异常
//        if (1==1){
//            throw new Exception("测试异常111");
//        }
    }

    public PageResp<TicketQueryResp> queryList(TicketQueryReq req) {
        TicketExample ticketExample = new TicketExample();
        ticketExample.setOrderByClause("id desc");
        TicketExample.Criteria criteria = ticketExample.createCriteria();
        if (ObjectUtil.isNotNull(req.getMemberId())){
            criteria.andMemberIdEqualTo(req.getMemberId());
        }


        LOG.info("查询页码：{}", req.getPage());
        LOG.info("每页条数：{}", req.getSize());

        PageHelper.startPage(req.getPage(), req.getSize());
        List<Ticket> ticketList = ticketMapper.selectByExample(ticketExample);

        PageInfo<Ticket> pageInfo = new PageInfo<>(ticketList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        List<TicketQueryResp> list = BeanUtil.copyToList(ticketList, TicketQueryResp.class);

        PageResp<TicketQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

    public void delete(Long id) {
        ticketMapper.deleteByPrimaryKey(id);
    }
}
