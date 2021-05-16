package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.zentao.filter.BurnSearchContext;
import cn.ibizlab.pms.core.zentao.service.impl.BurnServiceImpl;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.zentao.domain.Burn;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.util.DigestUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 实体[burn] 自定义服务对象
 */
@Slf4j
@Primary
@Service("BurnExService")
public class BurnExService extends BurnServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [ComputeBurn:更新燃尽图] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Burn computeBurn(Burn et) {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        getProxyService().remove(new QueryWrapper<Burn>().eq("date", ft.format(new Date())));

        String burnSql = "SELECT t1.project,0 as task,DATE_FORMAT( now( ), '%Y-%m-%d' ) as date,sum( t1.estimate ) AS estimate,sum( IF ( t1.`status` <> 'closed', t1.`left`, 0 ) ) AS `left`,sum( t1.consumed ) AS consumed FROM zt_task t1 LEFT JOIN zt_project t ON t.id = t1.project  WHERE t.`end` >= DATE_FORMAT( now( ), '%Y-%m-%d' )  AND t.type <> 'ops'  AND t.`status` NOT IN ( 'done', 'closed', 'suspended' )  AND t1.deleted = '0'  AND t1.parent >= 0  AND t1.`status` <> 'cancel'  GROUP BY t1.project";
        List<JSONObject> list = this.select(burnSql, null);
        for(JSONObject jsonObject : list) {
            Burn burn = new Burn();
            burn.setProject(jsonObject.getLongValue("project"));
            burn.setDate(jsonObject.getTimestamp("date"));
            burn.setEstimate(jsonObject.getDoubleValue("estimate"));
            burn.setConsumed(jsonObject.getDoubleValue("consumed"));
            burn.setLeft(jsonObject.getDoubleValue("left"));
            getProxyService().create(burn);
        }
        return super.computeBurn(et);
    }

    @Override
    public boolean create(Burn et) {
        et.setId(UUID.randomUUID().toString());
        // et.setId(DigestUtils.md5DigestAsHex(String.format("%1$s__%2$s", et.getProject(), et.getTask(),et.getDate()).getBytes()));
        return super.create(et);
    }
}

