package com.fanko.train.business.mq;

import com.fanko.train.business.dto.ConfirmOrderDelayMQDto;
import com.fanko.train.business.service.TicketDelayService;
import jakarta.annotation.Resource;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RocketMQMessageListener(consumerGroup = "default", topic = "TICKET_DELAY")
public class TicketDelayConsumer implements RocketMQListener<ConfirmOrderDelayMQDto> {
    private static final Logger LOG = LoggerFactory.getLogger(TicketDelayConsumer.class);
    @Resource
    private TicketDelayService ticketDelayService;


    @Override
    public void onMessage(ConfirmOrderDelayMQDto confirmOrderDelayMQDto) {
        LOG.info("ROCKETMQ收到消息：{}", confirmOrderDelayMQDto.toString());
        ticketDelayService.doTicketDelay(confirmOrderDelayMQDto);
    }
}
