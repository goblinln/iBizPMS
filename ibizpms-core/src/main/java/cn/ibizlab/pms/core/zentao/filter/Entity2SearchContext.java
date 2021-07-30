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
import cn.ibizlab.pms.core.zentao.domain.Entity2;
/**
 * 关系型数据实体[Entity2] 查询条件对象
 */
@Slf4j
@Data
public class Entity2SearchContext extends QueryWrapperContext<Entity2> {

	private String n_entity2name_like;//[实体2名称]
	public void setN_entity2name_like(String n_entity2name_like) {
        this.n_entity2name_like = n_entity2name_like;
        if(!ObjectUtils.isEmpty(this.n_entity2name_like)){
            this.getSearchCond().like("`entity2name`", n_entity2name_like);
        }
    }
	private String n_entity2id_eq;//[实体2标识]
	public void setN_entity2id_eq(String n_entity2id_eq) {
        this.n_entity2id_eq = n_entity2id_eq;
        if(!ObjectUtils.isEmpty(this.n_entity2id_eq)){
            this.getSearchCond().eq("`entity2id`", n_entity2id_eq);
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
                     wrapper.like("`entity2name`", query)
            );
		 }
	}
}



