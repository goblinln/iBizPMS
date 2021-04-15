import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { PSSysSFPubService } from '../../service';
import PSSysSFPubAuthService from '../../authservice/pssys-sfpub/pssys-sfpub-auth-service';

/**
 * 后台服务架构UI服务对象基类
 *
 * @export
 * @class PSSysSFPubUIServiceBase
 */
export class PSSysSFPubUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof PSSysSFPubUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/PSSysSFPub.json";

    /**
     * Creates an instance of  PSSysSFPubUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  PSSysSFPubUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  PSSysSFPubUIServiceBase
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
        this.authService = new PSSysSFPubAuthService({context:this.context});
        this.dataService = new PSSysSFPubService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  PSSysSFPubUIServiceBase
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
     * @memberof  PSSysSFPubUIServiceBase
     */  
    protected initViewFuncMap(){
    }

}