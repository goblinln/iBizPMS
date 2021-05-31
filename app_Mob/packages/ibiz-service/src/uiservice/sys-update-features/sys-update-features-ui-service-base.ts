import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { SysUpdateFeaturesService } from '../../service';
import SysUpdateFeaturesAuthService from '../../authservice/sys-update-features/sys-update-features-auth-service';

/**
 * 系统更新功能UI服务对象基类
 *
 * @export
 * @class SysUpdateFeaturesUIServiceBase
 */
export class SysUpdateFeaturesUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof SysUpdateFeaturesUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Mob/PSAPPDATAENTITIES/SysUpdateFeatures.json";

    /**
     * Creates an instance of  SysUpdateFeaturesUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  SysUpdateFeaturesUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  SysUpdateFeaturesUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.dynaInstTag = "";
        this.tempOrgIdDEField =null;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new SysUpdateFeaturesAuthService({context:this.context});
        this.dataService = new SysUpdateFeaturesService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  SysUpdateFeaturesUIServiceBase
     */
    protected async initActionMap() {
        if (this.entityModel && this.entityModel.getAllPSAppDEUIActions() && (this.entityModel.getAllPSAppDEUIActions() as IPSAppDEUIAction[]).length > 0) {
            for(let element of (this.entityModel.getAllPSAppDEUIActions() as IPSAppDEUIAction[])){
                const targetAction:any = await AppLogicFactory.getInstance(element, this.context);
                this.actionMap.set(element.uIActionTag, targetAction);
            }
        }
    }

    /**
     * 初始化视图功能数据Map
     * 
     * @memberof  SysUpdateFeaturesUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set('MOBEDITVIEW:','MOBEDITVIEW');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set('MOBMDATAVIEW:','MOBMDATAVIEW');
        this.allViewFuncMap.set(':','');
    }

}