package com.renewable.gateway.controller.portal;

import com.github.pagehelper.PageInfo;
import com.renewable.gateway.common.ServerResponse;
import com.renewable.gateway.service.ISensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description：
 * @Author: jarry
 */
@Controller
@RequestMapping("/sensor/data/")
public class SensorDataController {

    @Autowired
    private ISensorDataService iSensorDataService;


    //根据传感器id，pageHelper参数获取对应监控数据  //采用pageHelper技术
    @RequestMapping(value = "get_data_list.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo> getDataList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                int sensor_identifier) {    //这里的snesor_id指的是类似0x68这种标示符，并不是后来所作的传感器注册表中的id
        return iSensorDataService.getDataList(pageNum, pageSize, sensor_identifier);
    }

    //根据传感器id，时间区域获取对应监控数据
    @RequestMapping(value = "get_data_list_time.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<List<Object>> getDataListByTime(@RequestParam(value = "startTime", defaultValue = "1970-1-1 0:0:0") String startTime,
                                                          @RequestParam(value = "endTime", defaultValue = "2919-33-14 11:33:54") String endTime,
                                                          int sensor_identifier) {
        return iSensorDataService.getDataListByTime(startTime, endTime, sensor_identifier);
    }

//    //对内测试专用接口
//    @RequestMapping(value = "test_send_data.do", method = RequestMethod.GET)
//    @ResponseBody
//    public ServerResponse testSendData(){
//        return iSensorDataService.sendDataDemo();
//    }
}