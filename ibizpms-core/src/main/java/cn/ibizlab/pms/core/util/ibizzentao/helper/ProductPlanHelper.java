package cn.ibizlab.pms.core.util.ibizzentao.helper;

import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.zentao.domain.*;
import cn.ibizlab.pms.core.zentao.filter.ProductPlanSearchContext;
import cn.ibizlab.pms.core.zentao.mapper.ProductPlanMapper;
import cn.ibizlab.pms.core.zentao.service.IProductPlanService;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.security.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
/**
 * @author chenxiang
 */
@Component
public class ProductPlanHelper extends ZTBaseHelper<ProductPlanMapper, ProductPlan> {

    @Autowired
    ActionHelper actionHelper;

    @Autowired
    FileHelper fileHelper;

    @Autowired
    ProductHelper productHelper;

    @Autowired
    StoryHelper storyHelper;

    @Autowired
    IProductPlanService productPlanService;

    @Autowired
    TaskHelper taskHelper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean create(ProductPlan et) {
        // if(et.getParent() != null && et.getParent() > 0) {
        //     Long parent = et.getParent();
        //     ProductPlan productPlan = this.get(parent);
        //     et.setProduct(productPlan.getProduct());
        //     productPlan.setParent(-1L);
        //     this.edit(productPlan);
        // }
        fileHelper.processImgURL(et, null, null);
        if (!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);

        //Action
        actionHelper.create(StaticDict.Action__object_type.PRODUCTPLAN.getValue(), et.getId(), StaticDict.Action__type.OPENED.getValue(), "", "", null, true);

        return true;
    }

