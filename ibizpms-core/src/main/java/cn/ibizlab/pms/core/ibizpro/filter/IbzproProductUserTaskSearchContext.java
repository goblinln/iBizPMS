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
import cn.ibizlab.pms.core.ibizpro.domain.IbzproProductUserTask;
/**
 * 关系型数据实体[IbzproProductUserTask] 查询条件对象
 */
@Slf4j
@Data
public class IbzproProductUserTaskSearchContext extends QueryWrapperContext<IbzproProductUserTask> {

                
	private String n_tasktype_eq;//[任务类型]
	public void setN_tasktype_eq(String n_tasktype_eq) {
        this.n_tasktype_eq = n_tasktype_eq;
    }
                
	private Long n_id_eq;//[编号]
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
                     wrapper.like("t1.`ID`", query)
            );
		 }
	}
}



