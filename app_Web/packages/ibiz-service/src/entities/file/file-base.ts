import { EntityBase } from 'ibiz-core';
import { IFile } from '../interface';

/**
 * 附件基类
 *
 * @export
 * @abstract
 * @class FileBase
 * @extends {EntityBase}
 * @implements {IFile}
 */
export abstract class FileBase extends EntityBase implements IFile {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof FileBase
     */
    get srfdename(): string {
        return 'ZT_FILE';
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
     * 路径
     */
    pathname?: any;
    /**
     * 对象ID
     */
    objectid?: any;
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
     * 添加时间
     */
    addeddate?: any;
    /**
     * 下载次数
     */
    downloads?: any;
    /**
     * 大小
     */
    size?: any;
    /**
     * id
     */
    id?: any;
    /**
     * 备注
     */
    extra?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof FileBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.title = data.title || data.srfmajortext;
    }
}
