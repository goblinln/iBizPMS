import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { PSSystemDBCfgService } from '../../service/pssystem-dbcfg/pssystem-dbcfg.service';
import PSSystemDBCfgAuthService from '../../authservice/pssystem-dbcfg/pssystem-dbcfg-auth-service';

/**
 * 系统数据库UI服务对象基类
 *
 * @export
 * @class PSSystemDBCfgUIServiceBase
 */
export class PSSystemDBCfgUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof PSSystemDBCfgUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/PSSystemDBCfg.json";

    /**
     * Creates an instance of  PSSystemDBCfgUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  PSSystemDBCfgUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  PSSystemDBCfgUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new PSSystemDBCfgAuthService({context:this.context});
        this.dataService = new PSSystemDBCfgService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  PSSystemDBCfgUIServiceBase
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
     * @memberof  PSSystemDBCfgUIServiceBase
     */  
    protected initViewFuncMap(){
    }

}