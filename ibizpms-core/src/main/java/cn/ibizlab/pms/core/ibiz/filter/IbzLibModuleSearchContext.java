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
import cn.ibizlab.pms.core.ibiz.domain.IbzLibModule;
/**
 * 关系型数据实体[IbzLibModule] 查询条件对象
 */
@Slf4j
@Data
public class IbzLibModuleSearchContext extends QueryWrapperContext<IbzLibModule> {

	private Long n_id_eq;//[id]
	public void setN_id_eq(Long n_id_eq) {
        this.n_id_eq = n_id_eq;
        if(!ObjectUtils.isEmpty(this.n_id_eq)){
            this.getSearchCond().eq("`id`", n_id_eq);
        }
    }
	private String n_name_like;//[名称]
	public void setN_name_like(String n_name_like) {
        this.n_name_like = n_name_like;
        if(!ObjectUtils.isEmpty(this.n_name_like)){
            this.getSearchCond().like("`name`", n_name_like);
        }
    }
	private Long n_root_eq;//[编号]
	public void setN_root_eq(Long n_root_eq) {
        this.n_root_eq = n_root_eq;
        if(!ObjectUtils.isEmpty(this.n_root_eq)){
            this.getSearchCond().eq("`root`", n_root_eq);
        }
    }
	private Long n_parent_eq;//[id]
	public void setN_parent_eq(Long n_parent_eq) {
        this.n_parent_eq = n_parent_eq;
        if(!ObjectUtils.isEmpty(this.n_parent_eq)){
            this.getSearchCond().eq("`parent`", n_parent_eq);
        }
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
                     wrapper.like("`name`", query)
            );
		 }
	}
}