    /**
     * edit 编辑
     *
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean edit(ProductPlan et) {
        ProductPlan old = new ProductPlan();
        CachedBeanCopier.copy(this.get(et.getId()), old);
        fileHelper.processImgURL(et, null, null);
        if (!this.internalUpdate(et)) {
            return false;
        }

        List<History> changes = ChangeUtil.diff(old, et,null,new String[]{"begin","end","desc"},new String[]{"desc"});
        if (changes.size() > 0) {
            Action action = actionHelper.create(StaticDict.Action__object_type.PRODUCTPLAN.getValue(), et.getId(), StaticDict.Action__type.EDITED.getValue(), "", "", null, true);
            actionHelper.logHistory(action.getId(), changes);
        }
        return true;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long key) {
        boolean bOk = super.delete(key);

        changeParentField(key);


        return bOk;
    }
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
                this.internalUpdate(parentPlan);
            }else {
                parentPlan.setParent(0L);
                this.internalUpdate(parentPlan);
            }
        }
    }

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

        Product product = productHelper.get(et.getProduct());
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
            storyHelper.internalUpdate(story);
            actionHelper.create(StaticDict.Action__object_type.STORY.getValue(), Long.parseLong(storyId), StaticDict.Action__type.LINKED2PLAN.getValue(), "", String.valueOf(productPlanId), null, true);
            storyHelper.setStage(story);
        }

        old.setOrder(curOrder);
        this.internalUpdate(old);
        return old;
    }


    @Transactional(rollbackFor = Exception.class)
    public ProductPlan unlinkStory(ProductPlan et) {

        return et;
    }

    @Transactional(rollbackFor = Exception.class)
    public ProductPlan batchUnlinkStory(ProductPlan et) {

        throw new RuntimeException("未实现");
    }

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
            actionHelper.create(StaticDict.Action__object_type.BUG.getValue(), bug.getId(), StaticDict.Action__type.LINKED2PLAN.getValue(), "", String.valueOf(et.getId()), null, true);
        }
        return et ;
    }

    @Transactional(rollbackFor = Exception.class)
    public ProductPlan unlinkBug(ProductPlan et) {
        if(et.get(FIELD_BUGS)==null) {
            return et ;
        }
        for (String bugId :  et.get(FIELD_BUGS).toString().split(MULTIPLE_CHOICE)) {
            Bug bug = new Bug() ;
            bug.setId(Long.parseLong(bugId));
            bug.setPlan(0L);
            SpringContextHolder.getBean(BugHelper.class).internalUpdate(bug);
            actionHelper.create(StaticDict.Action__object_type.BUG.getValue(), bug.getId(), StaticDict.Action__type.UNLINKEDFROMPLAN.getValue(), "", String.valueOf(et.getId()), null, true);
        }
        return et ;
    }

    @Transactional(rollbackFor = Exception.class)
    public ProductPlan batchUnlinkBug(ProductPlan et) {

        throw new RuntimeException("未实现");
    }

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

        Product product = productHelper.get(et.getProduct());
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
            taskHelper.internalUpdate(task);
            actionHelper.create(StaticDict.Action__object_type.TASK.getValue(), Long.parseLong(taskId), StaticDict.Action__type.LINKED2PLAN.getValue(), "", String.valueOf(productPlanId), null, true);

        }
        old.setOrder(curOrder);
        this.internalUpdate(old);

        taskHelper.updateRelatedPlanStatus(null,et.getId());

        return old;
    }




    /**
     * 自定义行为[eeStartPlan]Ee开始计划
     * @param et
     * @return
     */
    @Transactional
    public ProductPlan eeStartPlan(ProductPlan et){
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";

        ProductPlan old = new ProductPlan();
        CachedBeanCopier.copy(this.get(et.getId()),old);
//        if (old.getParent() > 0 ){
//            throw new RuntimeException("功能未开发！");
//        }
        et.setStatus(StaticDict.Task__status.DOING.getValue());
        this.internalUpdate(et);
        List<History> changes = ChangeUtil.diff(old,et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)){
            Action action = actionHelper.create(StaticDict.Action__object_type.PRODUCTPLAN.getValue(),et.getId(),StaticDict.Action__type.STARTED.getValue(),comment,"",null,false);
            actionHelper.logHistory(action.getId(),changes);
        }
        return et;
    }

    /**
     * 自定义行为[eeStartPlan]Ee开始计划
     * @param et
     * @return
     */
    @Transactional
    public ProductPlan eePausePlan(ProductPlan et){
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";

        ProductPlan old = new ProductPlan();
        CachedBeanCopier.copy(this.get(et.getId()),old);
//        if (old.getParent() > 0 ){
//            throw new RuntimeException("功能未开发！");
//        }
        et.setStatus(StaticDict.Task__status.PAUSE.getValue());
        this.internalUpdate(et);
        List<History> changes = ChangeUtil.diff(old,et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)){
            Action action = actionHelper.create(StaticDict.Action__object_type.PRODUCTPLAN.getValue(),et.getId(),StaticDict.Action__type.PAUSED.getValue(),comment,"",null,false);
            actionHelper.logHistory(action.getId(),changes);
        }
        return et;
    }
    /**
     * 自定义行为[eeStartPlan]Ee开始计划
     * @param et
     * @return
     */
    @Transactional
    public ProductPlan eeRestartPlan(ProductPlan et){
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";

        ProductPlan old = new ProductPlan();
        CachedBeanCopier.copy(this.get(et.getId()),old);
//        if (old.getParent() > 0 ){
//            throw new RuntimeException("功能未开发！");
//        }
        et.setStatus(StaticDict.Task__status.DOING.getValue());
        this.internalUpdate(et);
        List<History> changes = ChangeUtil.diff(old,et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)){
            Action action = actionHelper.create(StaticDict.Action__object_type.PRODUCTPLAN.getValue(),et.getId(),StaticDict.Action__type.RESTARTED.getValue(),comment,"",null,false);
            actionHelper.logHistory(action.getId(),changes);
        }
        return et;
    }
    /**
     * 自定义行为[eeStartPlan]Ee开始计划
     * @param et
     * @return
     */
    @Transactional
    public ProductPlan eeFinishPlan(ProductPlan et){
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";

        ProductPlan old = new ProductPlan();
        CachedBeanCopier.copy(this.get(et.getId()),old);
//        if (old.getParent() > 0 ){
//            throw new RuntimeException("功能未开发！");
//        }
        et.setStatus(StaticDict.Task__status.DONE.getValue());
        this.internalUpdate(et);
        List<History> changes = ChangeUtil.diff(old,et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)){
            Action action = actionHelper.create(StaticDict.Action__object_type.PRODUCTPLAN.getValue(),et.getId(),StaticDict.Action__type.FINISHED.getValue(),comment,"",null,false);
            actionHelper.logHistory(action.getId(),changes);
        }
        return et;
    }

    /**
     * 自定义行为[eeStartPlan]Ee开始计划
     * @param et
     * @return
     */
    @Transactional
    public ProductPlan eeCancelPlan(ProductPlan et){
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        ProductPlan old = new ProductPlan();
        CachedBeanCopier.copy(this.get(et.getId()),old);
//        if (old.getParent() > 0 ){
//            throw new RuntimeException("功能未开发！");
//        }
        et.setStatus(StaticDict.Task__status.CANCEL.getValue());
        this.internalUpdate(et);

        List<History> changes = ChangeUtil.diff(old,et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)){
            Action action = actionHelper.create(StaticDict.Action__object_type.PRODUCTPLAN.getValue(),et.getId(),StaticDict.Action__type.CANCELED.getValue(),comment,"",null,false);
            actionHelper.logHistory(action.getId(),changes);
        }
        return et;
    }
    /**
     * 自定义行为[eeStartPlan]Ee开始计划
     * @param et
     * @return
     */
    @Transactional
    public ProductPlan eeActivePlan(ProductPlan et){
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        ProductPlan old = new ProductPlan();
        CachedBeanCopier.copy(this.get(et.getId()),old);
//        if (old.getParent() > 0 ){
//            throw new RuntimeException("功能未开发！");
//        }
        et.setStatus(StaticDict.Task__status.DOING.getValue());
        this.internalUpdate(et);
        List<History> changes = ChangeUtil.diff(old,et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)){
            Action action = actionHelper.create(StaticDict.Action__object_type.PRODUCTPLAN.getValue(),et.getId(),StaticDict.Action__type.ACTIVATED.getValue(),comment,"",null,false);
            actionHelper.logHistory(action.getId(),changes);
        }
        return et;
    }

    /**
     * 自定义行为[eeStartPlan]Ee关闭计划
     * @param et
     * @return
     */
    @Transactional
    public ProductPlan eeClosePlan(ProductPlan et){
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";

        ProductPlan old = new ProductPlan();
        CachedBeanCopier.copy(this.get(et.getId()),old);
//        if (old.getParent() > 0 ){
//            throw new RuntimeException("功能未开发！");
//        }
        et.setStatus(StaticDict.Task__status.CLOSED.getValue());
        this.internalUpdate(et);
        List<History> changes = ChangeUtil.diff(old,et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)){
            Action action = actionHelper.create(StaticDict.Action__object_type.PRODUCTPLAN.getValue(),et.getId(),StaticDict.Action__type.CLOSED.getValue(),comment,"",null,false);
            actionHelper.logHistory(action.getId(),changes);
        }
        return et;
    }
}
