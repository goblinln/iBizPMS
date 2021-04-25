package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.zentao.service.impl.FileServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.zentao.domain.File;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[附件] 自定义服务对象
 */
@Slf4j
@Primary
@Service("FileExService")
public class FileExService extends FileServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [UpdateObjectID:更新文件] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public File updateObjectID(File et) {
        return super.updateObjectID(et);
    }
    /**
     * [UpdateObjectIDForPmsEe:保存附件] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public File updateObjectIDForPmsEe(File et) {
        return super.updateObjectIDForPmsEe(et);
    }
}

