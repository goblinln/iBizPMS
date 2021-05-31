import { ViewEngine } from './view-engine';
/**
 * 实体移动端多数据界面引擎
 *
 * @export
 * @class MDViewEngine
 * @extends {ViewEngine}
 */
export class MobListExpViewEngine extends ViewEngine {

    /**
     * 表格部件
     *
     * @type {*}
     * @memberof GridViewEngine
     */
    protected listexpbar: any;


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
        this.listexpbar = options.listexpbar;
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
        super.onCtrlEvent(ctrlName, eventName, args);
    }

    /**
     * 获取多数据部件
     *
     * @returns {*}
     * @memberof MDViewEngine
     */
    public getListExpBar(): any {
        return this.listexpbar;
    }

    public load(opts: any): any {
        super.load(opts);
        if (this.getListExpBar()) {
            const tag = this.getListExpBar().name;
            this.setViewState2({ tag: tag, action: 'load', viewdata: { ...this.view.viewparams } });
        } else {
            this.isLoadDefault = true;
        }
    }
}