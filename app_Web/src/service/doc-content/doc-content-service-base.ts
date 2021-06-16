import { Environment } from '@/environments/environment';
import { Http } from '@/utils';
import { Util } from '@/utils';
import EntityService from '../entity-service';



/**
 * 文档内容服务对象基类
 *
 * @export
 * @class DocContentServiceBase
 * @extends {EntityServie}
 */
export default class DocContentServiceBase extends EntityService {

    /**
     * Creates an instance of  DocContentServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  DocContentServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     *
     * @memberof DocContentServiceBase
     */
    public initBasicData(){
        this.APPLYDEKEY ='doccontent';
        this.APPDEKEY = 'id';
        this.APPDENAME = 'doccontents';
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
     * @memberof DocContentServiceBase
     */
    public async CheckKey(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.doc && context.doccontent){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/docs/${context.doc}/doccontents/${context.doccontent}/checkkey`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/doccontents/${context.doccontent}/checkkey`,data,isloading);
            return res;
    }

    /**
     * Create接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocContentServiceBase
     */
    public async Create(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.doc && true){
            let masterData:any = {};
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/docs/${context.doc}/doccontents`,data,isloading);
            
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
        let res:any = await Http.getInstance().post(`/doccontents`,data,isloading);
        
        return res;
    }

    /**
     * Get接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocContentServiceBase
     */
    public async Get(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.doc && context.doccontent){
            let res:any = await Http.getInstance().get(`/docs/${context.doc}/doccontents/${context.doccontent}`,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().get(`/doccontents/${context.doccontent}`,isloading);
            
            return res;
    }

    /**
     * GetDraft接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocContentServiceBase
     */
    public async GetDraft(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.doc && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.doccontent) delete tempData.doccontent;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/docs/${context.doc}/doccontents/getdraft`,tempData,isloading);
            res.data.doccontent = data.doccontent;
            
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        if(tempData.doccontent) delete tempData.doccontent;
        if(tempData.id) delete tempData.id;
        let res:any = await  Http.getInstance().get(`/doccontents/getdraft`,tempData,isloading);
        res.data.doccontent = data.doccontent;
        
        return res;
    }

    /**
     * Remove接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocContentServiceBase
     */
    public async Remove(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.doc && context.doccontent){
            let res:any = await Http.getInstance().delete(`/docs/${context.doc}/doccontents/${context.doccontent}`,isloading);
            return res;
        }
            let res:any = await Http.getInstance().delete(`/doccontents/${context.doccontent}`,isloading);
            return res;
    }

    /**
     * Save接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocContentServiceBase
     */
    public async Save(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.doc && context.doccontent){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/docs/${context.doc}/doccontents/${context.doccontent}/save`,data,isloading);
            
            return res;
        }
        let masterData:any = {};
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().post(`/doccontents/${context.doccontent}/save`,data,isloading);
            
            return res;
    }

    /**
     * Update接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocContentServiceBase
     */
    public async Update(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.doc && context.doccontent){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/docs/${context.doc}/doccontents/${context.doccontent}`,data,isloading);
            
            return res;
        }
        let masterData:any = {};
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().put(`/doccontents/${context.doccontent}`,data,isloading);
            
            return res;
    }

    /**
     * FetchCurVersion接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocContentServiceBase
     */
    public async FetchCurVersion(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.doc && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/docs/${context.doc}/doccontents/fetchcurversion`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/doccontents/fetchcurversion`,tempData,isloading);
        return res;
    }

    /**
     * searchCurVersion接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocContentServiceBase
     */
    public async searchCurVersion(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.doc && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/docs/${context.doc}/doccontents/searchcurversion`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/doccontents/searchcurversion`,tempData,isloading);
    }

    /**
     * FetchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocContentServiceBase
     */
    public async FetchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.doc && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/docs/${context.doc}/doccontents/fetchdefault`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/doccontents/fetchdefault`,tempData,isloading);
        return res;
    }

    /**
     * searchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocContentServiceBase
     */
    public async searchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.doc && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/docs/${context.doc}/doccontents/searchdefault`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/doccontents/searchdefault`,tempData,isloading);
    }

    /**
     * Select接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocContentServiceBase
     */
    public async Select(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.doc && context.doccontent){
            let res:any = await Http.getInstance().get(`/docs/${context.doc}/doccontents/${context.doccontent}/select`,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().get(`/doccontents/${context.doccontent}/select`,isloading);
            
            return res;
    }
}