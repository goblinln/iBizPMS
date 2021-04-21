import { AppServiceBase } from "../app-service/app-base.service";

/**
 * 功能服务基类
 *
 * @export
 * @class UtilServiceBase
 */
export class UtilServiceBase {

    /**
     * 应用存储对象
     *
     * @private
     * @type {any}
     * @memberof UtilServiceBase
     */
    private $store: any;

    /**
     * 模型标识属性
     * 
     * @memberof  UtilServiceBase
     */ 
    protected modelIdField: string = "";

    /**
     * 模型存储属性
     * 
     * @memberof  UtilServiceBase
     */ 
    protected modelField: string = "";

    /**
     * 应用标识属性
     * 
     * @memberof  UtilServiceBase
     */ 
    protected appIdField: string = "";

    /**
     * 用户标识属性
     * 
     * @memberof  UtilServiceBase
     */ 
    protected userIdField: string = "";

    /**
     * 存储实体Name
     * 
     * @memberof  UtilServiceBase
     */ 
    protected stoageEntityName:string ="";

    /**
     * 存储实体Id
     * 
     * @memberof  UtilServiceBase
     */ 
    protected stoageEntityKey:string ="";

    /**
     * Creates an instance of UtilServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof UtilServiceBase
     */
    constructor(opts: any = {}) {
        this.$store = AppServiceBase.getInstance().getAppStore();
        this.initBasicParam();
    }

    /**
     * 获取应用存储对象
     *
     * @returns {(any | null)}
     * @memberof UtilServiceBase
     */
    public getStore(){
        return this.$store;
    }

    /**
     * 初始化基础参数
     *
     * @returns {(any | null)}
     * @memberof UtilServiceBase
     */
    public initBasicParam(){
        console.log("UtilService初始化参数未实现");
    }

    /**
     * 处理请求参数
     * 
     * @param context 应用上下文 
     * @param data 传入模型数据
     * @param isloading 是否加载
     * @memberof  UtilServiceBase
     */    
    protected handlePreParam(context:any,data:any ={}){
        let tempContext:Object = {};
        let tempData:Object = {};
        if(context.modelid){
            Object.defineProperty(tempContext,this.modelIdField,{
                value: context.modelid,
                writable: true,
                enumerable: true,
                configurable: true
            });
            Object.defineProperty(tempContext,this.stoageEntityName,{
                value: context.modelid,
                writable: true,
                enumerable: true,
                configurable: true
            });
            Object.defineProperty(tempData,this.modelIdField,{
                value: context.modelid,
                writable: true,
                enumerable: true,
                configurable: true
            });
            Object.defineProperty(tempData,this.stoageEntityKey,{
                value: context.modelid,
                writable: true,
                enumerable: true,
                configurable: true
            });
        }
        Object.defineProperty(tempData,this.modelField,{
            value: data,
            writable: true,
            enumerable: true,
            configurable: true
        });
        return {context:tempContext,data:tempData};
    }

    /**
     * 获取模型数据
     * 
     * @param context 应用上下文 
     * @param data 传入模型数据
     * @param isloading 是否加载
     * @memberof  UtilServiceBase
     */ 
    protected loadModelData(context: any = {},data: any = {}, isloading?: boolean):Promise<any>{
        return Promise.resolve(null);
    }

    /**
     * 保存模型数据
     * 
     * @param context 应用上下文 
     * @param data 传入模型数据
     * @param isloading 是否加载
     * @memberof  UtilServiceBase
     */ 
    protected saveModelData(context: any = {},data: any = {}, isloading?: boolean):Promise<any>{
        return Promise.resolve(null);
    }

   
}