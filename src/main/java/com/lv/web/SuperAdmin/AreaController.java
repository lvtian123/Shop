package com.lv.web.SuperAdmin;

import com.lv.entity.Area;
import com.lv.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/list")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/getarea", method = RequestMethod.GET)

    private Map<String, Object> getArea() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Area> list = new ArrayList<Area>();
        try {
            list = areaService.getAreaList();
            map.put("rows", list);
            map.put("total", list.size());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("error", e.toString());
        }
        return map;
    }
}
