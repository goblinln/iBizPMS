import { Environment } from '@/environments/environment';
import { Http } from '@/utils';
import { Util } from '@/utils';
import EntityService from '../entity-service';



/**
 * burn服务对象基类
 *
 * @export
 * @class BurnServiceBase
 * @extends {EntityServie}
 */
export default class BurnServiceBase extends EntityService {

    /**
     * Creates an instance of  BurnServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  BurnServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     *
     * @memberof BurnServiceBase
     */
    public initBasicData(){
        this.APPLYDEKEY ='burn';
        this.APPDEKEY = 'id';
        this.APPDENAME = 'burns';
        this.APPDETEXT = 'date';
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
     * @memberof BurnServiceBase
     */
    public async CheckKey(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.burn){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/burns/${context.burn}/checkkey`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/burns/${context.burn}/checkkey`,data,isloading);
            return res;
    }

    /**
     * ComputeBurn接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BurnServiceBase
     */
    public async ComputeBurn(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.burn){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/burns/${context.burn}/computeburn`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/burns/${context.burn}/computeburn`,data,isloading);
            return res;
    }

    /**
     * ComputeBurnBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BurnServiceBase
     */
    public async ComputeBurnBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/burns/computeburnbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/burns/computeburnbatch`,tempData,isloading);
    }

    /**
     * Create接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BurnServiceBase
     */
    public async Create(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let masterData:any = {};
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/projects/${context.project}/burns`,data,isloading);
            
            return res;
        }
        let masterData:any = {};
        Object.assign(data,masterData);
        if(!data.srffrontuf || data.srffrontuf !== "1"){
            data[this.APPDEKEY] = null;
        }
        if(data.srffrontuf){
            delete data.srffrontuf;
        }
        let tempContext:any = JSON.parse(JSON.stringify(context));
        let res:any = await Http.getInstance().post(`/burns`,data,isloading);
        
        return res;
    }

    /**
     * Get接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BurnServiceBase
     */
    public async Get(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.burn){
            let res:any = await Http.getInstance().get(`/projects/${context.project}/burns/${context.burn}`,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().get(`/burns/${context.burn}`,isloading);
            
            return res;
    }

    /**
     * GetDraft接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BurnServiceBase
     */
    public async GetDraft(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.burn) delete tempData.burn;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/projects/${context.project}/burns/getdraft`,tempData,isloading);
            res.data.burn = data.burn;
            
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        if(tempData.burn) delete tempData.burn;
        if(tempData.id) delete tempData.id;
        let res:any = await  Http.getInstance().get(`/burns/getdraft`,tempData,isloading);
        res.data.burn = data.burn;
        
        return res;
    }

    /**
     * Remove接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BurnServiceBase
     */
    public async Remove(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.burn){
            let res:any = await Http.getInstance().delete(`/projects/${context.project}/burns/${context.burn}`,isloading);
            return res;
        }
            let res:any = await Http.getInstance().delete(`/burns/${context.burn}`,isloading);
            return res;
    }

    /**
     * Save接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BurnServiceBase
     */
    public async Save(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.burn){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/burns/${context.burn}/save`,data,isloading);
            
            return res;
        }
        let masterData:any = {};
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().post(`/burns/${context.burn}/save`,data,isloading);
            
            return res;
    }

    /**
     * Update接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BurnServiceBase
     */
    public async Update(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.burn){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/burns/${context.burn}`,data,isloading);
            
            return res;
        }
        let masterData:any = {};
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().put(`/burns/${context.burn}`,data,isloading);
            
            return res;
    }

    /**
     * FetchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BurnServiceBase
     */
    public async FetchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/burns/fetchdefault`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/burns/fetchdefault`,tempData,isloading);
        return res;
    }

    /**
     * searchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BurnServiceBase
     */
    public async searchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/burns/searchdefault`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/burns/searchdefault`,tempData,isloading);
    }

    /**
     * FetchESTIMATEANDLEFT接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BurnServiceBase
     */
    public async FetchESTIMATEANDLEFT(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/burns/fetchestimateandleft`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/burns/fetchestimateandleft`,tempData,isloading);
        return res;
    }

    /**
     * searchESTIMATEANDLEFT接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BurnServiceBase
     */
    public async searchESTIMATEANDLEFT(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/burns/searchestimateandleft`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/burns/searchestimateandleft`,tempData,isloading);
    }

    /**
     * Select接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BurnServiceBase
     */
    public async Select(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.burn){
            let res:any = await Http.getInstance().get(`/projects/${context.project}/burns/${context.burn}/select`,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().get(`/burns/${context.burn}/select`,isloading);
            
            return res;
    }
}