package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.zentao.domain.ProjectStory;
import cn.ibizlab.pms.core.zentao.service.impl.ProjectStoryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Slf4j
@Primary
@Service("ProjectStoryExService")
public class ProjectStoryExService extends ProjectStoryServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    @Override
    public boolean create(ProjectStory et) {
        et.setId(DigestUtils.md5DigestAsHex(String.format("%1$s__%2$s", et.getProject(), et.getStory()).getBytes()));
        return super.create(et);
    }
}

