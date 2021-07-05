import { ViewEngine } from './view-engine';

/**
 * 图表导航视图界面引擎
 *
 * @export
 * @class ChartExpViewEngine
 * @extends {ViewEngine}
 */
export class ChartExpViewEngine extends ViewEngine {

    /**
     * 图表导航栏部件
     *
     * @type {*}
     * @memberof ChartExpViewEngine
     */
    public chartExpBar: any = null;

    /**
     * Creates an instance of ChartExpViewEngine.
     * 
     * @memberof ChartExpViewEngine
     */
    constructor() {
        super();
    }

    /**
     * 初始化引擎
     *
     * @param {*} options
     * @memberof ChartExpViewEngine
     */
    public init(options: any): void {
        this.chartExpBar = options.chartexpbar;
        super.init(options);
    }


    /**
     * 引擎加载
     *
     * @memberof ChartExpViewEngine
     */
    public load(): void {
        super.load();
        if (this.getChartExpBar() && this.isLoadDefault) {
            const tag = this.getChartExpBar().name;
            this.setViewState2({ tag: tag, action: 'load', viewdata: this.view.viewparams });
        } else {
            this.isLoadDefault = true;
        }
    }

    /**
     * 部件事件机制
     *
     * @param {string} ctrlName
     * @param {string} eventName
     * @param {*} args
     * @memberof ChartExpViewEngine
     */
    public onCtrlEvent(ctrlName: string, eventName: string, args: any): void {
        super.onCtrlEvent(ctrlName, eventName, args);
        if (Object.is(ctrlName, 'chartexpbar')) {
            this.chartExpBarEvent(eventName, args);

        }
    }

    /**
     * 图表导航事件
     *
     * @param {string} eventName
     * @param {*} args
     * @memberof ChartExpViewEngine
     */
    public chartExpBarEvent(eventName: string, args: any): void {
        if (Object.is(eventName, 'load')) {
            this.emitViewEvent('viewload', args);
        }
        if (Object.is(eventName, 'selectionchange')) {
            this.emitViewEvent('viewdataschange', args);
        }
        if (Object.is(eventName, 'activated')) {
            this.emitViewEvent('viewdatasactivated', args);
        }
    }

    /**
     * 获取部件对象
     *
     * @returns {*}
     * @memberof ChartExpViewEngine
     */
    public getChartExpBar(): any {
        return this.chartExpBar;
    }


}