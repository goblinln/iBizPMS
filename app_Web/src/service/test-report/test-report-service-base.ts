import { Environment } from '@/environments/environment';
import { Http } from '@/utils';
import { Util } from '@/utils';
import EntityService from '../entity-service';



/**
 * 测试报告服务对象基类
 *
 * @export
 * @class TestReportServiceBase
 * @extends {EntityServie}
 */
export default class TestReportServiceBase extends EntityService {

    /**
     * Creates an instance of  TestReportServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  TestReportServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     *
     * @memberof TestReportServiceBase
     */
    public initBasicData(){
        this.APPLYDEKEY ='testreport';
        this.APPDEKEY = 'id';
        this.APPDENAME = 'testreports';
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
     * @memberof TestReportServiceBase
     */
    public async CheckKey(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testreport){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/testreports/${context.testreport}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.product && context.testreport){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/testreports/${context.testreport}/checkkey`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/testreports/${context.testreport}/checkkey`,data,isloading);
            return res;
    }

    /**
     * Create接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestReportServiceBase
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
            let res:any = await Http.getInstance().post(`/projects/${context.project}/testreports`,data,isloading);
            
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
            let res:any = await Http.getInstance().post(`/products/${context.product}/testreports`,data,isloading);
            
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
        let res:any = await Http.getInstance().post(`/testreports`,data,isloading);
        
        return res;
    }

    /**
     * Get接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestReportServiceBase
     */
    public async Get(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testreport){
            let res:any = await Http.getInstance().get(`/projects/${context.project}/testreports/${context.testreport}`,isloading);
            
            return res;
        }
        if(context.product && context.testreport){
            let res:any = await Http.getInstance().get(`/products/${context.product}/testreports/${context.testreport}`,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().get(`/testreports/${context.testreport}`,isloading);
            
            return res;
    }

    /**
     * GetDraft接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestReportServiceBase
     */
    public async GetDraft(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.testreport) delete tempData.testreport;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/projects/${context.project}/testreports/getdraft`,tempData,isloading);
            res.data.testreport = data.testreport;
            
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.testreport) delete tempData.testreport;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/products/${context.product}/testreports/getdraft`,tempData,isloading);
            res.data.testreport = data.testreport;
            
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        if(tempData.testreport) delete tempData.testreport;
        if(tempData.id) delete tempData.id;
        let res:any = await  Http.getInstance().get(`/testreports/getdraft`,tempData,isloading);
        res.data.testreport = data.testreport;
        
