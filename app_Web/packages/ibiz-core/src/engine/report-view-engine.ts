import { MDViewEngine } from './md-view-engine';

/**
 * 实体报表视图引擎
 *
 * @export
 * @class ReportViewEngine
 * @extends {MDViewEngine}
 */
export class ReportViewEngine extends MDViewEngine {

    /**
     * 表格部件
     *
     * @type {*}
     * @memberof GridViewEngine
     */
    protected reportPanel: any;

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
        this.reportPanel = options.reportpanel;
        super.init(options);
    }

    /**
     * 部件事件
     *
     * @param {string} ctrlName
     * @param {string} eventName
     * @param {*} args
     * @memberof GridViewEngine
     */
    public onCtrlEvent(ctrlName: string, eventName: string, args: any): void {
        if (Object.is(ctrlName, this.getMDCtrl().name)) {
            this.MDCtrlEvent(eventName, args);
        }
        super.onCtrlEvent(ctrlName, eventName, args);
    }


    /**
     * 获取多数据部件
     *
     * @returns {*}
     * @memberof GridViewEngine
     */
    public getMDCtrl(): any {
        return this.reportPanel;
    }
}