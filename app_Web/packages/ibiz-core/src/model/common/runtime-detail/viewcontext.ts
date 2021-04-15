/**
 * 视图上下文
 *
 * @class ViewContext
 */
export class ViewContext {

    /**
     * 运行时数据
     *
     * @type {Object}
     * @memberof ViewContext
     */
    public runtimeData:any;

    /**
     *  初始化 ViewContext 对象
     *
     * @memberof ViewContext
     */
    constructor(opts:any){
        this.runtimeData = opts;
    }

    /**
     * 传入应用上下文
     *
     * @return {Object}
     * @memberof ViewContext
     */
    get viewdata(){
        return this.runtimeData.viewdata;
    }

   /**
    * 传入视图参数
    *
    * @return {Object}
    * @memberof ViewContext
    */
    get viewparam(){
        return this.runtimeData.viewparam;
    }

    /**
     * 获取应用上下文
     *
     * @return {Object}
     * @memberof ViewContext
     */
    get context(){
        return this.runtimeData.context;
    }

   /**
    * 获取视图参数
    *
    * @return {Object}
    * @memberof ViewContext
    */
    get viewparams(){
        return this.runtimeData.viewparams;
    }

   /**
    * 获取模型数据
    *
    * @return {Object}
    * @memberof ViewContext
    */    
    get modeldata(){
        return this.runtimeData.modeldata;
    }

   /**
    * 动态模型服务
    *
    * @return {any}
    * @memberof ViewContext
    */       
    get dynamicmodelservice(){
        return this.runtimeData.dynamicmodelservice;
    }

   /**
    * 视图传递对象
    *
    * @return {any}
    * @memberof ViewContext
    */  
    get viewState(){
        return this.runtimeData.viewState;
    }

   /**
    * 视图唯一标识
    *
    * @return {string}
    * @memberof ViewContext
    */  
    get viewtag(){
        return this.runtimeData.viewtag;
    }

   /**
    * 获取视图
    *
    * @return {string}
    * @memberof ViewContext
    */  
   get view(){
    return this.runtimeData.view;
    }

   /**
    * 视图模式
    *
    * @return {*}
    * @memberof ViewContext
    */ 
    get viewDefaultUsage(){
        return this.runtimeData.viewDefaultUsage;
    }

   /**
    * 导航服务
    *
    * @return {*}
    * @memberof ViewContext
    */ 
    get navDataService(){
        return this.runtimeData.navDataService;
    }

   /**
    * 门户部件通知对象
    *
    * @return {*}
    * @memberof ViewContext
    */ 
    get portletState(){
        return this.runtimeData.portletState;
    }
}