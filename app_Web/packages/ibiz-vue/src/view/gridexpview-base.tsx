import { IPSAppDEGridExplorerView, IPSGridExpBar } from '@ibiz/dynamic-model-api';
import { GridExpViewEngine, GridExpViewInterface, ModelTool } from 'ibiz-core';
import { ExpViewBase } from './expview-base';


/**
 * 表格导航视图基类
 *
 * @export
 * @class GridExpViewBase
 * @extends {ExpViewBase}
 * @implements {GridExpViewInterface}
 */
export class GridExpViewBase extends ExpViewBase implements GridExpViewInterface {

    /**
     * 视图实例
     * 
     * @memberof GridExpViewBase
     */
    public viewInstance!: IPSAppDEGridExplorerView;

    /**
     * 导航栏实例
     * 
     * @memberof GridExpViewBase
     */
    public expBarInstance!: IPSGridExpBar;

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof GridExpViewBase
     */
    public engine: GridExpViewEngine = new GridExpViewEngine;

    /**
     * 引擎初始化
     *
     * @public
     * @memberof GridExpViewBase
     */
    public engineInit(): void {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        let engineOpts = ({
            view: this,
            parentContainer: this.$parent,
            p2k: '0',
            gridexpbar: (this.$refs[this.expBarInstance.name] as any).ctrl,
            keyPSDEField: this.appDeCodeName.toLowerCase(),
            majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
            isLoadDefault: this.viewInstance.loadDefault,
        });
        this.engine.init(engineOpts);
    }

    /**
     * 初始化分页导航视图实例
     * 
     * @memberof GridExpViewBase
     */
    public async viewModelInit() {
        this.viewInstance = (this.staticProps?.modeldata) as IPSAppDEGridExplorerView;
        await super.viewModelInit();
        this.expBarInstance = ModelTool.findPSControlByType('GRIDEXPBAR', this.viewInstance.getPSControls() || []) as IPSGridExpBar;
    }

    /**
     * 计算目标部件所需参数
     *
     * @param {string} [controlType]
     * @returns
     * @memberof GridExpViewBase
     */
    public computeTargetCtrlData(controlInstance: any) {
        const { targetCtrlName, targetCtrlParam, targetCtrlEvent } = super.computeTargetCtrlData(controlInstance);
        Object.assign(targetCtrlParam.staticProps, {
            sideBarLayout: this.viewInstance.sideBarLayout
        })
        return { targetCtrlName: targetCtrlName, targetCtrlParam: targetCtrlParam, targetCtrlEvent: targetCtrlEvent };
    }

}