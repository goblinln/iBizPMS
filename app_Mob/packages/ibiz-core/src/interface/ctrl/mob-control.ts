import { Subject, Subscription } from 'rxjs';

/**
 * 部件基类接口
 *
 * @interface MobControlInterface
 */
export interface MobControlInterface {

    /**
     * 部件服务对象
     *
     * @type {*}
     * @memberof MobControlInterface
     */
    service: any;

    /**
     * 实体服务对象
     *
     * @type {*}
     * @memberof MobControlInterface
     */
    appEntityService: any;

    /**
     * 订阅视图状态事件
     *
     * @public
     * @type {(Subscription | undefined)}
     * @memberof MobControlInterface
     */
    viewStateEvent: Subscription | undefined;

    /**
     * 计数器服务对象集合
     *
     * @type {Array<*>}
     * @memberof MobControlInterface
     */
    counterServiceArray: Array<any>;

    /**
     * 获取部件类型
     *
     * @returns {string}
     * @memberof MobControlInterface
     */
    getControlType(): string;

    /**
     * 获取多项数据
     *
     * @returns {any[]}
     * @memberof MobControlInterface
     */
    getDatas(): any[];

    /**
     * 获取单项数据
     *
     * @returns {*}
     * @memberof MobControlInterface
     */
    getData():any;

    /**
     * 部件初始化
     *
     * @memberof MobControlInterface
     */
    ctrlInit(args?:any):void;

    /**
     * 部件挂载
     *
     * @memberof MobControlInterface
     */
    ctrlMounted(args?:any):void;

    /**
     * 部件销毁
     *
     * @memberof MobControlInterface
     */
    ctrlDestroyed(args?:any):void;

    /**
     *  计数器刷新
     *
     * @memberof MobControlInterface
     */
    counterRefresh():void;

    /**
     * 关闭视图
     *
     * @param {any} args
     * @memberof MobControlInterface
     */
    closeView(args: any): void;
}
