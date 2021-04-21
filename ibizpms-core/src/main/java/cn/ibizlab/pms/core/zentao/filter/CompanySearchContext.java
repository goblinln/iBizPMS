package cn.ibizlab.pms.core.zentao.filter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.fastjson.annotation.JSONField;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;


import cn.ibizlab.pms.util.filter.QueryWrapperContext;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.ibizlab.pms.core.zentao.domain.Company;
/**
 * 关系型数据实体[Company] 查询条件对象
 */
@Slf4j
@Data
public class CompanySearchContext extends QueryWrapperContext<Company> {

	private String n_name_like;//[公司名称]
	public void setN_name_like(String n_name_like) {
        this.n_name_like = n_name_like;
    }
	private String n_guest_eq;//[匿名登陆]
	public void setN_guest_eq(String n_guest_eq) {
        this.n_guest_eq = n_guest_eq;
    }

    /**
	 * 启用快速搜索
	 */
    @Override
	public void setQuery(String query)
	{
		 this.query=query;
		 if(!StringUtils.isEmpty(query)){
            this.getSearchCond().and( wrapper ->
                     wrapper.like("t1.`NAME`", query)
            );
		 }
	}
}



