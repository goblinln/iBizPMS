package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.zentao.domain.StorySpec;
import cn.ibizlab.pms.core.zentao.service.impl.StorySpecServiceImpl;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * 实体[发布] 自定义服务对象
 */
@Slf4j
@Primary
@Service("StorySpecExService")
public class StorySpecExService extends StorySpecServiceImpl {

    @Override
    public boolean create(StorySpec et) {

//        fillParentData(et);
//
//        if(!this.retBool(this.baseMapper.insert(et))) {
//            return false;
//        }
//        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    /**
     * 为当前实体填充父数据（外键值文本、外键值附加数据）
     * @param et
     */
    private void fillParentData(StorySpec et) {
        //实体关系[DER1N_ZT_STORYSPEC_ZT_STORY_STORY]
        if (!ObjectUtils.isEmpty(et.getStory())) {
            cn.ibizlab.pms.core.zentao.domain.Story ztstory = et.getZtstory();
            if (ObjectUtils.isEmpty(ztstory)) {
                cn.ibizlab.pms.core.zentao.domain.Story majorEntity = storyService.get(et.getStory());
                et.setZtstory(majorEntity);
                ztstory = majorEntity;
            }
            et.setTitle(ztstory.getTitle());
            et.setVersion(ztstory.getVersion());
        }
    }
}
