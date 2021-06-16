import { Environment } from '@/environments/environment';
import { Http } from '@/utils';
import { Util } from '@/utils';
import EntityService from '../entity-service';



/**
 * 用例步骤服务对象基类
 *
 * @export
 * @class CaseStepServiceBase
 * @extends {EntityServie}
 */
export default class CaseStepServiceBase extends EntityService {

    /**
     * Creates an instance of  CaseStepServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  CaseStepServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     *
     * @memberof CaseStepServiceBase
     */
    public initBasicData(){
        this.APPLYDEKEY ='casestep';
        this.APPDEKEY = 'id';
        this.APPDENAME = 'casesteps';
        this.APPDETEXT = 'expect';
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
     * @memberof CaseStepServiceBase
     */
    public async CheckKey(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case && context.casestep){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/${context.case}/casesteps/${context.casestep}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.story && context.case && context.casestep){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/cases/${context.case}/casesteps/${context.casestep}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.product && context.case && context.casestep){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/cases/${context.case}/casesteps/${context.casestep}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.case && context.casestep){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/cases/${context.case}/casesteps/${context.casestep}/checkkey`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/casesteps/${context.casestep}/checkkey`,data,isloading);
            return res;
    }

    /**
     * Create接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseStepServiceBase
     */
    public async Create(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case && true){
            let masterData:any = {};
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/${context.case}/casesteps`,data,isloading);
            
            return res;
        }
        if(context.story && context.case && true){
            let masterData:any = {};
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/cases/${context.case}/casesteps`,data,isloading);
            
