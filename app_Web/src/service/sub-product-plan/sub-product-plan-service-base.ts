import { Environment } from '@/environments/environment';
import { Http } from '@/utils';
import { Util } from '@/utils';
import EntityService from '../entity-service';



/**
 * 产品计划服务对象基类
 *
 * @export
 * @class SubProductPlanServiceBase
 * @extends {EntityServie}
 */
export default class SubProductPlanServiceBase extends EntityService {

    /**
     * Creates an instance of  SubProductPlanServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  SubProductPlanServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     *
     * @memberof SubProductPlanServiceBase
     */
    public initBasicData(){
        this.APPLYDEKEY ='subproductplan';
        this.APPDEKEY = 'id';
        this.APPDENAME = 'subproductplans';
        this.APPDETEXT = 'title';
        this.APPNAME = 'web';
        this.SYSTEMNAME = 'pms';
    }

// 实体接口

    /**
     * BatchUnlinkBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async BatchUnlinkBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/${context.subproductplan}/batchunlinkbug`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/${context.subproductplan}/batchunlinkbug`,data,isloading);
            
            return res;
        }
    }

    /**
     * BatchUnlinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async BatchUnlinkBugBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/batchunlinkbugbatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/batchunlinkbugbatch`,tempData,isloading);
        }
    }

    /**
     * BatchUnlinkStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async BatchUnlinkStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/${context.subproductplan}/batchunlinkstory`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/${context.subproductplan}/batchunlinkstory`,data,isloading);
            
            return res;
        }
    }

    /**
     * BatchUnlinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async BatchUnlinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/batchunlinkstorybatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/batchunlinkstorybatch`,tempData,isloading);
        }
    }

    /**
     * CheckKey接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async CheckKey(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/${context.subproductplan}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/${context.subproductplan}/checkkey`,data,isloading);
            
            return res;
        }
    }

    /**
     * Create接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async Create(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let masterData:any = {};
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans`,data,isloading);
            
            return res;
        }
        if(context.productplan && true){
            let masterData:any = {};
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans`,data,isloading);
            
            return res;
        }
    }

    /**
     * EeActivePlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async EeActivePlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/${context.subproductplan}/eeactiveplan`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/${context.subproductplan}/eeactiveplan`,data,isloading);
            
            return res;
        }
    }

    /**
     * EeActivePlanBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async EeActivePlanBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/eeactiveplanbatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/eeactiveplanbatch`,tempData,isloading);
        }
    }

    /**
     * EeCancelPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async EeCancelPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/${context.subproductplan}/eecancelplan`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/${context.subproductplan}/eecancelplan`,data,isloading);
            
            return res;
        }
    }

    /**
     * EeCancelPlanBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async EeCancelPlanBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/eecancelplanbatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/eecancelplanbatch`,tempData,isloading);
        }
    }

    /**
     * EeClosePlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async EeClosePlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/${context.subproductplan}/eecloseplan`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/${context.subproductplan}/eecloseplan`,data,isloading);
            
            return res;
        }
    }

    /**
     * EeClosePlanBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async EeClosePlanBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/eecloseplanbatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/eecloseplanbatch`,tempData,isloading);
        }
    }

    /**
     * EeFinishPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async EeFinishPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/${context.subproductplan}/eefinishplan`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/${context.subproductplan}/eefinishplan`,data,isloading);
            
            return res;
        }
    }

    /**
     * EeFinishPlanBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async EeFinishPlanBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/eefinishplanbatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/eefinishplanbatch`,tempData,isloading);
        }
    }

    /**
     * EePausePlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async EePausePlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/${context.subproductplan}/eepauseplan`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/${context.subproductplan}/eepauseplan`,data,isloading);
            
            return res;
        }
    }

    /**
     * EePausePlanBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async EePausePlanBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/eepauseplanbatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/eepauseplanbatch`,tempData,isloading);
        }
    }

    /**
     * EeRestartPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async EeRestartPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/${context.subproductplan}/eerestartplan`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/${context.subproductplan}/eerestartplan`,data,isloading);
            
            return res;
        }
    }

    /**
     * EeRestartPlanBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async EeRestartPlanBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/eerestartplanbatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/eerestartplanbatch`,tempData,isloading);
        }
    }

    /**
     * EeStartPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async EeStartPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/${context.subproductplan}/eestartplan`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/${context.subproductplan}/eestartplan`,data,isloading);
            
            return res;
        }
    }

    /**
     * EeStartPlanBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async EeStartPlanBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/eestartplanbatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/eestartplanbatch`,tempData,isloading);
        }
    }

    /**
     * Get接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async Get(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && context.subproductplan){
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/subproductplans/${context.subproductplan}`,isloading);
            
            return res;
        }
        if(context.productplan && context.subproductplan){
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/subproductplans/${context.subproductplan}`,isloading);
            
            return res;
        }
    }

    /**
     * GetDraft接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async GetDraft(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.subproductplan) delete tempData.subproductplan;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/subproductplans/getdraft`,tempData,isloading);
            res.data.subproductplan = data.subproductplan;
            
            return res;
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.subproductplan) delete tempData.subproductplan;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/subproductplans/getdraft`,tempData,isloading);
            res.data.subproductplan = data.subproductplan;
            
            return res;
        }
    }

    /**
     * GetOldPlanName接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async GetOldPlanName(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/${context.subproductplan}/getoldplanname`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/${context.subproductplan}/getoldplanname`,data,isloading);
            
            return res;
        }
    }

    /**
     * GetPlanEnd接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async GetPlanEnd(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan &&  true){
            return await Http.getInstance().post(`products/${context.product}/productplans/${context.productplan}/subproductplans/getplanend`,data,isloading);
        }
        if(context.productplan &&  true){
            return await Http.getInstance().post(`productplans/${context.productplan}/subproductplans/getplanend`,data,isloading);
        }
    }

    /**
     * ImportPlanTemplet接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async ImportPlanTemplet(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/${context.subproductplan}/importplantemplet`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/${context.subproductplan}/importplantemplet`,data,isloading);
            
            return res;
        }
    }

    /**
     * ImportPlanTempletBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async ImportPlanTempletBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/importplantempletbatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/importplantempletbatch`,tempData,isloading);
        }
    }

    /**
     * LinkBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async LinkBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/${context.subproductplan}/linkbug`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/${context.subproductplan}/linkbug`,data,isloading);
            
            return res;
        }
    }

    /**
     * LinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async LinkBugBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/linkbugbatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/linkbugbatch`,tempData,isloading);
        }
    }

    /**
     * LinkStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async LinkStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/${context.subproductplan}/linkstory`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/${context.subproductplan}/linkstory`,data,isloading);
            
            return res;
        }
    }

    /**
     * LinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async LinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/linkstorybatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/linkstorybatch`,tempData,isloading);
        }
    }

    /**
     * LinkTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async LinkTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/${context.subproductplan}/linktask`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/${context.subproductplan}/linktask`,data,isloading);
            
            return res;
        }
    }

    /**
     * LinkTaskBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async LinkTaskBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/linktaskbatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/linktaskbatch`,tempData,isloading);
        }
    }

    /**
     * MobProductPlanCounter接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async MobProductPlanCounter(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/productplans/${context.productplan}/subproductplans/${context.subproductplan}/mobproductplancounter`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/productplans/${context.productplan}/subproductplans/${context.subproductplan}/mobproductplancounter`,data,isloading);
            
            return res;
        }
    }

    /**
     * Remove接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async Remove(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && context.subproductplan){
            let res:any = await Http.getInstance().delete(`/products/${context.product}/productplans/${context.productplan}/subproductplans/${context.subproductplan}`,isloading);
            return res;
        }
        if(context.productplan && context.subproductplan){
            let res:any = await Http.getInstance().delete(`/productplans/${context.productplan}/subproductplans/${context.subproductplan}`,isloading);
            return res;
        }
    }

    /**
     * Save接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async Save(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/${context.subproductplan}/save`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/${context.subproductplan}/save`,data,isloading);
            
            return res;
        }
    }

    /**
     * UnlinkBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async UnlinkBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/${context.subproductplan}/unlinkbug`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/${context.subproductplan}/unlinkbug`,data,isloading);
            
            return res;
        }
    }

    /**
     * UnlinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async UnlinkBugBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/unlinkbugbatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/unlinkbugbatch`,tempData,isloading);
        }
    }

    /**
     * UnlinkStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async UnlinkStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/${context.subproductplan}/unlinkstory`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/${context.subproductplan}/unlinkstory`,data,isloading);
            
            return res;
        }
    }

    /**
     * UnlinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async UnlinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/unlinkstorybatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/unlinkstorybatch`,tempData,isloading);
        }
    }

    /**
     * Update接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async Update(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/productplans/${context.productplan}/subproductplans/${context.subproductplan}`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.subproductplan){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/productplans/${context.productplan}/subproductplans/${context.subproductplan}`,data,isloading);
            
            return res;
        }
    }

    /**
     * FetchChildPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async FetchChildPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/subproductplans/fetchchildplan`,tempData,isloading);
            return res;
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/subproductplans/fetchchildplan`,tempData,isloading);
            return res;
        }
    }

    /**
     * searchChildPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async searchChildPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/searchchildplan`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/searchchildplan`,tempData,isloading);
        }
    }

    /**
     * FetchCurProductPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async FetchCurProductPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/subproductplans/fetchcurproductplan`,tempData,isloading);
            return res;
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/subproductplans/fetchcurproductplan`,tempData,isloading);
            return res;
        }
    }

    /**
     * searchCurProductPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async searchCurProductPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/searchcurproductplan`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/searchcurproductplan`,tempData,isloading);
        }
    }

    /**
     * FetchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async FetchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/subproductplans/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/subproductplans/fetchdefault`,tempData,isloading);
            return res;
        }
    }

    /**
     * searchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async searchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/searchdefault`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/searchdefault`,tempData,isloading);
        }
    }

    /**
     * FetchDefaultParent接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async FetchDefaultParent(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/fetchdefaultparent`,tempData,isloading);
            return res;
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/fetchdefaultparent`,tempData,isloading);
            return res;
        }
    }

    /**
     * searchDefaultParent接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async searchDefaultParent(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/searchdefaultparent`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/searchdefaultparent`,tempData,isloading);
        }
    }

    /**
     * FetchPlanCodeList接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async FetchPlanCodeList(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/subproductplans/fetchplancodelist`,tempData,isloading);
            return res;
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/subproductplans/fetchplancodelist`,tempData,isloading);
            return res;
        }
    }

    /**
     * searchPlanCodeList接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async searchPlanCodeList(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/searchplancodelist`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/searchplancodelist`,tempData,isloading);
        }
    }

    /**
     * FetchPlanTasks接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async FetchPlanTasks(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/subproductplans/fetchplantasks`,tempData,isloading);
            return res;
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/subproductplans/fetchplantasks`,tempData,isloading);
            return res;
        }
    }

    /**
     * searchPlanTasks接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async searchPlanTasks(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/searchplantasks`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/searchplantasks`,tempData,isloading);
        }
    }

    /**
     * FetchProjectApp接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async FetchProjectApp(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/subproductplans/fetchprojectapp`,tempData,isloading);
            return res;
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/subproductplans/fetchprojectapp`,tempData,isloading);
            return res;
        }
    }

    /**
     * searchProjectApp接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async searchProjectApp(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/searchprojectapp`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/searchprojectapp`,tempData,isloading);
        }
    }

    /**
     * FetchProjectPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async FetchProjectPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/subproductplans/fetchprojectplan`,tempData,isloading);
            return res;
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/subproductplans/fetchprojectplan`,tempData,isloading);
            return res;
        }
    }

    /**
     * searchProjectPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async searchProjectPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/searchprojectplan`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/searchprojectplan`,tempData,isloading);
        }
    }

    /**
     * FetchRootPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async FetchRootPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/subproductplans/fetchrootplan`,tempData,isloading);
            return res;
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/subproductplans/fetchrootplan`,tempData,isloading);
            return res;
        }
    }

    /**
     * searchRootPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async searchRootPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/searchrootplan`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/searchrootplan`,tempData,isloading);
        }
    }

    /**
     * FetchTaskPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async FetchTaskPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/subproductplans/fetchtaskplan`,tempData,isloading);
            return res;
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/subproductplans/fetchtaskplan`,tempData,isloading);
            return res;
        }
    }

    /**
     * searchTaskPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async searchTaskPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/subproductplans/searchtaskplan`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/subproductplans/searchtaskplan`,tempData,isloading);
        }
    }

    /**
     * Select接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubProductPlanServiceBase
     */
    public async Select(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan && context.subproductplan){
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/subproductplans/${context.subproductplan}/select`,isloading);
            
            return res;
        }
        if(context.productplan && context.subproductplan){
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/subproductplans/${context.subproductplan}/select`,isloading);
            
            return res;
        }
    }
}