package cn.ibizlab.pms.core.ibiz.filter;

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
import cn.ibizlab.pms.core.ibiz.domain.TestModule;
/**
 * 关系型数据实体[TestModule] 查询条件对象
 */
@Slf4j
@Data
public class TestModuleSearchContext extends QueryWrapperContext<TestModule> {

                
	private Integer n_branch_eq;//[branch]
	public void setN_branch_eq(Integer n_branch_eq) {
        this.n_branch_eq = n_branch_eq;
    }
                
	private Long n_id_eq;//[id]
	public void setN_id_eq(Long n_id_eq) {
        this.n_id_eq = n_id_eq;
    }
                
	private String n_name_like;//[名称]
	public void setN_name_like(String n_name_like) {
        this.n_name_like = n_name_like;
    }
                
	private String n_parentname_eq;//[上级模块]
	public void setN_parentname_eq(String n_parentname_eq) {
        this.n_parentname_eq = n_parentname_eq;
    }
                
	private String n_parentname_like;//[上级模块]
	public void setN_parentname_like(String n_parentname_like) {
        this.n_parentname_like = n_parentname_like;
    }
                
	private String n_rootname_eq;//[测试]
	public void setN_rootname_eq(String n_rootname_eq) {
        this.n_rootname_eq = n_rootname_eq;
    }
                
	private String n_rootname_like;//[测试]
	public void setN_rootname_like(String n_rootname_like) {
        this.n_rootname_like = n_rootname_like;
    }
                
	private Long n_root_eq;//[编号]
	public void setN_root_eq(Long n_root_eq) {
        this.n_root_eq = n_root_eq;
    }
                
	private Long n_parent_eq;//[id]
	public void setN_parent_eq(Long n_parent_eq) {
        this.n_parent_eq = n_parent_eq;
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
                     wrapper.like("name", query)
            );
		 }
	}
}



