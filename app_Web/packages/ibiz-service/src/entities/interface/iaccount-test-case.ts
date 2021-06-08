import { IEntityBase } from 'ibiz-core';

/**
 * 测试用例
 *
 * @export
 * @interface IAccountTestCase
 * @extends {IEntityBase}
 */
export interface IAccountTestCase extends IEntityBase {
    /**
     * 修改日期
     */
    lastediteddate?: any;
    /**
     * scriptedDate
     */
    scripteddate?: any;
    /**
     * 标题颜色
     *
     * @type {('#3da7f5' | '#75c941' | '#2dbdb2' | '#797ec9' | '#ffaf38' | '#ff4e3e')} #3da7f5: #3da7f5, #75c941: #75c941, #2dbdb2: #2dbdb2, #797ec9: #797ec9, #ffaf38: #ffaf38, #ff4e3e: #ff4e3e
     */
    color?: '#3da7f5' | '#75c941' | '#2dbdb2' | '#797ec9' | '#ffaf38' | '#ff4e3e';
    /**
     * path
     */
    path?: any;
    /**
     * 创建日期
     */
    openeddate?: any;
    /**
     * 测试用例编号
     */
    casesn?: any;
    /**
     * 结果
     *
     * @type {('n/a' | 'pass' | 'fail' | 'blocked')} n/a: 忽略, pass: 通过, fail: 失败, blocked: 阻塞
     */
    lastrunresult?: 'n/a' | 'pass' | 'fail' | 'blocked';
    /**
     * 模块名称
     */
    modulename1?: any;
    /**
     * 相关用例
     */
    linkcase?: any;
    /**
     * 属性
     */
    task?: any;
    /**
     * 排序
     */
    order?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * howRun
     */
    howrun?: any;
    /**
     * 测试结果数
     */
    resultcnt?: any;
    /**
     * 消息通知用户
     */
    noticeusers?: any;
    /**
     * 来源用例版本
     */
    fromcaseversion?: any;
    /**
     * 用例版本
     */
    version?: any;
    /**
     * scriptedBy
     */
    scriptedby?: any;
    /**
     * 由谁创建
     */
    openedby?: any;
    /**
     * 用例类型
     *
     * @type {('feature' | 'performance' | 'config' | 'install' | 'security' | 'interface' | 'unit' | 'other')} feature: 功能测试, performance: 性能测试, config: 配置相关, install: 安装部署, security: 安全相关, interface: 接口测试, unit: 单元测试, other: 其他
     */
    type?: 'feature' | 'performance' | 'config' | 'install' | 'security' | 'interface' | 'unit' | 'other';
    /**
     * 测试失败数
     */
    resultfalicnt?: any;
    /**
     * 用例状态
     *
     * @type {('wait' | 'normal' | 'blocked' | 'investigate')} wait: 待评审, normal: 正常, blocked: 被阻塞, investigate: 研究中
     */
    status?: 'wait' | 'normal' | 'blocked' | 'investigate';
    /**
     * 备注
     */
    comment?: any;
    /**
     * auto
     */
    auto?: any;
    /**
     * 是否收藏
     */
    isfavorites?: any;
    /**
     * 归属部门名
     */
    deptname?: any;
    /**
     * 归属部门
     */
    dept?: any;
    /**
     * frequency
     *
     * @type {('1' | '2' | '3')} 1: 1, 2: 2, 3: 3
     */
    frequency?: '1' | '2' | '3';
    /**
     * 用例标题
     */
    title?: any;
    /**
     * 最后修改者
     */
    lasteditedby?: any;
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 由谁评审
     */
    reviewedby?: any;
    /**
     * 附件
     */
    files?: any;
    /**
     * 转bug数
     */
    tobugcnt?: any;
    /**
     * 指派给
     */
    assignedto?: any;
    /**
     * 已删除
     */
    deleted?: any;
    /**
     * 需求版本
     */
    storyversion?: any;
    /**
     * 评审时间
     */
    revieweddate?: any;
    /**
     * 优先级
     *
     * @type {(1 | 2 | 3 | 4)} 1: 1, 2: 2, 3: 3, 4: 4
     */
    pri?: 1 | 2 | 3 | 4;
    /**
     * 适用阶段
     *
     * @type {('unittest' | 'feature' | 'intergrate' | 'system' | 'smoke' | 'bvt')} unittest: 单元测试阶段, feature: 功能测试阶段, intergrate: 集成测试阶段, system: 系统测试阶段, smoke: 冒烟测试阶段, bvt: 版本验证阶段
     */
    stage?: 'unittest' | 'feature' | 'intergrate' | 'system' | 'smoke' | 'bvt';
    /**
     * scriptLocation
     */
    scriptlocation?: any;
    /**
     * 用例状态
     *
     * @type {('wait' | 'normal' | 'blocked' | 'investigate' | 'done' | 'storychange' | 'casechange')} wait: 未开始, normal: 正常, blocked: 被阻塞, investigate: 研究中, done: 已完成, storychange: 需求变更, casechange: 原用例更新
     */
    status1?: 'wait' | 'normal' | 'blocked' | 'investigate' | 'done' | 'storychange' | 'casechange';
    /**
     * 执行时间
     */
    lastrundate?: any;
    /**
     * 关键词
     */
    keywords?: any;
    /**
     * scriptStatus
     */
    scriptstatus?: any;
    /**
     * 工具/框架
     *
     * @type {('junit' | 'testng' | 'phpunit' | 'pytest' | 'jtest' | 'cppunit' | 'gtest' | 'qtest')} junit: JUnit, testng: TestNG, phpunit: PHPUnit, pytest: Pytest, jtest: JTest, cppunit: CppUnit, gtest: GTest, qtest: QTest
     */
    frame?: 'junit' | 'testng' | 'phpunit' | 'pytest' | 'jtest' | 'cppunit' | 'gtest' | 'qtest';
    /**
     * 测试用例结果
     *
     * @type {('n/a' | 'pass' | 'fail' | 'blocked')} n/a: 忽略, pass: 通过, fail: 失败, blocked: 阻塞
     */
    lastrunresult1?: 'n/a' | 'pass' | 'fail' | 'blocked';
    /**
     * 用例步骤数
     */
    stepcnt?: any;
    /**
     * 子状态
     */
    substatus?: any;
    /**
     * 用例编号
     */
    id?: any;
    /**
     * 前置条件
     */
    precondition?: any;
    /**
     * 执行人
     */
    lastrunner?: any;
    /**
     * 用例库
     */
    libname?: any;
    /**
     * 需求名称
     */
    storyname?: any;
    /**
     * 模块名称
     */
    modulename?: any;
    /**
     * 产品名称
     */
    productname?: any;
    /**
     * 来源用例
     */
    fromcaseid?: any;
    /**
     * 平台/分支
     */
    branch?: any;
    /**
     * 来源Bug
     */
    frombug?: any;
    /**
     * 相关需求
     */
    story?: any;
    /**
     * 所属产品
     */
    product?: any;
    /**
     * 所属库
     */
    lib?: any;
    /**
     * 所属模块
     */
    module?: any;
}
