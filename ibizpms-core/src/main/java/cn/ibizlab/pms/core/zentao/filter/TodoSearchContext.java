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
import cn.ibizlab.pms.core.zentao.domain.Todo;
/**
 * 关系型数据实体[Todo] 查询条件对象
 */
@Slf4j
@Data
public class TodoSearchContext extends QueryWrapperContext<Todo> {

	private String n_account_eq;//[所有者]
	public void setN_account_eq(String n_account_eq) {
        this.n_account_eq = n_account_eq;
    }
	private String n_config_type_eq;//[周期类型]
	public void setN_config_type_eq(String n_config_type_eq) {
        this.n_config_type_eq = n_config_type_eq;
    }
	private String n_type_eq;//[类型]
	public void setN_type_eq(String n_type_eq) {
        this.n_type_eq = n_type_eq;
    }
	private String n_type_noteq;//[类型]
	public void setN_type_noteq(String n_type_noteq) {
        this.n_type_noteq = n_type_noteq;
    }
	private Integer n_end_eq;//[结束]
	public void setN_end_eq(Integer n_end_eq) {
        this.n_end_eq = n_end_eq;
    }
	private Integer n_begin_eq;//[开始]
	public void setN_begin_eq(Integer n_begin_eq) {
        this.n_begin_eq = n_begin_eq;
    }
	private Integer n_cycle_eq;//[周期]
	public void setN_cycle_eq(Integer n_cycle_eq) {
        this.n_cycle_eq = n_cycle_eq;
    }
	private String n_status_eq;//[状态]
	public void setN_status_eq(String n_status_eq) {
        this.n_status_eq = n_status_eq;
    }
	private String n_status_noteq;//[状态]
	public void setN_status_noteq(String n_status_noteq) {
        this.n_status_noteq = n_status_noteq;
    }
	private String n_name_like;//[待办名称]
	public void setN_name_like(String n_name_like) {
        this.n_name_like = n_name_like;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Timestamp n_date_eq;//[日期]
	public void setN_date_eq(Timestamp n_date_eq) {
        this.n_date_eq = n_date_eq;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Timestamp n_date_gtandeq;//[日期]
	public void setN_date_gtandeq(Timestamp n_date_gtandeq) {
        this.n_date_gtandeq = n_date_gtandeq;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Timestamp n_date_ltandeq;//[日期]
	public void setN_date_ltandeq(Timestamp n_date_ltandeq) {
        this.n_date_ltandeq = n_date_ltandeq;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Timestamp n_date_noteq;//[日期]
	public void setN_date_noteq(Timestamp n_date_noteq) {
        this.n_date_noteq = n_date_noteq;
    }
	private String n_private_eq;//[私人事务]
	public void setN_private_eq(String n_private_eq) {
        this.n_private_eq = n_private_eq;
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



