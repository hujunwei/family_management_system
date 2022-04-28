package com.mashibing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mashibing.bean.*;
import com.mashibing.mapper.*;
import com.mashibing.service.EstateService;
import com.mashibing.vo.CellMessage;
import com.mashibing.vo.UnitMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: com.mashibing.service.impl
 */

@Service
public class EstateServiceImpl implements EstateService {
    @Autowired
    private TblCompanyMapper tblCompanyMapper;

    @Autowired
    private FcEstateMapper fcEstateMapper;

    @Autowired
    private FcBuildingMapper fcBuildingMapper;
    
    @Autowired
    private FcUnitMapper fcUnitMapper;

    @Autowired
    private FcCellMapper fcCellMapper;

    @Override
    public List<TblCompany> selectCompanies() {
        return tblCompanyMapper.selectCompanies();
    }

    @Override
    public Integer insertEstate(FcEstate fcEstate) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("estate_code", fcEstate.getEstateCode());
        FcEstate findResult = fcEstateMapper.selectOne(queryWrapper);
        int result = 0;
        if (findResult != null) {
            return result;
        } else {
            result = fcEstateMapper.insert(fcEstate);
        }

        return result;
    }

    @Override
    public List<FcBuilding> selectBuilding(Integer buildingNumber, String estateCode) {
        List<FcBuilding> fcBuildingList = new ArrayList<>();
        for (int i = 0; i < buildingNumber; i++) {
            FcBuilding fcBuilding = new FcBuilding();
            fcBuilding.setBuildingCode("B" + (i + 1));
            fcBuilding.setBuildingName("第" + (i + 1) + "号楼");
            fcBuilding.setEstateCode(estateCode);

            fcBuildingMapper.insert(fcBuilding);
            fcBuildingList.add(fcBuilding);
        }

        return fcBuildingList;
    }

    @Override
    public List<FcBuilding> selectBuildingByEstate(String estateCode) {
        QueryWrapper<FcBuilding> fcBuildingQueryWrapper = new QueryWrapper<>();
        fcBuildingQueryWrapper.eq("estate_code", estateCode);
        fcBuildingQueryWrapper.select("building_name", "building_code");
        List<FcBuilding> fcBuildingList = fcBuildingMapper.selectList(fcBuildingQueryWrapper);

        return fcBuildingList;
    }

    @Override
    public Integer updateBuilding(FcBuilding fcBuilding) {
        int rows = fcBuildingMapper.updateById(fcBuilding);
        return rows;
    }

    @Override
    public List<FcUnit> selectUnit(UnitMessage unitMessage) {
        List<FcUnit> fcUnits = new ArrayList<>();

        for (int i = 0; i < unitMessage.getUnitCount(); i++) {
            FcUnit fcUnit = new FcUnit();
            fcUnit.setBuildingCode(unitMessage.getBuildingCode());
            fcUnit.setUnitCode("U" + (i + 1));
            fcUnit.setUnitName("第" + (i + 1) + "单元");
            fcUnits.add(fcUnit);
            fcUnitMapper.insert(fcUnit);
        }

        return fcUnits;
    }

    @Override
    public Integer updateUnit(FcUnit fcUnit) {
        int rows = fcUnitMapper.updateById(fcUnit);
        return rows;
    }

    @Override
    public List<FcCell> insertCell(CellMessage[] cellMessages) {
        List<FcCell> lists = new ArrayList<>();
        for (CellMessage cellMessage : cellMessages) {

            for (int i = 1; i <= cellMessage.getStopFloor(); i++) {
                for (int j = cellMessage.getStartCellId(); j <= cellMessage.getStopCellId(); j++) {
                    FcCell fcCell = new FcCell();
                    fcCell.setUnitCode(cellMessage.getUnitCode());
                    fcCell.setCellName(i + "0" + j);
                    fcCell.setCellCode(cellMessage.getUnitCode() + "C" + i + "0" + j);
                    fcCell.setFloorNumber(i);

                    fcCellMapper.insert(fcCell);
                    lists.add(fcCell);
                }
            }
        }


        return lists;
    }

    @Override
    public List<FcUnit> selectUnitByBuildingCode(String buildingCode) {
        QueryWrapper<FcUnit> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("building_code", buildingCode);
        queryWrapper.select("unit_name", "unit_code");
        List<FcUnit> fcUnitList = fcUnitMapper.selectList(queryWrapper);

        return fcUnitList;
    }

    @Override
    public List<FcCell> selectCell(String unitCode) {
        QueryWrapper<FcCell> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("unit_code", unitCode);
        List<FcCell> fcCells = fcCellMapper.selectList(queryWrapper);

        return fcCells;
    }
}
