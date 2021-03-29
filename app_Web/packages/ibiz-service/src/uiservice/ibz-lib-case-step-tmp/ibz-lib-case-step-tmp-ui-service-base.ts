import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IbzLibCaseStepTmpService } from '../../service/ibz-lib-case-step-tmp/ibz-lib-case-step-tmp.service';
import IbzLibCaseStepTmpAuthService from '../../authservice/ibz-lib-case-step-tmp/ibz-lib-case-step-tmp-auth-service';

/**
 * 用例库用例步骤UI服务对象基类
 *
 * @export
 * @class IbzLibCaseStepTmpUIServiceBase
 */
export class IbzLibCaseStepTmpUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IbzLibCaseStepTmpUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IbzLibCaseStepTmp.json";

    /**
     * Creates an instance of  IbzLibCaseStepTmpUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzLibCaseStepTmpUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IbzLibCaseStepTmpUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new IbzLibCaseStepTmpAuthService({context:this.context});
        this.dataService = new IbzLibCaseStepTmpService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IbzLibCaseStepTmpUIServiceBase
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
     * @memberof  IbzLibCaseStepTmpUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
    }

}