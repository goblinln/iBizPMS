/**
 * 实体标准接口
 *
 * @export
 * @interface IEntityBase
 */
export interface IEntityBase {
    [key: string]: any;
    /**
     * 当前数据所属实体
     *
     * @type {string}
     * @memberof IEntityBase
     */
    readonly srfdename?: string;
    /**
     * 主键
     *
     * @type {string}
     * @memberof IEntityBase
     */
    srfkey?: string;
    /**
     * 主信息
     *
     * @type {string}
     * @memberof IEntityBase
     */
    srfmajortext?: string;
    /**
     * 自关系父键
     *
     * @type {string}
     * @memberof IEntityBase
     */
    srfpkey?: string;
    /**
     * 自关系父键文本
     *
     * @type {string}
     * @memberof IEntityBase
     */
    srfpmajortext?: string;
    /**
     * 排序值
     *
     * @type {number}
     * @memberof IEntityBase
     */
    srfordervalue?: number;
    /**
     * 前端临时数据最后更新时间时间戳
     *
     * @type {number}
     * @memberof IEntityBase
     */
    srftempdate?: number;
    /**
     * 当前数据所属状态
     *
     * @default 1 默认为非新建状态
     * @author chitanda
     * @date 2021-03-03 14:03:45
     * @type {(0 | 1)} 0: 新建，1: 非新建
     */
    srfuf?: 0 | 1;
    /**
     * 克隆自身
     *
     * @memberof IEntityBase
     */
    clone?(): IEntityBase;
    /**
     * 重置实体数据
     *
     * @param {*} [data={}]
     * @memberof IEntityBase
     */
    reset?(data?: any): void;
    /**
     * 合并数据
     *
     * @param {*} [data={}]
     * @memberof IEntityBase
     */
    assign?(data?: any): void;
}
// eslint-disable-next-line @typescript-eslint/no-empty-interface
export interface IEntity extends IEntityBase {}
