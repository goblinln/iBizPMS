import { EntityBase } from 'ibiz-core';
import { IDocLibModule } from '../interface';

/**
 * 文档库分类基类
 *
 * @export
 * @abstract
 * @class DocLibModuleBase
 * @extends {EntityBase}
 * @implements {IDocLibModule}
 */
export abstract class DocLibModuleBase extends EntityBase implements IDocLibModule {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof DocLibModuleBase
     */
    get srfdename(): string {
        return 'IBZ_DOCLIBMODULE';
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
     * 是否已收藏
     */
    isfavourites?: any;
    /**
     * grade
     */
    grade?: any;
    /**
     * path
     */
    path?: any;
    /**
     * 排序值
     */
    order?: any;
    /**
     * 名称
     */
    name?: any;
    /**
     * 查询类型
     */
    docqtype?: any;
    /**
     * owner
     */
    owner?: any;
    /**
     * branch
     */
    branch?: any;
    /**
     * 叶子模块
     */
    isleaf?: any;
    /**
     * 类型
     */
    type?: any;
    /**
     * 简称
     */
    ibizshort?: any;
    /**
     * 文档数
     */
    doccnt?: any;
    /**
     * collector
     */
    collector?: any;
    /**
     * id
     */
    id?: any;
    /**
     * 逻辑删除标志
     */
    deleted?: any;
    /**
     * 上级模块
     */
    modulename?: any;
    /**
     * 所属文档库
     */
    doclibname?: any;
    /**
     * id
     */
    parent?: any;
    /**
     * 编号
     */
    root?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof DocLibModuleBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.name = data.name || data.srfmajortext;
    }
}
