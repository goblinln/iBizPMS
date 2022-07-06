import { MobControlInterface } from 'ibiz-core';
/**
 * 实体部件基类接口
 *
 * @interface MobMainControlInterface
 */
export interface MobMainControlInterface extends MobControlInterface{

    /**
     * 编辑视图
     *
     * @type {*}
     * @memberof MobMainControlInterface
     */
    opendata?: any;

     /**
      * 新建视图
      *
      * @type {*}
      * @memberof MobMainControlInterface
      */
    newdata?: any;

    /**
     * 部件刷新数据
     *
     * @param {*} args 额外参数
     * @memberof  MobMainControlInterface
     */
    refresh(args?: any): void;

    /**
     * 开始加载
     * 
     * @memberof MobMainControlInterface
     */
    ctrlBeginLoading(): void;

    /**
     * 结束加载
     *
     * @memberof MobMainControlInterface
     */
     endLoading(): void;

    /**
     * 处理部件UI请求
     *
     * @param {string} action 行为名称
     * @param {*} context 上下文
     * @param {*} viewparam 视图参数
     * @memberof MobMainControlInterface
     */
    onControlRequset(action: string, context: any, viewparam: any): void;

    /**
     * 处理部件UI响应
     *
     * @param {string} action 行为
     * @param {*} response 响应对象
     * @memberof MobMainControlInterface
     */
    onControlResponse(action: string, response: any): void;

    /**
     * 转化数据
     *
     * @param {*} args 数据
     * @memberof  MobMainControlInterface
     */
    transformData(args: any): void;
}
