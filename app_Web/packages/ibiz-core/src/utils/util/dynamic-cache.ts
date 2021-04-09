import { Util } from "./util";

/**
 * 动态模型缓存
 * 
 * @memberof DynamicCache
 */
export class DynamicCache {

    /**
     * 模型数据缓存存储对象
     *
     * @private
     * @type {Map<string,Object>}
     * @memberof DynamicCache
     */    
    private modelDataCache:Map<string,Object> = new Map();

    /**
     * 初始化DynamicCache
     * @param opts 额外参数
     * 
     * @memberof DynamicCache
     */
    public constructor(opts: any = {}) {}

    /**
     * 判断是否有键值为key的模型数据
     * 
     * @param key 数据键值
     * 
     * @memberof DynamicCache
     */   
    public has(key:string){
        return this.modelDataCache.has(key);
    }

    /**
     * 获取键值为key的模型数据
     * 
     * @param key 数据键值
     * 
     * @memberof DynamicCache
     */  
    public get(key:string){
        if(this.has(key)){
            return Util.deepCopy(this.modelDataCache.get(key));
        }else{
            return null;
        }
    }

    /**
     * 删除键值为key的模型数据
     * 
     * @param key 数据键值
     * 
     * @memberof DynamicCache
     */  
    public remove(key:string){
        return this.modelDataCache.delete(key);
    }

    /**
     * 添加键值为key，数据为data的模型数据
     * 
     * @param key 数据键值
     * 
     * @memberof DynamicCache
     */  
    public add(key:string,data:any){
        if(this.modelDataCache.size > 100){
            this.modelDataCache.clear();
        }
        this.modelDataCache.set(key, data);
    }


    
}