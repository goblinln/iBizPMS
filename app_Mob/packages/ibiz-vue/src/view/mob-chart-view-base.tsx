import { IPSAppDataEntity, IPSAppDEField, IPSAppDEMobChartView, IPSDEChart } from '@ibiz/dynamic-model-api';
import { MobChartViewEngine, ModelTool } from 'ibiz-core';
import { MDViewBase } from './md-view-base';

/**
 * 图表视图基类
 *
 * @export
 * @class MobChartViewBase
 * @extends {MDViewBase}
 */
export class MobChartViewBase extends MDViewBase {

    /**
     * 视图实例
     * 
     * @memberof MobChartViewBase
     */
     public viewInstance!: IPSAppDEMobChartView;

     /**
      * 图表实例
      * 
      * @memberof MobChartViewBase
      */
     public chartInstance!: IPSDEChart;

    /**
     * 视图引擎
     *
     * @public
     * @type {ChartViewEngine}
     * @memberof MobChartViewBase
     */
    public engine: MobChartViewEngine = new MobChartViewEngine();

    /**
     * 引擎初始化
     *
     * @param {*} [opts={}] 引擎参数
     * @memberof MobChartViewBase
     */
    public engineInit(opts: any = {}): void {
        if (this.Environment?.isPreviewMode) {
            return;
        }
        if (this.engine && this.chartInstance) {
            let engineOpts = Object.assign({
                view: this,
                parentContainer: this.$parent,
                p2k: '0',
                isLoadDefault: this.viewInstance.loadDefault !== false ? true : false,
                keyPSDEField: (ModelTool.getViewAppEntityCodeName(this.viewInstance) as string).toLowerCase(),
                majorPSDEField: (ModelTool.getAppEntityMajorField(this.viewInstance.getPSAppDataEntity() as IPSAppDataEntity) as IPSAppDEField)?.codeName.toLowerCase(),
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
            } else if (this.quickSearchFormInstance?.name && this.$refs[this.quickSearchFormInstance.name]) {
                engineOpts.searchform = ((this.$refs[this.quickSearchFormInstance.name] as any).ctrl);
            }
            if (this.searchBarInstance?.name && this.$refs[this.searchBarInstance.name]) {
                engineOpts.searchbar = ((this.$refs[this.searchBarInstance.name] as any).ctrl);
            }
            this.engine.init(engineOpts);
        }
    }

    /**
      * 初始化图表视图实例
      * 
      * @param opts 
      * @memberof MobChartViewBase
      */
    public async viewModelInit() {
        await this.viewInstance.getPSAppDataEntity()?.fill();
        await super.viewModelInit();
        this.chartInstance = ModelTool.findPSControlByName('chart', this.viewInstance.getPSControls()) as IPSDEChart;
    }

    /**
     * 渲染视图主体内容区
     * 
     * @returns
     * @memberof MobChartViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(this.chartInstance);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.chartInstance.name, on: targetCtrlEvent });
    }

    /**
     * 快速搜索
     *
     * @param {*} $event
     * @memberof MobChartViewBase
     */
    public onSearch(): void {
        const refs: any = this.$refs[this.chartInstance?.name];
        if (refs && refs.$refs?.ctrl) {
            refs.$refs.ctrl.refresh({});
        }
    }

}
