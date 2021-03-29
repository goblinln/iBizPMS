import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { HistoryService } from '../../service/history/history.service';
import HistoryAuthService from '../../authservice/history/history-auth-service';

/**
 * 操作历史UI服务对象基类
 *
 * @export
 * @class HistoryUIServiceBase
 */
export class HistoryUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof HistoryUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/History.json";

    /**
     * Creates an instance of  HistoryUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  HistoryUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  HistoryUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new HistoryAuthService({context:this.context});
        this.dataService = new HistoryService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  HistoryUIServiceBase
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
     * @memberof  HistoryUIServiceBase
     */  
    protected initViewFuncMap(){
    }

}