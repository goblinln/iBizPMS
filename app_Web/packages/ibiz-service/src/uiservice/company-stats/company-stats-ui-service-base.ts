import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { CompanyStatsService } from '../../service';
import CompanyStatsAuthService from '../../authservice/company-stats/company-stats-auth-service';

/**
 * 公司动态汇总UI服务对象基类
 *
 * @export
 * @class CompanyStatsUIServiceBase
 */
export class CompanyStatsUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof CompanyStatsUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/CompanyStats.json";

    /**
     * Creates an instance of  CompanyStatsUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  CompanyStatsUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  CompanyStatsUIServiceBase
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
        this.authService = new CompanyStatsAuthService({context:this.context});
        this.dataService = new CompanyStatsService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  CompanyStatsUIServiceBase
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
     * @memberof  CompanyStatsUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
    }

}