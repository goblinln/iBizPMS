import { IEntityBase } from 'ibiz-core';

/**
 * 产品汇总表
 *
 * @export
 * @interface IProductSum
 * @extends {IEntityBase}
 */
export interface IProductSum extends IEntityBase {
    /**
     * 设计缺陷
     */
    designdefect?: any;
    /**
     * 已变更
     */
    changedstorycnt?: any;
    /**
     * 研发中阶段需求工时
     */
    developingstagestoryhours?: any;
    /**
     * 总计
     */
    storycnt?: any;
    /**
     * 其他
     */
    others?: any;
    /**
     * 配置相关
     */
    config?: any;
    /**
     * 代码错误
     */
    codeerror?: any;
    /**
     * bug总计
     */
    bugsum?: any;
    /**
     * 性能问题
     */
    performance?: any;
    /**
     * 结束日期
     */
    end?: any;
    /**
     * 已关闭
     */
    closedstorycnt?: any;
    /**
     * 已关闭阶段需求工时
     */
    closedstagestoryhours?: any;
    /**
     * 测试完毕阶段需求工时
     */
    testedstagestoryhours?: any;
    /**
     * 未开始阶段需求工时
     */
    waitstagestoryhours?: any;
    /**
     * 产品负责人
     */
    po?: any;
    /**
     * 测试中阶段需求工时
     */
    testingstagestoryhours?: any;
    /**
     * 已立项阶段需求工时
     */
    projectedstagestoryhours?: any;
    /**
     * 已立项阶段需求数量
     */
    projectedstagestorycnt?: any;
    /**
     * 已验收阶段需求数量
     */
    verifiedstagestorycnt?: any;
    /**
     * 主键标识
     */
    id?: any;
    /**
     * 总工时
     */
    totalhours?: any;
    /**
     * 已计划阶段需求数量
     */
    planedstagestorycnt?: any;
    /**
     * 研发中阶段需求数量
     */
    developingstagestorycnt?: any;
    /**
     * 激活
     */
    activestorycnt?: any;
    /**
     * Bug数
     */
    bugcnt?: any;
    /**
     * 未开始阶段需求数量
     */
    waitstagestorycnt?: any;
    /**
     * 已验收阶段需求工时
     */
    verifiedstagestoryhours?: any;
    /**
     * 草稿
     */
    waitstorycnt?: any;
    /**
     * 测试中阶段需求数量
     */
    testingstagestorycnt?: any;
    /**
     * 开始日期
     */
    begin?: any;
    /**
     * 计划
     */
    plan?: any;
    /**
     * 已发布阶段需求工时
     */
    releasedstagestoryhours?: any;
    /**
     * 研发完毕阶段需求工时
     */
    developedstagestoryhours?: any;
    /**
     * 产品名称
     */
    name?: any;
    /**
     * 研发完毕阶段需求数量
     */
    developedstagestorycnt?: any;
    /**
     * 测试脚本
     */
    automation?: any;
    /**
     * 已计划阶段需求工时
     */
    planedstagestoryhours?: any;
    /**
     * 安装部署
     */
    install?: any;
    /**
     * 安全相关
     */
    security?: any;
    /**
     * 已发布阶段需求数量
     */
    releasedstagestorycnt?: any;
    /**
     * 测试完毕阶段需求数量
     */
    testedstagestorycnt?: any;
    /**
     * 标准规范
     */
    standard?: any;
    /**
     * 已关闭阶段需求数量
     */
    closedstagestorycnt?: any;
}
