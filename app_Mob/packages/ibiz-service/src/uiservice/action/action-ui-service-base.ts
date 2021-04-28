import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { ActionService } from '../../service';
import ActionAuthService from '../../authservice/action/action-auth-service';

/**
 * 系统日志UI服务对象基类
 *
 * @export
 * @class ActionUIServiceBase
 */
export class ActionUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof ActionUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Mob/PSAPPDATAENTITIES/Action.json";

    /**
     * Creates an instance of  ActionUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  ActionUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  ActionUIServiceBase
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
        this.authService = new ActionAuthService({context:this.context});
        this.dataService = new ActionService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  ActionUIServiceBase
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
     * @memberof  ActionUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
    }

}