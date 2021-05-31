import { IEntityBase } from 'ibiz-core';

/**
 * 附件
 *
 * @export
 * @interface IFile
 * @extends {IEntityBase}
 */
export interface IFile extends IEntityBase {
    /**
     * 路径
     */
    pathname?: any;
    /**
     * 对象ID
     */
    objectid?: any;
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 显示大小
     */
    strsize?: any;
    /**
     * 文档类型
     */
    doclibtype?: any;
    /**
     * 已删除
     */
    deleted?: any;
    /**
     * 文件类型
     */
    extension?: any;
    /**
     * 对象类型
     *
     * @type {('product' | 'story' | 'productplan' | 'release' | 'project' | 'task' | 'build' | 'bug' | 'case' | 'caseresult' | 'stepresult' | 'testtask' | 'user' | 'doc' | 'doclib' | 'todo' | 'branch' | 'module' | 'testsuite' | 'caselib' | 'testreport' | 'entry' | 'webhook' | 'daily' | 'weekly' | 'monthly' | 'reportly')} product: 产品, story: 需求, productplan: 计划, release: 发布, project: 项目, task: 任务, build: 版本, bug: Bug, case: 用例, caseresult: 用例结果, stepresult: 用例步骤, testtask: 测试单, user: 用户, doc: 文档, doclib: 文档库, todo: 待办, branch: 分支, module: 模块, testsuite: 套件, caselib: 用例库, testreport: 报告, entry: 应用, webhook: Webhook, daily: 日报, weekly: 周报, monthly: 月报, reportly: 汇报
     */
    objecttype?: 'product' | 'story' | 'productplan' | 'release' | 'project' | 'task' | 'build' | 'bug' | 'case' | 'caseresult' | 'stepresult' | 'testtask' | 'user' | 'doc' | 'doclib' | 'todo' | 'branch' | 'module' | 'testsuite' | 'caselib' | 'testreport' | 'entry' | 'webhook' | 'daily' | 'weekly' | 'monthly' | 'reportly';
    /**
     * 由谁添加
     */
    addedby?: any;
    /**
     * 标题
     */
    title?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 添加时间
     */
    addeddate?: any;
    /**
     * 下载次数
     */
    downloads?: any;
    /**
     * 归属部门
     */
    dept?: any;
    /**
     * 大小
     */
    size?: any;
    /**
     * 归属部门名
     */
    deptname?: any;
    /**
     * id
     */
    id?: any;
    /**
     * 备注
     */
    extra?: any;
    /**
     * 附件编号
     */
    filesn?: any;
}
