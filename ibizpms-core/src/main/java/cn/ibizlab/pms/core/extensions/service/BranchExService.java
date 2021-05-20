package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.zentao.domain.History;
import cn.ibizlab.pms.core.zentao.domain.Story;
import cn.ibizlab.pms.core.zentao.service.IActionService;
import cn.ibizlab.pms.core.zentao.service.IStoryService;
import cn.ibizlab.pms.core.zentao.service.impl.BranchServiceImpl;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.zentao.domain.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;

import java.util.*;

/**
 * 实体[产品的分支和平台信息] 自定义服务对象
 */
@Slf4j
@Primary
@Service("BranchExService")
public class BranchExService extends BranchServiceImpl {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.zentao.runtime.BranchRuntime branchRuntime;
    @Autowired
    IStoryService iStoryService;
    @Autowired
    IActionService iActionService;


    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [Sort:排序] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Branch sort(Branch et) {
        return super.sort(et);
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        //关联需求后，需要先删除关联（需求需要记录变更日志）
        List<Story> storyList = iStoryService.list(new QueryWrapper<Story>().eq("branch", key));
        if (storyList.size() > 0) {
            for (Story story : storyList) {
                Story old = new Story();
                CachedBeanCopier.copy(story, old);
                story.setBranch(0L);
                iStoryService.update(story);
//                List<History> changes = ChangeUtil.diff(old, story,null,new String[]{"branch"},new String[]{"branch"});
                History history = new History();
                history.setField("branch");
                history.setOld(String.valueOf(old.getBranch()));
                history.setIbiznew(String.valueOf(0));
//                history.setDiff(String.format("001- <del>%s</del>\n001+ <ins>%s</ins>", history.getOld(), history.getIbiznew()));
                List<History> changes = Collections.singletonList(history);
                ActionHelper.createHis(story.getId(), StaticDict.Action__object_type.STORY.getValue(), changes, StaticDict.Action__type.UNLINKEDFROMBRANCH.getValue(), "", "", null, iActionService);
            }
        }
        if (!branchRuntime.isRtmodel()) {
        }
        boolean result = removeById(key);
        return result;
    }
}

