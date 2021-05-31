import { IEntityBase } from 'ibiz-core';

/**
 * Bug统计
 *
 * @export
 * @interface IBugStats
 * @extends {IEntityBase}
 */
export interface IBugStats extends IEntityBase {
    /**
     * 激活Bug
     */
    bugactive?: any;
    /**
     * 不予解决
     */
    bugwillnotfix?: any;
    /**
     * 由谁解决
     */
    resolvedby?: any;
    /**
     * Bug解决方案
     *
     * @type {('bydesign' | 'duplicate' | 'external' | 'fixed' | 'notrepro' | 'postponed' | 'willnotfix' | 'tostory')} bydesign: 设计如此, duplicate: 重复Bug, external: 外部原因, fixed: 已解决, notrepro: 无法重现, postponed: 延期处理, willnotfix: 不予解决, tostory: 转为需求
     */
    bugresolution?: 'bydesign' | 'duplicate' | 'external' | 'fixed' | 'notrepro' | 'postponed' | 'willnotfix' | 'tostory';
    /**
     * 部门
     */
    dept?: any;
    /**
     * 设计如此
     */
    bugbydesign?: any;
    /**
     * bug创建人
     */
    bugopenedby?: any;
    /**
     * Bug状态
     *
     * @type {('active' | 'resolved' | 'closed')} active: 激活, resolved: 已解决, closed: 已关闭
     */
    bugstatus?: 'active' | 'resolved' | 'closed';
    /**
     * 已关闭Bug
     */
    bugclosed?: any;
    /**
     * 指派给
     */
    assignedto?: any;
    /**
     * 外部原因
     */
    bugexternal?: any;
    /**
     * 开始
     */
    begin?: any;
    /**
     * bug解决日期
     */
    bugresolveddate?: any;
    /**
     * 结束
     */
    end?: any;
    /**
     * Bug编号
     */
    bugid?: any;
    /**
     * 项目名称
     */
    projectname?: any;
    /**
     * 已解决
     */
    bugfixed?: any;
    /**
     * 标识
     */
    id?: any;
    /**
     * 延期处理
     */
    bugpostponed?: any;
    /**
     * Bug标题
     */
    bugtitle?: any;
    /**
     * Bug
     */
    bugcnt?: any;
    /**
     * Bug创建日期
     */
    bugopeneddate?: any;
    /**
     * 未解决
     */
    bugwjj?: any;
    /**
     * 名称
     */
    title?: any;
    /**
     * Bug优先级
     *
     * @type {(1 | 2 | 3 | 4)} 1: 严重, 2: 主要, 3: 次要, 4: 不重要
     */
    bugpri?: 1 | 2 | 3 | 4;
    /**
     * 无法重现
     */
    bugnotrepro?: any;
    /**
     * 项目名称
     */
    projectname1?: any;
    /**
     * 已解决Bug
     */
    bugresolved?: any;
    /**
     * 重复Bug
     */
    bugduplicate?: any;
    /**
     * 由谁创建
     */
    openedby?: any;
    /**
     * 有效率
     */
    bugefficient?: any;
    /**
     * 转为需求
     */
    bugtostory?: any;
    /**
     * Bug严重程度
     *
     * @type {(1 | 2 | 3 | 4)} 1: 致命, 2: 严重, 3: 一般, 4: 轻微
     */
    bugseverity?: 1 | 2 | 3 | 4;
    /**
     * 总计
     */
    bugtotal?: any;
    /**
     * 产品名称
     */
    productname?: any;
    /**
     * 编号
     */
    product?: any;
    /**
     * 项目编号
     */
    project?: any;
}
