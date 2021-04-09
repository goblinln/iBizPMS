import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IbzReportRoleConfigService } from '../../service/ibz-report-role-config/ibz-report-role-config.service';
import IbzReportRoleConfigAuthService from '../../authservice/ibz-report-role-config/ibz-report-role-config-auth-service';

/**
 * 汇报角色配置UI服务对象基类
 *
 * @export
 * @class IbzReportRoleConfigUIServiceBase
 */
export class IbzReportRoleConfigUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IbzReportRoleConfigUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IbzReportRoleConfig.json";

    /**
     * Creates an instance of  IbzReportRoleConfigUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzReportRoleConfigUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IbzReportRoleConfigUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new IbzReportRoleConfigAuthService({context:this.context});
        this.dataService = new IbzReportRoleConfigService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IbzReportRoleConfigUIServiceBase
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
     * @memberof  IbzReportRoleConfigUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set('MDATAVIEW:','MDATAVIEW');
        this.allViewFuncMap.set('EDITVIEW:','EDITVIEW');
    }

}