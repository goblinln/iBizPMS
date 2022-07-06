import { IPSAppDataEntity, IPSAppDEField, IPSAppDEMobMapView, IPSMap } from '@ibiz/dynamic-model-api';
import { MobMapViewEngine, MobMapViewInterface, ModelTool } from 'ibiz-core';
import { MobMDViewBase } from './mob-md-view-base';


/**
 * 地图视图基类
 *
 * @export
 * @class MobMapViewBase
 * @extends {ExpViewBase}
 */
export class MobMapViewBase extends MobMDViewBase implements MobMapViewInterface {

    /**
     * 视图实例
     * 
     * @memberof MobMapViewBase
     */
    public viewInstance!: IPSAppDEMobMapView;

    /**
     * 导航栏实例
     * 
     * @memberof MobMapViewBase
     */
    public mapInstance!: IPSMap;

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof MobMapViewBase
     */
    public engine: MobMapViewEngine = new MobMapViewEngine();

    /**
     * 引擎初始化
     *
     * @public
     * @memberof MobMapViewBase
     */
    public engineInit(opts: any = {}): void {
        if (this.Environment?.isPreviewMode) {
            return;
        }
        let engineOpts = ({
            view: this,
            parentContainer: this.$parent,
            p2k: '0',
            map: (this.$refs[this.mapInstance.name] as any).ctrl,
            keyPSDEField: (ModelTool.getViewAppEntityCodeName(this.viewInstance) as string).toLowerCase(),
            majorPSDEField: (ModelTool.getAppEntityMajorField(this.viewInstance.getPSAppDataEntity() as IPSAppDataEntity) as IPSAppDEField)?.codeName.toLowerCase(),
            isLoadDefault: this.viewInstance.loadDefault,
        });
        this.engine.init(engineOpts);
    }

    /**
     * 初始化分页导航视图实例
     * 
     * @memberof MobMapViewBase
     */
    public async viewModelInit() {
        await super.viewModelInit();
        this.mapInstance = ModelTool.findPSControlByName('map', this.viewInstance.getPSControls()) as IPSMap;
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof MobMapViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(this.mapInstance);
        return this.$createElement(targetCtrlName, { slot: 'default', props: targetCtrlParam, ref: this.mapInstance?.name, on: targetCtrlEvent });
    }

    /**
     * 计算目标部件所需参数
     *
     * @param {string} [controlType]
     * @returns
     * @memberof MobMapViewBase
     */
    public computeTargetCtrlData(controlInstance: any) {
        const { targetCtrlName, targetCtrlParam, targetCtrlEvent } = super.computeTargetCtrlData(controlInstance);
        return { targetCtrlName: targetCtrlName, targetCtrlParam: targetCtrlParam, targetCtrlEvent: targetCtrlEvent };
    }
}