package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.zentao.service.impl.BuildServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.zentao.domain.Build;
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

    /**
     * 自定义行为[LinkStories]用户扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Build linkStories(Build et) {
        return super.linkStories(et);
    }
    /**
     * 自定义行为[LinkStory]用户扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Build linkStory(Build et) {
        return super.linkStory(et);
    }
}

