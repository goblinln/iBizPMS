import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { EmpLoyeeloadService } from '../../service/emp-loyeeload/emp-loyeeload.service';
import EmpLoyeeloadAuthService from '../../authservice/emp-loyeeload/emp-loyeeload-auth-service';

/**
 * 员工负载表UI服务对象基类
 *
 * @export
 * @class EmpLoyeeloadUIServiceBase
 */
export class EmpLoyeeloadUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof EmpLoyeeloadUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/EmpLoyeeload.json";

    /**
     * Creates an instance of  EmpLoyeeloadUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  EmpLoyeeloadUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  EmpLoyeeloadUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new EmpLoyeeloadAuthService({context:this.context});
        this.dataService = new EmpLoyeeloadService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  EmpLoyeeloadUIServiceBase
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
     * @memberof  EmpLoyeeloadUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set('EDITVIEW:','EDITVIEW');
        this.allViewFuncMap.set('MDATAVIEW:','MDATAVIEW');
    }

}