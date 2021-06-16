import { Environment } from '@/environments/environment';
import { Http } from '@/utils';
import { Util } from '@/utils';
import EntityService from '../entity-service';



/**
 * 系统更新功能服务对象基类
 *
 * @export
 * @class SysUpdateFeaturesServiceBase
 * @extends {EntityServie}
 */
export default class SysUpdateFeaturesServiceBase extends EntityService {

    /**
     * Creates an instance of  SysUpdateFeaturesServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  SysUpdateFeaturesServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     *
     * @memberof SysUpdateFeaturesServiceBase
     */
    public initBasicData(){
        this.APPLYDEKEY ='sysupdatefeatures';
        this.APPDEKEY = 'sysupdatefeaturesid';
        this.APPDENAME = 'sysupdatefeatures';
        this.APPDETEXT = 'sysupdatefeaturesname';
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
     * @memberof SysUpdateFeaturesServiceBase
     */
    public async CheckKey(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.sysupdatelog && context.sysupdatefeatures){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/sysupdatelogs/${context.sysupdatelog}/sysupdatefeatures/${context.sysupdatefeatures}/checkkey`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/sysupdatefeatures/${context.sysupdatefeatures}/checkkey`,data,isloading);
            return res;
    }

    /**
     * Create接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SysUpdateFeaturesServiceBase
     */
    public async Create(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.sysupdatelog && true){
            let masterData:any = {};
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/sysupdatelogs/${context.sysupdatelog}/sysupdatefeatures`,data,isloading);
            
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
        let res:any = await Http.getInstance().post(`/sysupdatefeatures`,data,isloading);
        
        return res;
    }

    /**
     * Get接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SysUpdateFeaturesServiceBase
     */
    public async Get(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.sysupdatelog && context.sysupdatefeatures){
            let res:any = await Http.getInstance().get(`/sysupdatelogs/${context.sysupdatelog}/sysupdatefeatures/${context.sysupdatefeatures}`,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().get(`/sysupdatefeatures/${context.sysupdatefeatures}`,isloading);
            
            return res;
    }

    /**
     * GetDraft接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SysUpdateFeaturesServiceBase
     */
    public async GetDraft(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.sysupdatelog && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.sysupdatefeatures) delete tempData.sysupdatefeatures;
            if(tempData.sysupdatefeaturesid) delete tempData.sysupdatefeaturesid;
            let res:any = await Http.getInstance().get(`/sysupdatelogs/${context.sysupdatelog}/sysupdatefeatures/getdraft`,tempData,isloading);
            res.data.sysupdatefeatures = data.sysupdatefeatures;
            
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        if(tempData.sysupdatefeatures) delete tempData.sysupdatefeatures;
        if(tempData.sysupdatefeaturesid) delete tempData.sysupdatefeaturesid;
        let res:any = await  Http.getInstance().get(`/sysupdatefeatures/getdraft`,tempData,isloading);
        res.data.sysupdatefeatures = data.sysupdatefeatures;
        
        return res;
    }

    /**
     * Remove接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SysUpdateFeaturesServiceBase
     */
    public async Remove(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.sysupdatelog && context.sysupdatefeatures){
            let res:any = await Http.getInstance().delete(`/sysupdatelogs/${context.sysupdatelog}/sysupdatefeatures/${context.sysupdatefeatures}`,isloading);
            return res;
        }
            let res:any = await Http.getInstance().delete(`/sysupdatefeatures/${context.sysupdatefeatures}`,isloading);
            return res;
    }

    /**
     * Save接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SysUpdateFeaturesServiceBase
     */
    public async Save(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.sysupdatelog && context.sysupdatefeatures){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/sysupdatelogs/${context.sysupdatelog}/sysupdatefeatures/${context.sysupdatefeatures}/save`,data,isloading);
            
            return res;
        }
        let masterData:any = {};
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().post(`/sysupdatefeatures/${context.sysupdatefeatures}/save`,data,isloading);
            
            return res;
    }

    /**
     * Update接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SysUpdateFeaturesServiceBase
     */
    public async Update(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.sysupdatelog && context.sysupdatefeatures){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/sysupdatelogs/${context.sysupdatelog}/sysupdatefeatures/${context.sysupdatefeatures}`,data,isloading);
            
            return res;
        }
        let masterData:any = {};
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().put(`/sysupdatefeatures/${context.sysupdatefeatures}`,data,isloading);
            
            return res;
    }

    /**
     * FetchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SysUpdateFeaturesServiceBase
     */
    public async FetchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.sysupdatelog && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/sysupdatelogs/${context.sysupdatelog}/sysupdatefeatures/fetchdefault`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/sysupdatefeatures/fetchdefault`,tempData,isloading);
        return res;
    }

    /**
     * searchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SysUpdateFeaturesServiceBase
     */
    public async searchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.sysupdatelog && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/sysupdatelogs/${context.sysupdatelog}/sysupdatefeatures/searchdefault`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/sysupdatefeatures/searchdefault`,tempData,isloading);
    }

    /**
     * Select接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SysUpdateFeaturesServiceBase
     */
    public async Select(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.sysupdatelog && context.sysupdatefeatures){
            let res:any = await Http.getInstance().get(`/sysupdatelogs/${context.sysupdatelog}/sysupdatefeatures/${context.sysupdatefeatures}/select`,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().get(`/sysupdatefeatures/${context.sysupdatefeatures}/select`,isloading);
            
            return res;
    }
}