import { Environment } from '@/environments/environment';
import { Http } from '@/utils';
import { Util } from '@/utils';
import EntityService from '../entity-service';



/**
 * 测试结果服务对象基类
 *
 * @export
 * @class TestResultServiceBase
 * @extends {EntityServie}
 */
export default class TestResultServiceBase extends EntityService {

    /**
     * Creates an instance of  TestResultServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  TestResultServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     *
     * @memberof TestResultServiceBase
     */
    public initBasicData(){
        this.APPLYDEKEY ='testresult';
        this.APPDEKEY = 'id';
        this.APPDENAME = 'testresults';
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
     * @memberof TestResultServiceBase
     */
    public async CheckKey(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case && context.testresult){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/${context.case}/testresults/${context.testresult}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.story && context.case && context.testresult){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/cases/${context.case}/testresults/${context.testresult}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.product && context.case && context.testresult){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/cases/${context.case}/testresults/${context.testresult}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.case && context.testresult){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/cases/${context.case}/testresults/${context.testresult}/checkkey`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/testresults/${context.testresult}/checkkey`,data,isloading);
            return res;
    }

    /**
     * Create接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestResultServiceBase
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
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/${context.case}/testresults`,data,isloading);
            
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
            let res:any = await Http.getInstance().post(`/stories/${context.story}/cases/${context.case}/testresults`,data,isloading);
            
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
            let res:any = await Http.getInstance().post(`/products/${context.product}/cases/${context.case}/testresults`,data,isloading);
            
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
            let res:any = await Http.getInstance().post(`/cases/${context.case}/testresults`,data,isloading);
            
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
        let res:any = await Http.getInstance().post(`/testresults`,data,isloading);
        
        return res;
    }

    /**
     * Get接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestResultServiceBase
     */
    public async Get(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case && context.testresult){
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/${context.case}/testresults/${context.testresult}`,isloading);
            
            return res;
        }
        if(context.story && context.case && context.testresult){
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/${context.case}/testresults/${context.testresult}`,isloading);
            
