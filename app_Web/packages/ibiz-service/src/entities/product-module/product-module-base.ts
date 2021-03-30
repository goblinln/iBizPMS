import { EntityBase } from 'ibiz-core';
import { IProductModule } from '../interface';

/**
 * 需求模块基类
 *
 * @export
 * @abstract
 * @class ProductModuleBase
 * @extends {EntityBase}
 * @implements {IProductModule}
 */
export abstract class ProductModuleBase extends EntityBase implements IProductModule {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof ProductModuleBase
     */
    get srfdename(): string {
        return 'IBZ_PRODUCTMODULE';
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
     * path
     */
    path?: any;
    /**
     * 数据选择排序
     */
    orderpk?: any;
    /**
     * 逻辑删除标志
     */
    deleted?: any;
    /**
     * 名称
     */
    name?: any;
    /**
     * branch
     */
    branch?: any;
    /**
     * 简称
     */
    ibizshort?: any;
    /**
     * 排序值
     */
    order?: any;
    /**
     * grade
     */
    grade?: any;
    /**
     * 类型（story）
     */
    type?: any;
    /**
     * owner
     */
    owner?: any;
    /**
     * 叶子模块
     */
    isleaf?: any;
    /**
     * id
     */
    id?: any;
    /**
     * collector
     */
    collector?: any;
    /**
     * 所属产品
     */
    rootname?: any;
    /**
     * 上级模块
     */
    parentname?: any;
    /**
     * 产品
     */
    root?: any;
    /**
     * id
     */
    parent?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof ProductModuleBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.name = data.name || data.srfmajortext;
    }
}
