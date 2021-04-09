package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibizpro.domain.IbzPlanTempletDetail;
import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.zentao.domain.Action;
import cn.ibizlab.pms.core.zentao.domain.History;
import cn.ibizlab.pms.core.zentao.domain.Story;
import cn.ibizlab.pms.core.zentao.filter.ProductPlanSearchContext;
import cn.ibizlab.pms.core.zentao.filter.StorySearchContext;
import cn.ibizlab.pms.core.zentao.service.IActionService;
import cn.ibizlab.pms.core.zentao.service.impl.ProductPlanServiceImpl;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.zentao.domain.ProductPlan;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[产品计划] 自定义服务对象
 */
@Slf4j
@Primary
@Service("ProductPlanExService")
public class ProductPlanExService extends ProductPlanServiceImpl {

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibizpro.service.IIbzPlanTempletDetailService ibzplantempletdetailService;


    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    @Override
    @Transactional
    public ProductPlan importPlanTemplet(ProductPlan et) {
        //自定义代码
        List<IbzPlanTempletDetail> ibzPlanTempletDetails = ibzplantempletdetailService.list(new QueryWrapper<IbzPlanTempletDetail>().eq("plantempletid", et.getPlantemplet()).orderByAsc("`order`").orderByAsc("PLANCODE"));
        Long parent = 0L;
        for (IbzPlanTempletDetail ibzPlanTempletDetail : ibzPlanTempletDetails) {
            if(StringUtils.isBlank(ibzPlanTempletDetail.getDesc())) {
                continue;
            }
            ProductPlan productPlan = new ProductPlan();
            productPlan.setProduct(et.getProduct());
            productPlan.setTitle(ibzPlanTempletDetail.getDesc());
            productPlan.setDesc(ibzPlanTempletDetail.getExpect());
            String type = ibzPlanTempletDetail.getType();
            if(StaticDict.PlantempletType.ITEM.getValue().equals(type)) {
                productPlan.setParent(parent);
            }else if(StaticDict.PlantempletType.STEP.getValue().equals(type)) {
                productPlan.setParent(0L);
            }else if(StaticDict.PlantempletType.GROUP.getValue().equals(type)) {
                productPlan.setParent(-1L);
            }
            this.create(productPlan);
            if(StaticDict.PlantempletType.GROUP.getValue().equals(type)) {parent = productPlan.getId();}
            if(StaticDict.PlantempletType.STEP.getValue().equals(type)) {parent = 0L;}
        }
        return et;
    }

    /**
     * 自定义行为[BatchUnlinkBug]用户扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProductPlan batchUnlinkBug(ProductPlan et) {
        return super.batchUnlinkBug(et);
    }
    /**
     * 自定义行为[BatchUnlinkStory]用户扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProductPlan batchUnlinkStory(ProductPlan et) {
        return super.batchUnlinkStory(et);
    }
    /**
     * 自定义行为[UnlinkBug]用户扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProductPlan unlinkBug(ProductPlan et) {
        return super.unlinkBug(et);
    }
    /**
     * 自定义行为[UnlinkStory]用户扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProductPlan unlinkStory(ProductPlan et) {
        return super.unlinkStory(et);
    }

    /**
     * 查询集合 默认查询
     */
    @Override
    public Page<ProductPlan> searchDefaultParent(ProductPlanSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProductPlan> pages=baseMapper.searchDefaultParent(context.getPages(),context,context.getSelectCond());
        for (ProductPlan productPlan : pages.getRecords()) {
            ProductPlanSearchContext productPlanSearchContext = new ProductPlanSearchContext();
            productPlanSearchContext.setSelectCond(context.getSelectCond().clone());
            productPlanSearchContext.setN_parent_eq(productPlan.getId());
            productPlan.set("items", this.searchDefault(productPlanSearchContext).getContent());
        }
        return new PageImpl<ProductPlan>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }
    /**
     * 查询集合 默认查询
     */
    @Override
    public Page<ProductPlan> searchPlanTasks(ProductPlanSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProductPlan> pages=baseMapper.searchPlanTasks(context.getPages(),context,context.getSelectCond());
        for (ProductPlan productPlan : pages.getRecords()) {
            ProductPlanSearchContext productPlanSearchContext = new ProductPlanSearchContext();
            productPlanSearchContext.setSelectCond(context.getSelectCond().clone());
            productPlanSearchContext.setN_parent_eq(productPlan.getId());
            productPlanSearchContext.setSort(context.getSort());
            productPlan.set("items", this.searchDefault(productPlanSearchContext).getContent());
        }
        return new PageImpl<ProductPlan>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }


}

