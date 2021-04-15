import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IbizproProjectWeeklyService } from '../../service';
import IbizproProjectWeeklyAuthService from '../../authservice/ibizpro-project-weekly/ibizpro-project-weekly-auth-service';

/**
 * 项目周报UI服务对象基类
 *
 * @export
 * @class IbizproProjectWeeklyUIServiceBase
 */
export class IbizproProjectWeeklyUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IbizproProjectWeeklyUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IbizproProjectWeekly.json";

    /**
     * Creates an instance of  IbizproProjectWeeklyUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IbizproProjectWeeklyUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IbizproProjectWeeklyUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = true;
        this.dynaInstTag = "";
        this.tempOrgIdDEField =null;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = ['projectweeklyid'];
        this.authService = new IbizproProjectWeeklyAuthService({context:this.context});
        this.dataService = new IbizproProjectWeeklyService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IbizproProjectWeeklyUIServiceBase
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
     * @memberof  IbizproProjectWeeklyUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set('MDATAVIEW:','MDATAVIEW');
        this.allViewFuncMap.set('EDITVIEW:','EDITVIEW');
    }

}