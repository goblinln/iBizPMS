import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { DynaFilterService } from '../../service';
import DynaFilterAuthService from '../../authservice/dyna-filter/dyna-filter-auth-service';

/**
 * 动态搜索栏UI服务对象基类
 *
 * @export
 * @class DynaFilterUIServiceBase
 */
export class DynaFilterUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof DynaFilterUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Mob/PSAPPDATAENTITIES/DynaFilter.json";

    /**
     * Creates an instance of  DynaFilterUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  DynaFilterUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  DynaFilterUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.dynaInstTag = "";
        this.tempOrgIdDEField ="orgid";
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new DynaFilterAuthService({context:this.context});
        this.dataService = new DynaFilterService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  DynaFilterUIServiceBase
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
     * @memberof  DynaFilterUIServiceBase
     */  
    protected initViewFuncMap(){
    }

}