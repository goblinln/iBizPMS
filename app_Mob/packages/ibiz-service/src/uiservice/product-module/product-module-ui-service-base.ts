import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { ProductModuleService } from '../../service';
import ProductModuleAuthService from '../../authservice/product-module/product-module-auth-service';

/**
 * 需求模块UI服务对象基类
 *
 * @export
 * @class ProductModuleUIServiceBase
 */
export class ProductModuleUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof ProductModuleUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Mob/PSAPPDATAENTITIES/ProductModule.json";

    /**
     * Creates an instance of  ProductModuleUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductModuleUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  ProductModuleUIServiceBase
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
        this.authService = new ProductModuleAuthService({context:this.context});
        this.dataService = new ProductModuleService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  ProductModuleUIServiceBase
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
     * @memberof  ProductModuleUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set('MOBPICKUPVIEW:','MOBPICKUPVIEW');
        this.allViewFuncMap.set(':','');
    }

}