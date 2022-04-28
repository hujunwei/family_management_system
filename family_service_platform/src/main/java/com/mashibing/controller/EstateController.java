package com.mashibing.controller;

import com.alibaba.fastjson.JSONObject;
import com.mashibing.bean.*;
import com.mashibing.returnJson.ReturnObject;
import com.mashibing.service.EstateService;
import com.mashibing.vo.CellMessage;
import com.mashibing.vo.UnitMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: com.mashibing.controller
 */

@RestController
public class EstateController {
    @Autowired
    private EstateService service;

    @RequestMapping("/estate/selectCompany")
    public String selectCompany() {
        List<TblCompany> tblCompanies = service.selectCompanies();
        ReturnObject returnObject = new ReturnObject(tblCompanies);
        return JSONObject.toJSONString(returnObject);
    }

    @RequestMapping("/estate/insertEstate")
    public String insertEstate(FcEstate fcEstate) {
        Integer res = service.insertEstate(fcEstate);
        if (res == 0) {
            return JSONObject.toJSONString(new ReturnObject("0", "插入成功"));
        } else {
            return JSONObject.toJSONString(new ReturnObject("1", "插入成功"));
        }
    }

    @RequestMapping("/estate/selectBuilding")
    public String selectBuilding(Integer buildingNumber, String estateCode) {
        List<FcBuilding> fcBuildingList = service.selectBuilding(buildingNumber, estateCode);
        return JSONObject.toJSONString(new ReturnObject(fcBuildingList));
    }

    @RequestMapping("/estate/selectBuildingByEstate")
    public String selectBuildingByEstate(String estateCode) {
        List<FcBuilding> fcBuildingList = service.selectBuildingByEstate(estateCode);
        return JSONObject.toJSONString(new ReturnObject(fcBuildingList));
    }

    @RequestMapping("/estate/updateBuilding")
    public String updateBuilding(FcBuilding fcBuilding) {
        Integer result = service.updateBuilding(fcBuilding);

        if (result == 1) {
            return JSONObject.toJSONString(new ReturnObject(("更新楼宇成功")));
        } else {
            return JSONObject.toJSONString(new ReturnObject(("更新楼宇失败")));
        }
    }

    @RequestMapping("/estate/selectUnit")
    public String selectUnit(@RequestBody UnitMessage[] unitMessages) {
        List<FcUnit> allUnits = new ArrayList<>();
        for (UnitMessage unitMessage : unitMessages) {
            allUnits.addAll(service.selectUnit(unitMessage));
        }

        return JSONObject.toJSONString(new ReturnObject(allUnits));
    }

    @RequestMapping("/estate/updateUnit")
    public String updateUnit(FcUnit fcUnit) {
        Integer result = service.updateUnit(fcUnit);
        if (result == 1) {
            return JSONObject.toJSONString(new ReturnObject(("更新单元成功")));
        } else {
            return JSONObject.toJSONString(new ReturnObject(("更新单元失败")));
        }
    }

    @RequestMapping("/estate/insertCell")
    public String incertCell(@RequestBody CellMessage[] cellMessages) {
        List<FcCell> fcCells = service.insertCell(cellMessages);
        return JSONObject.toJSONString(new ReturnObject(fcCells));
    }

    @RequestMapping("/estate/selectUnitByBuildingCode")
    public String selectUnitByBuildingCode(String buildingCode) {
        List<FcUnit> fcUnitList = service.selectUnitByBuildingCode(buildingCode);
        return JSONObject.toJSONString(new ReturnObject(fcUnitList));
    }

    @RequestMapping("/estate/selectCell")
    public String selectCell(String unitCode) {
        List<FcCell> fcCells = service.selectCell(unitCode);
        return JSONObject.toJSONString(new ReturnObject(fcCells));
    }
}
