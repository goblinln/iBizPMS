package cn.ibizlab.pms.core.util.ibizzentao.helper;

import cn.ibizlab.pms.core.ibiz.domain.IbzAgent;
import cn.ibizlab.pms.core.ibiz.mapper.IbzAgentMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wulonghu
 */
@Component
@Slf4j
public class IbzAgentHelper extends ZTBaseHelper<IbzAgentMapper, IbzAgent> {

    /**
     * 创建时判断同一代理用户的代理时间是否有交叉，若有则提示报错。
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean create(IbzAgent et) {
        List<IbzAgent> agentList = this.list(new QueryWrapper<IbzAgent>().eq("CreateManName", et.getCreatemanname())
                .le("AgentBegin", et.getAgentend()).ge("AgentEnd", et.getAgentbegin()));
        if (agentList.size() > 0) {
            throw new RuntimeException("您在该时间段已存在代理人！");
        }
        if (!SqlHelper.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        return true;
    }
}
