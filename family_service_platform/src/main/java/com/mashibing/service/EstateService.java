package com.mashibing.service;

import com.mashibing.bean.*;
import com.mashibing.vo.CellMessage;
import com.mashibing.vo.UnitMessage;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Description: com.mashibing.service
 */

public interface EstateService {
    List<TblCompany> selectCompanies();

    Integer insertEstate(FcEstate fcEstate);

    List<FcBuilding> selectBuilding(Integer buildingNumber, String estateCode);

    List<FcBuilding> selectBuildingByEstate(String estateCode);

    Integer updateBuilding(FcBuilding fcBuilding);

    List<FcUnit> selectUnit(UnitMessage unitMessage);

    Integer updateUnit(FcUnit fcUnit);

    List<FcCell> insertCell(CellMessage[] cellMessages);

    List<FcUnit> selectUnitByBuildingCode(String buildingCode);

    List<FcCell> selectCell(String unitCode);
}
