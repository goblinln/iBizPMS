import { EntityBase } from 'ibiz-core';
import { IBug } from '../interface';

/**
 * Bug基类
 *
 * @export
 * @abstract
 * @class BugBase
 * @extends {EntityBase}
 * @implements {IBug}
 */
export abstract class BugBase extends EntityBase implements IBug {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof BugBase
     */
    get srfdename(): string {
        return 'ZT_BUG';
    }
    get srfkey() {
        return this.id;
    }
    set srfkey(val: any) {
        this.id = val;
    }
    get srfmajortext() {
        return this.title;
    }
    set srfmajortext(val: any) {
        this.title = val;
    }
    /**
     * 严重程度
     *
     * @type {(1 | 2 | 3 | 4)} 1: 致命, 2: 严重, 3: 一般, 4: 轻微
     */
    severity?: 1 | 2 | 3 | 4;
    /**
     * 归属部门
     */
    dept?: any;
    /**
     * 需求版本
     */
    storyversion?: any;
    /**
     * 版本名称
     */
    buildname?: any;
    /**
     * 相关Bug
     */
    linkbug?: any;
    /**
     * 激活日期
     */
    activateddate?: any;
    /**
     * 过期天数
     */
    overduebugs?: any;
    /**
     * 创建版本
     *
     * @type {('1')} 1: 创建
     */
    createbuild?: '1';
    /**
     * 指派给
     */
    assignedto?: any;
    /**
     * 解决方案
     *
     * @type {('bydesign' | 'duplicate' | 'external' | 'fixed' | 'notrepro' | 'postponed' | 'willnotfix' | 'tostory')} bydesign: 设计如此, duplicate: 重复Bug, external: 外部原因, fixed: 已解决, notrepro: 无法重现, postponed: 延期处理, willnotfix: 不予解决, tostory: 转为需求
     */
    resolution?: 'bydesign' | 'duplicate' | 'external' | 'fixed' | 'notrepro' | 'postponed' | 'willnotfix' | 'tostory';
    /**
     * 修改日期
     */
    lastediteddate?: any;
    /**
     * 移动端图片
     */
    mobimage?: any;
    /**
     * result
     */
    result?: any;
    /**
     * 关键词
     */
    keywords?: any;
    /**
     * 是否收藏
     */
    isfavorites?: any;
    /**
     * 模块名称
     */
    modulename1?: any;
    /**
     * 由谁关闭
     */
    closedby?: any;
    /**
     * 浏览器
     *
     * @type {('all' | 'ie' | 'ie11' | 'ie10' | 'ie9' | 'ie8' | 'ie7' | 'ie6' | 'chrome' | 'firefox' | 'firefox4' | 'firefox3' | 'firefox2' | 'opera' | 'opera11' | 'oprea10' | 'opera9' | 'safari' | 'maxthon' | 'uc' | 'others')} all: 全部, ie: IE系列, ie11: IE11, ie10: IE10, ie9: IE9, ie8: IE8, ie7: IE7, ie6: IE6, chrome: chrome, firefox: firefox系列, firefox4: firefox4, firefox3: firefox3, firefox2: firefox2, opera: opera系列, opera11: opera11, oprea10: oprea10, opera9: opera9, safari: safari, maxthon: 傲游, uc: UC, others: 其他
     */
    browser?: 'all' | 'ie' | 'ie11' | 'ie10' | 'ie9' | 'ie8' | 'ie7' | 'ie6' | 'chrome' | 'firefox' | 'firefox4' | 'firefox3' | 'firefox2' | 'opera' | 'opera11' | 'oprea10' | 'opera9' | 'safari' | 'maxthon' | 'uc' | 'others';
    /**
     * 归属部门名
     */
    deptname?: any;
    /**
     * 消息通知用户
     */
    noticeusers?: any;
    /**
     * 重现步骤
     */
    steps?: any;
    /**
     * v2
     */
    v2?: any;
    /**
     * 是否确认
     *
     * @type {(1 | 0)} 1: 是, 0: 否
     */
    confirmed?: 1 | 0;
    /**
     * 联系人
     */
    mailtoconact?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 由谁创建
     */
    openedby?: any;
    /**
     * 激活次数
     */
    activatedcount?: any;
    /**
     * 创建日期
     */
    openeddate?: any;
    /**
     * 关闭日期
     */
    closeddate?: any;
    /**
     * 抄送给
     */
    mailto?: any;
    /**
     * 指派日期
     */
    assigneddate?: any;
    /**
     * 截止日期
     */
    deadline?: any;
    /**
     * 标题颜色
     *
     * @type {('#3da7f5' | '#75c941' | '#2dbdb2' | '#797ec9' | '#ffaf38' | '#ff4e3e')} #3da7f5: #3da7f5, #75c941: #75c941, #2dbdb2: #2dbdb2, #797ec9: #797ec9, #ffaf38: #ffaf38, #ff4e3e: #ff4e3e
     */
    color?: '#3da7f5' | '#75c941' | '#2dbdb2' | '#797ec9' | '#ffaf38' | '#ff4e3e';
    /**
     * 备注
     */
    comment?: any;
    /**
     * 解决日期
     */
    resolveddate?: any;
    /**
     * Bug类型
     *
     * @type {('codeerror' | 'config' | 'install' | 'security' | 'performance' | 'standard' | 'automation' | 'designdefect' | 'others')} codeerror: 代码错误, config: 配置相关, install: 安装部署, security: 安全相关, performance: 性能问题, standard: 标准规范, automation: 测试脚本, designdefect: 设计缺陷, others: 其他
     */
    type?: 'codeerror' | 'config' | 'install' | 'security' | 'performance' | 'standard' | 'automation' | 'designdefect' | 'others';
    /**
     * Bug状态
     *
     * @type {('active' | 'resolved' | 'closed')} active: 激活, resolved: 已解决, closed: 已关闭
     */
    status?: 'active' | 'resolved' | 'closed';
    /**
     * 影响版本
     */
    openedbuild?: any;
    /**
     * 延期解决
     */
    delayresolve?: any;
    /**
     * 附件
     */
    files?: any;
    /**
     * 抄送给
     */
    mailtopk?: any;
    /**
     * v1
     */
    v1?: any;
    /**
     * 已删除
     */
    deleted?: any;
    /**
     * lines
     */
    lines?: any;
    /**
     * 子状态
     */
    substatus?: any;
    /**
     * BUG编号
     */
    bugsn?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 版本项目
     */
    buildproject?: any;
    /**
     * Bug编号
     */
    id?: any;
    /**
     * 延期
     */
    delay?: any;
    /**
     * found
     */
    found?: any;
    /**
     * 解决者
     */
    resolvedby?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * 解决版本
     */
    resolvedbuild?: any;
    /**
     * 用例版本
     */
    caseversion?: any;
    /**
     * 优先级
     *
     * @type {(1 | 2 | 3 | 4)} 1: 严重, 2: 主要, 3: 次要, 4: 不重要
     */
    pri?: 1 | 2 | 3 | 4;
    /**
     * 操作系统
     *
     * @type {('all' | 'windows' | 'win10' | 'win8' | 'win7' | 'vista' | 'winxp' | 'win2012' | 'win2008' | 'win2003' | 'win2000' | 'android' | 'ios' | 'wp8' | 'wp7' | 'symbian' | 'linux' | 'freebsd' | 'osx' | 'unix' | 'others')} all: 全部, windows: Windows, win10: Windows 10, win8: Windows 8, win7: Windows 7, vista: Windows Vista, winxp: Windows XP, win2012: Windows 2012, win2008: Windows 2008, win2003: Windows 2003, win2000: Windows 2000, android: Android, ios: IOS, wp8: WP8, wp7: WP7, symbian: Symbian, linux: Linux, freebsd: FreeBSD, osx: OS X, unix: Unix, others: 其他
     */
    os?: 'all' | 'windows' | 'win10' | 'win8' | 'win7' | 'vista' | 'winxp' | 'win2012' | 'win2008' | 'win2003' | 'win2000' | 'android' | 'ios' | 'wp8' | 'wp7' | 'symbian' | 'linux' | 'freebsd' | 'osx' | 'unix' | 'others';
    /**
     * 代码类型
     */
    repotype?: any;
    /**
     * hardware
     */
    hardware?: any;
    /**
     * 最后修改者
     */
    lasteditedby?: any;
    /**
     * Bug标题
     */
    title?: any;
    /**
     * 产品
     */
    productname?: any;
    /**
     * 平台/分支
     */
    branchname?: any;
    /**
     * 相关任务
     */
    taskname?: any;
    /**
     * 相关用例
     */
    casename?: any;
    /**
     * 项目
     */
    projectname?: any;
    /**
     * 相关需求
     */
    storyname?: any;
    /**
     * 模块名称
     */
    modulename?: any;
    /**
     * 转需求
     */
    tostory?: any;
    /**
     * 应用
     */
    entry?: any;
    /**
     * 所属产品
     */
    product?: any;
    /**
     * 转任务
     */
    totask?: any;
    /**
     * 所属计划
     */
    plan?: any;
    /**
     * 所属模块
     */
    module?: any;
    /**
     * 平台/分支
     */
    branch?: any;
    /**
     * 重复ID
     */
    duplicatebug?: any;
    /**
     * 代码
     */
    repo?: any;
    /**
     * 相关需求
     */
    story?: any;
    /**
     * 相关用例
     */
    ibizcase?: any;
    /**
     * 所属项目
     */
    project?: any;
    /**
     * 相关任务
     */
    task?: any;
    /**
     * 测试单
     */
    testtask?: any;
    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof BugBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.title = data.title || data.srfmajortext;
    }
}
