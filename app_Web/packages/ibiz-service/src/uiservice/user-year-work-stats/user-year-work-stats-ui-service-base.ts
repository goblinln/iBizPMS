import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { UserYearWorkStatsService } from '../../service/user-year-work-stats/user-year-work-stats.service';
import UserYearWorkStatsAuthService from '../../authservice/user-year-work-stats/user-year-work-stats-auth-service';

/**
 * 用户年度工作内容统计UI服务对象基类
 *
 * @export
 * @class UserYearWorkStatsUIServiceBase
 */
export class UserYearWorkStatsUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof UserYearWorkStatsUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/UserYearWorkStats.json";

    /**
     * Creates an instance of  UserYearWorkStatsUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  UserYearWorkStatsUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  UserYearWorkStatsUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new UserYearWorkStatsAuthService({context:this.context});
        this.dataService = new UserYearWorkStatsService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  UserYearWorkStatsUIServiceBase
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
     * @memberof  UserYearWorkStatsUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set('EDITVIEW:','EDITVIEW');
        this.allViewFuncMap.set(':','');
    }

}