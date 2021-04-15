import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IBIZProKeywordService } from '../../service';
import IBIZProKeywordAuthService from '../../authservice/ibizpro-keyword/ibizpro-keyword-auth-service';

/**
 * 关键字UI服务对象基类
 *
 * @export
 * @class IBIZProKeywordUIServiceBase
 */
export class IBIZProKeywordUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IBIZProKeywordUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IBIZProKeyword.json";

    /**
     * Creates an instance of  IBIZProKeywordUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IBIZProKeywordUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IBIZProKeywordUIServiceBase
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
        this.authService = new IBIZProKeywordAuthService({context:this.context});
        this.dataService = new IBIZProKeywordService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IBIZProKeywordUIServiceBase
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
     * @memberof  IBIZProKeywordUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set('MDATAVIEW:','MDATAVIEW');
        this.allViewFuncMap.set('EDITVIEW:','EDITVIEW');
    }

}