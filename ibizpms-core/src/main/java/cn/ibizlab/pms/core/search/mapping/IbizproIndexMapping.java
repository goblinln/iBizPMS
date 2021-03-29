package cn.ibizlab.pms.core.search.mapping;

import cn.ibizlab.pms.core.es.domain.IbizproIndex;

import lombok.extern.slf4j.Slf4j;
import java.io.IOException;

@Slf4j
public class IbizproIndexMapping {

    /**
     * 映射关系：Bug --> IbizproIndex
     */
    public static IbizproIndex bug2IbizproIndex(cn.ibizlab.pms.core.zentao.domain.Bug srcET) {
        IbizproIndex indexET = new IbizproIndex();

        //添加类型信息
        indexET.setIndextype("bug");

        //添加doc主键、数据主键及主信息
        indexET.setDocid("bug:" + srcET.getId());
        indexET.setIndexid(srcET.getId());
        indexET.setIndexname(srcET.getTitle());

        //添加其他关系映射字段
        indexET.setDeleted(srcET.getDeleted());
        indexET.setProduct(srcET.getProduct());
        indexET.setIndexdesc(srcET.getSteps());
        indexET.setProject(srcET.getProject());

        return indexET;
    }
    /**
     * 映射关系：Case --> IbizproIndex
     */
    public static IbizproIndex case2IbizproIndex(cn.ibizlab.pms.core.zentao.domain.Case srcET) {
        IbizproIndex indexET = new IbizproIndex();

        //添加类型信息
        indexET.setIndextype("case");

        //添加doc主键、数据主键及主信息
        indexET.setDocid("case:" + srcET.getId());
        indexET.setIndexid(srcET.getId());
        indexET.setIndexname(srcET.getTitle());

        //添加其他关系映射字段
        indexET.setDeleted(srcET.getDeleted());
        indexET.setIndexdesc(srcET.getPrecondition());
        indexET.setProduct(srcET.getProduct());

        return indexET;
    }
    /**
     * 映射关系：Product --> IbizproIndex
     */
    public static IbizproIndex product2IbizproIndex(cn.ibizlab.pms.core.zentao.domain.Product srcET) {
        IbizproIndex indexET = new IbizproIndex();

        //添加类型信息
        indexET.setIndextype("product");

        //添加doc主键、数据主键及主信息
        indexET.setDocid("product:" + srcET.getId());
        indexET.setIndexid(srcET.getId());
        indexET.setIndexname(srcET.getName());

        //添加其他关系映射字段
        indexET.setDeleted(srcET.getDeleted());
        indexET.setIndexdesc(srcET.getDesc());
        indexET.setProduct(srcET.getId());

        return indexET;
    }
    /**
     * 映射关系：Project --> IbizproIndex
     */
    public static IbizproIndex project2IbizproIndex(cn.ibizlab.pms.core.zentao.domain.Project srcET) {
        IbizproIndex indexET = new IbizproIndex();

        //添加类型信息
        indexET.setIndextype("project");

        //添加doc主键、数据主键及主信息
        indexET.setDocid("project:" + srcET.getId());
        indexET.setIndexid(srcET.getId());
        indexET.setIndexname(srcET.getName());

        //添加其他关系映射字段
        indexET.setDeleted(srcET.getDeleted());
        indexET.setIndexdesc(srcET.getDesc());
        indexET.setProject(srcET.getId());

        return indexET;
    }
    /**
     * 映射关系：Story --> IbizproIndex
     */
    public static IbizproIndex story2IbizproIndex(cn.ibizlab.pms.core.zentao.domain.Story srcET) {
        IbizproIndex indexET = new IbizproIndex();

        //添加类型信息
        indexET.setIndextype("story");

        //添加doc主键、数据主键及主信息
        indexET.setDocid("story:" + srcET.getId());
        indexET.setIndexid(srcET.getId());
        indexET.setIndexname(srcET.getTitle());

        //添加其他关系映射字段
        indexET.setDeleted(srcET.getDeleted());
        indexET.setColor(srcET.getColor());
        indexET.setProduct(srcET.getProduct());
        indexET.setIndexdesc(srcET.getSpec());

        return indexET;
    }
    /**
     * 映射关系：Task --> IbizproIndex
     */
    public static IbizproIndex task2IbizproIndex(cn.ibizlab.pms.core.zentao.domain.Task srcET) {
        IbizproIndex indexET = new IbizproIndex();

        //添加类型信息
        indexET.setIndextype("task");

        //添加doc主键、数据主键及主信息
        indexET.setDocid("task:" + srcET.getId());
        indexET.setIndexid(srcET.getId());
        indexET.setIndexname(srcET.getName());

        //添加其他关系映射字段
        indexET.setDeleted(srcET.getDeleted());
        indexET.setIndexdesc(srcET.getDesc());
        indexET.setProject(srcET.getProject());

        return indexET;
    }
}




