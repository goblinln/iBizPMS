import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IBzDocService } from '../../service/ibz-doc/ibz-doc.service';
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
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IBzDoc.json";

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
     * @memberof  IBzDocUIServiceBase
     */  
    protected initViewFuncMap(){
    }

}