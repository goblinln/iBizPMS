import { AppServiceBase, UIServiceBase } from 'ibiz-core';
import { AppLogicFactory } from 'ibiz-vue';
import { ProjectProductService } from '../../service/project-product/project-product.service';
import ProjectProductAuthService from '../../authservice/project-product/project-product-auth-service';

/**
 * 项目产品UI服务对象基类
 *
 * @export
 * @class ProjectProductUIServiceBase
 */
export class ProjectProductUIServiceBase extends UIServiceBase {

    /**
     * 应用实体动态模型文件路径
     *
     * @protected
     * @type {string}
     * @memberof ProjectProductUIServiceBase
     */
    protected dynaModelFilePath:string = "PSSYSAPPS/Web/PSAPPDATAENTITIES/ProjectProduct.json";

    /**
     * Creates an instance of  ProjectProductUIServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectProductUIServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof  ProjectProductUIServiceBase
     */
    protected initBasicData(){
        this.isEnableDEMainState = false;
        this.stateValue = 0;
        this.multiFormDEField = null;
        this.indexTypeDEField = null;
        this.stateField = "";
        this.mainStateFields = [];
        this.authService = new ProjectProductAuthService({context:this.context});
        this.dataService = new ProjectProductService();
    }

    /**
     * 初始化界面行为数据
     * 
     * @memberof  ProjectProductUIServiceBase
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
     * @memberof  ProjectProductUIServiceBase
     */  
    protected initViewFuncMap(){
        this.allViewFuncMap.set(':','');
        this.allViewFuncMap.set(':','');
    }

}