/**
 * 配置参数
 *
 * @export
 * @interface IBzDynamicConfig
 */
export declare interface IBzDynamicConfig {
    /**
     * 前端数据库名称
     *
     * @author chitanda
     * @date 2021-07-22 16:07:43
     * @type {string}
     */
    dbName?: string;
    /**
     * 前端数据版本
     *
     * @author chitanda
     * @date 2021-07-22 16:07:47
     * @type {number}
     */
    dbVersion?: number;
    /**
     * 指定请求根路径
     *
     * @default ''
     * @type {string}
     * @memberof Config
     */
    baseUrl?: string;
}
