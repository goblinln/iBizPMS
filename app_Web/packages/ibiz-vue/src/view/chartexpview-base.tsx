import { IPSAppDEChartExplorerView, IPSChartExpBar } from '@ibiz/dynamic-model-api';
import { ChartExpViewEngine, ModelTool } from 'ibiz-core';
import { ExpViewBase } from './expview-base';

export class ChartExpViewBase extends ExpViewBase {

    /**
     * 图表导航视图实例对象
     * 
     * @type {IPSAppDEChartExplorerView}
     * @memberof ChartExpViewBase
     */
    public viewInstance!: IPSAppDEChartExplorerView;

    /**
     * 图表导航栏实例对象
     * 
     * @type {IPSChartExpBar}
     * @memberof ChartExpViewBase
     */
    public expBarInstance!: IPSChartExpBar;

    /**
     * 图表导航视图引擎对象
     * 
     * @type {ChartExpViewEngine}
     * @memberof ChartExpViewBase
     */
    public engine: ChartExpViewEngine = new ChartExpViewEngine();

    /**
     * 视图模型初始化
     * 
     * @memberof ChartExpViewBase
     */
    public async viewModelInit() {
        await super.viewModelInit();
        this.viewInstance = (this.staticProps?.modeldata) as IPSAppDEChartExplorerView;
        this.expBarInstance = ModelTool.findPSControlByType('CHARTEXPBAR', this.viewInstance?.getPSControls?.() || []);
    }

    /**
     * 引擎初始化
     * 
     * @memberof ChartExpViewBase
     */
    public engineInit() {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        let engineOpts = ({
          view: this,
          parentContainer: this.$parent,
          p2k: '0',
          chartexpbar:(this.$refs[this.expBarInstance.name] as any).ctrl,
          keyPSDEField: this.appDeCodeName.toLowerCase(),
          majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
          isLoadDefault: this.viewInstance.loadDefault,
        });
        this.engine.init(engineOpts);
    }

    /**
     * 计算图表导航栏部件参数
     * 
     * @memberof CalendarExpViewBase
     */
    public computeTargetCtrlData(controlInstance:any) {
        const { targetCtrlName, targetCtrlParam, targetCtrlEvent } = super.computeTargetCtrlData(controlInstance);
        Object.assign(targetCtrlParam.staticProps,{
            sideBarLayout:this.viewInstance.sideBarLayout
        })
        return { targetCtrlName: targetCtrlName, targetCtrlParam: targetCtrlParam, targetCtrlEvent: targetCtrlEvent };
    }
}