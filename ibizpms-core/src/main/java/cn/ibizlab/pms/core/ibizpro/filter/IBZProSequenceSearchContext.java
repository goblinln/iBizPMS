package cn.ibizlab.pms.core.ibizpro.filter;

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
import cn.ibizlab.pms.core.ibizpro.domain.IBZProSequence;
/**
 * 关系型数据实体[IBZProSequence] 查询条件对象
 */
@Slf4j
@Data
public class IBZProSequenceSearchContext extends QueryWrapperContext<IBZProSequence> {

	private String n_ibzpro_sequencename_like;//[业务序列表名称]
	public void setN_ibzpro_sequencename_like(String n_ibzpro_sequencename_like) {
        this.n_ibzpro_sequencename_like = n_ibzpro_sequencename_like;
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
                     wrapper.like("t1.`IBZPRO_SEQUENCENAME`", query)
            );
		 }
	}
}



