import { IEntityBase } from '../interface';

/**
 * 实体基类
 *
 * @export
 * @abstract
 * @class EntityBase
 * @implements {IEntityBase}
 */
export abstract class EntityBase implements IEntityBase {
    /**
     * 当前实体所有属性
     *
     * @protected
     * @type {string[]}
     * @memberof EntityBase
     */
    protected get keys(): string[] {
        return [];
    }
    [key: string]: any;
    get srfdename(): string {
        return '';
    }
    get srfkey() {
        return null;
    }
    set srfkey(_val: any) {}
    get srfmajortext() {
        return null;
    }
    set srfmajortext(_val: any) {}
    srfordervalue?: number;
    srftempdate?: number;
    srfuf?: 0 | 1;
    /**
     * Creates an instance of EntityBase.
     * @param {*} [data]
     * @memberof EntityBase
     */
    constructor(data?: any) {
        this.setData(data);
    }

    /**
     * 设置数据
     *
     * @author chitanda
     * @date 2021-03-03 16:03:09
     * @protected
     * @param {*} [data={}]
     * @param {boolean} [reset=false]
     */
    protected setData(data: any = {}, reset = false): void {
        const keyMap: Map<string, null> = new Map();
        for (const key in data) {
            if (data[key] !== void 0 && typeof data[key] !== 'function') {
                this[key] = data[key];
                keyMap.set(key, null);
            }
        }
        this.keys.forEach(key => {
            if (keyMap.has(key)) {
                return;
            }
            if (data[key] !== void 0) {
                this[key] = data[key];
            } else if (reset && this[key] !== void 0) {
                delete this[key];
            }
        });
    }

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof EntityBase
     */
    reset(data: any = {}): void {
        this.setData(data, true);
    }

    /**
     * 合并数据
     *
     * @param {*} [data={}]
     * @memberof EntityBase
     */
    assign(data: any = {}): void {
        this.setData(data);
    }

    /**
     * 克隆当前实体
     *
     * @abstract
     * @return {*}  {EntityBase}
     * @memberof EntityBase
     */
    abstract clone(): EntityBase;
}