        return res;
    }

    /**
     * GetInfoTaskOvByTime接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestReportServiceBase
     */
    public async GetInfoTaskOvByTime(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testreport){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/testreports/${context.testreport}/getinfotaskovbytime`,data,isloading);
            
            return res;
        }
        if(context.product && context.testreport){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/testreports/${context.testreport}/getinfotaskovbytime`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().put(`/testreports/${context.testreport}/getinfotaskovbytime`,data,isloading);
            return res;
    }

    /**
     * GetInfoTestTaskOvProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestReportServiceBase
     */
    public async GetInfoTestTaskOvProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testreport){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/testreports/${context.testreport}/getinfotesttaskovproject`,data,isloading);
            
            return res;
        }
        if(context.product && context.testreport){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/testreports/${context.testreport}/getinfotesttaskovproject`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().put(`/testreports/${context.testreport}/getinfotesttaskovproject`,data,isloading);
            return res;
    }

    /**
     * GetInfoTestTaskProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestReportServiceBase
     */
    public async GetInfoTestTaskProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testreport){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/testreports/${context.testreport}/getinfotesttaskproject`,data,isloading);
            
            return res;
        }
        if(context.product && context.testreport){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/testreports/${context.testreport}/getinfotesttaskproject`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().put(`/testreports/${context.testreport}/getinfotesttaskproject`,data,isloading);
            return res;
    }

    /**
     * GetInfoTestTaskR接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestReportServiceBase
     */
    public async GetInfoTestTaskR(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testreport){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/testreports/${context.testreport}/getinfotesttaskr`,data,isloading);
            
            return res;
        }
        if(context.product && context.testreport){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/testreports/${context.testreport}/getinfotesttaskr`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().put(`/testreports/${context.testreport}/getinfotesttaskr`,data,isloading);
            return res;
    }

    /**
     * GetInfoTestTaskS接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestReportServiceBase
     */
    public async GetInfoTestTaskS(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testreport){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/testreports/${context.testreport}/getinfotesttasks`,data,isloading);
            
            return res;
        }
        if(context.product && context.testreport){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/testreports/${context.testreport}/getinfotesttasks`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().put(`/testreports/${context.testreport}/getinfotesttasks`,data,isloading);
            return res;
    }

    /**
     * GetTestReportBasicInfo接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestReportServiceBase
     */
    public async GetTestReportBasicInfo(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testreport){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/testreports/${context.testreport}/gettestreportbasicinfo`,data,isloading);
            
            return res;
        }
        if(context.product && context.testreport){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/testreports/${context.testreport}/gettestreportbasicinfo`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().put(`/testreports/${context.testreport}/gettestreportbasicinfo`,data,isloading);
            return res;
    }

    /**
     * GetTestReportProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestReportServiceBase
     */
    public async GetTestReportProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testreport){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/testreports/${context.testreport}/gettestreportproject`,data,isloading);
            
            return res;
        }
        if(context.product && context.testreport){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/testreports/${context.testreport}/gettestreportproject`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().put(`/testreports/${context.testreport}/gettestreportproject`,data,isloading);
            return res;
    }

    /**
     * Remove接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestReportServiceBase
     */
    public async Remove(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testreport){
            let res:any = await Http.getInstance().delete(`/projects/${context.project}/testreports/${context.testreport}`,isloading);
            return res;
        }
        if(context.product && context.testreport){
            let res:any = await Http.getInstance().delete(`/products/${context.product}/testreports/${context.testreport}`,isloading);
            return res;
        }
            let res:any = await Http.getInstance().delete(`/testreports/${context.testreport}`,isloading);
            return res;
    }

    /**
     * Save接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestReportServiceBase
     */
    public async Save(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testreport){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/testreports/${context.testreport}/save`,data,isloading);
            
            return res;
        }
        if(context.product && context.testreport){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/testreports/${context.testreport}/save`,data,isloading);
            
            return res;
        }
        let masterData:any = {};
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().post(`/testreports/${context.testreport}/save`,data,isloading);
            
            return res;
    }

    /**
     * Update接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestReportServiceBase
     */
    public async Update(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testreport){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/testreports/${context.testreport}`,data,isloading);
            
            return res;
        }
        if(context.product && context.testreport){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/testreports/${context.testreport}`,data,isloading);
            
            return res;
        }
        let masterData:any = {};
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().put(`/testreports/${context.testreport}`,data,isloading);
            
            return res;
    }

    /**
     * getinfotesttask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestReportServiceBase
     */
    public async getinfotesttask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testreport){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/testreports/${context.testreport}/getinfotesttask`,data,isloading);
            
            return res;
        }
        if(context.product && context.testreport){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/testreports/${context.testreport}/getinfotesttask`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().put(`/testreports/${context.testreport}/getinfotesttask`,data,isloading);
            return res;
    }

    /**
     * FetchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestReportServiceBase
     */
    public async FetchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projects/${context.project}/testreports/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/testreports/fetchdefault`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/testreports/fetchdefault`,tempData,isloading);
        return res;
    }

    /**
     * searchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestReportServiceBase
     */
    public async searchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/testreports/searchdefault`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/testreports/searchdefault`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/testreports/searchdefault`,tempData,isloading);
    }

    /**
     * Select接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestReportServiceBase
     */
    public async Select(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.testreport){
            let res:any = await Http.getInstance().get(`/projects/${context.project}/testreports/${context.testreport}/select`,isloading);
            
            return res;
        }
        if(context.product && context.testreport){
            let res:any = await Http.getInstance().get(`/products/${context.product}/testreports/${context.testreport}/select`,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().get(`/testreports/${context.testreport}/select`,isloading);
            
            return res;
    }
}