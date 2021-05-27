import { IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IBZPROJECTTEAMService } from '../../service';
import IBZPROJECTTEAMAuthService from '../../authservice/ibzprojectteam/ibzprojectteam-auth-service';

/**
 * 项目团队UI服务对象基类
 *
 * @export
 * @class IBZPROJECTTEAMUIServiceBase
 */
export class IBZPROJECTTEAMUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IBZPROJECTTEAMUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Mob/PSAPPDATAENTITIES/IBZPROJECTTEAM.json";

    /**
     * Creates an instance of  IBZPROJECTTEAMUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZPROJECTTEAMUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IBZPROJECTTEAMUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.dynaInstTag = "";
        this.tempOrgIdDEField ="org";
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new IBZPROJECTTEAMAuthService({context:this.context});
        this.dataService = new IBZPROJECTTEAMService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IBZPROJECTTEAMUIServiceBase
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
     * @memberof  IBZPROJECTTEAMUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
    }

}