import { MDViewEngine } from './md-view-engine';


/**
 * 视图引擎基础
 *
 * @export
 * @class GridViewEngine
 * @extends {MDViewEngine}
 */
export class MapViewEngine extends MDViewEngine {

    /**
     * 表格部件
     *
     * @type {*}
     * @memberof GridViewEngine
     */
    protected map: any;

    /**
     * Creates an instance of GridViewEngine.
     * @memberof MapViewEngine
     */
    constructor() {
        super();
    }

    /**
     * 引擎初始化
     *
     * @param {*} [options={}]
     * @memberof MapViewEngine
     */
    public init(options: any = {}): void {
        this.map = options.map;
        super.init(options);
    }


    /**
     * 获取多数据部件
     *
     * @returns {*}
     * @memberof MapViewEngine
     */
    public getMDCtrl(): any {
        return this.map;
    }

    /**
     * 部件事件
     *
     * @param {string} ctrlName
     * @param {string} eventName
     * @param {*} args
     * @memberof MapViewEngine
     */
     public onCtrlEvent(ctrlName: string, eventName: string, args: any): void {
        if (Object.is(ctrlName, 'map')) {
            this.MDCtrlEvent(eventName, args);
        }
        super.onCtrlEvent(ctrlName, eventName, args);
    }

    /**
     * 事件处理
     *
     * @param {string} eventName
     * @param {*} args
     * @memberof MapViewEngine
     */
     public MDCtrlEvent(eventName: string, args: any): void {
        if (Object.is(eventName, 'selectionchange')) {
            // this.selectionChange(args);
            // this.doEdit(args);
            return;
        }
        super.MDCtrlEvent(eventName, args);
    }

}