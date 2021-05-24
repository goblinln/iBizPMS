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
import cn.ibizlab.pms.core.ibiz.domain.IBZCaseHistory;
/**
 * 关系型数据实体[IBZCaseHistory] 查询条件对象
 */
@Slf4j
@Data
public class IBZCaseHistorySearchContext extends QueryWrapperContext<IBZCaseHistory> {

                
	private String n_diff_like;//[不同]
	public void setN_diff_like(String n_diff_like) {
        this.n_diff_like = n_diff_like;
    }
                
	private Long n_id_eq;//[id]
	public void setN_id_eq(Long n_id_eq) {
        this.n_id_eq = n_id_eq;
    }
                
	private Long n_action_eq;//[id]
	public void setN_action_eq(Long n_action_eq) {
        this.n_action_eq = n_action_eq;
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
                     wrapper.like("t1.`DIFF`", query)
            );
		 }
	}
}



