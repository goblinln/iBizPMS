import { MobMDViewEngine } from './mob-mdview-engine';

/**
 * 实体移动端地图视图界面引擎
 *
 * @export
 * @class MobMapViewEngine
 * @extends {MobMDViewEngine}
 */
export class MobMapViewEngine extends MobMDViewEngine {

    /**
     * 地图部件
     *
     * @type {*}
     * @memberof MobDataViewEngine
     */
    protected map: any;


    /**
     * Creates an instance of MobDataViewEngine.
     * @memberof MobDataViewEngine
     */
    constructor() {
        super();
    }

    /**
     * 引擎初始化
     *
     * @param {*} [options={}]
     * @memberof MobDataViewEngine
     */
    public init(options: any = {}): void {
        this.map = options.map;
        super.init(options);
    }

    /**
     * 多数据部件
     *
     * @param {string} ctrlName
     * @param {string} eventName
     * @param {*} args
     * @memberof MobDataViewEngine
     */
    public onCtrlEvent(ctrlName: string, eventName: string, args: any): void {
        if (Object.is(ctrlName, 'map')) {
            this.MDCtrlEvent(eventName, args);
        }
        super.onCtrlEvent(ctrlName, eventName, args);
    }

    /**
     * 获取多数据部件
     *
     * @returns {*}
     * @memberof MobDataViewEngine
     */
    public getMDCtrl(): any {
        return this.map;
    }
}