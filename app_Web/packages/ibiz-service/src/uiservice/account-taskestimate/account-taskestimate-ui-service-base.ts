import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { AccountTaskestimateService } from '../../service';
import AccountTaskestimateAuthService from '../../authservice/account-taskestimate/account-taskestimate-auth-service';

/**
 * 用户工时统计UI服务对象基类
 *
 * @export
 * @class AccountTaskestimateUIServiceBase
 */
export class AccountTaskestimateUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof AccountTaskestimateUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/AccountTaskestimate.json";

    /**
     * Creates an instance of  AccountTaskestimateUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  AccountTaskestimateUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  AccountTaskestimateUIServiceBase
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
        this.authService = new AccountTaskestimateAuthService({context:this.context});
        this.dataService = new AccountTaskestimateService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  AccountTaskestimateUIServiceBase
     */
    protected async initActionMap() {
        if (this.entityModel && this.entityModel.getAllPSAppDEUIActions && this.entityModel.getAllPSAppDEUIActions.length > 0) {
            this.entityModel.getAllPSAppDEUIActions.forEach(async (element: any) => {
                const targetAction:any = await AppLogicFactory.getInstance(element, this.context);
                this.actionMap.set(element.uIActionTag, targetAction);
            });
        }
    }

    /**
     * 初始化视图功能数据Map
     * 
     * @memberof  AccountTaskestimateUIServiceBase
     */  
    protected initViewFuncMap(){
    }

}