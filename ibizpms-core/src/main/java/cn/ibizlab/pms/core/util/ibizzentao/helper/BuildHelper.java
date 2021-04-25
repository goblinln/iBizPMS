package cn.ibizlab.pms.core.util.ibizzentao.helper;

import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.util.ibizzentao.common.ZTDateUtil;
import cn.ibizlab.pms.core.zentao.domain.Action;
import cn.ibizlab.pms.core.zentao.domain.Bug;
import cn.ibizlab.pms.core.zentao.domain.Build;
import cn.ibizlab.pms.core.zentao.domain.History;
import cn.ibizlab.pms.core.zentao.mapper.BuildMapper;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author chenxiang
 */
@Component
public class BuildHelper extends ZTBaseHelper<BuildMapper, Build> {

    @Autowired
    ActionHelper actionHelper;

    @Autowired
    FileHelper fileHelper;

    @Autowired
    BugHelper bugHelper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean create(Build et) {
        String files = et.getFiles();
        boolean bOk = super.create(et);
        fileHelper.updateObjectID(et.getId(), StaticDict.File__object_type.BUILD.getValue(), files, "");

        actionHelper.create(StaticDict.Action__object_type.BUILD.getValue(), et.getId(), StaticDict.Action__type.OPENED.getValue(), "", "", null, true);

        return bOk;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean edit(Build et) {
        Build old = this.get(et.getId());

        fileHelper.processImgURL(et, null, null);
        String files = et.getFiles();
        internalUpdate(et);
        fileHelper.updateObjectID(et.getId(), StaticDict.File__object_type.BUILD.getValue(), files, "");

        List<History> changes = ChangeUtil.diff(old, et);
        if (changes.size() > 0) {
            Action action = actionHelper.create(StaticDict.Action__object_type.BUILD.getValue(), et.getId(), StaticDict.Action__type.EDITED.getValue(), "", "", null, true);
            actionHelper.logHistory(action.getId(), changes);
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public Build linkStory(Build et) {

        if (et.getId() == null) {
            return et;
        }
        Build build = get(et.getId());
        String stories = "";
        if(et.getStories() != null && !"".equals(et.getStories())) {
            stories = et.getStories();
        } else if(et.get(FIELD_SRFACTIONPARAM) != null) {
            ArrayList<Map> list = (ArrayList) et.get(FIELD_SRFACTIONPARAM);
            for (Map data : list) {
                if (stories.length() > 0) {
                    stories += MULTIPLE_CHOICE;
                }
                stories += data.get(FIELD_ID);
            }
        }
        if("".equals(stories)) {
            return et;
        }
        for (String storyId : stories.split(MULTIPLE_CHOICE)) {
            if (StringUtils.isBlank(build.getStories())) {
                build.setStories(storyId);
                internalUpdate(build);
            } else {
                if (!(MULTIPLE_CHOICE + build.getStories() ).contains(MULTIPLE_CHOICE + storyId )) {
                    build.setStories(build.getStories() + MULTIPLE_CHOICE + storyId);
                    internalUpdate(build);
                    actionHelper.create(StaticDict.Action__object_type.STORY.getValue(), Long.parseLong(storyId), StaticDict.Action__type.LINKED2BUILD.getValue(),
                            "", String.valueOf(build.getId()), null, true);
                }
            }
        }
        return et;
    }

    @Transactional(rollbackFor = Exception.class)
    public Build unlinkStory(Build et) {
        Build build = get(et.getId());
        if (et.get(FIELD_STORIES) == null) {
            return et;
        }
        for (String storyId : et.get(FIELD_STORIES).toString().split(MULTIPLE_CHOICE)) {
            if ((MULTIPLE_CHOICE + build.getStories() ).contains(MULTIPLE_CHOICE + storyId )) {
                String stories = (MULTIPLE_CHOICE + build.getStories() ).replace(MULTIPLE_CHOICE + storyId , "");
                if(stories.indexOf(MULTIPLE_CHOICE)==0) {
                    stories = stories.substring(1,stories.length()) ;
                }
                build.setStories(stories);
                internalUpdate(build);
                actionHelper.create(StaticDict.Action__object_type.STORY.getValue(), Long.parseLong(storyId), StaticDict.Action__type.UNLINKEDFROMBUILD.getValue(),
                        "", String.valueOf(build.getId()), null, true);
            }
        }
        return et;
    }

    @Transactional(rollbackFor = Exception.class)
    public Build linkBug(Build et) {
        Build build = get(et.getId());
        if (et.get(FIELD_BUGS) == null) {
            return et;
        }
        String[] resolvedbys = et.get(FIELD_RESOLVEDBY).toString().split(MULTIPLE_CHOICE);
        int i = 0;
        for (String bugId : et.get(FIELD_BUGS).toString().split(MULTIPLE_CHOICE)) {
            String bug = "";
            if (StringUtils.isBlank(build.getBugs())) {
                bug = bugId;
            } else {
                if (!(MULTIPLE_CHOICE + build.getBugs() + MULTIPLE_CHOICE).contains(MULTIPLE_CHOICE + bugId + MULTIPLE_CHOICE)) {
                    bug = build.getBugs() + MULTIPLE_CHOICE + bugId;
                }else {
                    bug = build.getBugs();
                }
            }
            build.setBugs(bug);
            internalUpdate(build);

            actionHelper.create(StaticDict.Action__object_type.BUG.getValue(), Long.parseLong(bugId), StaticDict.Action__type.LINKED2BUG.getValue(),
                    "", String.valueOf(build.getId()), null, true);
            Bug bugs = bugHelper.get(Long.parseLong(bugId));
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
            bugHelper.internalUpdate(newBug);
            i ++;
        }
        return et;
    }

    @Transactional(rollbackFor = Exception.class)
    public Build unlinkBug(Build et) {
        Build build = get(et.getId());
        if (et.get(FIELD_BUGS) == null) {
            return et;
        }
        for (String bugId : et.get(FIELD_BUGS).toString().split(MULTIPLE_CHOICE)) {
            if ((MULTIPLE_CHOICE + build.getBugs()).contains(MULTIPLE_CHOICE + bugId )) {
                String bugs =(MULTIPLE_CHOICE + build.getBugs()).replace(MULTIPLE_CHOICE + bugId, "");
                if(bugs.indexOf(MULTIPLE_CHOICE)==0) {
                    bugs = bugs.substring(1,bugs.length()) ;
                }
                build.setBugs(bugs);
                internalUpdate(build);
                actionHelper.create(StaticDict.Action__object_type.BUG.getValue(), Long.parseLong(bugId), StaticDict.Action__type.UNLINKEDFROMBUILD.getValue(),
                        "", String.valueOf(build.getId()), null, true);
            }
        }
        return et;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(Long key){
        Build old = this.get(key);
        if (old.getBugs() !=null &&  !"".equals(old.getBugs())){
            throw  new RuntimeException("已有Bug的解决版本关联此版本，不能删除!");
        }
        List<Bug> linkedBugs = bugHelper.list(new QueryWrapper<Bug>().in("openedBuild",old.getId()));
        for (Bug bug : linkedBugs) {
            if (Arrays.asList(bug.getOpenedbuild().split(",")).contains(key.toString())){
                throw new RuntimeException("已有bug的影响版本关联此版本，不能删除！");
            }
        }

        return super.delete(key);
    }


}
