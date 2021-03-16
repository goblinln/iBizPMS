import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { IBIZProTagService } from '../../service/ibizpro-tag/ibizpro-tag.service';
import IBIZProTagAuthService from '../../authservice/ibizpro-tag/ibizpro-tag-auth-service';

/**
 * 标签UI服务对象基类
 *
 * @export
 * @class IBIZProTagUIServiceBase
 */
export class IBIZProTagUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof IBIZProTagUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/IBIZProTag.json";

    /**
     * Creates an instance of  IBIZProTagUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IBIZProTagUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  IBIZProTagUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new IBIZProTagAuthService({context:this.context});
        this.dataService = new IBIZProTagService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  IBIZProTagUIServiceBase
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
     * @memberof  IBIZProTagUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set('EDITVIEW:','EDITVIEW');
        this.allViewFuncMap.set('MDATAVIEW:','MDATAVIEW');
    }

}