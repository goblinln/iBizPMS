import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IbzReportlyService } from '../../service/ibz-reportly/ibz-reportly.service';
import IbzReportlyAuthService from '../../authservice/ibz-reportly/ibz-reportly-auth-service';

/**
 * 汇报UI服务对象基类
 *
 * @export
 * @class IbzReportlyUIServiceBase
 */
export class IbzReportlyUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IbzReportlyUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IbzReportly.json";

    /**
     * Creates an instance of  IbzReportlyUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzReportlyUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IbzReportlyUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = true;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = ['issubmit'];
        this.authService = new IbzReportlyAuthService({context:this.context});
        this.dataService = new IbzReportlyService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IbzReportlyUIServiceBase
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
     * @memberof  IbzReportlyUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
    }

}