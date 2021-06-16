import { Environment } from '@/environments/environment';
import { Http } from '@/utils';
import { Util } from '@/utils';
import EntityService from '../entity-service';
import GetCurUserConcatLogic from '@/service/test-task/get-cur-user-concat-logic';



/**
 * 测试版本服务对象基类
 *
 * @export
 * @class TestTaskServiceBase
 * @extends {EntityServie}
 */
export default class TestTaskServiceBase extends EntityService {

    /**
     * Creates an instance of  TestTaskServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  TestTaskServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     *
     * @memberof TestTaskServiceBase
     */
    public initBasicData(){
        this.APPLYDEKEY ='testtask';
        this.APPDEKEY = 'id';
        this.APPDENAME = 'testtasks';
        this.APPDETEXT = 'name';
        this.APPNAME = 'web';
        this.SYSTEMNAME = 'pms';
    }

// 实体接口

    /**
     * Activate接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
     */
    public async Activate(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/testtasks/${context.testtask}/activate`,data,isloading);
            
            return res;
        }
        if(context.product && context.testtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/testtasks/${context.testtask}/activate`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/testtasks/${context.testtask}/activate`,data,isloading);
            return res;
    }

    /**
     * ActivateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
     */
    public async ActivateBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/testtasks/activatebatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/testtasks/activatebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/testtasks/activatebatch`,tempData,isloading);
    }

    /**
     * Block接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
     */
    public async Block(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/testtasks/${context.testtask}/block`,data,isloading);
            
            return res;
        }
        if(context.product && context.testtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/testtasks/${context.testtask}/block`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/testtasks/${context.testtask}/block`,data,isloading);
            return res;
    }

    /**
     * BlockBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
     */
    public async BlockBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/testtasks/blockbatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/testtasks/blockbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/testtasks/blockbatch`,tempData,isloading);
    }

    /**
     * CheckKey接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
     */
    public async CheckKey(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/testtasks/${context.testtask}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.product && context.testtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/testtasks/${context.testtask}/checkkey`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/testtasks/${context.testtask}/checkkey`,data,isloading);
            return res;
    }

    /**
     * Close接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
     */
    public async Close(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/testtasks/${context.testtask}/close`,data,isloading);
            
            return res;
        }
        if(context.product && context.testtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/testtasks/${context.testtask}/close`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/testtasks/${context.testtask}/close`,data,isloading);
            return res;
    }

    /**
     * CloseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
     */
    public async CloseBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/testtasks/closebatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/testtasks/closebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/testtasks/closebatch`,tempData,isloading);
    }

    /**
     * Create接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
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
            let res:any = await Http.getInstance().post(`/projects/${context.project}/testtasks`,data,isloading);
            this.tempStorage.setItem(tempContext.srfsessionkey+'_testruns',JSON.stringify(res.data.testruns?res.data.testruns:[]));
            
            return res;
        }
        if(context.product && true){
            let masterData:any = {};
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/products/${context.product}/testtasks`,data,isloading);
            this.tempStorage.setItem(tempContext.srfsessionkey+'_testruns',JSON.stringify(res.data.testruns?res.data.testruns:[]));
            
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
        let res:any = await Http.getInstance().post(`/testtasks`,data,isloading);
        this.tempStorage.setItem(tempContext.srfsessionkey+'_testruns',JSON.stringify(res.data.testruns?res.data.testruns:[]));
        
        return res;
    }

    /**
     * Get接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
     */
    public async Get(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testtask){
            let res:any = await Http.getInstance().get(`/projects/${context.project}/testtasks/${context.testtask}`,isloading);
            
            return res;
        }
        if(context.product && context.testtask){
            let res:any = await Http.getInstance().get(`/products/${context.product}/testtasks/${context.testtask}`,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().get(`/testtasks/${context.testtask}`,isloading);
            
            return res;
    }

    /**
     * GetDraft接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
     */
    public async GetDraft(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.testtask) delete tempData.testtask;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/projects/${context.project}/testtasks/getdraft`,tempData,isloading);
            res.data.testtask = data.testtask;
            
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.testtask) delete tempData.testtask;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/products/${context.product}/testtasks/getdraft`,tempData,isloading);
            res.data.testtask = data.testtask;
            
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        if(tempData.testtask) delete tempData.testtask;
        if(tempData.id) delete tempData.id;
        let res:any = await  Http.getInstance().get(`/testtasks/getdraft`,tempData,isloading);
        res.data.testtask = data.testtask;
        
        return res;
    }

    /**
     * GetUserConcat接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
     */
    public async GetUserConcat(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let appLogic:GetCurUserConcatLogic = new GetCurUserConcatLogic({context:JSON.parse(JSON.stringify(context)),data:JSON.parse(JSON.stringify(data))});
        const res = await appLogic.onExecute(context,data,isloading?true:false);
        return {status:200,data:res};
    }

    /**
     * LinkCase接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
     */
    public async LinkCase(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/testtasks/${context.testtask}/linkcase`,data,isloading);
            
            return res;
        }
        if(context.product && context.testtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/testtasks/${context.testtask}/linkcase`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/testtasks/${context.testtask}/linkcase`,data,isloading);
            return res;
    }

    /**
     * LinkCaseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
     */
    public async LinkCaseBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/testtasks/linkcasebatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/testtasks/linkcasebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/testtasks/linkcasebatch`,tempData,isloading);
    }

    /**
     * MobTestTaskCounter接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
     */
    public async MobTestTaskCounter(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/testtasks/${context.testtask}/mobtesttaskcounter`,data,isloading);
            
            return res;
        }
        if(context.product && context.testtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/testtasks/${context.testtask}/mobtesttaskcounter`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/testtasks/${context.testtask}/mobtesttaskcounter`,data,isloading);
            return res;
    }

    /**
     * Remove接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
     */
    public async Remove(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testtask){
            let res:any = await Http.getInstance().delete(`/projects/${context.project}/testtasks/${context.testtask}`,isloading);
            return res;
        }
        if(context.product && context.testtask){
            let res:any = await Http.getInstance().delete(`/products/${context.product}/testtasks/${context.testtask}`,isloading);
            return res;
        }
            let res:any = await Http.getInstance().delete(`/testtasks/${context.testtask}`,isloading);
            return res;
    }

    /**
     * Save接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
     */
    public async Save(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/testtasks/${context.testtask}/save`,data,isloading);
            
            return res;
        }
        if(context.product && context.testtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/testtasks/${context.testtask}/save`,data,isloading);
            
            return res;
        }
        let masterData:any = {};
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().post(`/testtasks/${context.testtask}/save`,data,isloading);
            
            return res;
    }

    /**
     * Start接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
     */
    public async Start(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/testtasks/${context.testtask}/start`,data,isloading);
            
            return res;
        }
        if(context.product && context.testtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/testtasks/${context.testtask}/start`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/testtasks/${context.testtask}/start`,data,isloading);
            return res;
    }

    /**
     * StartBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
     */
    public async StartBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/testtasks/startbatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/testtasks/startbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/testtasks/startbatch`,tempData,isloading);
    }

    /**
     * UnlinkCase接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
     */
    public async UnlinkCase(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/testtasks/${context.testtask}/unlinkcase`,data,isloading);
            
            return res;
        }
        if(context.product && context.testtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/testtasks/${context.testtask}/unlinkcase`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/testtasks/${context.testtask}/unlinkcase`,data,isloading);
            return res;
    }

    /**
     * UnlinkCaseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
     */
    public async UnlinkCaseBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/testtasks/unlinkcasebatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/testtasks/unlinkcasebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/testtasks/unlinkcasebatch`,tempData,isloading);
    }

    /**
     * Update接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
     */
    public async Update(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/testtasks/${context.testtask}`,data,isloading);
            
            return res;
        }
        if(context.product && context.testtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/testtasks/${context.testtask}`,data,isloading);
            
            return res;
        }
        let masterData:any = {};
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().put(`/testtasks/${context.testtask}`,data,isloading);
            
            return res;
    }

    /**
     * FetchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
     */
    public async FetchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projects/${context.project}/testtasks/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/testtasks/fetchdefault`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/testtasks/fetchdefault`,tempData,isloading);
        return res;
    }

    /**
     * searchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
     */
    public async searchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/testtasks/searchdefault`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/testtasks/searchdefault`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/testtasks/searchdefault`,tempData,isloading);
    }

    /**
     * FetchMyTestTaskPc接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
     */
    public async FetchMyTestTaskPc(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/testtasks/fetchmytesttaskpc`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/testtasks/fetchmytesttaskpc`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/testtasks/fetchmytesttaskpc`,tempData,isloading);
        return res;
    }

    /**
     * searchMyTestTaskPc接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
     */
    public async searchMyTestTaskPc(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/testtasks/searchmytesttaskpc`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/testtasks/searchmytesttaskpc`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/testtasks/searchmytesttaskpc`,tempData,isloading);
    }

    /**
     * Select接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
     */
    public async Select(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testtask){
            let res:any = await Http.getInstance().get(`/projects/${context.project}/testtasks/${context.testtask}/select`,isloading);
            
            return res;
        }
        if(context.product && context.testtask){
            let res:any = await Http.getInstance().get(`/products/${context.product}/testtasks/${context.testtask}/select`,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().get(`/testtasks/${context.testtask}/select`,isloading);
            
            return res;
    }
}