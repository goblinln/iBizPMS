/**
 * 代码表--项目产品计划（动态）
 *
 * @export
 * @class ProjectProductPlan
 */
export default class ProjectProductPlan {

    /**
     * 是否启用缓存
     *
     * @type boolean
     * @memberof ProjectProductPlan
     */
    public isEnableCache:boolean = false;

    /**
     * 过期时间
     *
     * @type any
     * @memberof ProjectProductPlan
     */
    public static expirationTime:any;

    /**
     * 预定义类型
     *
     * @type string
     * @memberof ProjectProductPlan
     */
    public predefinedType:string ='';

    /**
     * 缓存超长时长
     *
     * @type any
     * @memberof ProjectProductPlan
     */
    public cacheTimeout:any = -1;

    /**
     * 代码表模型对象
     *
     * @type any
     * @memberof ProjectProductPlan
     */
    public codelistModel:any = {
        codelistid:"ProjectProductPlan"
    };

    /**
     * 获取过期时间
     *
     * @type any
     * @memberof ProjectProductPlan
     */
    public getExpirationTime(){
        return ProjectProductPlan.expirationTime;
    }

    /**
     * 设置过期时间
     *
     * @type any
     * @memberof ProjectProductPlan
     */
    public setExpirationTime(value:any){
        ProjectProductPlan.expirationTime = value; 
    }

    /**
     * 自定义参数集合
     *
     * @type any
     * @memberof ProjectProductPlan
     */
    public userParamNames:any ={
    }

    /**
     * 查询参数集合
     *
     * @type any
     * @memberof ProjectProductPlan
     */
    public queryParamNames:any ={
    }



    /**
     * 获取数据项
     *
     * @param {*} data
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectProductPlan
     */
    public getItems(data: any={}, isloading?: boolean): Promise<any> {
        return Promise.reject([]);
    }

    /**
     * 处理查询参数
     * @param data 传入data
     * @memberof ProjectProductPlan
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
