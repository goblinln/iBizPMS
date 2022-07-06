
/**
 * 视图基类接口
 *
 * @interface ViewInterface
 */
export interface MobViewInterface {

    /**
     *  视图初始化
     *
     * @memberof ViewInterface
     */
    viewInit(): void;


    /**
     * 视图挂载
     *
     * @memberof ViewInterface
     */
    viewMounted(): void;


    /**
     * 视图销毁
     *
     * @memberof ViewInterface
     */
    viewDestroyed(): void;

    /**
     * 计数器刷新
     *
     * @memberof ViewInterface
     */
    counterRefresh(): void;

    /**
     * 视图刷新
     *
     * @param {*} [args]
     * @memberof ViewInterface
     */
    refresh(args?: any): void;

    /**
     * 关闭视图
     *
     * @param {any[]} args 关闭
     * @memberof ViewInterface
     */
    closeView(args: any[]): void;


    /**
     * 部件事件
     * 
     * @param controlname 部件名称
     * @param action  行为
     * @param data 数据
     * @memberof ViewInterface
     */
    onCtrlEvent(controlname: string, action: string, data: any): void;


    /**
     * 初始化模型服务
     *
     * @memberof ViewInterface
     */
    initModelService(): void;


    /**
     * 初始化沙箱实例 TODO
     * 
     * @param {*} args
     * @memberof ViewInterface
     */
    initSandBoxInst(args: any): void;


    /**
     * 初始化应用界面服务
     *
     * @memberof ViewInterface
     */
    initAppUIService(): void;


    /**
     * 初始化计数器服务
     *
     * @param {*} param 视图实例
     * @memberof ViewInterface
     */
    initCounterService(param: any): void;



    /**
     * 解析视图参数
     *
     * @param {*} inputvalue 额外参数
     * @memberof ViewInterface
     */
    parseViewParam(inputvalue: any): void;

}
