package com.fanko.train.business.mapper.customer;

import java.util.Date;

public interface DailyTrainTicketMapperCust {
    void updateCountBySell(
            Date date,
            String trainCode,
            String seatTypeCode,
            Integer minStartIndex,
            Integer maxStartIndex,
            Integer minEndIndex,
            Integer maxEndIndex);
}
