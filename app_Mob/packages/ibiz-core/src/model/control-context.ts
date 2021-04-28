/**
 * 部件上下文
 *
 * @class ControlContext
 */
export class ControlContext {

    /**
     * 运行时数据
     *
     * @type {Object}
     * @memberof ControlContext
     */
    public runtimeData:any;

    /**
     *  初始化 ControlContext 对象
     *
     * @memberof ControlContext
     */
    constructor(opts:any){
        this.runtimeData = opts;
    }

    /**
     * 获取应用上下文
     *
     * @return {Object}
     * @memberof ControlContext
     */
    get context(){
        return this.runtimeData.context;
    }

   /**
    * 获取视图参数
    *
    * @return {Object}
    * @memberof ControlContext
    */
    get viewparams(){
        return this.runtimeData.viewparam;
    }

   /**
    * 获取模型数据
    *
    * @return {Object}
    * @memberof ControlContext
    */    
    get modelData(){
        return this.runtimeData.modelData;
    }

   /**
    * 动态模型服务
    *
    * @return {any}
    * @memberof ControlContext
    */       
    get dynamicmodelservice(){
        return this.runtimeData.dynamicmodelservice;
    }

   /**
    * 视图传递对象
    *
    * @return {any}
    * @memberof ControlContext
    */  
    get viewState(){
        return this.runtimeData.viewState;
    }

   /**
    * 视图唯一标识
    *
    * @return {string}
    * @memberof ControlContext
    */  
    get viewtag(){
        return this.runtimeData.viewtag;
    }

}