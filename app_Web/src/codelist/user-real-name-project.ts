import UserService from '@service/user/user-service';
/**
 * 代码表--用户真实名称（项目团队成员）
 *
 * @export
 * @class UserRealNameProject
 */
export default class UserRealNameProject {

    /**
     * 是否启用缓存
     *
     * @type boolean
     * @memberof UserRealNameProject
     */
    public isEnableCache:boolean = false;

    /**
     * 过期时间
     *
     * @type any
     * @memberof UserRealNameProject
     */
    public expirationTime:any;

    /**
     * 缓存超长时长
     *
     * @type any
     * @memberof UserRealNameProject
     */
    public cacheTimeout:any = -1;

    /**
     * 代码表模型对象
     *
     * @type any
     * @memberof UserRealNameProject
     */
    public codelistModel:any = {
        codelistid:"UserRealNameProject"
    };

    /**
     * 自定义参数集合
     *
     * @type any
     * @memberof UserRealNameProject
     */
    public userParamNames:any ={
    }

    /**
     * 查询参数集合
     *
     * @type any
     * @memberof UserRealNameProject
     */
    public queryParamNames:any ={
    }

    /**
     * 用户应用实体服务对象
     *
     * @type {UserService}
     * @memberof UserRealNameProject
     */
    public userService: UserService = new UserService();


    /**
     * 处理数据
     *
     * @private
     * @param {any[]} items
     * @returns {any[]}
     * @memberof UserRealNameProject
     */
    private doItems(items: any[]): any[] {
        let _items: any[] = [];
        items.forEach((item: any) => {
            let itemdata:any = {};
            Object.assign(itemdata,{id:item.account});
            Object.assign(itemdata,{value:item.account});
            Object.assign(itemdata,{text:item.realname});
            
            _items.push(itemdata);
        });
        return _items;
    }

    /**
     * 获取数据项
     *
     * @param {*} context
     * @param {*} data
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof UserRealNameProject
     */
    public getItems(context: any={}, data: any={}, isloading?: boolean): Promise<any> {
        return new Promise((resolve, reject) => {
            data = this.handleQueryParam(data);
            const promise: Promise<any> = this.userService.FetchProjectTeamUser(context, data, isloading);
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
     * @memberof UserRealNameProject
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
