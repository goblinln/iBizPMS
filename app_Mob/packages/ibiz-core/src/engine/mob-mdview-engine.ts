
import { MDViewEngineBase } from './md-view-engine-base';

/**
 * 实体移动端多数据界面引擎
 *
 * @export
 * @class MDViewEngine
 * @extends {ViewEngine}
 */
export class MobMDViewEngine extends MDViewEngineBase {

    /**
     * 表格部件
     *
     * @type {*}
     * @memberof GridViewEngine
     */
    protected mdctrl: any;


    /**
     * Creates an instance of GridViewEngine.
     * @memberof GridViewEngine
     */
    constructor() {
        super();
    }

    /**
     * 引擎初始化
     *
     * @param {*} [options={}]
     * @memberof GridViewEngine
     */
    public init(options: any = {}): void {
        this.mdctrl = options.mdctrl;
        super.init(options);
    }

    /**
     * 多数据部件
     *
     * @param {string} ctrlName
     * @param {string} eventName
     * @param {*} args
     * @memberof MobMDViewEngine
     */
    public onCtrlEvent(ctrlName: string, eventName: string, args: any): void {
        if (Object.is(ctrlName, 'mdctrl')) {
            this.MDCtrlEvent(eventName, args);
        }
        super.onCtrlEvent(ctrlName, eventName, args);
    }

    /**
     * 获取多数据部件
     *
     * @returns {*}
     * @memberof MDViewEngine
     */
    public getMDCtrl(): any {
        return this.mdctrl;
    }
}