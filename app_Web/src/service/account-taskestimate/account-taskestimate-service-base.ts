import { Environment } from '@/environments/environment';
import { Http } from '@/utils';
import { Util } from '@/utils';
import EntityService from '../entity-service';



/**
 * 用户工时统计服务对象基类
 *
 * @export
 * @class AccountTaskestimateServiceBase
 * @extends {EntityServie}
 */
export default class AccountTaskestimateServiceBase extends EntityService {

    /**
     * Creates an instance of  AccountTaskestimateServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  AccountTaskestimateServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     *
     * @memberof AccountTaskestimateServiceBase
     */
    public initBasicData(){
        this.APPLYDEKEY ='accounttaskestimate';
        this.APPDEKEY = 'id';
        this.APPDENAME = 'accounttaskestimates';
        this.APPDETEXT = '';
        this.APPNAME = 'web';
        this.SYSTEMNAME = 'pms';
    }

// 实体接口

    /**
     * CheckKey接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AccountTaskestimateServiceBase
     */
    public async CheckKey(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().post(`/accounttaskestimates/${context.accounttaskestimate}/checkkey`,data,isloading);
            return res;
    }

    /**
     * Create接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AccountTaskestimateServiceBase
     */
    public async Create(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let masterData:any = {};
        Object.assign(data,masterData);
        if(!data.srffrontuf || data.srffrontuf !== "1"){
            data[this.APPDEKEY] = null;
        }
        if(data.srffrontuf){
            delete data.srffrontuf;
        }
        let tempContext:any = JSON.parse(JSON.stringify(context));
        let res:any = await Http.getInstance().post(`/accounttaskestimates`,data,isloading);
        
        return res;
    }

    /**
     * Get接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AccountTaskestimateServiceBase
     */
    public async Get(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().get(`/accounttaskestimates/${context.accounttaskestimate}`,isloading);
            
            return res;
    }

    /**
     * GetDraft接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AccountTaskestimateServiceBase
     */
    public async GetDraft(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        if(tempData.accounttaskestimate) delete tempData.accounttaskestimate;
        if(tempData.id) delete tempData.id;
        let res:any = await  Http.getInstance().get(`/accounttaskestimates/getdraft`,tempData,isloading);
        res.data.accounttaskestimate = data.accounttaskestimate;
        
        return res;
    }

    /**
     * Remove接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AccountTaskestimateServiceBase
     */
    public async Remove(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().delete(`/accounttaskestimates/${context.accounttaskestimate}`,isloading);
            return res;
    }

    /**
     * Save接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AccountTaskestimateServiceBase
     */
    public async Save(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let masterData:any = {};
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().post(`/accounttaskestimates/${context.accounttaskestimate}/save`,data,isloading);
            
            return res;
    }

    /**
     * Update接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AccountTaskestimateServiceBase
     */
    public async Update(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let masterData:any = {};
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().put(`/accounttaskestimates/${context.accounttaskestimate}`,data,isloading);
            
            return res;
    }

    /**
     * FetchAllAccountEstimate接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AccountTaskestimateServiceBase
     */
    public async FetchAllAccountEstimate(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/accounttaskestimates/fetchallaccountestimate`,tempData,isloading);
        return res;
    }

    /**
     * searchAllAccountEstimate接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AccountTaskestimateServiceBase
     */
    public async searchAllAccountEstimate(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/accounttaskestimates/searchallaccountestimate`,tempData,isloading);
    }

    /**
     * FetchCurDaySum接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AccountTaskestimateServiceBase
     */
    public async FetchCurDaySum(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/accounttaskestimates/fetchcurdaysum`,tempData,isloading);
        return res;
    }

    /**
     * searchCurDaySum接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AccountTaskestimateServiceBase
     */
    public async searchCurDaySum(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/accounttaskestimates/searchcurdaysum`,tempData,isloading);
    }

    /**
     * FetchCurDaySunByProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AccountTaskestimateServiceBase
     */
    public async FetchCurDaySunByProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/accounttaskestimates/fetchcurdaysunbyproject`,tempData,isloading);
        return res;
    }

    /**
     * searchCurDaySunByProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AccountTaskestimateServiceBase
     */
    public async searchCurDaySunByProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/accounttaskestimates/searchcurdaysunbyproject`,tempData,isloading);
    }

    /**
     * FetchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AccountTaskestimateServiceBase
     */
    public async FetchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/accounttaskestimates/fetchdefault`,tempData,isloading);
        return res;
    }

    /**
     * searchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AccountTaskestimateServiceBase
     */
    public async searchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/accounttaskestimates/searchdefault`,tempData,isloading);
    }

    /**
     * FetchMyDaySum接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AccountTaskestimateServiceBase
     */
    public async FetchMyDaySum(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/accounttaskestimates/fetchmydaysum`,tempData,isloading);
        return res;
    }

    /**
     * searchMyDaySum接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AccountTaskestimateServiceBase
     */
    public async searchMyDaySum(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/accounttaskestimates/searchmydaysum`,tempData,isloading);
    }

    /**
     * Select接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AccountTaskestimateServiceBase
     */
    public async Select(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().get(`/accounttaskestimates/${context.accounttaskestimate}/select`,isloading);
            
            return res;
    }
}