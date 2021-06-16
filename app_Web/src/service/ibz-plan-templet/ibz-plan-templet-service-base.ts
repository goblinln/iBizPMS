import { Environment } from '@/environments/environment';
import { Http } from '@/utils';
import { Util } from '@/utils';
import EntityService from '../entity-service';



/**
 * 计划模板服务对象基类
 *
 * @export
 * @class IbzPlanTempletServiceBase
 * @extends {EntityServie}
 */
export default class IbzPlanTempletServiceBase extends EntityService {

    /**
     * Creates an instance of  IbzPlanTempletServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzPlanTempletServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     *
     * @memberof IbzPlanTempletServiceBase
     */
    public initBasicData(){
        this.APPLYDEKEY ='ibzplantemplet';
        this.APPDEKEY = 'ibzplantempletid';
        this.APPDENAME = 'ibzplantemplets';
        this.APPDETEXT = 'ibzplantempletname';
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
     * @memberof IbzPlanTempletServiceBase
     */
    public async CheckKey(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().post(`/ibzplantemplets/${context.ibzplantemplet}/checkkey`,data,isloading);
            return res;
    }

    /**
     * Create接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzPlanTempletServiceBase
     */
    public async Create(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let masterData:any = {};
        let plantempletdetailsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_plantempletdetails'),'undefined')){
            plantempletdetailsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_plantempletdetails') as any);
            if(plantempletdetailsData && plantempletdetailsData.length && plantempletdetailsData.length > 0 && Environment.isStudioSystem === false){
                plantempletdetailsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.ibzplantempletdetailid = null;
                            if(item.hasOwnProperty('ibzplantempletid') && item.ibzplantempletid) delete item.ibzplantempletid;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.plantempletdetails = plantempletdetailsData;
        Object.assign(data,masterData);
        if(!data.srffrontuf || data.srffrontuf !== "1"){
            data[this.APPDEKEY] = null;
        }
        if(data.srffrontuf){
            delete data.srffrontuf;
        }
        let tempContext:any = JSON.parse(JSON.stringify(context));
        let res:any = await Http.getInstance().post(`/ibzplantemplets`,data,isloading);
        this.tempStorage.setItem(tempContext.srfsessionkey+'_plantempletdetails',JSON.stringify(res.data.plantempletdetails?res.data.plantempletdetails:[]));
        
        return res;
    }

    /**
     * Get接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzPlanTempletServiceBase
     */
    public async Get(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().get(`/ibzplantemplets/${context.ibzplantemplet}`,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_plantempletdetails',JSON.stringify(res.data.plantempletdetails?res.data.plantempletdetails:[]));

            return res;
    }

    /**
     * GetDraft接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzPlanTempletServiceBase
     */
    public async GetDraft(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        if(tempData.ibzplantemplet) delete tempData.ibzplantemplet;
        if(tempData.ibzplantempletid) delete tempData.ibzplantempletid;
        let res:any = await  Http.getInstance().get(`/ibzplantemplets/getdraft`,tempData,isloading);
        res.data.ibzplantemplet = data.ibzplantemplet;
                    this.tempStorage.setItem(context.srfsessionkey+'_plantempletdetails',JSON.stringify(res.data.plantempletdetails?res.data.plantempletdetails:[]));

        return res;
    }

    /**
     * GetPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzPlanTempletServiceBase
     */
    public async GetPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().get(`/ibzplantemplets/${context.ibzplantemplet}/getplan`,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_plantempletdetails',JSON.stringify(res.data.plantempletdetails?res.data.plantempletdetails:[]));

            return res;
    }

    /**
     * GetPlanBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzPlanTempletServiceBase
     */
    public async GetPlanBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/ibzplantemplets/getplanbatch`,tempData,isloading);
    }

    /**
     * Remove接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzPlanTempletServiceBase
     */
    public async Remove(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().delete(`/ibzplantemplets/${context.ibzplantemplet}`,isloading);
            return res;
    }

    /**
     * Save接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzPlanTempletServiceBase
     */
    public async Save(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let masterData:any = {};
        let plantempletdetailsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_plantempletdetails'),'undefined')){
            plantempletdetailsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_plantempletdetails') as any);
            if(plantempletdetailsData && plantempletdetailsData.length && plantempletdetailsData.length > 0 && Environment.isStudioSystem === false){
                plantempletdetailsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.ibzplantempletdetailid = null;
                            if(item.hasOwnProperty('ibzplantempletid') && item.ibzplantempletid) delete item.ibzplantempletid;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.plantempletdetails = plantempletdetailsData;
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().post(`/ibzplantemplets/${context.ibzplantemplet}/save`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_plantempletdetails',JSON.stringify(res.data.plantempletdetails?res.data.plantempletdetails:[]));

            return res;
    }

    /**
     * Update接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzPlanTempletServiceBase
     */
    public async Update(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let masterData:any = {};
        let plantempletdetailsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_plantempletdetails'),'undefined')){
            plantempletdetailsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_plantempletdetails') as any);
            if(plantempletdetailsData && plantempletdetailsData.length && plantempletdetailsData.length > 0 && Environment.isStudioSystem === false){
                plantempletdetailsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.ibzplantempletdetailid = null;
                            if(item.hasOwnProperty('ibzplantempletid') && item.ibzplantempletid) delete item.ibzplantempletid;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.plantempletdetails = plantempletdetailsData;
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().put(`/ibzplantemplets/${context.ibzplantemplet}`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_plantempletdetails',JSON.stringify(res.data.plantempletdetails?res.data.plantempletdetails:[]));

            return res;
    }

    /**
     * FetchCurUserTemplet接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzPlanTempletServiceBase
     */
    public async FetchCurUserTemplet(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/ibzplantemplets/fetchcurusertemplet`,tempData,isloading);
        return res;
    }

    /**
     * searchCurUserTemplet接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzPlanTempletServiceBase
     */
    public async searchCurUserTemplet(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/ibzplantemplets/searchcurusertemplet`,tempData,isloading);
    }

    /**
     * FetchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzPlanTempletServiceBase
     */
    public async FetchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/ibzplantemplets/fetchdefault`,tempData,isloading);
        return res;
    }

    /**
     * searchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzPlanTempletServiceBase
     */
    public async searchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/ibzplantemplets/searchdefault`,tempData,isloading);
    }

    /**
     * FetchTempCurUserTemplet接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzPlanTempletServiceBase
     */
    public async FetchTempCurUserTemplet(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzPlanTempletServiceBase
     */
    public async FetchTempDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * Select接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzPlanTempletServiceBase
     */
    public async Select(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().get(`/ibzplantemplets/${context.ibzplantemplet}/select`,isloading);
            
            return res;
    }
}