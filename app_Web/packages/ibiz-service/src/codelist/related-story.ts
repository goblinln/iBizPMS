import { ProductStoryService } from "../service";
/**
 * 代码表--关联需求（动态）
 *
 * @export
 * @class RelatedStory
 */
export default class RelatedStory {

    /**
     * 是否启用缓存
     *
     * @type boolean
     * @memberof RelatedStory
     */
    public isEnableCache:boolean = true;

    /**
     * 过期时间
     *
     * @type any
     * @memberof RelatedStory
     */
    public static expirationTime:any;

    /**
     * 预定义类型
     *
     * @type string
     * @memberof RelatedStory
     */
    public predefinedType:string ='';

    /**
     * 缓存超长时长
     *
     * @type any
     * @memberof RelatedStory
     */
    public cacheTimeout:any = 600;

    /**
     * 代码表模型对象
     *
     * @type any
     * @memberof RelatedStory
     */
    public codelistModel:any = {
        codelistid:"RelatedStory"
    };

    /**
     * 获取过期时间
     *
     * @type any
     * @memberof RelatedStory
     */
    public getExpirationTime(){
        return RelatedStory.expirationTime;
    }

    /**
     * 设置过期时间
     *
     * @type any
     * @memberof RelatedStory
     */
    public setExpirationTime(value:any){
        RelatedStory.expirationTime = value; 
    }

    /**
     * 自定义参数集合
     *
     * @type any
     * @memberof RelatedStory
     */
    public userParamNames:any ={
    }

    /**
     * 查询参数集合
     *
     * @type any
     * @memberof RelatedStory
     */
    public queryParamNames:any ={
    }

    /**
     * 需求应用实体服务对象
     *
     * @type {ProductStoryService}
     * @memberof RelatedStory
     */
    public productstoryService: ProductStoryService = new ProductStoryService();


    /**
     * 处理数据
     *
     * @public
     * @param {any[]} items
     * @returns {any[]}
     * @memberof RelatedStory
     */
    public doItems(items: any[]): any[] {
        let _items: any[] = [];
        if(items && items instanceof Array && items.length >0){
            items.forEach((item: any) => {
                let itemdata:any = {};
                Object.assign(itemdata,{id:item.id});
                Object.assign(itemdata,{value:item.id});
                Object.assign(itemdata,{text:item.title});
                Object.assign(itemdata,{label:item.title});
                
                
                
                _items.push(itemdata);
            });
        }
        return _items;
    }

    /**
     * 获取数据项
     *
     * @param {*} context
     * @param {*} data
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof RelatedStory
     */
    public getItems(context: any={}, data: any={}, isloading?: boolean): Promise<any> {
        return new Promise((resolve, reject) => {
            data = this.handleQueryParam(data);
            const promise: Promise<any> = this.productstoryService.FetchDefault(context, data);
            promise.then((response: any) => {
                if (response && response.status === 200) {
                    const data =  response.data;
                    resolve(this.doItems(data));
                } else {
                    resolve([]);
                }
            }).catch((response: any) => {
                console.error(response);
                reject(response);
            });
        });
    }

    /**
     * 处理查询参数
     * @param data 传入data
     * @memberof RelatedStory
     */
    public handleQueryParam(data:any){
        let tempData:any = data?JSON.parse(JSON.stringify(data)):{};
        if(this.userParamNames && Object.keys(this.userParamNames).length >0){
            Object.keys(this.userParamNames).forEach((name: string) => {
                if (!name) {
                    return;
                }
                let value: string | null = this.userParamNames[name];
                if (value && value.startsWith('%') && value.endsWith('%')) {
                    const key = value.substring(1, value.length - 1);
                    if (this.codelistModel && this.codelistModel.hasOwnProperty(key)) {
                        value = (this.codelistModel[key] !== null && this.codelistModel[key] !== undefined) ? this.codelistModel[key] : null;
                    } else {
                        value = null;
                    }
                }
                Object.assign(tempData, { [name]: value });
            });
        }
        Object.assign(tempData,{page: 0, size: 1000});
        if(this.queryParamNames && Object.keys(this.queryParamNames).length > 0){
            Object.assign(tempData,this.queryParamNames);
        }
        return tempData;
    }
}
