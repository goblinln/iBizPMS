import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IBZTaskEstimateService } from '../../service';
import IBZTaskEstimateAuthService from '../../authservice/ibztask-estimate/ibztask-estimate-auth-service';

/**
 * 任务预计UI服务对象基类
 *
 * @export
 * @class IBZTaskEstimateUIServiceBase
 */
export class IBZTaskEstimateUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IBZTaskEstimateUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IBZTaskEstimate.json";

    /**
     * Creates an instance of  IBZTaskEstimateUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZTaskEstimateUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IBZTaskEstimateUIServiceBase
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
        this.authService = new IBZTaskEstimateAuthService({context:this.context});
        this.dataService = new IBZTaskEstimateService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IBZTaskEstimateUIServiceBase
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
     * @memberof  IBZTaskEstimateUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
    }

}