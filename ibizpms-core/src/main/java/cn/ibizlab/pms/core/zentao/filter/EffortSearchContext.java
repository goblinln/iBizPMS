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
import cn.ibizlab.pms.core.zentao.domain.Effort;
/**
 * 关系型数据实体[Effort] 查询条件对象
 */
@Slf4j
@Data
public class EffortSearchContext extends QueryWrapperContext<Effort> {

                
	private String n_status_eq;//[status]
	public void setN_status_eq(String n_status_eq) {
        this.n_status_eq = n_status_eq;
    }
                
	private String n_name_like;//[name]
	public void setN_name_like(String n_name_like) {
        this.n_name_like = n_name_like;
    }
                
	private String n_todo_eq;//[todo]
	public void setN_todo_eq(String n_todo_eq) {
        this.n_todo_eq = n_todo_eq;
    }
                
	private String n_type_eq;//[type]
	public void setN_type_eq(String n_type_eq) {
        this.n_type_eq = n_type_eq;
    }
                
	private Long n_id_eq;//[id]
	public void setN_id_eq(Long n_id_eq) {
        this.n_id_eq = n_id_eq;
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



