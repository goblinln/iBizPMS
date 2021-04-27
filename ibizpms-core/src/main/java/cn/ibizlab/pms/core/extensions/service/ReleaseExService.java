package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.util.ibizzentao.common.ZTDateUtil;
import cn.ibizlab.pms.core.zentao.domain.*;
import cn.ibizlab.pms.core.zentao.service.*;
import cn.ibizlab.pms.core.zentao.service.impl.ReleaseServiceImpl;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

import static cn.ibizlab.pms.core.util.ibizzentao.helper.ZTBaseHelper.*;

/**
 * 实体[发布] 自定义服务对象
 */
@Slf4j
@Primary
@Service("ReleaseExService")
public class ReleaseExService extends ReleaseServiceImpl {

    @Autowired
    IActionService iActionService;
    @Autowired
    IFileService iFileService;
    @Autowired
    IBuildService iBuildService;
    @Autowired
    IBugService iBugService;
    @Autowired
    IStoryService iStoryService;
    @Autowired
    IStoryStageService iStoryStageService;
    @Autowired
    IProductService iProductService;

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [Activate:状态变更（激活）] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Release activate(Release et) {
        et.setStatus(StaticDict.Release__status.NORMAL.getValue());
        super.update(et);
        ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.RELEASE.getValue(),null,StaticDict.Action__type.CHANGESTATUS.getValue(),
                "",StaticDict.Release__status.NORMAL.getValue(), null,iActionService);
        return et;
    }
    /**
     * [BatchUnlinkBug:批量解除关联Bug] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Release batchUnlinkBug(Release et) {
        throw new RuntimeException("未实现");
    }
    /**
     * [ChangeStatus:状态变更] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Release changeStatus(Release et) {
        throw new RuntimeException("未实现");
    }
    /**
     * [LinkBug:关联Bug] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Release linkBug(Release et) {

        if (et.getId() == null || et.get(FIELD_SRFACTIONPARAM) == null) {
            return et;
        }
        String bugs = "";
        ArrayList<Map> list = (ArrayList) et.get(FIELD_SRFACTIONPARAM);
        for (Map data : list) {
            if (bugs.length() > 0) {
                bugs += MULTIPLE_CHOICE;
            }
            bugs += data.get(FIELD_ID);
        }
        et = this.get(et.getId());
        Release release = new Release();
        release.setId(et.getId());
        release.setBugs(et.getBugs() + MULTIPLE_CHOICE + bugs);
        Product product = iProductService.get(et.getProduct());
        super.update(release);

        for(String bug : bugs.split(MULTIPLE_CHOICE)) {
            ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.BUG.getValue(),null,StaticDict.Action__type.LINKED2RELEASE.getValue(),
                    "",String.valueOf(et.getId()), null,iActionService);
        }
        return et;
    }
    /**
     * [LinkBugbyBug:关联Bug（解决Bug）] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Release linkBugbyBug(Release et) {
        throw new RuntimeException("未实现");
    }
    /**
     * [LinkBugbyLeftBug:关联Bug（遗留Bug）] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Release linkBugbyLeftBug(Release et) {
        if (et.getId() == null || et.get(FIELD_SRFACTIONPARAM) == null) {
            return et;
        }
        String bugs = "";
        ArrayList<Map> list = (ArrayList) et.get(FIELD_SRFACTIONPARAM);
        for (Map data : list) {
            if (bugs.length() > 0) {
                bugs += MULTIPLE_CHOICE;
            }
            bugs += data.get(FIELD_ID);
        }
        et = this.get(et.getId());
        Release release = new Release();
        release.setId(et.getId());
        release.setLeftbugs(bugs);
        Product product = iProductService.get(et.getProduct());
        super.update(release);

        for(String bug : bugs.split(MULTIPLE_CHOICE)) {
            ActionHelper.createHis(Long.parseLong(bug),StaticDict.Action__object_type.BUG.getValue(),null,StaticDict.Action__type.LINKED2RELEASE.getValue(),
                    "",String.valueOf(et.getId()), null,iActionService);
        }
        return et;
    }
    /**
     * [LinkStory:关联需求] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Release linkStory(Release et) {
        if (et.getId() == null || et.get(FIELD_SRFACTIONPARAM) == null) {
            return et;
        }
        String stories = "";
        ArrayList<Map> list = (ArrayList) et.get(FIELD_SRFACTIONPARAM);
        for (Map data : list) {
            if (stories.length() > 0) {
                stories += MULTIPLE_CHOICE;
            }
            stories += data.get(FIELD_ID);
        }
        et = this.get(et.getId());
        Release release = new Release();
        release.setId(et.getId());
        release.setStories(et.getStories() + MULTIPLE_CHOICE + stories);
        Product product = iProductService.get(et.getProduct());
        super.update(release);

        for(String story : stories.split(MULTIPLE_CHOICE)) {
            if("".equals(story)) {
                continue;
            }
            Story story1 = new Story();
            story1.setId(Long.parseLong(story));
            story1.setStagedby("");
            iStoryService.sysUpdate(story1);
            if(!StaticDict.Product__status.NORMAL.getValue().equals(product.getType())) {
                StoryStage storyStage = new StoryStage();
                storyStage.setStagedby("");
                Map<String,Object> param = new HashMap<>();
                param.put(StaticDict.Action__object_type.STORY.getValue(),story);
                param.put(StaticDict.Action__object_type.BRANCH.getValue(),et.getBranch());
                iStoryStageService.update(storyStage, (Wrapper)storyStage.getUpdateWrapper(true).allEq(param));
            }
            iStoryService.setStage(story1);
            ActionHelper.createHis(Long.parseLong(story),StaticDict.Action__object_type.STORY.getValue(),null,StaticDict.Action__type.LINKED2RELEASE.getValue(),
                    "",String.valueOf(et.getId()), null,iActionService);

        }
        return et;
    }
    /**
     * [OneClickRelease:一键发布] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Release oneClickRelease(Release et) {
        return super.oneClickRelease(et);
    }
    /**
     * [Terminate:状态变更（停止维护）] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Release terminate(Release et) {
        et.setStatus(StaticDict.Release__status.TERMINATE.getValue());
        super.update(et);
        ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.RELEASE.getValue(),null,StaticDict.Action__type.CHANGESTATUS.getValue(),
                "",StaticDict.Release__status.TERMINATE.getValue(), null,iActionService);

        return et;
    }
    /**
     * [UnlinkBug:解除关联Bug] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Release unlinkBug(Release et) {
        throw new RuntimeException("未实现");
    }

    @Override
    public boolean create(Release et) {
        boolean bOk = false;

        if (et.getBuild() == 0) {
            Build buildQuery = new Build();
            buildQuery.setDeleted(StaticDict.YesNo.ITEM_0.getValue());
            buildQuery.setName(et.getName());
            buildQuery.setProduct(et.getProduct());
            buildQuery.setBranch(et.getBranch());
            Build build = iBuildService.getOne(new QueryWrapper<Build>().setEntity(buildQuery));
            if (build == null) {
                buildQuery.setDesc(et.getDesc());
                buildQuery.setDate(ZTDateUtil.now());
                buildQuery.setBuilder(AuthenticationUser.getAuthenticationUser().getUsername());
                iBuildService.create(buildQuery);
                build = buildQuery;
            }
            et.setBuild(build.getId());
        }

        String files = et.getFiles();
        bOk = super.create(et);
        FileHelper.updateObjectID(et.getId(),StaticDict.File__object_type.RELEASE.getValue(),files,"",iFileService);

        ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.RELEASE.getValue(),null,StaticDict.Action__type.OPENED.getValue(),
                "","", null,iActionService);
        return bOk;
    }

    @Override
    public boolean update(Release et) {
        Release old = new Release();
        CachedBeanCopier.copy(get(et.getId()), old);
        String files = et.getFiles();
        if (!super.update(et)) {
            return false;
        }
        FileHelper.updateObjectID(et.getId(),StaticDict.File__object_type.RELEASE.getValue(),files,"",iFileService);

        List<History> changes = ChangeUtil.diff(old, et,null,null,new String[]{"desc"});
        if (changes.size() > 0) {
            ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.RELEASE.getValue(),changes,StaticDict.Action__type.EDITED.getValue(),
                    "","", null,iActionService);
        }
        return true;
    }

    @Override
    public boolean remove(Long key){
        boolean bOk = false;

        Release old = this.get(key);
        if(old.getBuild() != null && old.getBuild() != 0) {
            deletedBuild(old.getBuild(), old.getName());
        }

        bOk = super.remove(key);
        return bOk;
    }

    public boolean deletedBuild(Long key, String name){
        List<Build> list1 = iBuildService.list(new QueryWrapper<Build>().eq("`id`", key));
        for(Build build : list1) {
            if(build.getProject() == null || build.getProject() == 0L) {
                iBuildService.remove(build.getId());
            }

        }
        List<Build> list = iBuildService.list(new QueryWrapper<Build>().eq("`id`", key).eq("`name`", name));
        for(Build build : list) {
            iBuildService.remove(build.getId());
        }
        return true;
    }


}

