export interface IContext {
    /**
     * 当前上下文唯一主数据标识
     *
     * @type {string}
     * @memberof IContext
     */
    srfsessionkey?: string;
    [key: string]: any;
}
