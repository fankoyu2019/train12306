package com.fanko.train.business.service;

import com.fanko.train.business.domain.ConfirmOrder;
import com.fanko.train.business.domain.DailyTrainSeat;
import com.fanko.train.business.dto.ConfirmOrderDelayMQDto;
import com.fanko.train.business.enums.ConfirmOrderStatusEnum;
import com.fanko.train.business.mapper.ConfirmOrderMapper;
import com.fanko.train.business.mapper.DailyTrainSeatMapper;
import com.fanko.train.business.mapper.customer.DailyTrainTicketMapperCust;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

public class TicketDelayService {
    private static final Logger LOG = LoggerFactory.getLogger(TicketDelayService.class);
    @Resource
    private DailyTrainTicketMapperCust dailyTrainTicketMapperCust;
    @Resource
    private ConfirmOrderMapper confirmOrderMapper;
    @Resource
    private DailyTrainSeatMapper dailyTrainSeatMapper;

    public void doTicketDelay(ConfirmOrderDelayMQDto confirmOrderDelayMQDto) {
        // 检验这个订单是否已付款，没有则取消订单并添加库存

        ConfirmOrder order = confirmOrderMapper.selectByPrimaryKey(confirmOrderDelayMQDto.getId());
        String status = order.getStatus();
        if (!status.equals(ConfirmOrderStatusEnum.WAITPAYMENT.getCode())) {
            return;
        }
        // 更新订单状态为超时取消
        ConfirmOrder confirmOrderForUpdate = new ConfirmOrder();
        confirmOrderForUpdate.setId(confirmOrderDelayMQDto.getId());
        confirmOrderForUpdate.setUpdateTime(new Date());
        confirmOrderForUpdate.setStatus(ConfirmOrderStatusEnum.CANCEL.getCode());
        confirmOrderMapper.updateByPrimaryKeySelective(confirmOrderForUpdate);

        // TODO: 更新库存
        List<Long> seatId = confirmOrderDelayMQDto.getSeatId();
        for (int i = 0; i < seatId.size(); i++) {
            // 1.更新余票数量
            dailyTrainTicketMapperCust.updateCountByDelay(
                    confirmOrderDelayMQDto.getDate(),
                    confirmOrderDelayMQDto.getTrainCode(),
                    confirmOrderDelayMQDto.getSeatType(),
                    confirmOrderDelayMQDto.getMinStartIndex().get(i),
                    confirmOrderDelayMQDto.getMaxStartIndex().get(i),
                    confirmOrderDelayMQDto.getMinEndIndex().get(i),
                    confirmOrderDelayMQDto.getMaxEndIndex().get(i)
            );
            // 2.恢复座位售票状态
            Integer startIndex = confirmOrderDelayMQDto.getStartIndex();
            Integer endIndex = confirmOrderDelayMQDto.getEndIndex();
            String sell = confirmOrderDelayMQDto.getSell().get(i);
            sell = sell.substring(0,startIndex) +
                    sell.substring(startIndex,endIndex).replace('1','0') +
                    sell.substring(endIndex);
            DailyTrainSeat seatForUpdate = new DailyTrainSeat();
            seatForUpdate.setId(confirmOrderDelayMQDto.getSeatId().get(i));
            seatForUpdate.setSell(sell);
            seatForUpdate.setUpdateTime(new Date());
            dailyTrainSeatMapper.updateByPrimaryKeySelective(seatForUpdate);
        }

    }
}
