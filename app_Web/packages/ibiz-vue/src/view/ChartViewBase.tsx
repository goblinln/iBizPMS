import { ChartViewEngine, IBizChartModel, IBizChartViewModel } from 'ibiz-core';
import { MDViewBase } from './MDViewBase';

/**
 * 图表视图基类
 *
 * @export
 * @class ChartViewBase
 * @extends {MDViewBase}
 */
export class ChartViewBase extends MDViewBase {

    /**
     * 视图实例
     * 
     * @memberof ListViewBase
     */
    public viewInstance!: IBizChartViewModel;

    /**
     * 图表实例
     * 
     * @memberof ChartViewBase
     */
    public chartInstance!: IBizChartModel;

    /**
     * 视图引擎
     *
     * @public
     * @type {ChartViewEngine}
     * @memberof ChartViewBase
     */
    public engine: ChartViewEngine = new ChartViewEngine();

    /**
     * 引擎初始化
     *
     * @param {*} [opts={}] 引擎参数
     * @memberof ChartViewBase
     */
    public engineInit(opts: any = {}): void {
        if(this.engine && this.chartInstance){
            let engineOpts = Object.assign({
                view: this,
                parentContainer: this.$parent,
                p2k: '0',
                isLoadDefault: this.viewInstance.loadDefault,
                keyPSDEField: (this.viewInstance.appDataEntity.codeName).toLowerCase(),
                majorPSDEField: (this.viewInstance.appDataEntity.majorField?.codeName)?.toLowerCase(),
                opendata: (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
                    this.opendata(args, fullargs, params, $event, xData);
                },
                newdata: (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
                    this.newdata(args, fullargs, params, $event, xData);
                },
                chart: (this.$refs[this.chartInstance.name] as any).ctrl,
            }, opts)
            if (this.searchFormInstance?.name && this.$refs[this.searchFormInstance.name]) {
                engineOpts.searchform = ((this.$refs[this.searchFormInstance.name] as any).ctrl);
            } else if(this.quickSearchFormInstance?.name && this.$refs[this.quickSearchFormInstance.name] ){
                engineOpts.searchform = ((this.$refs[this.quickSearchFormInstance.name] as any).ctrl);
            }
            if(this.searchBarInstance?.name && this.$refs[this.searchBarInstance.name]) {
                engineOpts.searchbar = ((this.$refs[this.searchBarInstance.name] as any).ctrl);
            }
            this.engine.init(engineOpts);
        }
    }

    /**
      * 初始化图表视图实例
      * 
      * @param opts 
      * @memberof ChartViewBase
      */
    public async viewModelInit() {
        this.viewInstance = new IBizChartViewModel(this.staticProps.modeldata, this.context);
        await this.viewInstance.loaded();
        await super.viewModelInit();
        this.chartInstance = this.viewInstance.getControl('chart');
    }

    /**
     * 渲染视图主体内容区
     * 
     * @returns
     * @memberof ChartViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(this.chartInstance);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.viewInstance.viewChart.name, on: targetCtrlEvent });
    }

    /**
     * 快速搜索
     *
     * @param {*} $event
     * @memberof ChartViewBase
     */
    public onSearch($event: any): void {
        const refs: any = this.$refs[this.chartInstance?.name];
        if (refs && refs.$refs?.ctrl) {
            refs.$refs.ctrl.refresh({});
        }
    }

}
