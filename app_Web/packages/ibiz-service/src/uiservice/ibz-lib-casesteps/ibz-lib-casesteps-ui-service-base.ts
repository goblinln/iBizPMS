import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IbzLibCasestepsService } from '../../service';
import IbzLibCasestepsAuthService from '../../authservice/ibz-lib-casesteps/ibz-lib-casesteps-auth-service';

/**
 * 用例库用例步骤UI服务对象基类
 *
 * @export
 * @class IbzLibCasestepsUIServiceBase
 */
export class IbzLibCasestepsUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IbzLibCasestepsUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IbzLibCasesteps.json";

    /**
     * Creates an instance of  IbzLibCasestepsUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzLibCasestepsUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IbzLibCasestepsUIServiceBase
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
        this.authService = new IbzLibCasestepsAuthService({context:this.context});
        this.dataService = new IbzLibCasestepsService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IbzLibCasestepsUIServiceBase
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
     * @memberof  IbzLibCasestepsUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
    }

}