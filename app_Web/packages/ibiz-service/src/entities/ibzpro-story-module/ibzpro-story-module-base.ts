import { EntityBase } from 'ibiz-core';
import { IIBZProStoryModule } from '../interface';

/**
 * 需求模块（iBizSys）基类
 *
 * @export
 * @abstract
 * @class IBZProStoryModuleBase
 * @extends {EntityBase}
 * @implements {IIBZProStoryModule}
 */
export abstract class IBZProStoryModuleBase extends EntityBase implements IIBZProStoryModule {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof IBZProStoryModuleBase
     */
    get srfdename(): string {
        return 'IBZPRO_STORYMODULE';
    }
    get srfkey() {
        return this.id;
    }
    set srfkey(val: any) {
        this.id = val;
    }
    get srfmajortext() {
        return this.name;
    }
    set srfmajortext(val: any) {
        this.name = val;
    }
    /**
     * 级别
     */
    grade?: any;
    /**
     * collector
     */
    collector?: any;
    /**
     * owner
     */
    owner?: any;
    /**
     * 需求模块类型
     *
     * @type {('pmsStoryModule' | 'iBizSysModule' | 'iBizReqModule')} pmsStoryModule: PMS需求模块, iBizSysModule: iBiz系统模块, iBizReqModule: iBiz需求模块
     */
    ibiz_storytype?: 'pmsStoryModule' | 'iBizSysModule' | 'iBizReqModule';
    /**
     * id
     */
    id?: any;
    /**
     * 类型
     *
     * @type {('line' | 'story' | 'task' | 'doc' | 'case' | 'bug')} line: 产品线, story: 需求, task: 任务, doc: 文档目录, case: 测试用例, bug: Bug
     */
    type?: 'line' | 'story' | 'task' | 'doc' | 'case' | 'bug';
    /**
     * 简称
     */
    ibizshort?: any;
    /**
     * 名称
     */
    name?: any;
    /**
     * 已删除
     */
    deleted?: any;
    /**
     * 路径
     */
    path?: any;
    /**
     * IBIZ标识
     */
    ibizid?: any;
    /**
     * 产品
     */
    productname?: any;
    /**
     * 编号
     */
    root?: any;
    /**
     * id
     */
    parent?: any;
    /**
     * 部门标识
     */
    mdeptid?: any;
    /**
     * 归属部门名
     */
    mdeptname?: any;
    /**
     * 组织机构标识
     */
    orgid?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * 由谁更新
     */
    updateby?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof IBZProStoryModuleBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.name = data.name || data.srfmajortext;
    }
}
