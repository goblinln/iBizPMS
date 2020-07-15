import { Http,Util } from '@/utils';
import EntityService from '../entity-service';



/**
 * 系统日志服务对象基类
 *
 * @export
 * @class ActionServiceBase
 * @extends {EntityServie}
 */
export default class ActionServiceBase extends EntityService {

    /**
     * Creates an instance of  ActionServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  ActionServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     *
     * @memberof ActionServiceBase
     */
    public initBasicData(){
        this.APPLYDEKEY ='action';
        this.APPDEKEY = 'id';
        this.APPDENAME = 'actions';
        this.APPDETEXT = 'comment';
        this.APPNAME = 'web';
        this.SYSTEMNAME = 'pms';
    }

// 实体接口

    /**
     * Select接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ActionServiceBase
     */
    public async Select(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            return Http.getInstance().get(`/actions/${context.action}/select`,isloading);
    }

    /**
     * Create接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ActionServiceBase
     */
    public async Create(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let masterData:any = {};
        let historiesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_histories'),'undefined')){
            historiesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_histories') as any);
            if(historiesData && historiesData.length && historiesData.length > 0){
                historiesData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.histories = historiesData;
        Object.assign(data,masterData);
        if(!data.srffrontuf || data.srffrontuf !== "1"){
            data[this.APPDEKEY] = null;
        }
        if(data.srffrontuf){
            delete data.srffrontuf;
        }
        let tempContext:any = JSON.parse(JSON.stringify(context));
        let res:any = await Http.getInstance().post(`/actions`,data,isloading);
        this.tempStorage.setItem(tempContext.srfsessionkey+'_histories',JSON.stringify(res.data.histories));
        return res;
    }

    /**
     * Update接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ActionServiceBase
     */
    public async Update(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let masterData:any = {};
        let historiesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_histories'),'undefined')){
            historiesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_histories') as any);
            if(historiesData && historiesData.length && historiesData.length > 0){
                historiesData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.histories = historiesData;
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().put(`/actions/${context.action}`,data,isloading);
            this.tempStorage.setItem(context.srfsessionkey+'_histories',JSON.stringify(res.data.histories));
            return res;
    }

    /**
     * Remove接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ActionServiceBase
     */
    public async Remove(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            return Http.getInstance().delete(`/actions/${context.action}`,isloading);
    }

    /**
     * Get接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ActionServiceBase
     */
    public async Get(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().get(`/actions/${context.action}`,isloading);
            this.tempStorage.setItem(context.srfsessionkey+'_histories',JSON.stringify(res.data.histories));
            return res;
    }

    /**
     * GetDraft接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ActionServiceBase
     */
    public async GetDraft(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let res:any = await  Http.getInstance().get(`/actions/getdraft`,isloading);
        res.data.action = data.action;
            this.tempStorage.setItem(context.srfsessionkey+'_histories',JSON.stringify(res.data.histories));
        return res;
    }

    /**
     * CheckKey接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ActionServiceBase
     */
    public async CheckKey(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            return Http.getInstance().post(`/actions/${context.action}/checkkey`,data,isloading);
    }

    /**
     * Save接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ActionServiceBase
     */
    public async Save(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let masterData:any = {};
        let historiesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_histories'),'undefined')){
            historiesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_histories') as any);
            if(historiesData && historiesData.length && historiesData.length > 0){
                historiesData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.histories = historiesData;
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().post(`/actions/${context.action}/save`,data,isloading);
            this.tempStorage.setItem(context.srfsessionkey+'_histories',JSON.stringify(res.data.histories));
            return res;
    }

    /**
     * FetchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ActionServiceBase
     */
    public async FetchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return Http.getInstance().get(`/actions/fetchdefault`,tempData,isloading);
    }

    /**
     * FetchProductTrends接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ActionServiceBase
     */
    public async FetchProductTrends(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return Http.getInstance().get(`/actions/fetchproducttrends`,tempData,isloading);
    }

    /**
     * FetchProjectTrends接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ActionServiceBase
     */
    public async FetchProjectTrends(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return Http.getInstance().get(`/actions/fetchprojecttrends`,tempData,isloading);
    }

    /**
     * FetchType接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ActionServiceBase
     */
    public async FetchType(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return Http.getInstance().get(`/actions/fetchtype`,tempData,isloading);
    }
}