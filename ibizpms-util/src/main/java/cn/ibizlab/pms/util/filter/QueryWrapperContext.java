package cn.ibizlab.pms.util.filter;

import cn.ibizlab.pms.util.helper.DEFieldCacheMap;
import cn.ibizlab.pms.util.security.SpringContextHolder;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.function.Consumer;

@Slf4j
@Data
public class QueryWrapperContext<T> extends SearchContextBase implements ISearchContext {

    @JsonIgnore
    @JSONField(serialize = false)
    private final ExpressionParser parser = new SpelExpressionParser();

    @JsonIgnore
    @JSONField(serialize = false)
    private QueryWrapper<T> selectCond=new QueryWrapper<T>();

    
    public String srfcustomizedcond;

    public void setsrfcustomizedcond(String srfcustomizedcond){
        this.srfcustomizedcond = srfcustomizedcond;
        //((QueryWrapper)selectCond).and(ScopeUtils.parse(srfcustomizedcond));
    }

    /**
     * 解析查询上下文中的参数，构建mybatis-plus分页对象
     * @return
     */
    @JsonIgnore
    @JSONField(serialize = false)
    public Page getPages(){
        Page page;
        List<String> asc_fieldList = new ArrayList<>();
        List<String> desc_fieldList = new ArrayList<>();

        int currentPage=getPageable().getPageNumber();
        int pageSize=getPageable().getPageSize();

        //构造mybatis-plus分页
        if(StringUtils.isEmpty(currentPage) || StringUtils.isEmpty(pageSize)) {
            page=new Page(1,Short.MAX_VALUE);
        }
        else {
            page=new Page(currentPage+1,pageSize);
        }

        //构造mybatis-plus排序
        Sort sort = getPageable().getSort();
        Iterator<Sort.Order> it_sort = sort.iterator();

        if(ObjectUtils.isEmpty(it_sort)) {
            return page;
        }

        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        Class<T> type = (Class<T>)parameterizedType.getActualTypeArguments()[0];
        while (it_sort.hasNext()) {
            Sort.Order sort_order = it_sort.next();
            if(sort_order.getDirection()== Sort.Direction.ASC) {
                page.addOrder(OrderItem.asc(sort_order.getProperty()));
            }else if(sort_order.getDirection()== Sort.Direction.DESC) {
                page.addOrder(OrderItem.desc(sort_order.getProperty()));
            }
        }

        return page;
    }

    public QueryWrapper<T> getSearchCond(){
        return this.selectCond;
    }

    /**
     * 填充自定义查询条件
     * @return
     */
    public QueryWrapper<T> getSelectCond() {
        if(!ObjectUtils.isEmpty(filter)){
            Consumer queryWrapper = parseQueryFilter(filter);
            if(!ObjectUtils.isEmpty(queryWrapper)){
                selectCond.and(queryWrapper);
            }
        }
        return selectCond;
    }

    /**
     * 解析自定义查询条件
     * @param queryFilter
     * @return
     */
    private Consumer<QueryWrapper<T>> parseQueryFilter(QueryFilter queryFilter){
        if(queryFilter.any().size()==0  && queryFilter.get$or()==null && queryFilter.get$and()==null) {
            return null;
        }
        Consumer<QueryWrapper<T>> consumer = queryWrapper -> {
            Consumer fieldConsumer=parseFieldMap(queryFilter.any());
            Consumer orConsumer=parseOrQueryFilter(queryFilter.get$or());
            Consumer andConsumer=parseAndQueryFilter(queryFilter.get$and());
            if(!ObjectUtils.isEmpty(fieldConsumer)){
                queryWrapper.and(fieldConsumer);
            }
            if(!ObjectUtils.isEmpty(orConsumer)){
                queryWrapper.and(orConsumer);
            }
            if(!ObjectUtils.isEmpty(andConsumer)){
                queryWrapper.and(andConsumer);
            }
        };
        return consumer;
    }

