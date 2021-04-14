import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IbizproProjectDailyService } from '../../service/ibizpro-project-daily/ibizpro-project-daily.service';
import IbizproProjectDailyAuthService from '../../authservice/ibizpro-project-daily/ibizpro-project-daily-auth-service';

/**
 * 项目日报UI服务对象基类
 *
 * @export
 * @class IbizproProjectDailyUIServiceBase
 */
export class IbizproProjectDailyUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IbizproProjectDailyUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IbizproProjectDaily.json";

    /**
     * Creates an instance of  IbizproProjectDailyUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IbizproProjectDailyUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IbizproProjectDailyUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = true;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = ['ibizproprojectdailyid'];
        this.authService = new IbizproProjectDailyAuthService({context:this.context});
        this.dataService = new IbizproProjectDailyService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IbizproProjectDailyUIServiceBase
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
     * @memberof  IbizproProjectDailyUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set('EDITVIEW:','EDITVIEW');
        this.allViewFuncMap.set('MDATAVIEW:','MDATAVIEW');
    }

}