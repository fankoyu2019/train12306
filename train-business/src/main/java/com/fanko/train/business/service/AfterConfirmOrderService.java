package com.fanko.train.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.fanko.train.business.domain.*;
import com.fanko.train.business.enums.ConfirmOrderStatusEnum;
import com.fanko.train.business.enums.SeatColEnum;
import com.fanko.train.business.enums.SeatTypeEnum;
import com.fanko.train.business.mapper.ConfirmOrderMapper;
import com.fanko.train.business.mapper.DailyTrainSeatMapper;
import com.fanko.train.business.req.ConfirmOrderDoReq;
import com.fanko.train.business.req.ConfirmOrderQueryReq;
import com.fanko.train.business.req.ConfirmOrderTicketReq;
import com.fanko.train.business.resp.ConfirmOrderQueryResp;
import com.fanko.train.common.context.LoginMemberContext;
import com.fanko.train.common.exception.BusinessException;
import com.fanko.train.common.exception.BusinessExceptionEnum;
import com.fanko.train.common.resp.PageResp;
import com.fanko.train.common.util.SnowUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AfterConfirmOrderService {
    private static final Logger LOG = LoggerFactory.getLogger(AfterConfirmOrderService.class);
    @Resource
    private DailyTrainSeatMapper dailyTrainSeatMapper;

    /*
      选中座位后事务处理：
      座位表修改售卖情况sell；
      余票详情表修改余票；
      为会员增加购票记录
      更新确认订单为成功
    * */
    @Transactional
    public void afterDoConfirm(List<DailyTrainSeat> finalSeatList) {
        for(DailyTrainSeat dailyTrainSeat:finalSeatList){
            DailyTrainSeat seatForUpdate = new DailyTrainSeat();
            seatForUpdate.setId(dailyTrainSeat.getId());
            seatForUpdate.setSell(dailyTrainSeat.getSell());
            seatForUpdate.setUpdateTime(new Date());
            dailyTrainSeatMapper.updateByPrimaryKeySelective(seatForUpdate);
        }
    }

}
