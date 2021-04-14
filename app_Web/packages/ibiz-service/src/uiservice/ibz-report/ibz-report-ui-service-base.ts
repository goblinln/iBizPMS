import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IbzReportService } from '../../service/ibz-report/ibz-report.service';
import IbzReportAuthService from '../../authservice/ibz-report/ibz-report-auth-service';

/**
 * 汇报汇总UI服务对象基类
 *
 * @export
 * @class IbzReportUIServiceBase
 */
export class IbzReportUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IbzReportUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IbzReport.json";

    /**
     * Creates an instance of  IbzReportUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzReportUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IbzReportUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = true;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = ['type'];
        this.authService = new IbzReportAuthService({context:this.context});
        this.dataService = new IbzReportService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IbzReportUIServiceBase
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
     * @memberof  IbzReportUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
    }

}