            return res;
        }
        if(context.product && context.case && context.testresult){
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/${context.case}/testresults/${context.testresult}`,isloading);
            
            return res;
        }
        if(context.case && context.testresult){
            let res:any = await Http.getInstance().get(`/cases/${context.case}/testresults/${context.testresult}`,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().get(`/testresults/${context.testresult}`,isloading);
            
            return res;
    }

    /**
     * GetDraft接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestResultServiceBase
     */
    public async GetDraft(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.testresult) delete tempData.testresult;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/${context.case}/testresults/getdraft`,tempData,isloading);
            res.data.testresult = data.testresult;
            
            return res;
        }
        if(context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.testresult) delete tempData.testresult;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/${context.case}/testresults/getdraft`,tempData,isloading);
            res.data.testresult = data.testresult;
            
            return res;
        }
        if(context.product && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.testresult) delete tempData.testresult;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/${context.case}/testresults/getdraft`,tempData,isloading);
            res.data.testresult = data.testresult;
            
            return res;
        }
        if(context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.testresult) delete tempData.testresult;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/cases/${context.case}/testresults/getdraft`,tempData,isloading);
            res.data.testresult = data.testresult;
            
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        if(tempData.testresult) delete tempData.testresult;
        if(tempData.id) delete tempData.id;
        let res:any = await  Http.getInstance().get(`/testresults/getdraft`,tempData,isloading);
        res.data.testresult = data.testresult;
        
        return res;
    }

    /**
     * Remove接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestResultServiceBase
     */
    public async Remove(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case && context.testresult){
            let res:any = await Http.getInstance().delete(`/products/${context.product}/stories/${context.story}/cases/${context.case}/testresults/${context.testresult}`,isloading);
            return res;
        }
        if(context.story && context.case && context.testresult){
            let res:any = await Http.getInstance().delete(`/stories/${context.story}/cases/${context.case}/testresults/${context.testresult}`,isloading);
            return res;
        }
        if(context.product && context.case && context.testresult){
            let res:any = await Http.getInstance().delete(`/products/${context.product}/cases/${context.case}/testresults/${context.testresult}`,isloading);
            return res;
        }
        if(context.case && context.testresult){
            let res:any = await Http.getInstance().delete(`/cases/${context.case}/testresults/${context.testresult}`,isloading);
            return res;
        }
            let res:any = await Http.getInstance().delete(`/testresults/${context.testresult}`,isloading);
            return res;
    }

    /**
     * Save接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestResultServiceBase
     */
    public async Save(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case && context.testresult){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/${context.case}/testresults/${context.testresult}/save`,data,isloading);
            
            return res;
        }
        if(context.story && context.case && context.testresult){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/cases/${context.case}/testresults/${context.testresult}/save`,data,isloading);
            
            return res;
        }
        if(context.product && context.case && context.testresult){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/cases/${context.case}/testresults/${context.testresult}/save`,data,isloading);
            
            return res;
        }
        if(context.case && context.testresult){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/cases/${context.case}/testresults/${context.testresult}/save`,data,isloading);
            
            return res;
        }
        let masterData:any = {};
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().post(`/testresults/${context.testresult}/save`,data,isloading);
            
            return res;
    }

    /**
     * Update接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestResultServiceBase
     */
    public async Update(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case && context.testresult){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/stories/${context.story}/cases/${context.case}/testresults/${context.testresult}`,data,isloading);
            
            return res;
        }
        if(context.story && context.case && context.testresult){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/stories/${context.story}/cases/${context.case}/testresults/${context.testresult}`,data,isloading);
            
            return res;
        }
        if(context.product && context.case && context.testresult){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/cases/${context.case}/testresults/${context.testresult}`,data,isloading);
            
            return res;
        }
        if(context.case && context.testresult){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/cases/${context.case}/testresults/${context.testresult}`,data,isloading);
            
            return res;
        }
        let masterData:any = {};
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().put(`/testresults/${context.testresult}`,data,isloading);
            
            return res;
    }

    /**
     * FetchCurTestRun接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestResultServiceBase
     */
    public async FetchCurTestRun(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/${context.case}/testresults/fetchcurtestrun`,tempData,isloading);
            return res;
        }
        if(context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/${context.case}/testresults/fetchcurtestrun`,tempData,isloading);
            return res;
        }
        if(context.product && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/${context.case}/testresults/fetchcurtestrun`,tempData,isloading);
            return res;
        }
        if(context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/cases/${context.case}/testresults/fetchcurtestrun`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/testresults/fetchcurtestrun`,tempData,isloading);
        return res;
    }

    /**
     * searchCurTestRun接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestResultServiceBase
     */
    public async searchCurTestRun(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/${context.case}/testresults/searchcurtestrun`,tempData,isloading);
        }
        if(context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/${context.case}/testresults/searchcurtestrun`,tempData,isloading);
        }
        if(context.product && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/${context.case}/testresults/searchcurtestrun`,tempData,isloading);
        }
        if(context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/cases/${context.case}/testresults/searchcurtestrun`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/testresults/searchcurtestrun`,tempData,isloading);
    }

    /**
     * FetchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestResultServiceBase
     */
    public async FetchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/${context.case}/testresults/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/${context.case}/testresults/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.product && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/${context.case}/testresults/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/cases/${context.case}/testresults/fetchdefault`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/testresults/fetchdefault`,tempData,isloading);
        return res;
    }

    /**
     * searchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestResultServiceBase
     */
    public async searchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/${context.case}/testresults/searchdefault`,tempData,isloading);
        }
        if(context.story && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/${context.case}/testresults/searchdefault`,tempData,isloading);
        }
        if(context.product && context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/${context.case}/testresults/searchdefault`,tempData,isloading);
        }
        if(context.case && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/cases/${context.case}/testresults/searchdefault`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/testresults/searchdefault`,tempData,isloading);
    }

    /**
     * Select接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestResultServiceBase
     */
    public async Select(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case && context.testresult){
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/${context.case}/testresults/${context.testresult}/select`,isloading);
            
            return res;
        }
        if(context.story && context.case && context.testresult){
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/${context.case}/testresults/${context.testresult}/select`,isloading);
            
            return res;
        }
        if(context.product && context.case && context.testresult){
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/${context.case}/testresults/${context.testresult}/select`,isloading);
            
            return res;
        }
        if(context.case && context.testresult){
            let res:any = await Http.getInstance().get(`/cases/${context.case}/testresults/${context.testresult}/select`,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().get(`/testresults/${context.testresult}/select`,isloading);
            
            return res;
    }
}