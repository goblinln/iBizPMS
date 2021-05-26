package cn.ibizlab.pms.core.zentao.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.math.BigInteger;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import cn.ibizlab.pms.util.errors.BadRequestAlertException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Lazy;
import cn.ibizlab.pms.core.zentao.domain.Story;
import cn.ibizlab.pms.core.zentao.filter.StorySearchContext;
import cn.ibizlab.pms.core.zentao.service.IStoryService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.zentao.mapper.StoryMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[需求] 服务对象接口实现
 */
@Slf4j
@Service("StoryServiceImpl")
public class StoryServiceImpl extends ServiceImpl<StoryMapper, Story> implements IStoryService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.zentao.runtime.StoryRuntime storyRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.IIBZStoryActionService ibzstoryactionService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IBugService bugService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.ICaseService caseService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProjectStoryService projectstoryService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IStorySpecService storyspecService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IStoryStageService storystageService;

    protected cn.ibizlab.pms.core.zentao.service.IStoryService storyService = this;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.ITaskService taskService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.IProductModuleService productmoduleService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IBranchService branchService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProductService productService;

    protected int batchSize = 500;

    @Override
    public List<Story> select(StorySearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Story> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(Story et) {
        if(!storyRuntime.isRtmodel()){
            fillParentData(et);
        }
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        if(!storyRuntime.isRtmodel()){
            ibzstoryactionService.saveByObjectid(et.getId(), et.getIbzstoryaction());
        }
        if(!storyRuntime.isRtmodel()){
            storyspecService.saveByStory(et.getId(), et.getStoryspecs());
        }
        if(!storyRuntime.isRtmodel()){
            storystageService.saveByStory(et.getId(), et.getStorystages());
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }


    @Override
    @Transactional
    public boolean update(Story et) {
        if(!storyRuntime.isRtmodel()){
            fillParentData(et);
        }
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        if(!storyRuntime.isRtmodel()){
            ibzstoryactionService.saveByObjectid(et.getId(), et.getIbzstoryaction());
        }
        if(!storyRuntime.isRtmodel()){
            storyspecService.saveByStory(et.getId(), et.getStoryspecs());
        }
        if(!storyRuntime.isRtmodel()){
            storystageService.saveByStory(et.getId(), et.getStorystages());
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }


    @Override
    @Transactional
    public boolean sysUpdate(Story et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        if(!storyRuntime.isRtmodel()){
            ibzstoryactionService.removeByObjectid(key) ;
            storyspecService.removeByStory(key) ;
            storystageService.removeByStory(key) ;
        }
        boolean result = removeById(key);
        return result ;
    }


    @Override
    @Transactional
    public Story get(Long key) {
        Story et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
            if(!storyRuntime.isRtmodel()){
                et.setIbzstoryaction(ibzstoryactionService.selectByObjectid(key));
                et.setStoryspecs(storyspecService.selectByStory(key));
                et.setStorystages(storystageService.selectByStory(key));
            }
        }
        return et;
    }

     /**
     *  系统获取
     *  @return
     */
    @Override
    @Transactional
    public Story sysGet(Long key) {
        Story et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public Story getDraft(Story et) {
        if(!storyRuntime.isRtmodel()){
            fillParentData(et);
        }
        return et;
    }

    @Override
    @Transactional
    public Story activate(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Story allPush(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Story assignTo(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Story batchAssignTo(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Story batchChangeBranch(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Story batchChangeModule(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Story batchChangePlan(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Story batchChangeStage(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Story batchClose(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Story batchReview(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Story batchUnlinkStory(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Story bugToStory(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Story buildBatchUnlinkStory(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Story buildLinkStory(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Story buildUnlinkStory(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Story buildUnlinkStorys(Story et) {
         return et ;
    }

    @Override
    @Transactional
    public Story change(Story et) {
        //自定义代码
        return et;
    }

    @Override
    public boolean checkKey(Story et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public Story close(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Story createTasks(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Story getStorySpec(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Story getStorySpecs(Story et) {
         return et ;
    }

    @Override
    @Transactional
    public Story importPlanStories(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Story linkStory(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Story projectBatchUnlinkStory(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Story projectLinkStory(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Story projectUnlinkStory(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Story projectUnlinkStorys(Story et) {
         return et ;
    }

    @Override
    @Transactional
    public Story push(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Story releaseBatchUnlinkStory(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Story releaseLinkStory(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Story releaseUnlinkStory(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Story resetReviewedBy(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Story review(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public boolean save(Story et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(Story et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<Story> list) {
        if(!storyRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        List<Story> create = new ArrayList<>();
        List<Story> update = new ArrayList<>();
        for (Story et : list) {
            if (ObjectUtils.isEmpty(et.getId()) || ObjectUtils.isEmpty(getById(et.getId()))) {
                create.add(et);
            } else {
                update.add(et);
            }
        }
        if (create.size() > 0) {
            getProxyService().createBatch(create);
        }
        if (update.size() > 0) {
            getProxyService().updateBatch(update);
        }
        return true;
    }

    @Override
    @Transactional
    public void saveBatch(List<Story> list) {
        if(!storyRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        List<Story> create = new ArrayList<>();
        List<Story> update = new ArrayList<>();
        for (Story et : list) {
            if (ObjectUtils.isEmpty(et.getId()) || ObjectUtils.isEmpty(getById(et.getId()))) {
                create.add(et);
            } else {
                update.add(et);
            }
        }
        if (create.size() > 0) {
            getProxyService().createBatch(create);
        }
        if (update.size() > 0) {
            getProxyService().updateBatch(update);
        }
    }

    @Override
    @Transactional
    public Story sendMessage(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Story sendMsgPreProcess(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Story setStage(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Story storyFavorites(Story et) {
         return et ;
    }

    @Override
    @Transactional
    public Story storyNFavorites(Story et) {
         return et ;
    }

    @Override
    @Transactional
    public Story syncFromIbiz(Story et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Story unlinkStory(Story et) {
        //自定义代码
        return et;
    }


	@Override
    public List<Story> selectByModule(Long id) {
        return baseMapper.selectByModule(id);
    }
    @Override
    public void removeByModule(Long id) {
        this.remove(new QueryWrapper<Story>().eq("module",id));
    }

	@Override
    public List<Story> selectByBranch(Long id) {
        return baseMapper.selectByBranch(id);
    }
    @Override
    public void removeByBranch(Long id) {
        this.remove(new QueryWrapper<Story>().eq("branch",id));
    }

	@Override
    public List<Story> selectByFrombug(Long id) {
        return baseMapper.selectByFrombug(id);
    }
    @Override
    public void removeByFrombug(Long id) {
        this.remove(new QueryWrapper<Story>().eq("frombug",id));
    }

	@Override
    public List<Story> selectByTobug(Long id) {
        return baseMapper.selectByTobug(id);
    }
    @Override
    public void removeByTobug(Long id) {
        this.remove(new QueryWrapper<Story>().eq("tobug",id));
    }

	@Override
    public List<Story> selectByProduct(Long id) {
        return baseMapper.selectByProduct(id);
    }
    @Override
    public void removeByProduct(Long id) {
        this.remove(new QueryWrapper<Story>().eq("product",id));
    }

	@Override
    public List<Story> selectByDuplicatestory(Long id) {
        return baseMapper.selectByDuplicatestory(id);
    }
    @Override
    public void removeByDuplicatestory(Long id) {
        this.remove(new QueryWrapper<Story>().eq("duplicatestory",id));
    }

	@Override
    public List<Story> selectByParent(Long id) {
        return baseMapper.selectByParent(id);
    }
    @Override
    public void removeByParent(Long id) {
        this.remove(new QueryWrapper<Story>().eq("parent",id));
    }


    public List<Story> selectAssignedToMyStory(StorySearchContext context){
        return baseMapper.selectAssignedToMyStory(context, context.getSelectCond());
    }
    public List<Story> selectAssignedToMyStoryCalendar(StorySearchContext context){
        return baseMapper.selectAssignedToMyStoryCalendar(context, context.getSelectCond());
    }
    public List<Story> selectBugStory(StorySearchContext context){
        return baseMapper.selectBugStory(context, context.getSelectCond());
    }
    public List<Story> selectBuildLinkCompletedStories(StorySearchContext context){
        return baseMapper.selectBuildLinkCompletedStories(context, context.getSelectCond());
    }
    public List<Story> selectBuildLinkableStories(StorySearchContext context){
        return baseMapper.selectBuildLinkableStories(context, context.getSelectCond());
    }
    public List<Story> selectBuildStories(StorySearchContext context){
        return baseMapper.selectBuildStories(context, context.getSelectCond());
    }
    public List<Story> selectByModule(StorySearchContext context){
        return baseMapper.selectByModule(context, context.getSelectCond());
    }
    public List<Story> selectCaseStory(StorySearchContext context){
        return baseMapper.selectCaseStory(context, context.getSelectCond());
    }
    public List<Story> selectChildMore(StorySearchContext context){
        return baseMapper.selectChildMore(context, context.getSelectCond());
    }
    public List<Story> selectDefault(StorySearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<Story> selectESBulk(StorySearchContext context){
        return baseMapper.selectESBulk(context, context.getSelectCond());
    }
    public List<Story> selectGetProductStories(StorySearchContext context){
        return baseMapper.selectGetProductStories(context, context.getSelectCond());
    }
    public List<Story> selectMyAgentStory(StorySearchContext context){
        return baseMapper.selectMyAgentStory(context, context.getSelectCond());
    }
    public List<Story> selectMyCreateOrPartake(StorySearchContext context){
        return baseMapper.selectMyCreateOrPartake(context, context.getSelectCond());
    }
    public List<Story> selectMyCurOpenedStory(StorySearchContext context){
        return baseMapper.selectMyCurOpenedStory(context, context.getSelectCond());
    }
    public List<Story> selectMyFavorites(StorySearchContext context){
        return baseMapper.selectMyFavorites(context, context.getSelectCond());
    }
    public List<Story> selectNotCurPlanLinkStory(StorySearchContext context){
        return baseMapper.selectNotCurPlanLinkStory(context, context.getSelectCond());
    }
    public List<Story> selectParentDefault(StorySearchContext context){
        return baseMapper.selectParentDefault(context, context.getSelectCond());
    }
    public List<Story> selectParentDefaultQ(StorySearchContext context){
        return baseMapper.selectParentDefaultQ(context, context.getSelectCond());
    }
    public List<Story> selectProjectLinkStory(StorySearchContext context){
        return baseMapper.selectProjectLinkStory(context, context.getSelectCond());
    }
    public List<Story> selectProjectStories(StorySearchContext context){
        return baseMapper.selectProjectStories(context, context.getSelectCond());
    }
    public List<Story> selectReleaseLinkableStories(StorySearchContext context){
        return baseMapper.selectReleaseLinkableStories(context, context.getSelectCond());
    }
    public List<Story> selectReleaseStories(StorySearchContext context){
        return baseMapper.selectReleaseStories(context, context.getSelectCond());
    }
    public List<Story> selectReportStories(StorySearchContext context){
        return baseMapper.selectReportStories(context, context.getSelectCond());
    }
    public List<Story> selectStoryChild(StorySearchContext context){
        return baseMapper.selectStoryChild(context, context.getSelectCond());
    }
    public List<Story> selectStoryRelated(StorySearchContext context){
        return baseMapper.selectStoryRelated(context, context.getSelectCond());
    }
    public List<Story> selectSubStory(StorySearchContext context){
        return baseMapper.selectSubStory(context, context.getSelectCond());
    }
    public List<Story> selectTaskRelatedStory(StorySearchContext context){
        return baseMapper.selectTaskRelatedStory(context, context.getSelectCond());
    }
    public List<Story> selectView(StorySearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 指派给我的需求
     */
    @Override
    public Page<Story> searchAssignedToMyStory(StorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Story> pages=baseMapper.searchAssignedToMyStory(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Story>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 指派给我的需求（日历）
     */
    @Override
    public Page<Story> searchAssignedToMyStoryCalendar(StorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Story> pages=baseMapper.searchAssignedToMyStoryCalendar(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Story>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 Bug相关需求
     */
    @Override
    public Page<Story> searchBugStory(StorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Story> pages=baseMapper.searchBugStory(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Story>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 版本关联已完成的需求（选择数据源）
     */
    @Override
    public Page<Story> searchBuildLinkCompletedStories(StorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Story> pages=baseMapper.searchBuildLinkCompletedStories(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Story>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 版本可关联的需求（产品内）
     */
    @Override
    public Page<Story> searchBuildLinkableStories(StorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Story> pages=baseMapper.searchBuildLinkableStories(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Story>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 获取版本相关需求
     */
    @Override
    public Page<Story> searchBuildStories(StorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Story> pages=baseMapper.searchBuildStories(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Story>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 通过模块查询
     */
    @Override
    public Page<Story> searchByModule(StorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Story> pages=baseMapper.searchByModule(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Story>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 相关用例需求
     */
    @Override
    public Page<Story> searchCaseStory(StorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Story> pages=baseMapper.searchCaseStory(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Story>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 子需求（更多）
     */
    @Override
    public Page<Story> searchChildMore(StorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Story> pages=baseMapper.searchChildMore(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Story>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 DEFAULT
     */
    @Override
    public Page<Story> searchDefault(StorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Story> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Story>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 ES批量的导入
     */
    @Override
    public Page<Story> searchESBulk(StorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Story> pages=baseMapper.searchESBulk(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Story>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 获取产品需求
     */
    @Override
    public Page<Story> searchGetProductStories(StorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Story> pages=baseMapper.searchGetProductStories(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Story>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我代理的需求
     */
    @Override
    public Page<Story> searchMyAgentStory(StorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Story> pages=baseMapper.searchMyAgentStory(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Story>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我创建或参与（权限）
     */
    @Override
    public Page<Story> searchMyCreateOrPartake(StorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Story> pages=baseMapper.searchMyCreateOrPartake(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Story>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 所创建需求数和对应的优先级及状态
     */
    @Override
    public Page<Story> searchMyCurOpenedStory(StorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Story> pages=baseMapper.searchMyCurOpenedStory(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Story>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我的收藏
     */
    @Override
    public Page<Story> searchMyFavorites(StorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Story> pages=baseMapper.searchMyFavorites(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Story>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 计划关联需求(去除已关联)
     */
    @Override
    public Page<Story> searchNotCurPlanLinkStory(StorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Story> pages=baseMapper.searchNotCurPlanLinkStory(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Story>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 数据查询
     */
    @Override
    public Page<Story> searchParentDefault(StorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Story> pages=baseMapper.searchParentDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Story>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 数据查询
     */
    @Override
    public Page<Story> searchParentDefaultQ(StorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Story> pages=baseMapper.searchParentDefaultQ(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Story>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 项目关联需求
     */
    @Override
    public Page<Story> searchProjectLinkStory(StorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Story> pages=baseMapper.searchProjectLinkStory(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Story>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 项目相关需求
     */
    @Override
    public Page<Story> searchProjectStories(StorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Story> pages=baseMapper.searchProjectStories(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Story>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 版本可关联的完成的需求
     */
    @Override
    public Page<Story> searchReleaseLinkableStories(StorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Story> pages=baseMapper.searchReleaseLinkableStories(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Story>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 获取产品发布相关需求
     */
    @Override
    public Page<Story> searchReleaseStories(StorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Story> pages=baseMapper.searchReleaseStories(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Story>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 通过模块查询
     */
    @Override
    public Page<Story> searchReportStories(StorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Story> pages=baseMapper.searchReportStories(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Story>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 获取产品发布相关需求
     */
    @Override
    public Page<Story> searchStoryChild(StorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Story> pages=baseMapper.searchStoryChild(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Story>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 获取产品发布相关需求
     */
    @Override
    public Page<Story> searchStoryRelated(StorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Story> pages=baseMapper.searchStoryRelated(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Story>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 需求细分
     */
    @Override
    public Page<Story> searchSubStory(StorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Story> pages=baseMapper.searchSubStory(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Story>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 任务相关需求
     */
    @Override
    public Page<Story> searchTaskRelatedStory(StorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Story> pages=baseMapper.searchTaskRelatedStory(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Story>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 默认（全部数据）
     */
    @Override
    public Page<Story> searchView(StorySearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Story> pages=baseMapper.searchView(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Story>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }



    /**
     * 为当前实体填充父数据（外键值文本、外键值附加数据）
     * @param et
     */
    private void fillParentData(Story et){
        //实体关系[DER1N_ZT_STORY_IBZ_PRODUCTMODULE_MODULE]
        if(!ObjectUtils.isEmpty(et.getModule())){
            cn.ibizlab.pms.core.ibiz.domain.ProductModule ibzproductmodule=et.getIbzproductmodule();
            if(ObjectUtils.isEmpty(ibzproductmodule)){
                cn.ibizlab.pms.core.ibiz.domain.ProductModule majorEntity=productmoduleService.get(et.getModule());
                et.setIbzproductmodule(majorEntity);
                ibzproductmodule=majorEntity;
            }
            et.setPath(ibzproductmodule.getPath());
            et.setModulename(ibzproductmodule.getName());
        }
        //实体关系[DER1N_ZT_STORY_ZT_BRANCH_BRANCH]
        if(!ObjectUtils.isEmpty(et.getBranch())){
            cn.ibizlab.pms.core.zentao.domain.Branch ztbranch=et.getZtbranch();
            if(ObjectUtils.isEmpty(ztbranch)){
                cn.ibizlab.pms.core.zentao.domain.Branch majorEntity=branchService.get(et.getBranch());
                et.setZtbranch(majorEntity);
                ztbranch=majorEntity;
            }
            et.setBranchname(ztbranch.getName());
        }
        //实体关系[DER1N_ZT_STORY_ZT_PRODUCT_PRODUCT]
        if(!ObjectUtils.isEmpty(et.getProduct())){
            cn.ibizlab.pms.core.zentao.domain.Product ztproduct=et.getZtproduct();
            if(ObjectUtils.isEmpty(ztproduct)){
                cn.ibizlab.pms.core.zentao.domain.Product majorEntity=productService.get(et.getProduct());
                et.setZtproduct(majorEntity);
                ztproduct=majorEntity;
            }
            et.setProductname(ztproduct.getName());
        }
        //实体关系[DER1N__ZT_STORY__ZT_STORY__PARENT]
        if(!ObjectUtils.isEmpty(et.getParent())){
            cn.ibizlab.pms.core.zentao.domain.Story ztparent=et.getZtparent();
            if(ObjectUtils.isEmpty(ztparent)){
                cn.ibizlab.pms.core.zentao.domain.Story majorEntity=storyService.get(et.getParent());
                et.setZtparent(majorEntity);
                ztparent=majorEntity;
            }
            et.setParentname(ztparent.getTitle());
        }
    }




    @Override
    public List<JSONObject> select(String sql, Map param){
        return this.baseMapper.selectBySQL(sql,param);
    }

    @Override
    @Transactional
    public boolean execute(String sql , Map param){
        if (sql == null || sql.isEmpty()) {
            return false;
        }
        if (sql.toLowerCase().trim().startsWith("insert")) {
            return this.baseMapper.insertBySQL(sql,param);
        }
        if (sql.toLowerCase().trim().startsWith("update")) {
            return this.baseMapper.updateBySQL(sql,param);
        }
        if (sql.toLowerCase().trim().startsWith("delete")) {
            return this.baseMapper.deleteBySQL(sql,param);
        }
        log.warn("暂未支持的SQL语法");
        return true;
    }

    @Override
    public List<Story> getStoryByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<Story> getStoryByEntities(List<Story> entities) {
        List ids =new ArrayList();
        for(Story entity : entities){
            Serializable id=entity.getId();
            if(!ObjectUtils.isEmpty(id)){
                ids.add(id);
            }
        }
        if(ids.size()>0) {
            return this.listByIds(ids);
        }
        else {
            return entities;
        }
    }


    public IStoryService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public Story dynamicCall(Long key, String action, Story et) {
        return et;
    }
}



