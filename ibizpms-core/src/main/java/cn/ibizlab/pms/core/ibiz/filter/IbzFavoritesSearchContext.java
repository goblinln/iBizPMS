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
import cn.ibizlab.pms.core.ibiz.domain.IbzFavorites;
/**
 * 关系型数据实体[IbzFavorites] 查询条件对象
 */
@Slf4j
@Data
public class IbzFavoritesSearchContext extends QueryWrapperContext<IbzFavorites> {

                
	private String n_type_eq;//[类型]
	public void setN_type_eq(String n_type_eq) {
        this.n_type_eq = n_type_eq;
    }
                
	private String n_ibz_favoritesid_eq;//[收藏标识]
	public void setN_ibz_favoritesid_eq(String n_ibz_favoritesid_eq) {
        this.n_ibz_favoritesid_eq = n_ibz_favoritesid_eq;
    }
                
	private Long n_objectid_eq;//[数据对象标识]
	public void setN_objectid_eq(Long n_objectid_eq) {
        this.n_objectid_eq = n_objectid_eq;
    }
                
	private String n_account_eq;//[收藏用户]
	public void setN_account_eq(String n_account_eq) {
        this.n_account_eq = n_account_eq;
    }
                
	private String n_ibz_favoritesname_like;//[收藏名称]
	public void setN_ibz_favoritesname_like(String n_ibz_favoritesname_like) {
        this.n_ibz_favoritesname_like = n_ibz_favoritesname_like;
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
                     wrapper.like("t1.`IBZ_FAVORITESNAME`", query)
            );
		 }
	}
}



