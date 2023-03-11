package com.dc.contrlloer;

import com.dc.model.HistorytradeInfo;
import com.dc.service.Monitor_881;
import com.dc.service.bigScreen.Top10ValService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/getTop10Vol")
public class GetTop10Vol {

    @Autowired
    Top10ValService top10ValService;



    @RequestMapping(value = "top10Vol", method = RequestMethod.GET)
    public List<HistorytradeInfo> getTop10Val(String endDateStr) throws Exception {
        List<HistorytradeInfo> hisList =  top10ValService.getTop10Val(endDateStr);
        return hisList;
    }
}
