package com.mashibing.mapper;

import com.mashibing.bean.TblCompany;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 企业档案 Mapper 接口
 * </p>
 *
 * @author lian
 * @since 2022-04-26
 */

@Component
public interface TblCompanyMapper extends BaseMapper<TblCompany> {
    List<TblCompany> selectCompanies();
}
