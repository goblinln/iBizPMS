import { SearchViewEngine } from './search-view-engine';

/**
 * 实体图表视图界面引擎
 *
 * @export
 * @class ChartViewEngine
 * @extends {SearchViewEngine}
 */
export class ChartViewEngine extends SearchViewEngine {

    /**
     * 图表对象
     *
     * @type {*}
     * @memberof ChartViewEngine
     */
    public chart: any;

    /**
     * 图表初始化
     *
     * @param {*} options
     * @memberof ChartViewEngine
     */
    public init(options: any): void {
        this.chart = options.chart;
        super.init(options);

    }

    /**
     * 引擎加载
     *
     * @param {*} [opts={}]
     * @memberof ChartViewEngine
     */
    public load(opts: any = {}): void {
        if (this.getSearchForm()) {
            const tag = this.getSearchForm().name;
            this.setViewState2({ tag: tag, action: 'loaddraft', viewdata: this.view.viewparams });
        }else if(this.getDataCtrl() && this.isLoadDefault) {
            const tag = this.getDataCtrl().name;
            this.setViewState2({ tag: tag, action: 'load', viewdata: {} });
        } else {
            this.isLoadDefault = true;
        }
    }

    /**
     * 部件事件
     *
     * @param {string} ctrlName
     * @param {string} eventName
     * @param {*} args
     * @memberof ChartViewEngine
     */
    public onCtrlEvent(ctrlName: string, eventName: string, args: any): void {
        super.onCtrlEvent(ctrlName, eventName, args);
        if (Object.is(ctrlName, 'chart')) {
            this.chartEvent(eventName, args);
        }
    }

    /**
     * 图表事件
     *
     * @param {string} eventName
     * @param {*} args
     * @memberof ChartViewEngine
     */
    public chartEvent(eventName: string, args: any): void {
        if (Object.is(eventName, 'beforeload')) {
            this.dataCtrlBeforeLoad(args)
        }
    }

    /**
     * 搜索表单加载完成
     *
     * @param {*} [args={}]
     * @memberof MDViewEngine
     */
    public onSearchFormLoad(args: any = {}): void {
        if (this.getDataCtrl() && this.isLoadDefault) {
            const tag = this.getDataCtrl().name;
            this.setViewState2({ tag: tag, action: 'load', viewdata: args });
        }
        this.isLoadDefault = true;
    }

    /**
     * 搜索表单事件
     *
     * @param {string} eventName
     * @param {*} [args={}]
     * @memberof ChartViewEngine
     */
    public searchFormEvent(eventName: string, args: any = {}): void {
        if (Object.is(eventName, 'load')) {
            this.onSearchFormLoad(args);
        }
        if (Object.is(eventName, 'search')) {
            this.onSearchFormLoad(args);
        }
    }

    /**
     * 获取数据部件
     *
     * @returns {*}
     * @memberof ChartViewEngine
     */
    public getDataCtrl(): any {
        return this.chart;
    }
}