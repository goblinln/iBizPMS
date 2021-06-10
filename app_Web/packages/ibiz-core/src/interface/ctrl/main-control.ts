import { ControlInterface } from 'ibiz-core';
/**
 * 实体部件基类接口
 *
 * @interface MainControlInterface
 */
export interface MainControlInterface extends ControlInterface{

    /**
     * 编辑视图
     *
     * @type {*}
     * @memberof MainControlInterface
     */
    opendata?: any;

     /**
      * 新建视图
      *
      * @type {*}
      * @memberof MainControlInterface
      */
    newdata?: any;

    /**
     * 部件刷新数据
     *
     * @param {*} args 额外参数
     * @memberof  MainControlInterface
     */
    refresh(args?: any): void;

    /**
     * 开始加载
     * 
     * @memberof MainControlInterface
     */
    ctrlBeginLoading(): void;

    /**
     * 结束加载
     *
     * @memberof MainControlInterface
     */
    ctrlEndLoading(): void;

    /**
     * 处理部件UI请求
     *
     * @param {string} action 行为名称
     * @param {*} context 上下文
     * @param {*} viewparam 视图参数
     * @memberof MainControlInterface
     */
    onControlRequset(action: string, context: any, viewparam: any): void;

    /**
     * 处理部件UI响应
     *
     * @param {string} action 行为
     * @param {*} response 响应对象
     * @memberof MainControlInterface
     */
    onControlResponse(action: string, response: any): void;

    /**
     * 转化数据
     *
     * @param {*} args 数据
     * @memberof  MainControlInterface
     */
    transformData(args: any): void;
}
