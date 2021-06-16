import { Environment } from '@/environments/environment';
import { Http } from '@/utils';
import { Util } from '@/utils';
import EntityService from '../entity-service';



/**
 * 用户年度工作内容统计服务对象基类
 *
 * @export
 * @class UserYearWorkStatsServiceBase
 * @extends {EntityServie}
 */
export default class UserYearWorkStatsServiceBase extends EntityService {

    /**
     * Creates an instance of  UserYearWorkStatsServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  UserYearWorkStatsServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     *
     * @memberof UserYearWorkStatsServiceBase
     */
    public initBasicData(){
        this.APPLYDEKEY ='useryearworkstats';
        this.APPDEKEY = 'id';
        this.APPDENAME = 'useryearworkstats';
        this.APPDETEXT = 'title';
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
     * @memberof UserYearWorkStatsServiceBase
     */
    public async CheckKey(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().post(`/useryearworkstats/${context.useryearworkstats}/checkkey`,data,isloading);
            return res;
    }

    /**
     * Create接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof UserYearWorkStatsServiceBase
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
        let res:any = await Http.getInstance().post(`/useryearworkstats`,data,isloading);
        
        return res;
    }

    /**
     * Get接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof UserYearWorkStatsServiceBase
     */
    public async Get(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().get(`/useryearworkstats/${context.useryearworkstats}`,isloading);
            
            return res;
    }

    /**
     * GetDevInfomation接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof UserYearWorkStatsServiceBase
     */
    public async GetDevInfomation(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().get(`/useryearworkstats/${context.useryearworkstats}/getdevinfomation`,isloading);
            
            return res;
    }

    /**
     * GetDraft接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof UserYearWorkStatsServiceBase
     */
    public async GetDraft(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        if(tempData.useryearworkstats) delete tempData.useryearworkstats;
        if(tempData.id) delete tempData.id;
        let res:any = await  Http.getInstance().get(`/useryearworkstats/getdraft`,tempData,isloading);
        res.data.useryearworkstats = data.useryearworkstats;
        
        return res;
    }

    /**
     * GetPoInfomation接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof UserYearWorkStatsServiceBase
     */
    public async GetPoInfomation(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().get(`/useryearworkstats/${context.useryearworkstats}/getpoinfomation`,isloading);
            
            return res;
    }

    /**
     * GetQaInfomation接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof UserYearWorkStatsServiceBase
     */
    public async GetQaInfomation(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().get(`/useryearworkstats/${context.useryearworkstats}/getqainfomation`,isloading);
            
            return res;
    }

    /**
     * GetUserYearAction接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof UserYearWorkStatsServiceBase
     */
    public async GetUserYearAction(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().put(`/useryearworkstats/${context.useryearworkstats}/getuseryearaction`,data,isloading);
            return res;
    }

    /**
     * GetUserYearActionBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof UserYearWorkStatsServiceBase
     */
    public async GetUserYearActionBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/useryearworkstats/getuseryearactionbatch`,tempData,isloading);
    }

    /**
     * Remove接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof UserYearWorkStatsServiceBase
     */
    public async Remove(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().delete(`/useryearworkstats/${context.useryearworkstats}`,isloading);
            return res;
    }

    /**
     * Save接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof UserYearWorkStatsServiceBase
     */
    public async Save(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let masterData:any = {};
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().post(`/useryearworkstats/${context.useryearworkstats}/save`,data,isloading);
            
            return res;
    }

    /**
     * Update接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof UserYearWorkStatsServiceBase
     */
    public async Update(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let masterData:any = {};
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().put(`/useryearworkstats/${context.useryearworkstats}`,data,isloading);
            
            return res;
    }

    /**
     * UpdateTitleByYear接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof UserYearWorkStatsServiceBase
     */
    public async UpdateTitleByYear(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().put(`/useryearworkstats/${context.useryearworkstats}/updatetitlebyyear`,data,isloading);
            return res;
    }

    /**
     * UpdateTitleByYearBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof UserYearWorkStatsServiceBase
     */
    public async UpdateTitleByYearBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/useryearworkstats/updatetitlebyyearbatch`,tempData,isloading);
    }

    /**
     * FetchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof UserYearWorkStatsServiceBase
     */
    public async FetchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/useryearworkstats/fetchdefault`,tempData,isloading);
        return res;
    }

    /**
     * searchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof UserYearWorkStatsServiceBase
     */
    public async searchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/useryearworkstats/searchdefault`,tempData,isloading);
    }

    /**
     * FetchMonthFinishTaskAndBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof UserYearWorkStatsServiceBase
     */
    public async FetchMonthFinishTaskAndBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/useryearworkstats/fetchmonthfinishtaskandbug`,tempData,isloading);
        return res;
    }

    /**
     * searchMonthFinishTaskAndBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof UserYearWorkStatsServiceBase
     */
    public async searchMonthFinishTaskAndBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/useryearworkstats/searchmonthfinishtaskandbug`,tempData,isloading);
    }

    /**
     * FetchMonthOpenedBugAndCase接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof UserYearWorkStatsServiceBase
     */
    public async FetchMonthOpenedBugAndCase(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/useryearworkstats/fetchmonthopenedbugandcase`,tempData,isloading);
        return res;
    }

    /**
     * searchMonthOpenedBugAndCase接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof UserYearWorkStatsServiceBase
     */
    public async searchMonthOpenedBugAndCase(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/useryearworkstats/searchmonthopenedbugandcase`,tempData,isloading);
    }

    /**
     * FetchMonthOpenedStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof UserYearWorkStatsServiceBase
     */
    public async FetchMonthOpenedStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/useryearworkstats/fetchmonthopenedstory`,tempData,isloading);
        return res;
    }

    /**
     * searchMonthOpenedStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof UserYearWorkStatsServiceBase
     */
    public async searchMonthOpenedStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/useryearworkstats/searchmonthopenedstory`,tempData,isloading);
    }

    /**
     * Select接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof UserYearWorkStatsServiceBase
     */
    public async Select(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().get(`/useryearworkstats/${context.useryearworkstats}/select`,isloading);
            
            return res;
    }
}