package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibizpro.domain.IbzPlanTempletDetail;
import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.util.ibizzentao.helper.BugHelper;
import cn.ibizlab.pms.core.zentao.domain.*;
import cn.ibizlab.pms.core.zentao.filter.ProductPlanSearchContext;
import cn.ibizlab.pms.core.zentao.service.*;
import cn.ibizlab.pms.core.zentao.service.impl.ProductPlanServiceImpl;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.security.SpringContextHolder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.kie.api.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

import static cn.ibizlab.pms.core.util.ibizzentao.helper.ZTBaseHelper.*;

/**
 * 实体[产品计划] 自定义服务对象
 */
@Slf4j
@Primary
@Service("ProductPlanExService")
public class ProductPlanExService extends ProductPlanServiceImpl {

    @Autowired
    IActionService iActionService;
    @Autowired
    IFileService iFileService;
    @Autowired
    IProductService iProductService;
    @Autowired
    IStoryService iStoryService;
    @Autowired
    IProductPlanService productPlanService;
    @Autowired
    ITaskService taskService;

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
    @Transactional(rollbackFor = Exception.class)
    public ProductPlan batchUnlinkBug(ProductPlan et) {
        throw new RuntimeException("未实现");
    }
    /**
     * 自定义行为[BatchUnlinkStory]用户扩展
     * @param et
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProductPlan batchUnlinkStory(ProductPlan et) {
        throw new RuntimeException("未实现");
    }
    /**
     * 自定义行为[UnlinkBug]用户扩展
     * @param et
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProductPlan unlinkBug(ProductPlan et) {
        if(et.get(FIELD_BUGS)==null) {
            return et ;
        }
        for (String bugId :  et.get(FIELD_BUGS).toString().split(MULTIPLE_CHOICE)) {
            Bug bug = new Bug() ;
            bug.setId(Long.parseLong(bugId));
            bug.setPlan(0L);
            SpringContextHolder.getBean(IBugService.class).update(bug);
            ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.BUG.getValue(),null,StaticDict.Action__type.UNLINKEDFROMPLAN.getValue(),
                    "",String.valueOf(et.getId()), null,iActionService);
        }
        return et ;
    }

    /**
     * 自定义行为[UnlinkStory]用户扩展
     * @param et
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProductPlan unlinkStory(ProductPlan et) {
        return et;
    }

    /**
     * 查询集合 默认查询
     */
    @Override
    @Transactional
    public Page<ProductPlan> searchDefaultParent(ProductPlanSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProductPlan> pages=baseMapper.searchDefaultParent(context.getPages(),context,context.getSelectCond());
        for (ProductPlan productPlan : pages.getRecords()) {
            if(productPlan.getParent() == 0L) {
                continue;
            }
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
    @Transactional
    public Page<ProductPlan> searchPlanTasks(ProductPlanSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProductPlan> pages=baseMapper.searchPlanTasks(context.getPages(),context,context.getSelectCond());
        for (ProductPlan productPlan : pages.getRecords()) {
            if(productPlan.getParent() == 0L) {
                continue;
            }
            ProductPlanSearchContext productPlanSearchContext = new ProductPlanSearchContext();
            productPlanSearchContext.setSelectCond(context.getSelectCond().clone());
            productPlanSearchContext.setN_parent_eq(productPlan.getId());
            productPlanSearchContext.setSort(context.getSort());
            productPlan.set("items", this.searchDefault(productPlanSearchContext).getContent());
        }
        return new PageImpl<ProductPlan>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean create(ProductPlan et) {
        if(et.getParent() != null && et.getParent() > 0) {
            Long parent = et.getParent();
            ProductPlan productPlan = this.get(parent);
            et.setProduct(productPlan.getProduct());
            productPlan.setParent(-1L);
            this.update(productPlan);
        }

        if (!super.create(et)) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);

        ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.PRODUCTPLAN.getValue(),null,StaticDict.Action__type.OPENED.getValue(),
                "","", null,iActionService);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(ProductPlan et) {
        ProductPlan old = new ProductPlan();
        CachedBeanCopier.copy(this.get(et.getId()), old);

        if (!super.update(et)) {
            return false;
        }

        List<History> changes = ChangeUtil.diff(old, et,null,new String[]{"begin","end","desc"},new String[]{"desc"});
        if (changes.size() > 0) {
            ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.PRODUCTPLAN.getValue(),changes,StaticDict.Action__type.OPENED.getValue(),
                    "","", null,iActionService);
        }
        return true;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean remove(Long key) {
        boolean bOk = super.remove(key);

        changeParentField(key);


        return bOk;
    }

    @Transactional
    public void changeParentField(Long key) {
        ProductPlan plan = this.get(key);
        if(plan == null && plan.getParent() <= 0) {
            return;
        }
        ProductPlanSearchContext planSearchContext = new ProductPlanSearchContext();
        planSearchContext.setN_parent_eq(plan.getParent());
        planSearchContext.setN_product_eq(plan.getProduct());
        long parent = productPlanService.searchDefault(planSearchContext).getContent().size() > 0 ? -1L : 0L;
        ProductPlan parentPlan = this.get(plan.getParent());
        if(parentPlan != null) {
            if("0".equals(parentPlan.getDeleted())) {
                parentPlan.setParent(parent);
                super.update(parentPlan);
            }else {
                parentPlan.setParent(0L);
                super.update(parentPlan);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProductPlan linkStory(ProductPlan et) {
        Long productPlanId = et.getId();
        if (productPlanId == null) {
            if (et.get(StaticDict.Action__object_type.PRODUCTPLAN.getValue()) == null) {
                throw new RuntimeException("缺少计划");
            }
            productPlanId = Long.parseLong(et.get(StaticDict.Action__object_type.PRODUCTPLAN.getValue()).toString());
        }
        String stories = "";
        if(et.get(FIELD_STORIES)!=null) {
            stories = et.get(FIELD_STORIES).toString();
        }
        else if(et.get(FIELD_SRFACTIONPARAM) != null){
            List<Map<String,Object>> list = (List<Map<String, Object>>) et.get(FIELD_SRFACTIONPARAM);
            for (Map<String, Object> jsonObject : list) {
                if(!"".equals(stories)) {
                    stories += MULTIPLE_CHOICE;
                }
                stories += jsonObject.get(FIELD_ID);
            }

        }
        if("".equals(stories)) {
            return et;
        }
        ProductPlan old  = this.get(productPlanId);

        Product product = iProductService.get(et.getProduct());
        String curOrder = old.getOrder();

        for (String storyId :  stories.split(MULTIPLE_CHOICE)) {
            if (curOrder.contains(storyId)) {
                continue;
            }
            curOrder += storyId +MULTIPLE_CHOICE;
            Story story = new Story();
            story.setId(Long.parseLong(storyId));
            if (StringUtils.compare(product.getType(), StaticDict.Product__type.NORMAL.getValue()) == 0) {
                story.setPlan(String.valueOf(productPlanId));
            } else {
                story.setPlan(String.valueOf(productPlanId));

            }
            iStoryService.sysUpdate(story);
            ActionHelper.createHis(Long.parseLong(storyId),StaticDict.Action__object_type.STORY.getValue(),null,StaticDict.Action__type.LINKED2PLAN.getValue(),
                    "",String.valueOf(productPlanId), null,iActionService);
            iStoryService.setStage(story);
        }

        old.setOrder(curOrder);
        super.update(old);
        return old;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProductPlan linkBug(ProductPlan et) {

        String bugs = "";
        if(et.get(FIELD_BUILDS)!=null) {
            bugs = et.get(FIELD_BUILDS).toString();
        }
        else if(et.get(FIELD_SRFACTIONPARAM) != null){
            List<Map<String,Object>> list = (List<Map<String, Object>>) et.get(FIELD_SRFACTIONPARAM);
            for (Map<String, Object> jsonObject : list) {
                if(!"".equals(bugs)) {
                    bugs += MULTIPLE_CHOICE;
                }
                bugs += jsonObject.get(FIELD_ID);
            }

        }
        if("".equals(bugs)) {
            return et;
        }


        for (String bugId :  bugs.split(MULTIPLE_CHOICE)) {
            Bug bug = new Bug() ;
            bug.setId(Long.parseLong(bugId));
            bug.setPlan(et.getId());
            SpringContextHolder.getBean(BugHelper.class).internalUpdate(bug);
            ActionHelper.createHis(bug.getId(),StaticDict.Action__object_type.BUG.getValue(),null,StaticDict.Action__type.LINKED2PLAN.getValue(),
                    "",String.valueOf(et.getId()), null,iActionService);
        }
        return et ;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProductPlan linkTask(ProductPlan et) {
        Long productPlanId = et.getId();
        if (productPlanId == null) {
            if (et.get(StaticDict.Action__object_type.PRODUCTPLAN.getValue()) == null) {
                throw new RuntimeException("缺少计划");
            }
            productPlanId = Long.parseLong(et.get(StaticDict.Action__object_type.PRODUCTPLAN.getValue()).toString());
        }
        String tasks = "";
        if (et.get(FIELD_SRFACTIONPARAM) != null) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) et.get(FIELD_SRFACTIONPARAM);
            for (Map<String, Object> jsonObject : list) {
                if (!"".equals(tasks)) {
                    tasks += MULTIPLE_CHOICE;
                }
                tasks += jsonObject.get(FIELD_ID);
            }
        }else if (et.get("tasks") != null) {
            tasks = et.get("tasks").toString();
        }
        if ("".equals(tasks)) {
            return et;
        }
        ProductPlan old = this.get(productPlanId);

        Product product = iProductService.get(et.getProduct());
        String curOrder = old.getOrder();

        for (String taskId : tasks.split(MULTIPLE_CHOICE)) {
            if (curOrder.contains(taskId)) {
                continue;
            }
            curOrder += taskId + MULTIPLE_CHOICE;
            Task task = new Task();
            task.setId(Long.parseLong(taskId));
            if (StringUtils.compare(product.getType(), StaticDict.Product__type.NORMAL.getValue()) == 0) {
                task.setPlan(productPlanId);
            } else {
                task.setPlan(productPlanId);
            }
            taskService.sysUpdate(task);
            ActionHelper.createHis(Long.parseLong(taskId),StaticDict.Action__object_type.TASK.getValue(),null,StaticDict.Action__type.LINKED2PLAN.getValue(),
                    "",String.valueOf(productPlanId), null,iActionService);
        }
        old.setOrder(curOrder);
        super.update(old);
        Task relatedTask = new Task();
        relatedTask.setPlan(et.getId());
        taskService.updateRelatedPlanStatus(relatedTask);

        return old;
    }

    @Override
    @Transactional
    public ProductPlan eeStartPlan(ProductPlan et){
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";

        ProductPlan old = new ProductPlan();
        CachedBeanCopier.copy(this.get(et.getId()),old);
//        if (old.getParent() > 0 ){
//            throw new RuntimeException("功能未开发！");
//        }
        et.setStatus(StaticDict.Task__status.DOING.getValue());
        super.update(et);
        List<History> changes = ChangeUtil.diff(old,et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)){
            ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.PRODUCTPLAN.getValue(),null,StaticDict.Action__type.STARTED.getValue(),
                    comment,"", null,iActionService);
        }
        return et;
    }

    @Override
    @Transactional
    public ProductPlan eePausePlan(ProductPlan et){
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";

        ProductPlan old = new ProductPlan();
        CachedBeanCopier.copy(this.get(et.getId()),old);
//        if (old.getParent() > 0 ){
//            throw new RuntimeException("功能未开发！");
//        }
        et.setStatus(StaticDict.Task__status.PAUSE.getValue());
        super.update(et);
        List<History> changes = ChangeUtil.diff(old,et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)){
            ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.PRODUCTPLAN.getValue(),changes,StaticDict.Action__type.PAUSED.getValue(),
                    comment,"", null,iActionService);
        }
        return et;
    }

    @Override
    @Transactional
    public ProductPlan eeRestartPlan(ProductPlan et){
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";

        ProductPlan old = new ProductPlan();
        CachedBeanCopier.copy(this.get(et.getId()),old);
//        if (old.getParent() > 0 ){
//            throw new RuntimeException("功能未开发！");
//        }
        et.setStatus(StaticDict.Task__status.DOING.getValue());
        super.update(et);
        List<History> changes = ChangeUtil.diff(old,et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)){
            ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.PRODUCTPLAN.getValue(),changes,StaticDict.Action__type.RESTARTED.getValue(),
                    comment,"", null,iActionService);
        }
        return et;
    }

    @Transactional
    public ProductPlan eeFinishPlan(ProductPlan et){
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";

        ProductPlan old = new ProductPlan();
        CachedBeanCopier.copy(this.get(et.getId()),old);
//        if (old.getParent() > 0 ){
//            throw new RuntimeException("功能未开发！");
//        }
        et.setStatus(StaticDict.Task__status.DONE.getValue());
        update(et);
        List<History> changes = ChangeUtil.diff(old,et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)){
            ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.PRODUCTPLAN.getValue(),changes,StaticDict.Action__type.FINISHED.getValue(),
                    comment,"", null,iActionService);
        }
        return et;
    }

    @Transactional
    public ProductPlan eeCancelPlan(ProductPlan et){
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        ProductPlan old = new ProductPlan();
        CachedBeanCopier.copy(this.get(et.getId()),old);
//        if (old.getParent() > 0 ){
//            throw new RuntimeException("功能未开发！");
//        }
        et.setStatus(StaticDict.Task__status.CANCEL.getValue());
        super.update(et);

        List<History> changes = ChangeUtil.diff(old,et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)){
            ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.PRODUCTPLAN.getValue(),changes,StaticDict.Action__type.CANCELED.getValue(),
                    comment,"", null,iActionService);
        }
        return et;
    }

    @Override
    @Transactional
    public ProductPlan eeActivePlan(ProductPlan et){
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        ProductPlan old = new ProductPlan();
        CachedBeanCopier.copy(this.get(et.getId()),old);
//        if (old.getParent() > 0 ){
//            throw new RuntimeException("功能未开发！");
//        }
        et.setStatus(StaticDict.Task__status.DOING.getValue());
        super.update(et);
        List<History> changes = ChangeUtil.diff(old,et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)){
            ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.PRODUCTPLAN.getValue(),changes,StaticDict.Action__type.ACTIVATED.getValue(),
                    comment,"", null,iActionService);
        }
        return et;
    }

    @Override
    @Transactional
    public ProductPlan eeClosePlan(ProductPlan et){
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";

        ProductPlan old = new ProductPlan();
        CachedBeanCopier.copy(this.get(et.getId()),old);
//        if (old.getParent() > 0 ){
//            throw new RuntimeException("功能未开发！");
//        }
        et.setStatus(StaticDict.Task__status.CLOSED.getValue());
        super.update(et);
        List<History> changes = ChangeUtil.diff(old,et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)){
            ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.PRODUCTPLAN.getValue(),changes,StaticDict.Action__type.CLOSED.getValue(),
                    comment,"", null,iActionService);
        }
        return et;
    }
}

