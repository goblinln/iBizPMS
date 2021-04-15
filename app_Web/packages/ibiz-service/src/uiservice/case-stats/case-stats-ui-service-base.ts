import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { CaseStatsService } from '../../service/case-stats/case-stats.service';
import CaseStatsAuthService from '../../authservice/case-stats/case-stats-auth-service';

/**
 * 测试用例统计UI服务对象基类
 *
 * @export
 * @class CaseStatsUIServiceBase
 */
export class CaseStatsUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof CaseStatsUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/CaseStats.json";

    /**
     * Creates an instance of  CaseStatsUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  CaseStatsUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  CaseStatsUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new CaseStatsAuthService({context:this.context});
        this.dataService = new CaseStatsService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  CaseStatsUIServiceBase
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
     * @memberof  CaseStatsUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
    }

}