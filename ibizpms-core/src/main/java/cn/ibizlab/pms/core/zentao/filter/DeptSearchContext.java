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
import cn.ibizlab.pms.core.zentao.domain.Dept;
/**
 * 关系型数据实体[Dept] 查询条件对象
 */
@Slf4j
@Data
public class DeptSearchContext extends QueryWrapperContext<Dept> {

	private String n_name_like;//[name]
	public void setN_name_like(String n_name_like) {
        this.n_name_like = n_name_like;
        if(!ObjectUtils.isEmpty(this.n_name_like)){
            this.getSelectCond().like("name", n_name_like);
        }
    }
	private String n_parentname_eq;//[parent]
	public void setN_parentname_eq(String n_parentname_eq) {
        this.n_parentname_eq = n_parentname_eq;
        if(!ObjectUtils.isEmpty(this.n_parentname_eq)){
            this.getSelectCond().eq("parentname", n_parentname_eq);
        }
    }
	private String n_parentname_like;//[parent]
	public void setN_parentname_like(String n_parentname_like) {
        this.n_parentname_like = n_parentname_like;
        if(!ObjectUtils.isEmpty(this.n_parentname_like)){
            this.getSelectCond().like("parentname", n_parentname_like);
        }
    }
	private BigInteger n_parent_eq;//[parent]
	public void setN_parent_eq(BigInteger n_parent_eq) {
        this.n_parent_eq = n_parent_eq;
        if(!ObjectUtils.isEmpty(this.n_parent_eq)){
            this.getSelectCond().eq("parent", n_parent_eq);
        }
    }

    /**
	 * 启用快速搜索
	 */
	public void setQuery(String query)
	{
		 this.query=query;
		 if(!StringUtils.isEmpty(query)){
            this.getSelectCond().and( wrapper ->
                     wrapper.like("name", query)   
            );
		 }
	}
}