    /**
     * 解析自定义条件[or]
     * @param queryFilters
     * @return
     */
    private Consumer<QueryWrapper<T>> parseOrQueryFilter(List<QueryFilter> queryFilters) {
        if(queryFilters==null || queryFilters.size()==0)
            return null;
            Consumer<QueryWrapper<T>> consumer = queryWrapper -> {
            for(QueryFilter queryFilter: queryFilters){
                Consumer tempQueryWrapper=parseQueryFilter(queryFilter);
                queryWrapper.or(tempQueryWrapper);
            }
        };
        return consumer;
    }

    /**
     * 解析自定义条件[and]
     * @param queryFilters
     * @return
     */
    private Consumer<QueryWrapper<T>> parseAndQueryFilter(List<QueryFilter> queryFilters) {
        if(queryFilters==null || queryFilters.size()==0) {
            return null;
        }
        Consumer<QueryWrapper<T>> consumer = queryWrapper -> {
            for(QueryFilter queryFilter: queryFilters){
                Consumer tempQueryWrapper=parseQueryFilter(queryFilter);
                queryWrapper.and(tempQueryWrapper);
            }
        };
        return consumer;
    }

    /**
     * 解析自定义条件[字段条件]
     * @param fieldMap
     * @return
     */
    private Consumer<QueryWrapper<T>> parseFieldMap(Map<String , QueryFilter.SegmentCond> fieldMap) {
        if(fieldMap.size()==0) {
            return null;
        }
            Consumer<QueryWrapper<T>> consumer = queryWrapper -> {
        for(Map.Entry<String, QueryFilter.SegmentCond> field: fieldMap.entrySet()){
            String fieldName=field.getKey();
            QueryFilter.SegmentCond segmentCond=field.getValue();
            Map<String , Object> segmentCondMap =  segmentCond.any();
            for(Map.Entry<String , Object> fieldCond: segmentCondMap.entrySet()){
                Object value=fieldCond.getValue();
                switch (fieldCond.getKey()){
                    case "$eq":
                        queryWrapper.eq(getFieldQueryExp(fieldName),value);
                        break;
                    case "$ne":
                        queryWrapper.ne(getFieldQueryExp(fieldName),value);
                        break;
                    case "$gt":
                        queryWrapper.gt(getFieldQueryExp(fieldName),value);
                        break;
                    case "$gte":
                        queryWrapper.ge(getFieldQueryExp(fieldName),value);
                        break;
                    case "$lt":
                        queryWrapper.lt(getFieldQueryExp(fieldName),value);
                        break;
                    case "$lte":
                        queryWrapper.le(getFieldQueryExp(fieldName),value);
                        break;
                    case "$null":
                        queryWrapper.isNull(getFieldQueryExp(fieldName));
                        break;
                    case "$notNull":
                        queryWrapper.isNotNull(getFieldQueryExp(fieldName));
                        break;
                    case "$in":
                        queryWrapper.in(getFieldQueryExp(fieldName),(Collection)value);
                        break;
                    case "$notIn":
                        queryWrapper.notIn(getFieldQueryExp(fieldName),(Collection)value);
                        break;
                    case "$like":
                        queryWrapper.like(getFieldQueryExp(fieldName),value);
                        break;
                    case "$startsWith":
                        queryWrapper.likeRight(getFieldQueryExp(fieldName),value);
                        break;
                    case "$endsWith":
                        queryWrapper.likeLeft(getFieldQueryExp(fieldName),value);
                        break;
                    case "$exists":
                        break;
                    case "$notExists":
                        break;
                }
            }
        }
      };
        return consumer;
    }

    /**
     *
     * @param strField
     * @return
     */
    public String getFieldQueryExp(String strField) {
        try {
            EvaluationContext elContext = new StandardEvaluationContext();
            Object runtime = SpringContextHolder.getBean(this.getClass().getSimpleName().replace("SearchContext", "") + "Runtime");
            elContext.setVariable("runtime", runtime);
            elContext.setVariable("field", strField);
            Expression expression = parser.parseExpression("#runtime.getFieldQueryExp(#field)");
            String strFieldQueryExp = expression.getValue(elContext, String.class);
            if(StringUtils.isEmpty(strFieldQueryExp))
                return strField;
            return strFieldQueryExp;
        } catch (Exception e) {
            log.warn(String.format("获取字段[%s]查询表达式发生错误：%s",strField,e.getMessage()));
        }
        return strField;
    }
}
