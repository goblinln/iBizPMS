import { EntityBase } from 'ibiz-core';
import { IModule } from '../interface';

/**
 * 模块基类
 *
 * @export
 * @abstract
 * @class ModuleBase
 * @extends {EntityBase}
 * @implements {IModule}
 */
export abstract class ModuleBase extends EntityBase implements IModule {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof ModuleBase
     */
    get srfdename(): string {
        return 'ZT_MODULE';
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
     * 所属根
     */
    root?: any;
    /**
     * 级别
     */
    grade?: any;
    /**
     * 类型
     *
     * @type {('line' | 'story' | 'task' | 'doc' | 'case' | 'bug')} line: 产品线, story: 需求, task: 任务, doc: 文档目录, case: 测试用例, bug: Bug
     */
    type?: 'line' | 'story' | 'task' | 'doc' | 'case' | 'bug';
    /**
     * 模块名称
     */
    name?: any;
    /**
     * 排序
     */
    order?: any;
    /**
     * 负责人
     */
    owner?: any;
    /**
     * id
     */
    id?: any;
    /**
     * 数据选择排序
     */
    orderpk?: any;
    /**
     * 收藏者
     */
    collector?: any;
    /**
     * 简称
     */
    ibizshort?: any;
    /**
     * 路径
     */
    path?: any;
    /**
     * 部门标识
     */
    mdeptid?: any;
    /**
     * 组织标识
     */
    orgid?: any;
    /**
     * 已删除
     */
    deleted?: any;
    /**
     * 上级模块
     */
    parentname?: any;
    /**
     * 平台/分支
     */
    branch?: any;
    /**
     * 上级模块
     */
    parent?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 由谁更新
     */
    updateby?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof ModuleBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.name = data.name || data.srfmajortext;
    }
}
