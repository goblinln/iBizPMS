package cn.ibizlab.pms.core.ou.service;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.math.BigInteger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cache.annotation.CacheEvict;

import cn.ibizlab.pms.core.ou.domain.SysTeam;
import cn.ibizlab.pms.core.ou.filter.SysTeamSearchContext;


/**
 * 实体[SysTeam] 服务对象接口
 */
public interface ISysTeamService {

    /**
     * 业务实体显示文本名称
     */
    final static String OBJECT_TEXT_NAME = "组";

    /**
     * 业务实体资源路径名
     */
    final static String OBJECT_SOURCE_PATH = "systeams";

    boolean create(SysTeam et);
    void createBatch(List<SysTeam> list);
    boolean update(SysTeam et);
    void updateBatch(List<SysTeam> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    SysTeam get(String key);
    SysTeam getDraft(SysTeam et);
    boolean checkKey(SysTeam et);
    boolean save(SysTeam et);
    void saveBatch(List<SysTeam> list);
    Page<SysTeam> searchDefault(SysTeamSearchContext context);
    SysTeam dynamicCall(String key, String action, SysTeam et);


}



