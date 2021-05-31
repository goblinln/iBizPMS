import { BranchService } from "../service";
/**
 * 代码表--产品平台（动态）
 *
 * @export
 * @class ProductBranch
 */
export default class ProductBranch {

    /**
     * 是否启用缓存
     *
     * @type boolean
     * @memberof ProductBranch
     */
    public isEnableCache:boolean = false;

    /**
     * 过期时间
     *
     * @type any
     * @memberof ProductBranch
     */
    public static expirationTime:any;

    /**
     * 预定义类型
     *
     * @type string
     * @memberof ProductBranch
     */
    public predefinedType:string ='';

    /**
     * 缓存超长时长
     *
     * @type any
     * @memberof ProductBranch
     */
    public cacheTimeout:any = -1;

    /**
     * 代码表模型对象
     *
     * @type any
     * @memberof ProductBranch
     */
    public codelistModel:any = {
        codelistid:"ProductBranch"
    };

    /**
     * 获取过期时间
     *
     * @type any
     * @memberof ProductBranch
     */
    public getExpirationTime(){
        return ProductBranch.expirationTime;
    }

    /**
     * 设置过期时间
     *
     * @type any
     * @memberof ProductBranch
     */
    public setExpirationTime(value:any){
        ProductBranch.expirationTime = value; 
    }

    /**
     * 自定义参数集合
     *
     * @type any
     * @memberof ProductBranch
     */
    public userParamNames:any ={
    }

    /**
     * 查询参数集合
     *
     * @type any
     * @memberof ProductBranch
     */
    public queryParamNames:any ={
    }

    /**
     * 产品的分支和平台信息应用实体服务对象
     *
     * @type {BranchService}
     * @memberof ProductBranch
     */
    public branchService: BranchService = new BranchService();


    /**
     * 处理数据
     *
     * @public
     * @param {any[]} items
     * @returns {any[]}
     * @memberof ProductBranch
     */
    public doItems(items: any[]): any[] {
        let _items: any[] = [];
        if(items && items instanceof Array && items.length >0){
            items.forEach((item: any) => {
                let itemdata:any = {};
                Object.assign(itemdata,{id:item.id});
                Object.assign(itemdata,{value:item.id});
                Object.assign(itemdata,{text:item.name});
                Object.assign(itemdata,{label:item.name});
                
                
                
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
     * @memberof ProductBranch
     */
    public getItems(context: any={}, data: any={}, isloading?: boolean): Promise<any> {
        return new Promise((resolve, reject) => {
            data = this.handleQueryParam(data);
            const promise: Promise<any> = this.branchService.FetchCurProduct(context, data);
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
     * @memberof ProductBranch
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
