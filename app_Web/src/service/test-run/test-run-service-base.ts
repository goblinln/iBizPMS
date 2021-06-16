import { Environment } from '@/environments/environment';
import { Http } from '@/utils';
import { Util } from '@/utils';
import EntityService from '../entity-service';



/**
 * 测试运行服务对象基类
 *
 * @export
 * @class TestRunServiceBase
 * @extends {EntityServie}
 */
export default class TestRunServiceBase extends EntityService {

    /**
     * Creates an instance of  TestRunServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  TestRunServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     *
     * @memberof TestRunServiceBase
     */
    public initBasicData(){
        this.APPLYDEKEY ='testrun';
        this.APPDEKEY = 'id';
        this.APPDENAME = 'testruns';
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
     * @memberof TestRunServiceBase
     */
    public async CheckKey(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testtask && context.testrun){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/testtasks/${context.testtask}/testruns/${context.testrun}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.product && context.testtask && context.testrun){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/testtasks/${context.testtask}/testruns/${context.testrun}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.testtask && context.testrun){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/testtasks/${context.testtask}/testruns/${context.testrun}/checkkey`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/testruns/${context.testrun}/checkkey`,data,isloading);
            return res;
    }

    /**
     * Create接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestRunServiceBase
     */
    public async Create(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testtask && true){
            let masterData:any = {};
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/projects/${context.project}/testtasks/${context.testtask}/testruns`,data,isloading);
            
            return res;
        }
        if(context.product && context.testtask && true){
            let masterData:any = {};
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/products/${context.product}/testtasks/${context.testtask}/testruns`,data,isloading);
            
            return res;
        }
        if(context.testtask && true){
            let masterData:any = {};
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/testtasks/${context.testtask}/testruns`,data,isloading);
            
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
        let res:any = await Http.getInstance().post(`/testruns`,data,isloading);
        
        return res;
    }

    /**
     * Get接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestRunServiceBase
     */
    public async Get(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testtask && context.testrun){
            let res:any = await Http.getInstance().get(`/projects/${context.project}/testtasks/${context.testtask}/testruns/${context.testrun}`,isloading);
            
            return res;
        }
        if(context.product && context.testtask && context.testrun){
            let res:any = await Http.getInstance().get(`/products/${context.product}/testtasks/${context.testtask}/testruns/${context.testrun}`,isloading);
            
            return res;
        }
        if(context.testtask && context.testrun){
            let res:any = await Http.getInstance().get(`/testtasks/${context.testtask}/testruns/${context.testrun}`,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().get(`/testruns/${context.testrun}`,isloading);
            
            return res;
    }

    /**
     * GetDraft接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestRunServiceBase
     */
    public async GetDraft(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testtask && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.testrun) delete tempData.testrun;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/projects/${context.project}/testtasks/${context.testtask}/testruns/getdraft`,tempData,isloading);
            res.data.testrun = data.testrun;
            
            return res;
        }
        if(context.product && context.testtask && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.testrun) delete tempData.testrun;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/products/${context.product}/testtasks/${context.testtask}/testruns/getdraft`,tempData,isloading);
            res.data.testrun = data.testrun;
            
            return res;
        }
        if(context.testtask && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.testrun) delete tempData.testrun;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/testtasks/${context.testtask}/testruns/getdraft`,tempData,isloading);
            res.data.testrun = data.testrun;
            
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        if(tempData.testrun) delete tempData.testrun;
        if(tempData.id) delete tempData.id;
        let res:any = await  Http.getInstance().get(`/testruns/getdraft`,tempData,isloading);
        res.data.testrun = data.testrun;
        
        return res;
    }

    /**
     * Remove接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestRunServiceBase
     */
    public async Remove(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testtask && context.testrun){
            let res:any = await Http.getInstance().delete(`/projects/${context.project}/testtasks/${context.testtask}/testruns/${context.testrun}`,isloading);
            return res;
        }
        if(context.product && context.testtask && context.testrun){
            let res:any = await Http.getInstance().delete(`/products/${context.product}/testtasks/${context.testtask}/testruns/${context.testrun}`,isloading);
            return res;
        }
        if(context.testtask && context.testrun){
            let res:any = await Http.getInstance().delete(`/testtasks/${context.testtask}/testruns/${context.testrun}`,isloading);
            return res;
        }
            let res:any = await Http.getInstance().delete(`/testruns/${context.testrun}`,isloading);
            return res;
    }

    /**
     * Save接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestRunServiceBase
     */
    public async Save(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testtask && context.testrun){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/testtasks/${context.testtask}/testruns/${context.testrun}/save`,data,isloading);
            
            return res;
        }
        if(context.product && context.testtask && context.testrun){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/testtasks/${context.testtask}/testruns/${context.testrun}/save`,data,isloading);
            
            return res;
        }
        if(context.testtask && context.testrun){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/testtasks/${context.testtask}/testruns/${context.testrun}/save`,data,isloading);
            
            return res;
        }
        let masterData:any = {};
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().post(`/testruns/${context.testrun}/save`,data,isloading);
            
            return res;
    }

    /**
     * Update接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestRunServiceBase
     */
    public async Update(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testtask && context.testrun){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/testtasks/${context.testtask}/testruns/${context.testrun}`,data,isloading);
            
            return res;
        }
        if(context.product && context.testtask && context.testrun){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/testtasks/${context.testtask}/testruns/${context.testrun}`,data,isloading);
            
            return res;
        }
        if(context.testtask && context.testrun){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/testtasks/${context.testtask}/testruns/${context.testrun}`,data,isloading);
            
            return res;
        }
        let masterData:any = {};
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().put(`/testruns/${context.testrun}`,data,isloading);
            
            return res;
    }

    /**
     * FetchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestRunServiceBase
     */
    public async FetchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testtask && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/testtasks/${context.testtask}/testruns/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.product && context.testtask && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/testtasks/${context.testtask}/testruns/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.testtask && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/testtasks/${context.testtask}/testruns/fetchdefault`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/testruns/fetchdefault`,tempData,isloading);
        return res;
    }

    /**
     * searchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestRunServiceBase
     */
    public async searchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testtask && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/testtasks/${context.testtask}/testruns/searchdefault`,tempData,isloading);
        }
        if(context.product && context.testtask && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/testtasks/${context.testtask}/testruns/searchdefault`,tempData,isloading);
        }
        if(context.testtask && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/testtasks/${context.testtask}/testruns/searchdefault`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/testruns/searchdefault`,tempData,isloading);
    }

    /**
     * Select接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestRunServiceBase
     */
    public async Select(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testtask && context.testrun){
            let res:any = await Http.getInstance().get(`/projects/${context.project}/testtasks/${context.testtask}/testruns/${context.testrun}/select`,isloading);
            
            return res;
        }
        if(context.product && context.testtask && context.testrun){
            let res:any = await Http.getInstance().get(`/products/${context.product}/testtasks/${context.testtask}/testruns/${context.testrun}/select`,isloading);
            
            return res;
        }
        if(context.testtask && context.testrun){
            let res:any = await Http.getInstance().get(`/testtasks/${context.testtask}/testruns/${context.testrun}/select`,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().get(`/testruns/${context.testrun}/select`,isloading);
            
            return res;
    }
}