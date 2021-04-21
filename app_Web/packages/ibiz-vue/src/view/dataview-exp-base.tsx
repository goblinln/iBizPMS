import { IPSAppDEDataViewExplorerView, IPSDataViewExpBar } from '@ibiz/dynamic-model-api';
import { DataViewExpViewEngine, ModelTool} from 'ibiz-core';
import { ExpViewBase } from './expview-base';

/**
 * 卡片导航视图基类
 *
 * @export
 * @class DataViewExpBase
 * @extends {ExpViewBase}
 */
export class DataViewExpBase extends ExpViewBase {
    /**
     * 视图实例
     * 
     * @memberof GridExpViewBase
     */
    public viewInstance!: IPSAppDEDataViewExplorerView;
    
    /**
     * 导航栏实例
     * 
     * @memberof GridExpViewBase
     */
    public expBarInstance!: IPSDataViewExpBar;

    /**
     * 视图引擎
     *
     * @public
     * @type {ChartViewEngine}
     * @memberof ChartViewBase
     */
    public engine: DataViewExpViewEngine = new DataViewExpViewEngine();

    /**
     * 引擎初始化
     *
     * @public
     * @memberof DataViewExpBase
     */
    public engineInit(): void {
        if (this.Environment && this.Environment.isPreviewMode) {
          return;
        }
        let engineOpts = ({
            view: this,
            parentContainer: this.$parent,
            p2k: '0',
            keyPSDEField: this.appDeCodeName.toLowerCase(),
            majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
            isLoadDefault: this.viewInstance.loadDefault,
            dataviewexpbar: (this.$refs[this.expBarInstance.name] as any).ctrl,
        });
        this.engine.init(engineOpts);
    }

    /**
     * 初始化分页导航视图实例
     * 
     * @memberof DataViewExpBase
     */
    public async viewModelInit() {
        this.viewInstance = (this.staticProps?.modeldata) as IPSAppDEDataViewExplorerView;
        await super.viewModelInit();
        this.expBarInstance = ModelTool.findPSControlByType('DATAVIEWEXPBAR', this.viewInstance.getPSControls() || []);
    }

    /**
     * 计算目标部件所需参数
     *
     * @param {string} [controlType]
     * @returns
     * @memberof DataViewExpBase
     */
    public computeTargetCtrlData(controlInstance:any) {
      const { targetCtrlName, targetCtrlParam, targetCtrlEvent } = super.computeTargetCtrlData(controlInstance);
      Object.assign(targetCtrlParam.staticProps,{
        sideBarLayout:this.viewInstance.sideBarLayout
      })
      return { targetCtrlName: targetCtrlName, targetCtrlParam: targetCtrlParam, targetCtrlEvent: targetCtrlEvent };
    }    

}