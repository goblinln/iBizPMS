import { UtilServiceBase } from 'ibiz-core';
import { DynaFilterService } from 'ibiz-service';

/**
 * 移动端动态搜索栏功能服务对象基类
 *
 * @export
 * @class DYNAFILTERUtilServiceBase
 */
export class DYNAFILTERUtilServiceBase extends UtilServiceBase {

    /**
     * 存储数据服务
     * 
     * @memberof  DYNAFILTERUtilServiceBase
     */ 
    public stoageDataService: DynaFilterService = new DynaFilterService();

    /**
     * 获取数据行为
     * 
     * @memberof  DYNAFILTERUtilServiceBase
     */ 
    public loadAction: string = "Get";

    /**
     * 建立数据行为
     * 
     * @memberof  DYNAFILTERUtilServiceBase
     */ 
    public createAction: string = "Create";

    /**
     * 更新数据行为
     * 
     * @memberof  DYNAFILTERUtilServiceBase
     */ 
    public updateAction: string = "Update";

    /**
     * 删除数据行为
     * 
     * @memberof  DYNAFILTERUtilServiceBase
     */ 
    public removeAction: string = "Remove";

    /**
     * Creates an instance of  DYNAFILTERUtilServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  DYNAFILTERUtilServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
    * 初始化基础参数
    *
    * @memberof DYNAFILTERUtilServiceBase
    */
   public initBasicParam(){
        
        
        
        
        this.stoageEntityName ="dynafilter";
        this.stoageEntityKey ="dynafilterid";
   }

    /**
     * 获取模型数据
     * 
     * @param context 应用上下文 
     * @param data 传入模型数据
     * @param isloading 是否加载
     * @memberof  DYNAFILTERUtilServiceBase
     */ 
    public loadModelData(context: any = {},data: any = {}, isloading?: boolean): Promise<any>{
        return new Promise((resolve: any, reject: any) => {
            //从接口加载
            if(data && data.utilServiceName){
                const requestParam:any ={configType:data.utilServiceName,targetType:data.modelid};
                this.stoageDataService.getDynaModel(context,requestParam).then((res:any) =>{
                    if(res && res.status === 200){
                        const data: any = res.data;
                        resolve({status:200,data:(data && data.model)?data.model:[]});
                    }
                }).catch((error:any) =>{
                    reject(error);
                })
            }else{
                let dataStr = window.localStorage.getItem(data.modelid);
                if(dataStr) {
                    const data: any = JSON.parse(dataStr);
                    resolve({status:200,data:data.model});
                } else {
                    resolve({status:200,data:[]});
                }
            }
        });
    }

    /**
     * 保存模型数据
     * 
     * @param context 应用上下文 
     * @param action 操作行为 Update|Create
     * @param data 传入模型数据
     * @param isloading 是否加载
     * @memberof  DYNAFILTERUtilServiceBase
     */ 
    public saveModelData(context: any = {},action:string,data: any = {}, isloading?: boolean):Promise<any>{
        return new Promise((resolve: any, reject: any) => {
            // 存储到数据库
            if(data && data.utilServiceName){
                const requestParam:any ={configType:data.utilServiceName,targetType:data.modelid,model:data.model};
                this.stoageDataService.setDynaModel(context,requestParam).then((res:any) =>{
                    if(res && res.status === 200){
                        resolve({status:200,data:data.model});
                    }
                }).catch((error:any) =>{
                    reject(error);
                })
            }else{
                window.localStorage.setItem(data.modelid, JSON.stringify(data));
                resolve({status:200,data:data.model});
            }
        });   
    }


}