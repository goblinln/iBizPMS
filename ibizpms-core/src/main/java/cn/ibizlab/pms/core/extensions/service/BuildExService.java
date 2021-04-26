package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.util.ibizzentao.common.ZTDateUtil;
import cn.ibizlab.pms.core.zentao.domain.Bug;
import cn.ibizlab.pms.core.zentao.domain.History;
import cn.ibizlab.pms.core.zentao.service.IActionService;
import cn.ibizlab.pms.core.zentao.service.IBugService;
import cn.ibizlab.pms.core.zentao.service.IFileService;
import cn.ibizlab.pms.core.zentao.service.impl.BuildServiceImpl;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.zentao.domain.Build;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[版本] 自定义服务对象
 */
@Slf4j
@Primary
@Service("BuildExService")
public class BuildExService extends BuildServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    @Autowired
    IActionService iActionService;

    @Autowired
    IFileService iFileService;

    @Autowired
    IBugService iBugService;

    @Override
    public boolean create(Build et) {
        String files = et.getFiles();
        if(!super.create(et)) {
            return false;
        }
        FileHelper.updateObjectID(et.getId(), StaticDict.File__object_type.BUILD.getValue(), files, "", iFileService);
        ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.BUILD.getValue(), null,  StaticDict.Action__type.OPENED.getValue(), "","", null, iActionService);
        return true;
    }

    @Override
    public boolean update(Build et) {
        Build old = this.get(et.getId());
        String files = et.getFiles();
        if(!super.update(et)) {
            return false;
        }
        FileHelper.updateObjectID(et.getId(), StaticDict.File__object_type.BUILD.getValue(), files, "", iFileService);
        List<History> changes = ChangeUtil.diff(old, et);
        if (changes.size() > 0) {
            ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.BUILD.getValue(), changes, StaticDict.Action__type.EDITED.getValue(), "", "", null, iActionService);
        }
        return true;
    }

    @Override
    public boolean remove(Long key) {
        Build old = this.get(key);
        if (old.getBugs() !=null &&  !"".equals(old.getBugs())){
            throw  new RuntimeException("已有Bug的解决版本关联此版本，不能删除!");
        }
        List<Bug> linkedBugs = iBugService.list(new QueryWrapper<Bug>().in("openedBuild",old.getId()));
        for (Bug bug : linkedBugs) {
            if (Arrays.asList(bug.getOpenedbuild().split(",")).contains(key.toString())){
                throw new RuntimeException("已有bug的影响版本关联此版本，不能删除！");
            }
        }
        return super.remove(key);
    }

    /**
     * [LinkBug:关联Bug] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Build linkBug(Build et) {
        Build build = get(et.getId());
        if (et.get("bugs") == null) {
            return et;
        }
        String[] resolvedbys = et.get("resolvedby").toString().split(",");
        int i = 0;
        for (String bugId : et.get("bugs").toString().split(",")) {
            String bug = "";
            if (StringUtils.isBlank(build.getBugs())) {
                bug = bugId;
            } else {
                if (!("," + build.getBugs() + ",").contains("," + bugId + ",")) {
                    bug = build.getBugs() + "," + bugId;
                }else {
                    bug = build.getBugs();
                }
            }
            build.setBugs(bug);
            super.update(build);

            ActionHelper.createHis(Long.parseLong(bugId), StaticDict.Action__object_type.BUG.getValue(), null,  StaticDict.Action__type.LINKED2BUG.getValue(), "",String.valueOf(build.getId()), null, iActionService);
            Bug bugs = iBugService.get(Long.parseLong(bugId));
            if(StaticDict.Bug__status.RESOLVED.getValue().equals(bugs.getStatus()) || StaticDict.Bug__status.CLOSED.getValue().equals(bugs.getStatus())) {
                i ++;
                continue;
            }
            String resolvedby = AuthenticationUser.getAuthenticationUser().getUsername();
            if(resolvedbys.length > i && resolvedbys[i] != null && !"".equals(resolvedbys[i])) {
                resolvedby = resolvedbys[i];
            }
            Bug newBug = new Bug();
            newBug.setId(Long.parseLong(bugId));
            newBug.setStatus(StaticDict.Bug__status.RESOLVED.getValue());
            newBug.setResolveddate(ZTDateUtil.now());
            newBug.setResolvedby(resolvedby);
            newBug.setConfirmed(1);
            newBug.setAssignedto(bugs.getOpenedby());
            newBug.setAssigneddate(ZTDateUtil.now());
            newBug.setLasteditedby(AuthenticationUser.getAuthenticationUser().getUsername());
            newBug.setLastediteddate(ZTDateUtil.now());
            newBug.setResolution(StaticDict.Bug__resolution.FIXED.getValue());
            newBug.setResolvedbuild(String.valueOf(et.getId()));
            iBugService.sysUpdate(newBug);
            i ++;
        }
        return et;
    }

    @Override
    public Build unlinkStory(Build et) {
        Build build = get(et.getId());
        if (et.get("stories") == null) {
            return et;
        }
        for (String storyId : et.get("stories").toString().split(",")) {
            if (("," + build.getStories() ).contains("," + storyId )) {
                String stories = ("," + build.getStories() ).replace("," + storyId , "");
                if(stories.indexOf(",")==0) {
                    stories = stories.substring(1,stories.length()) ;
                }
                build.setStories(stories);
                super.update(build);
                ActionHelper.createHis(Long.parseLong(storyId), StaticDict.Action__object_type.STORY.getValue(), null,  StaticDict.Action__type.UNLINKEDFROMBUILD.getValue(), "",String.valueOf(build.getId()), null, iActionService);
            }
        }
        return et;
    }

    /**
     * [LinkStory:关联需求] 行为扩展：【版本】关联需求、使用多项数据选择视图，选择多个数据，再保存关联性。
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Build linkStory(Build et) {
        if (et.getId() == null) {
            return et;
        }
        Build build = get(et.getId());
        String stories = "";
        if(et.getStories() != null && !"".equals(et.getStories())) {
            stories = et.getStories();
        } else if(et.get("srfactionparam") != null) {
            ArrayList<Map> list = (ArrayList) et.get("srfactionparam");
            for (Map data : list) {
                if (stories.length() > 0) {
                    stories += ",";
                }
                stories += data.get("id");
            }
        }
        if("".equals(stories)) {
            return et;
        }
        for (String storyId : stories.split(",")) {
            if (StringUtils.isBlank(build.getStories())) {
                build.setStories(storyId);
                super.update(build);
            } else {
                if (!("," + build.getStories() ).contains("," + storyId )) {
                    build.setStories(build.getStories() + "," + storyId);
                    super.update(build);

                    ActionHelper.createHis(Long.parseLong(storyId), StaticDict.Action__object_type.STORY.getValue(), null,  StaticDict.Action__type.LINKED2BUILD.getValue(), "",String.valueOf(build.getId()), null, iActionService);

                }
            }
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
    public Build oneClickRelease(Build et) {
        return super.oneClickRelease(et);
    }
    /**
     * [UnlinkBug:移除Bug关联] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Build unlinkBug(Build et) {
        Build build = get(et.getId());
        if (et.get("bugs") == null) {
            return et;
        }
        for (String bugId : et.get("bugs").toString().split(";")) {
            if (("," + build.getBugs()).contains("," + bugId )) {
                String bugs =("," + build.getBugs()).replace("," + bugId, "");
                if(bugs.indexOf(",")==0) {
                    bugs = bugs.substring(1,bugs.length()) ;
                }
                build.setBugs(bugs);
                super.update(build);
                ActionHelper.createHis(Long.parseLong(bugId), StaticDict.Action__object_type.BUG.getValue(), null,  StaticDict.Action__type.UNLINKEDFROMBUILD.getValue(), "",String.valueOf(build.getId()), null, iActionService);
            }
        }
        return et;
    }
}