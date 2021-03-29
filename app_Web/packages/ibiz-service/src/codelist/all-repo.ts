/**
 * 代码表--所有代码
 *
 * @export
 * @class AllRepo
 */
export default class AllRepo {

    /**
     * 是否启用缓存
     *
     * @type boolean
     * @memberof AllRepo
     */
    public isEnableCache:boolean = true;

    /**
     * 过期时间
     *
     * @type any
     * @memberof AllRepo
     */
    public static expirationTime:any;

    /**
     * 预定义类型
     *
     * @type string
     * @memberof AllRepo
     */
    public predefinedType:string ='';

    /**
     * 缓存超长时长
     *
     * @type any
     * @memberof AllRepo
     */
    public cacheTimeout:any = 600;

    /**
     * 代码表模型对象
     *
     * @type any
     * @memberof AllRepo
     */
    public codelistModel:any = {
        codelistid:"AllRepo"
    };

    /**
     * 获取过期时间
     *
     * @type any
     * @memberof AllRepo
     */
    public getExpirationTime(){
        return AllRepo.expirationTime;
    }

    /**
     * 设置过期时间
     *
     * @type any
     * @memberof AllRepo
     */
    public setExpirationTime(value:any){
        AllRepo.expirationTime = value; 
    }

    /**
     * 自定义参数集合
     *
     * @type any
     * @memberof AllRepo
     */
    public userParamNames:any ={
    }

    /**
     * 查询参数集合
     *
     * @type any
     * @memberof AllRepo
     */
    public queryParamNames:any ={
    }



    /**
     * 获取数据项
     *
     * @param {*} data
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AllRepo
     */
    public getItems(data: any={}, isloading?: boolean): Promise<any> {
        return Promise.reject([]);
    }

    /**
     * 处理查询参数
     * @param data 传入data
     * @memberof AllRepo
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
