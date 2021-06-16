import { Environment } from '@/environments/environment';
import { Http } from '@/utils';
import { Util } from '@/utils';
import EntityService from '../entity-service';



/**
 * 产品计划服务对象基类
 *
 * @export
 * @class ProductPlanServiceBase
 * @extends {EntityServie}
 */
export default class ProductPlanServiceBase extends EntityService {

    /**
     * Creates an instance of  ProductPlanServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductPlanServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     *
     * @memberof ProductPlanServiceBase
     */
    public initBasicData(){
        this.APPLYDEKEY ='productplan';
        this.APPDEKEY = 'id';
        this.APPDENAME = 'productplans';
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
     * @memberof ProductPlanServiceBase
     */
    public async BatchUnlinkBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan){
            let masterData:any = {};
        let tasksData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_tasks'),'undefined')){
            tasksData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_tasks') as any);
            if(tasksData && tasksData.length && tasksData.length > 0 && Environment.isStudioSystem === false){
                tasksData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.tasks = tasksData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/batchunlinkbug`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/batchunlinkbug`,data,isloading);
            return res;
    }

    /**
     * BatchUnlinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async BatchUnlinkBugBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/batchunlinkbugbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/productplans/batchunlinkbugbatch`,tempData,isloading);
    }

    /**
     * BatchUnlinkStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async BatchUnlinkStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan){
            let masterData:any = {};
        let tasksData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_tasks'),'undefined')){
            tasksData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_tasks') as any);
            if(tasksData && tasksData.length && tasksData.length > 0 && Environment.isStudioSystem === false){
                tasksData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.tasks = tasksData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/batchunlinkstory`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/batchunlinkstory`,data,isloading);
            return res;
    }

    /**
     * BatchUnlinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async BatchUnlinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/batchunlinkstorybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/productplans/batchunlinkstorybatch`,tempData,isloading);
    }

    /**
     * CheckKey接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async CheckKey(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan){
            let masterData:any = {};
        let tasksData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_tasks'),'undefined')){
            tasksData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_tasks') as any);
            if(tasksData && tasksData.length && tasksData.length > 0 && Environment.isStudioSystem === false){
                tasksData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.tasks = tasksData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/checkkey`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/checkkey`,data,isloading);
            return res;
    }

    /**
     * Create接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async Create(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let masterData:any = {};
        let tasksData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_tasks'),'undefined')){
            tasksData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_tasks') as any);
            if(tasksData && tasksData.length && tasksData.length > 0 && Environment.isStudioSystem === false){
                tasksData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.tasks = tasksData;
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans`,data,isloading);
            this.tempStorage.setItem(tempContext.srfsessionkey+'_subproductplans',JSON.stringify(res.data.subproductplans?res.data.subproductplans:[]));
            this.tempStorage.setItem(tempContext.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));
            
            return res;
        }
        let masterData:any = {};
        let tasksData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_tasks'),'undefined')){
            tasksData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_tasks') as any);
            if(tasksData && tasksData.length && tasksData.length > 0 && Environment.isStudioSystem === false){
                tasksData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.tasks = tasksData;
        Object.assign(data,masterData);
        if(!data.srffrontuf || data.srffrontuf !== "1"){
            data[this.APPDEKEY] = null;
        }
        if(data.srffrontuf){
            delete data.srffrontuf;
        }
        let tempContext:any = JSON.parse(JSON.stringify(context));
        let res:any = await Http.getInstance().post(`/productplans`,data,isloading);
        this.tempStorage.setItem(tempContext.srfsessionkey+'_subproductplans',JSON.stringify(res.data.subproductplans?res.data.subproductplans:[]));
        this.tempStorage.setItem(tempContext.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));
        
        return res;
    }

    /**
     * EeActivePlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async EeActivePlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan){
            let masterData:any = {};
        let tasksData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_tasks'),'undefined')){
            tasksData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_tasks') as any);
            if(tasksData && tasksData.length && tasksData.length > 0 && Environment.isStudioSystem === false){
                tasksData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.tasks = tasksData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/eeactiveplan`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/eeactiveplan`,data,isloading);
            return res;
    }

    /**
     * EeActivePlanBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async EeActivePlanBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/eeactiveplanbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/productplans/eeactiveplanbatch`,tempData,isloading);
    }

    /**
     * EeCancelPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async EeCancelPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan){
            let masterData:any = {};
        let tasksData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_tasks'),'undefined')){
            tasksData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_tasks') as any);
            if(tasksData && tasksData.length && tasksData.length > 0 && Environment.isStudioSystem === false){
                tasksData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.tasks = tasksData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/eecancelplan`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/eecancelplan`,data,isloading);
            return res;
    }

    /**
     * EeCancelPlanBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async EeCancelPlanBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/eecancelplanbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/productplans/eecancelplanbatch`,tempData,isloading);
    }

    /**
     * EeClosePlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async EeClosePlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan){
            let masterData:any = {};
        let tasksData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_tasks'),'undefined')){
            tasksData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_tasks') as any);
            if(tasksData && tasksData.length && tasksData.length > 0 && Environment.isStudioSystem === false){
                tasksData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.tasks = tasksData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/eecloseplan`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/eecloseplan`,data,isloading);
            return res;
    }

    /**
     * EeClosePlanBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async EeClosePlanBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/eecloseplanbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/productplans/eecloseplanbatch`,tempData,isloading);
    }

    /**
     * EeFinishPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async EeFinishPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan){
            let masterData:any = {};
        let tasksData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_tasks'),'undefined')){
            tasksData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_tasks') as any);
            if(tasksData && tasksData.length && tasksData.length > 0 && Environment.isStudioSystem === false){
                tasksData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.tasks = tasksData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/eefinishplan`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/eefinishplan`,data,isloading);
            return res;
    }

    /**
     * EeFinishPlanBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async EeFinishPlanBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/eefinishplanbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/productplans/eefinishplanbatch`,tempData,isloading);
    }

    /**
     * EePausePlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async EePausePlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan){
            let masterData:any = {};
        let tasksData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_tasks'),'undefined')){
            tasksData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_tasks') as any);
            if(tasksData && tasksData.length && tasksData.length > 0 && Environment.isStudioSystem === false){
                tasksData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.tasks = tasksData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/eepauseplan`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/eepauseplan`,data,isloading);
            return res;
    }

    /**
     * EePausePlanBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async EePausePlanBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/eepauseplanbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/productplans/eepauseplanbatch`,tempData,isloading);
    }

    /**
     * EeRestartPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async EeRestartPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan){
            let masterData:any = {};
        let tasksData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_tasks'),'undefined')){
            tasksData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_tasks') as any);
            if(tasksData && tasksData.length && tasksData.length > 0 && Environment.isStudioSystem === false){
                tasksData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.tasks = tasksData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/eerestartplan`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/eerestartplan`,data,isloading);
            return res;
    }

    /**
     * EeRestartPlanBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async EeRestartPlanBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/eerestartplanbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/productplans/eerestartplanbatch`,tempData,isloading);
    }

    /**
     * EeStartPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async EeStartPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan){
            let masterData:any = {};
        let tasksData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_tasks'),'undefined')){
            tasksData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_tasks') as any);
            if(tasksData && tasksData.length && tasksData.length > 0 && Environment.isStudioSystem === false){
                tasksData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.tasks = tasksData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/eestartplan`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/eestartplan`,data,isloading);
            return res;
    }

    /**
     * EeStartPlanBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async EeStartPlanBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/eestartplanbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/productplans/eestartplanbatch`,tempData,isloading);
    }

    /**
     * Get接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async Get(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan){
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}`,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
        }
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}`,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
    }

    /**
     * GetDraft接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async GetDraft(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.productplan) delete tempData.productplan;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/getdraft`,tempData,isloading);
            res.data.productplan = data.productplan;
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        if(tempData.productplan) delete tempData.productplan;
        if(tempData.id) delete tempData.id;
        let res:any = await  Http.getInstance().get(`/productplans/getdraft`,tempData,isloading);
        res.data.productplan = data.productplan;
                    this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

        return res;
    }

    /**
     * GetOldPlanName接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async GetOldPlanName(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan){
            let masterData:any = {};
        let tasksData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_tasks'),'undefined')){
            tasksData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_tasks') as any);
            if(tasksData && tasksData.length && tasksData.length > 0 && Environment.isStudioSystem === false){
                tasksData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.tasks = tasksData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/getoldplanname`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/getoldplanname`,data,isloading);
            return res;
    }

    /**
     * GetPlanEnd接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async GetPlanEnd(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * ImportPlanTemplet接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async ImportPlanTemplet(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan){
            let masterData:any = {};
        let tasksData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_tasks'),'undefined')){
            tasksData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_tasks') as any);
            if(tasksData && tasksData.length && tasksData.length > 0 && Environment.isStudioSystem === false){
                tasksData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.tasks = tasksData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/importplantemplet`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/importplantemplet`,data,isloading);
            return res;
    }

    /**
     * ImportPlanTempletBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async ImportPlanTempletBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/importplantempletbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/productplans/importplantempletbatch`,tempData,isloading);
    }

    /**
     * LinkBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async LinkBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan){
            let masterData:any = {};
        let tasksData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_tasks'),'undefined')){
            tasksData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_tasks') as any);
            if(tasksData && tasksData.length && tasksData.length > 0 && Environment.isStudioSystem === false){
                tasksData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.tasks = tasksData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/linkbug`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/linkbug`,data,isloading);
            return res;
    }

    /**
     * LinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async LinkBugBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/linkbugbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/productplans/linkbugbatch`,tempData,isloading);
    }

    /**
     * LinkStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async LinkStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan){
            let masterData:any = {};
        let tasksData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_tasks'),'undefined')){
            tasksData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_tasks') as any);
            if(tasksData && tasksData.length && tasksData.length > 0 && Environment.isStudioSystem === false){
                tasksData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.tasks = tasksData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/linkstory`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/linkstory`,data,isloading);
            return res;
    }

    /**
     * LinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async LinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/linkstorybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/productplans/linkstorybatch`,tempData,isloading);
    }

    /**
     * LinkTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async LinkTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan){
            let masterData:any = {};
        let tasksData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_tasks'),'undefined')){
            tasksData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_tasks') as any);
            if(tasksData && tasksData.length && tasksData.length > 0 && Environment.isStudioSystem === false){
                tasksData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.tasks = tasksData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/linktask`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/linktask`,data,isloading);
            return res;
    }

    /**
     * LinkTaskBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async LinkTaskBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/linktaskbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/productplans/linktaskbatch`,tempData,isloading);
    }

    /**
     * MobProductPlanCounter接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async MobProductPlanCounter(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan){
            let masterData:any = {};
        let tasksData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_tasks'),'undefined')){
            tasksData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_tasks') as any);
            if(tasksData && tasksData.length && tasksData.length > 0 && Environment.isStudioSystem === false){
                tasksData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.tasks = tasksData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/productplans/${context.productplan}/mobproductplancounter`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
        }
            let res:any = await Http.getInstance().put(`/productplans/${context.productplan}/mobproductplancounter`,data,isloading);
            return res;
    }

    /**
     * Remove接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async Remove(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan){
            let res:any = await Http.getInstance().delete(`/products/${context.product}/productplans/${context.productplan}`,isloading);
            return res;
        }
            let res:any = await Http.getInstance().delete(`/productplans/${context.productplan}`,isloading);
            return res;
    }

    /**
     * Save接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async Save(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan){
            let masterData:any = {};
        let tasksData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_tasks'),'undefined')){
            tasksData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_tasks') as any);
            if(tasksData && tasksData.length && tasksData.length > 0 && Environment.isStudioSystem === false){
                tasksData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.tasks = tasksData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/save`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
        }
        let masterData:any = {};
        let tasksData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_tasks'),'undefined')){
            tasksData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_tasks') as any);
            if(tasksData && tasksData.length && tasksData.length > 0 && Environment.isStudioSystem === false){
                tasksData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.tasks = tasksData;
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().post(`/productplans/${context.productplan}/save`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
    }

    /**
     * UnlinkBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async UnlinkBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan){
            let masterData:any = {};
        let tasksData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_tasks'),'undefined')){
            tasksData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_tasks') as any);
            if(tasksData && tasksData.length && tasksData.length > 0 && Environment.isStudioSystem === false){
                tasksData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.tasks = tasksData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/unlinkbug`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/unlinkbug`,data,isloading);
            return res;
    }

    /**
     * UnlinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async UnlinkBugBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/unlinkbugbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/productplans/unlinkbugbatch`,tempData,isloading);
    }

    /**
     * UnlinkStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async UnlinkStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan){
            let masterData:any = {};
        let tasksData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_tasks'),'undefined')){
            tasksData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_tasks') as any);
            if(tasksData && tasksData.length && tasksData.length > 0 && Environment.isStudioSystem === false){
                tasksData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.tasks = tasksData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/unlinkstory`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/unlinkstory`,data,isloading);
            return res;
    }

    /**
     * UnlinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async UnlinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/unlinkstorybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/productplans/unlinkstorybatch`,tempData,isloading);
    }

    /**
     * Update接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async Update(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan){
            let masterData:any = {};
        let tasksData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_tasks'),'undefined')){
            tasksData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_tasks') as any);
            if(tasksData && tasksData.length && tasksData.length > 0 && Environment.isStudioSystem === false){
                tasksData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.tasks = tasksData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/productplans/${context.productplan}`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
        }
        let masterData:any = {};
        let tasksData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_tasks'),'undefined')){
            tasksData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_tasks') as any);
            if(tasksData && tasksData.length && tasksData.length > 0 && Environment.isStudioSystem === false){
                tasksData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.tasks = tasksData;
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().put(`/productplans/${context.productplan}`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
    }

    /**
     * FetchChildPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async FetchChildPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/fetchchildplan`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/productplans/fetchchildplan`,tempData,isloading);
        return res;
    }

    /**
     * searchChildPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async searchChildPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/searchchildplan`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/productplans/searchchildplan`,tempData,isloading);
    }

    /**
     * FetchCurProductPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async FetchCurProductPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/fetchcurproductplan`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/productplans/fetchcurproductplan`,tempData,isloading);
        return res;
    }

    /**
     * searchCurProductPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async searchCurProductPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/searchcurproductplan`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/productplans/searchcurproductplan`,tempData,isloading);
    }

    /**
     * FetchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async FetchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/fetchdefault`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/productplans/fetchdefault`,tempData,isloading);
        return res;
    }

    /**
     * searchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async searchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/searchdefault`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/productplans/searchdefault`,tempData,isloading);
    }

    /**
     * FetchDefaultParent接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async FetchDefaultParent(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/fetchdefaultparent`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/productplans/fetchdefaultparent`,tempData,isloading);
        return res;
    }

    /**
     * searchDefaultParent接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async searchDefaultParent(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/searchdefaultparent`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/productplans/searchdefaultparent`,tempData,isloading);
    }

    /**
     * FetchPlanCodeList接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async FetchPlanCodeList(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/fetchplancodelist`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/productplans/fetchplancodelist`,tempData,isloading);
        return res;
    }

    /**
     * searchPlanCodeList接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async searchPlanCodeList(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/searchplancodelist`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/productplans/searchplancodelist`,tempData,isloading);
    }

    /**
     * FetchPlanTasks接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async FetchPlanTasks(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/fetchplantasks`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/productplans/fetchplantasks`,tempData,isloading);
        return res;
    }

    /**
     * searchPlanTasks接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async searchPlanTasks(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/searchplantasks`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/productplans/searchplantasks`,tempData,isloading);
    }

    /**
     * FetchProjectApp接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async FetchProjectApp(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/fetchprojectapp`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/productplans/fetchprojectapp`,tempData,isloading);
        return res;
    }

    /**
     * searchProjectApp接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async searchProjectApp(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/searchprojectapp`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/productplans/searchprojectapp`,tempData,isloading);
    }

    /**
     * FetchProjectPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async FetchProjectPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/fetchprojectplan`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/productplans/fetchprojectplan`,tempData,isloading);
        return res;
    }

    /**
     * searchProjectPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async searchProjectPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/searchprojectplan`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/productplans/searchprojectplan`,tempData,isloading);
    }

    /**
     * FetchRootPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async FetchRootPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/fetchrootplan`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/productplans/fetchrootplan`,tempData,isloading);
        return res;
    }

    /**
     * searchRootPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async searchRootPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/searchrootplan`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/productplans/searchrootplan`,tempData,isloading);
    }

    /**
     * FetchTaskPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async FetchTaskPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/fetchtaskplan`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/productplans/fetchtaskplan`,tempData,isloading);
        return res;
    }

    /**
     * searchTaskPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async searchTaskPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/searchtaskplan`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/productplans/searchtaskplan`,tempData,isloading);
    }

    /**
     * Select接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async Select(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productplan){
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/select`,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/select`,isloading);
            
            return res;
    }
}