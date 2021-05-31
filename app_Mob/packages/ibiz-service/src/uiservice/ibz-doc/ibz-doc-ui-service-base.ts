import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IBzDocService } from '../../service';
import IBzDocAuthService from '../../authservice/ibz-doc/ibz-doc-auth-service';

/**
 * 文档UI服务对象基类
 *
 * @export
 * @class IBzDocUIServiceBase
 */
export class IBzDocUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IBzDocUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Mob/PSAPPDATAENTITIES/IBzDoc.json";

    /**
     * Creates an instance of  IBzDocUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IBzDocUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IBzDocUIServiceBase
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
        this.authService = new IBzDocAuthService({context:this.context});
        this.dataService = new IBzDocService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IBzDocUIServiceBase
     */
    protected async initActionMap() {
        if (this.entityModel && this.entityModel.getAllPSAppDEUIActions() && (this.entityModel.getAllPSAppDEUIActions() as IPSAppDEUIAction[]).length > 0) {
            for(let element of (this.entityModel.getAllPSAppDEUIActions() as IPSAppDEUIAction[])){
                const targetAction:any = await AppLogicFactory.getInstance(element, this.context);
                this.actionMap.set(element.uIActionTag, targetAction);
            }
        }
    }

    /**
     * 初始化视图功能数据Map
     * 
     * @memberof  IBzDocUIServiceBase
     */  
    protected initViewFuncMap(){
    }

}