import { IPSAppDataEntity, IPSAppDEField, IPSAppDEMobListExplorerView, IPSListExpBar } from '@ibiz/dynamic-model-api';
import { MobListExpViewEngine, ModelTool } from 'ibiz-core';
import { MobExpViewBase } from './mob-exp-view-base';


/**
 * 列表导航视图基类
 *
 * @export
 * @class MobListExpViewBase
 * @extends {ExpViewBase}
 */
export class MobListExpViewBase extends MobExpViewBase {

    /**
     * 视图实例
     * 
     * @memberof MobListExpViewBase
     */
    public viewInstance!: IPSAppDEMobListExplorerView;

    /**
     * 导航栏实例
     * 
     * @memberof MobListExpViewBase
     */
    public expBarInstance!: IPSListExpBar;

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof MobListExpViewBase
     */
    public engine: MobListExpViewEngine = new MobListExpViewEngine();

    /**
     * 引擎初始化
     *
     * @public
     * @memberof MobListExpViewBase
     */
    public engineInit(opts: any = {}): void {
        if (this.Environment?.isPreviewMode) {
            return;
        }
        let engineOpts = ({
            view: this,
            parentContainer: this.$parent,
            p2k: '0',
            listexpbar: (this.$refs[this.expBarInstance.name] as any).ctrl,
            keyPSDEField: (ModelTool.getViewAppEntityCodeName(this.viewInstance) as string).toLowerCase(),
            majorPSDEField: (ModelTool.getAppEntityMajorField(this.viewInstance.getPSAppDataEntity() as IPSAppDataEntity) as IPSAppDEField)?.codeName.toLowerCase(),
            isLoadDefault: this.viewInstance.loadDefault,
        });
        this.engine.init(engineOpts);
    }

    /**
     * 初始化分页导航视图实例
     * 
     * @memberof MobListExpViewBase
     */
    public async viewModelInit() {
        await super.viewModelInit();
        this.expBarInstance = ModelTool.findPSControlByName('listexpbar', this.viewInstance.getPSControls()) as IPSListExpBar;
    }

    /**
     * 计算目标部件所需参数
     *
     * @param {string} [controlType]
     * @returns
     * @memberof MobListExpViewBase
     */
    public computeTargetCtrlData(controlInstance: any) {
        const { targetCtrlName, targetCtrlParam, targetCtrlEvent } = super.computeTargetCtrlData(controlInstance);
        Object.assign(targetCtrlParam.staticProps, {
            sideBarLayout: this.viewInstance.sideBarLayout
        })
        return { targetCtrlName: targetCtrlName, targetCtrlParam: targetCtrlParam, targetCtrlEvent: targetCtrlEvent };
    }
}