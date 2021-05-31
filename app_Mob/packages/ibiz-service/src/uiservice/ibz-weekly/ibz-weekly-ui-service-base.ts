import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IbzWeeklyService } from '../../service';
import IbzWeeklyAuthService from '../../authservice/ibz-weekly/ibz-weekly-auth-service';

/**
 * 周报UI服务对象基类
 *
 * @export
 * @class IbzWeeklyUIServiceBase
 */
export class IbzWeeklyUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IbzWeeklyUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzWeekly.json";

    /**
     * Creates an instance of  IbzWeeklyUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzWeeklyUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IbzWeeklyUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = true;
        this.dynaInstTag = "";
        this.tempOrgIdDEField =null;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = ['issubmit'];
        this.authService = new IbzWeeklyAuthService({context:this.context});
        this.dataService = new IbzWeeklyService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IbzWeeklyUIServiceBase
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
     * @memberof  IbzWeeklyUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set('MOBEDITVIEW:','MOBEDITVIEW');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
    }

}