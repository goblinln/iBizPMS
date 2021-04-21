import { IEntityBase } from 'ibiz-core';

/**
 * 测试用例
 *
 * @export
 * @interface IIbzCase
 * @extends {IEntityBase}
 */
export interface IIbzCase extends IEntityBase {
    /**
     * 最后修改者
     */
    lasteditedby?: any;
    /**
     * path
     */
    path?: any;
    /**
     * 用例编号
     */
    id?: any;
    /**
     * scriptedBy
     */
    scriptedby?: any;
    /**
     * 用例类型
     *
     * @type {('feature' | 'performance' | 'config' | 'install' | 'security' | 'interface' | 'unit' | 'other')} feature: 功能测试, performance: 性能测试, config: 配置相关, install: 安装部署, security: 安全相关, interface: 接口测试, unit: 单元测试, other: 其他
     */
    type?: 'feature' | 'performance' | 'config' | 'install' | 'security' | 'interface' | 'unit' | 'other';
    /**
     * scriptStatus
     */
    scriptstatus?: any;
    /**
     * 适用阶段
     *
     * @type {('unittest' | 'feature' | 'intergrate' | 'system' | 'smoke' | 'bvt')} unittest: 单元测试阶段, feature: 功能测试阶段, intergrate: 集成测试阶段, system: 系统测试阶段, smoke: 冒烟测试阶段, bvt: 版本验证阶段
     */
    stage?: 'unittest' | 'feature' | 'intergrate' | 'system' | 'smoke' | 'bvt';
    /**
     * 创建日期
     */
    openeddate?: any;
    /**
     * 修改日期
     */
    lastediteddate?: any;
    /**
     * auto
     */
    auto?: any;
    /**
     * 用例标题
     */
    title?: any;
    /**
     * howRun
     */
    howrun?: any;
    /**
     * 优先级
     *
     * @type {(1 | 2 | 3 | 4)} 1: 1, 2: 2, 3: 3, 4: 4
     */
    pri?: 1 | 2 | 3 | 4;
    /**
     * 备注
     */
    comment?: any;
    /**
     * 关键词
     */
    keywords?: any;
    /**
     * scriptLocation
     */
    scriptlocation?: any;
    /**
     * 用例版本
     */
    version?: any;
    /**
     * 状态
     *
     * @type {('wait' | 'normal' | 'blocked' | 'investigate')} wait: 待评审, normal: 正常, blocked: 被阻塞, investigate: 研究中
     */
    status?: 'wait' | 'normal' | 'blocked' | 'investigate';
    /**
     * 前置条件
     */
    precondition?: any;
    /**
     * 已删除
     */
    deleted?: any;
    /**
     * 排序
     */
    order?: any;
    /**
     * 由谁创建
     */
    openedby?: any;
    /**
     * scriptedDate
     */
    scripteddate?: any;
    /**
     * 用例库
     */
    libname?: any;
    /**
     * 所属模块
     */
    modulename?: any;
    /**
     * id
     */
    module?: any;
    /**
     * 编号
     */
    lib?: any;
}
