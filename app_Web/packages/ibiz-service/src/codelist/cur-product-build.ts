import { ProjectBuildService } from "../service";
/**
 * 代码表--当前产品版本（动态）
 *
 * @export
 * @class CurProductBuild
 */
export default class CurProductBuild {

    /**
     * 是否启用缓存
     *
     * @type boolean
     * @memberof CurProductBuild
     */
    public isEnableCache:boolean = false;

    /**
     * 过期时间
     *
     * @type any
     * @memberof CurProductBuild
     */
    public static expirationTime:any;

    /**
     * 预定义类型
     *
     * @type string
     * @memberof CurProductBuild
     */
    public predefinedType:string ='';

    /**
     * 缓存超长时长
     *
     * @type any
     * @memberof CurProductBuild
     */
    public cacheTimeout:any = -1;

    /**
     * 代码表模型对象
     *
     * @type any
     * @memberof CurProductBuild
     */
    public codelistModel:any = {
        codelistid:"CurProductBuild"
    };

    /**
     * 获取过期时间
     *
     * @type any
     * @memberof CurProductBuild
     */
    public getExpirationTime(){
        return CurProductBuild.expirationTime;
    }

    /**
     * 设置过期时间
     *
     * @type any
     * @memberof CurProductBuild
     */
    public setExpirationTime(value:any){
        CurProductBuild.expirationTime = value; 
    }

    /**
     * 自定义参数集合
     *
     * @type any
     * @memberof CurProductBuild
     */
    public userParamNames:any ={
    }

    /**
     * 查询参数集合
     *
     * @type any
     * @memberof CurProductBuild
     */
    public queryParamNames:any ={
        sort: 'id,desc'
    }

    /**
     * build应用实体服务对象
     *
     * @type {ProjectBuildService}
     * @memberof CurProductBuild
     */
    public projectbuildService: ProjectBuildService = new ProjectBuildService();


    /**
     * 处理数据
     *
     * @public
     * @param {any[]} items
     * @returns {any[]}
     * @memberof CurProductBuild
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
     * @memberof CurProductBuild
     */
    public getItems(context: any={}, data: any={}, isloading?: boolean): Promise<any> {
        return new Promise((resolve, reject) => {
            data = this.handleQueryParam(data);
            const promise: Promise<any> = this.projectbuildService.FetchDefault(context, data);
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
     * @memberof CurProductBuild
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
