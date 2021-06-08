import { Subject, Subscription } from 'rxjs';

/**
 * 部件基类接口
 *
 * @interface ControlInterface
 */
export interface ControlInterface {

    /**
     * 部件服务对象
     *
     * @type {*}
     * @memberof ControlInterface
     */
    service: any;

    /**
     * 实体服务对象
     *
     * @type {*}
     * @memberof ControlInterface
     */
    appEntityService: any;

    /**
     * 订阅视图状态事件
     *
     * @public
     * @type {(Subscription | undefined)}
     * @memberof ControlInterface
     */
    viewStateEvent: Subscription | undefined;

    /**
     * 计数器服务对象集合
     *
     * @type {Array<*>}
     * @memberof ControlInterface
     */
    counterServiceArray: Array<any>;

    /**
     * 获取部件类型
     *
     * @returns {string}
     * @memberof ControlInterface
     */
    getControlType(): string;

    /**
     * 获取多项数据
     *
     * @returns {any[]}
     * @memberof ControlInterface
     */
    getDatas(): any[];

    /**
     * 获取单项数据
     *
     * @returns {*}
     * @memberof ControlInterface
     */
    getData():any;

    /**
     * 部件初始化
     *
     * @memberof ControlInterface
     */
    ctrlInit(args?:any):void;

    /**
     * 部件挂载
     *
     * @memberof ControlInterface
     */
    ctrlMounted(args?:any):void;

    /**
     * 部件销毁
     *
     * @memberof ControlInterface
     */
    ctrlDestroyed(args?:any):void;

    /**
     *  计数器刷新
     *
     * @memberof ControlInterface
     */
    counterRefresh():void;

    /**
     * 关闭视图
     *
     * @param {any} args
     * @memberof ControlInterface
     */
    closeView(args: any): void;
}
