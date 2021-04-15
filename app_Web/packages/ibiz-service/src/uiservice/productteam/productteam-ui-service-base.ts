import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { PRODUCTTEAMService } from '../../service/productteam/productteam.service';
import PRODUCTTEAMAuthService from '../../authservice/productteam/productteam-auth-service';

/**
 * 产品团队UI服务对象基类
 *
 * @export
 * @class PRODUCTTEAMUIServiceBase
 */
export class PRODUCTTEAMUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof PRODUCTTEAMUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/PRODUCTTEAM.json";

    /**
     * Creates an instance of  PRODUCTTEAMUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  PRODUCTTEAMUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  PRODUCTTEAMUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new PRODUCTTEAMAuthService({context:this.context});
        this.dataService = new PRODUCTTEAMService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  PRODUCTTEAMUIServiceBase
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
     * @memberof  PRODUCTTEAMUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set('EDITVIEW:','EDITVIEW');
    }

}