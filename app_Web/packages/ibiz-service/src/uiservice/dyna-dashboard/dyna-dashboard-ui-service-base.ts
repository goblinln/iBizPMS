import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { DynaDashboardService } from '../../service';
import DynaDashboardAuthService from '../../authservice/dyna-dashboard/dyna-dashboard-auth-service';

/**
 * 动态数据看板UI服务对象基类
 *
 * @export
 * @class DynaDashboardUIServiceBase
 */
export class DynaDashboardUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof DynaDashboardUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/DynaDashboard.json";

    /**
     * Creates an instance of  DynaDashboardUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  DynaDashboardUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  DynaDashboardUIServiceBase
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
        this.authService = new DynaDashboardAuthService({context:this.context});
        this.dataService = new DynaDashboardService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  DynaDashboardUIServiceBase
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
     * @memberof  DynaDashboardUIServiceBase
     */  
    protected initViewFuncMap(){
    }

}