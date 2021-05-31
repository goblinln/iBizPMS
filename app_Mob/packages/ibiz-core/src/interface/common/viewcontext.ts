/**
 * 视图上下文
 *
 * @interface ViewContext
 */
export interface ViewContext {

    /**
     * 应用上下文
     *
     * @type {Object}
     * @memberof ViewContext
     */
    context: Object;

    /**
     * 视图参数
     *
     * @type {Object}
     * @memberof ViewContext
     */
    viewparam:Object;

    /**
     * 模型数据
     *
     * @type {Object}
     * @memberof ViewContext
     */    
    modeldata:Object;

    /**
     * 额外参数
     *
     * @type {any}
     * @memberof ViewContext
     */       
    otherParam:any;

}