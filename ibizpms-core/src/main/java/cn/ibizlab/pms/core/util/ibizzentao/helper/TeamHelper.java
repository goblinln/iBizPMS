package cn.ibizlab.pms.core.util.ibizzentao.helper;

import cn.ibizlab.pms.core.zentao.domain.Team;
import cn.ibizlab.pms.core.zentao.mapper.TeamMapper;
import org.springframework.stereotype.Component;

/**
 * @author chenxiang
 */
@Component
public class TeamHelper extends ZTBaseHelper<TeamMapper, Team> {

    public Team managePorjectMembers(Team et) {
        throw new RuntimeException("未实现");
    }

    public Team unlinkPorjectMember(Team et) {
        throw new RuntimeException("未实现");
    }
}
