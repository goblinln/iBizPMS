import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IbizproProjectMonthlyService } from '../../service';
import IbizproProjectMonthlyAuthService from '../../authservice/ibizpro-project-monthly/ibizpro-project-monthly-auth-service';

/**
 * 项目月报UI服务对象基类
 *
 * @export
 * @class IbizproProjectMonthlyUIServiceBase
 */
export class IbizproProjectMonthlyUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IbizproProjectMonthlyUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IbizproProjectMonthly.json";

    /**
     * Creates an instance of  IbizproProjectMonthlyUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IbizproProjectMonthlyUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IbizproProjectMonthlyUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = true;
        this.dynaInstTag = "";
        this.tempOrgIdDEField =null;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = ['ibizproprojectmonthlyid'];
        this.authService = new IbizproProjectMonthlyAuthService({context:this.context});
        this.dataService = new IbizproProjectMonthlyService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IbizproProjectMonthlyUIServiceBase
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
     * @memberof  IbizproProjectMonthlyUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set('MDATAVIEW:','MDATAVIEW');
        this.allViewFuncMap.set('EDITVIEW:','EDITVIEW');
    }

}