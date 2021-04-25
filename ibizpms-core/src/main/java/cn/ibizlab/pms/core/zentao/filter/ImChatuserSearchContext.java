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
import cn.ibizlab.pms.core.zentao.domain.ImChatuser;
/**
 * 关系型数据实体[ImChatuser] 查询条件对象
 */
@Slf4j
@Data
public class ImChatuserSearchContext extends QueryWrapperContext<ImChatuser> {

	private Long n_id_eq;//[id]
	public void setN_id_eq(Long n_id_eq) {
        this.n_id_eq = n_id_eq;
    }
	private String n_freeze_eq;//[freeze]
	public void setN_freeze_eq(String n_freeze_eq) {
        this.n_freeze_eq = n_freeze_eq;
    }
	private String n_star_eq;//[star]
	public void setN_star_eq(String n_star_eq) {
        this.n_star_eq = n_star_eq;
    }
	private String n_hide_eq;//[hide]
	public void setN_hide_eq(String n_hide_eq) {
        this.n_hide_eq = n_hide_eq;
    }
	private String n_mute_eq;//[mute]
	public void setN_mute_eq(String n_mute_eq) {
        this.n_mute_eq = n_mute_eq;
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
                     wrapper.like("t1.`ID`", query)
            );
		 }
	}
}



