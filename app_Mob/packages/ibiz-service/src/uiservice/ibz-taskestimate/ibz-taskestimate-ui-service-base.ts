import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IbzTaskestimateService } from '../../service';
import IbzTaskestimateAuthService from '../../authservice/ibz-taskestimate/ibz-taskestimate-auth-service';

/**
 * 任务预计UI服务对象基类
 *
 * @export
 * @class IbzTaskestimateUIServiceBase
 */
export class IbzTaskestimateUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IbzTaskestimateUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzTaskestimate.json";

    /**
     * Creates an instance of  IbzTaskestimateUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzTaskestimateUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IbzTaskestimateUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.dynaInstTag = "";
        this.tempOrgIdDEField ="org";
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new IbzTaskestimateAuthService({context:this.context});
        this.dataService = new IbzTaskestimateService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IbzTaskestimateUIServiceBase
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
     * @memberof  IbzTaskestimateUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
    }

}