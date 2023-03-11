package com.dc.service.bigScreen;

import com.dc.dal.HistorytradeInfoRepository;
import com.dc.model.*;
import com.dc.service.Monitor_881;
import com.dc.utils.Helper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Top10ValService {

    @Autowired
    private HistorytradeInfoRepository historytradeInfoRepository;


    @Autowired
    private Helper helper;

    Logger logger = LogManager.getLogger(Monitor_881.class);

    public List<HistorytradeInfo> getTop10Val(String endDateStr) throws Exception
    {
        String tradeDate = helper.getTradeDate(endDateStr); // 要当天
        List<HistorytradeInfo> resultList2Days  = historytradeInfoRepository.findHistorytradeInfoByDate(tradeDate);
        return resultList2Days;
    }

}
