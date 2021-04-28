package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.zentao.domain.StorySpec;
import cn.ibizlab.pms.core.zentao.service.impl.StorySpecServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * 实体[发布] 自定义服务对象
 */
@Slf4j
@Primary
@Service("StorySpecExService")
public class StorySpecExService extends StorySpecServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    @Override
    public boolean create(StorySpec et) {
        et.setId(DigestUtils.md5DigestAsHex(String.format("%1$s__%2$s", et.getStory(), et.getVersion()).getBytes()));
        // return super.create(et);
        return true;
    }


}