            return res;
        }
        if(context.product && context.case && true){
            let masterData:any = {};
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/products/${context.product}/cases/${context.case}/casesteps`,data,isloading);
            
            return res;
        }
        if(context.case && true){
            let masterData:any = {};
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/cases/${context.case}/casesteps`,data,isloading);
            
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
        let res:any = await Http.getInstance().post(`/casesteps`,data,isloading);
        
        return res;
    }

    /**
     * Get接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseStepServiceBase
     */
    public async Get(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case && context.casestep){
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/${context.case}/casesteps/${context.casestep}`,isloading);
            
            return res;
        }
        if(context.story && context.case && context.casestep){
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/${context.case}/casesteps/${context.casestep}`,isloading);
            
            return res;
        }
        if(context.product && context.case && context.casestep){
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/${context.case}/casesteps/${context.casestep}`,isloading);
            
            return res;
        }
        if(context.case && context.casestep){
            let res:any = await Http.getInstance().get(`/cases/${context.case}/casesteps/${context.casestep}`,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().get(`/casesteps/${context.casestep}`,isloading);
            
            return res;
    }

    /**
     * GetDraft接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseStepServiceBase
     */
    public async GetDraft(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.casestep) delete tempData.casestep;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/${context.case}/casesteps/getdraft`,tempData,isloading);
            res.data.casestep = data.casestep;
            
            return res;
        }
        if(context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.casestep) delete tempData.casestep;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/${context.case}/casesteps/getdraft`,tempData,isloading);
            res.data.casestep = data.casestep;
            
            return res;
        }
        if(context.product && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.casestep) delete tempData.casestep;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/${context.case}/casesteps/getdraft`,tempData,isloading);
            res.data.casestep = data.casestep;
            
            return res;
        }
        if(context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.casestep) delete tempData.casestep;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/cases/${context.case}/casesteps/getdraft`,tempData,isloading);
            res.data.casestep = data.casestep;
            
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        if(tempData.casestep) delete tempData.casestep;
        if(tempData.id) delete tempData.id;
        let res:any = await  Http.getInstance().get(`/casesteps/getdraft`,tempData,isloading);
        res.data.casestep = data.casestep;
        
        return res;
    }

    /**
     * Remove接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseStepServiceBase
     */
    public async Remove(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case && context.casestep){
            let res:any = await Http.getInstance().delete(`/products/${context.product}/stories/${context.story}/cases/${context.case}/casesteps/${context.casestep}`,isloading);
            return res;
        }
        if(context.story && context.case && context.casestep){
            let res:any = await Http.getInstance().delete(`/stories/${context.story}/cases/${context.case}/casesteps/${context.casestep}`,isloading);
            return res;
        }
        if(context.product && context.case && context.casestep){
            let res:any = await Http.getInstance().delete(`/products/${context.product}/cases/${context.case}/casesteps/${context.casestep}`,isloading);
            return res;
        }
        if(context.case && context.casestep){
            let res:any = await Http.getInstance().delete(`/cases/${context.case}/casesteps/${context.casestep}`,isloading);
            return res;
        }
            let res:any = await Http.getInstance().delete(`/casesteps/${context.casestep}`,isloading);
            return res;
    }

    /**
     * Save接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseStepServiceBase
     */
    public async Save(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case && context.casestep){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/${context.case}/casesteps/${context.casestep}/save`,data,isloading);
            
            return res;
        }
        if(context.story && context.case && context.casestep){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/cases/${context.case}/casesteps/${context.casestep}/save`,data,isloading);
            
            return res;
        }
        if(context.product && context.case && context.casestep){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/cases/${context.case}/casesteps/${context.casestep}/save`,data,isloading);
            
            return res;
        }
        if(context.case && context.casestep){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/cases/${context.case}/casesteps/${context.casestep}/save`,data,isloading);
            
            return res;
        }
        let masterData:any = {};
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().post(`/casesteps/${context.casestep}/save`,data,isloading);
            
            return res;
    }

    /**
     * Update接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseStepServiceBase
     */
    public async Update(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case && context.casestep){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/stories/${context.story}/cases/${context.case}/casesteps/${context.casestep}`,data,isloading);
            
            return res;
        }
        if(context.story && context.case && context.casestep){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/stories/${context.story}/cases/${context.case}/casesteps/${context.casestep}`,data,isloading);
            
            return res;
        }
        if(context.product && context.case && context.casestep){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/cases/${context.case}/casesteps/${context.casestep}`,data,isloading);
            
            return res;
        }
        if(context.case && context.casestep){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/cases/${context.case}/casesteps/${context.casestep}`,data,isloading);
            
            return res;
        }
        let masterData:any = {};
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().put(`/casesteps/${context.casestep}`,data,isloading);
            
            return res;
    }

    /**
     * FetchCurTest接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseStepServiceBase
     */
    public async FetchCurTest(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/${context.case}/casesteps/fetchcurtest`,tempData,isloading);
            return res;
        }
        if(context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/${context.case}/casesteps/fetchcurtest`,tempData,isloading);
            return res;
        }
        if(context.product && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/${context.case}/casesteps/fetchcurtest`,tempData,isloading);
            return res;
        }
        if(context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/cases/${context.case}/casesteps/fetchcurtest`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/casesteps/fetchcurtest`,tempData,isloading);
        return res;
    }

    /**
     * searchCurTest接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseStepServiceBase
     */
    public async searchCurTest(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/${context.case}/casesteps/searchcurtest`,tempData,isloading);
        }
        if(context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/${context.case}/casesteps/searchcurtest`,tempData,isloading);
        }
        if(context.product && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/${context.case}/casesteps/searchcurtest`,tempData,isloading);
        }
        if(context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/cases/${context.case}/casesteps/searchcurtest`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/casesteps/searchcurtest`,tempData,isloading);
    }

    /**
     * FetchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseStepServiceBase
     */
    public async FetchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/${context.case}/casesteps/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/${context.case}/casesteps/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.product && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/${context.case}/casesteps/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/cases/${context.case}/casesteps/fetchdefault`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/casesteps/fetchdefault`,tempData,isloading);
        return res;
    }

    /**
     * searchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseStepServiceBase
     */
    public async searchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/${context.case}/casesteps/searchdefault`,tempData,isloading);
        }
        if(context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/${context.case}/casesteps/searchdefault`,tempData,isloading);
        }
        if(context.product && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/${context.case}/casesteps/searchdefault`,tempData,isloading);
        }
        if(context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/cases/${context.case}/casesteps/searchdefault`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/casesteps/searchdefault`,tempData,isloading);
    }

    /**
     * FetchDefault1接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseStepServiceBase
     */
    public async FetchDefault1(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/${context.case}/casesteps/fetchdefault1`,tempData,isloading);
            return res;
        }
        if(context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/${context.case}/casesteps/fetchdefault1`,tempData,isloading);
            return res;
        }
        if(context.product && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/${context.case}/casesteps/fetchdefault1`,tempData,isloading);
            return res;
        }
        if(context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/cases/${context.case}/casesteps/fetchdefault1`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/casesteps/fetchdefault1`,tempData,isloading);
        return res;
    }

    /**
     * searchDefault1接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseStepServiceBase
     */
    public async searchDefault1(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/${context.case}/casesteps/searchdefault1`,tempData,isloading);
        }
        if(context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/${context.case}/casesteps/searchdefault1`,tempData,isloading);
        }
        if(context.product && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/${context.case}/casesteps/searchdefault1`,tempData,isloading);
        }
        if(context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/cases/${context.case}/casesteps/searchdefault1`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/casesteps/searchdefault1`,tempData,isloading);
    }

    /**
     * FetchMob接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseStepServiceBase
     */
    public async FetchMob(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/${context.case}/casesteps/fetchmob`,tempData,isloading);
            return res;
        }
        if(context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/${context.case}/casesteps/fetchmob`,tempData,isloading);
            return res;
        }
        if(context.product && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/${context.case}/casesteps/fetchmob`,tempData,isloading);
            return res;
        }
        if(context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/cases/${context.case}/casesteps/fetchmob`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/casesteps/fetchmob`,tempData,isloading);
        return res;
    }

    /**
     * searchMob接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseStepServiceBase
     */
    public async searchMob(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/${context.case}/casesteps/searchmob`,tempData,isloading);
        }
        if(context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/${context.case}/casesteps/searchmob`,tempData,isloading);
        }
        if(context.product && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/${context.case}/casesteps/searchmob`,tempData,isloading);
        }
        if(context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/cases/${context.case}/casesteps/searchmob`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/casesteps/searchmob`,tempData,isloading);
    }

    /**
     * FetchVersion接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseStepServiceBase
     */
    public async FetchVersion(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/${context.case}/casesteps/fetchversion`,tempData,isloading);
            return res;
        }
        if(context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/${context.case}/casesteps/fetchversion`,tempData,isloading);
            return res;
        }
        if(context.product && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/${context.case}/casesteps/fetchversion`,tempData,isloading);
            return res;
        }
        if(context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/cases/${context.case}/casesteps/fetchversion`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/casesteps/fetchversion`,tempData,isloading);
        return res;
    }

    /**
     * searchVersion接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseStepServiceBase
     */
    public async searchVersion(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/${context.case}/casesteps/searchversion`,tempData,isloading);
        }
        if(context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/${context.case}/casesteps/searchversion`,tempData,isloading);
        }
        if(context.product && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/${context.case}/casesteps/searchversion`,tempData,isloading);
        }
        if(context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/cases/${context.case}/casesteps/searchversion`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/casesteps/searchversion`,tempData,isloading);
    }

    /**
     * FetchVersions接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseStepServiceBase
     */
    public async FetchVersions(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/${context.case}/casesteps/fetchversions`,tempData,isloading);
            return res;
        }
        if(context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/${context.case}/casesteps/fetchversions`,tempData,isloading);
            return res;
        }
        if(context.product && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/${context.case}/casesteps/fetchversions`,tempData,isloading);
            return res;
        }
        if(context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/cases/${context.case}/casesteps/fetchversions`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/casesteps/fetchversions`,tempData,isloading);
        return res;
    }

    /**
     * searchVersions接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseStepServiceBase
     */
    public async searchVersions(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/${context.case}/casesteps/searchversions`,tempData,isloading);
        }
        if(context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/${context.case}/casesteps/searchversions`,tempData,isloading);
        }
        if(context.product && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/${context.case}/casesteps/searchversions`,tempData,isloading);
        }
        if(context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/cases/${context.case}/casesteps/searchversions`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/casesteps/searchversions`,tempData,isloading);
    }

    /**
     * FetchTempCurTest接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseStepServiceBase
     */
    public async FetchTempCurTest(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseStepServiceBase
     */
    public async FetchTempDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempDefault1接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseStepServiceBase
     */
    public async FetchTempDefault1(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempMob接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseStepServiceBase
     */
    public async FetchTempMob(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempVersion接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseStepServiceBase
     */
    public async FetchTempVersion(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempVersions接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseStepServiceBase
     */
    public async FetchTempVersions(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * Select接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseStepServiceBase
     */
    public async Select(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case && context.casestep){
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/${context.case}/casesteps/${context.casestep}/select`,isloading);
            
            return res;
        }
        if(context.story && context.case && context.casestep){
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/${context.case}/casesteps/${context.casestep}/select`,isloading);
            
            return res;
        }
        if(context.product && context.case && context.casestep){
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/${context.case}/casesteps/${context.casestep}/select`,isloading);
            
            return res;
        }
        if(context.case && context.casestep){
            let res:any = await Http.getInstance().get(`/cases/${context.case}/casesteps/${context.casestep}/select`,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().get(`/casesteps/${context.casestep}/select`,isloading);
            
            return res;
    }
}