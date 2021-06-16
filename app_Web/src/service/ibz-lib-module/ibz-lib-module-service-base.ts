import { Environment } from '@/environments/environment';
import { Http } from '@/utils';
import { Util } from '@/utils';
import EntityService from '../entity-service';



/**
 * 用例库模块服务对象基类
 *
 * @export
 * @class IbzLibModuleServiceBase
 * @extends {EntityServie}
 */
export default class IbzLibModuleServiceBase extends EntityService {

    /**
     * Creates an instance of  IbzLibModuleServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzLibModuleServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     *
     * @memberof IbzLibModuleServiceBase
     */
    public initBasicData(){
        this.APPLYDEKEY ='ibzlibmodule';
        this.APPDEKEY = 'id';
        this.APPDENAME = 'ibzlibmodules';
        this.APPDETEXT = 'name';
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
     * @memberof IbzLibModuleServiceBase
     */
    public async CheckKey(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.ibzlib && context.ibzlibmodule){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/ibzlibs/${context.ibzlib}/ibzlibmodules/${context.ibzlibmodule}/checkkey`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/ibzlibmodules/${context.ibzlibmodule}/checkkey`,data,isloading);
            return res;
    }

    /**
     * Create接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzLibModuleServiceBase
     */
    public async Create(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.ibzlib && true){
            let masterData:any = {};
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/ibzlibs/${context.ibzlib}/ibzlibmodules`,data,isloading);
            
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
        let res:any = await Http.getInstance().post(`/ibzlibmodules`,data,isloading);
        
        return res;
    }

    /**
     * Get接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzLibModuleServiceBase
     */
    public async Get(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.ibzlib && context.ibzlibmodule){
            let res:any = await Http.getInstance().get(`/ibzlibs/${context.ibzlib}/ibzlibmodules/${context.ibzlibmodule}`,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().get(`/ibzlibmodules/${context.ibzlibmodule}`,isloading);
            
            return res;
    }

    /**
     * GetDraft接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzLibModuleServiceBase
     */
    public async GetDraft(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.ibzlib && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.ibzlibmodule) delete tempData.ibzlibmodule;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/ibzlibs/${context.ibzlib}/ibzlibmodules/getdraft`,tempData,isloading);
            res.data.ibzlibmodule = data.ibzlibmodule;
            
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        if(tempData.ibzlibmodule) delete tempData.ibzlibmodule;
        if(tempData.id) delete tempData.id;
        let res:any = await  Http.getInstance().get(`/ibzlibmodules/getdraft`,tempData,isloading);
        res.data.ibzlibmodule = data.ibzlibmodule;
        
        return res;
    }

    /**
     * Remove接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzLibModuleServiceBase
     */
    public async Remove(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.ibzlib && context.ibzlibmodule){
            let res:any = await Http.getInstance().delete(`/ibzlibs/${context.ibzlib}/ibzlibmodules/${context.ibzlibmodule}`,isloading);
            return res;
        }
            let res:any = await Http.getInstance().delete(`/ibzlibmodules/${context.ibzlibmodule}`,isloading);
            return res;
    }

    /**
     * Save接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzLibModuleServiceBase
     */
    public async Save(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.ibzlib && context.ibzlibmodule){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/ibzlibs/${context.ibzlib}/ibzlibmodules/${context.ibzlibmodule}/save`,data,isloading);
            
            return res;
        }
        let masterData:any = {};
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().post(`/ibzlibmodules/${context.ibzlibmodule}/save`,data,isloading);
            
            return res;
    }

    /**
     * Update接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzLibModuleServiceBase
     */
    public async Update(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.ibzlib && context.ibzlibmodule){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/ibzlibs/${context.ibzlib}/ibzlibmodules/${context.ibzlibmodule}`,data,isloading);
            
            return res;
        }
        let masterData:any = {};
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().put(`/ibzlibmodules/${context.ibzlibmodule}`,data,isloading);
            
            return res;
    }

    /**
     * FetchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzLibModuleServiceBase
     */
    public async FetchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.ibzlib && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/ibzlibs/${context.ibzlib}/ibzlibmodules/fetchdefault`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/ibzlibmodules/fetchdefault`,tempData,isloading);
        return res;
    }

    /**
     * searchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzLibModuleServiceBase
     */
    public async searchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.ibzlib && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/ibzlibs/${context.ibzlib}/ibzlibmodules/searchdefault`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/ibzlibmodules/searchdefault`,tempData,isloading);
    }

    /**
     * FetchRoot_NoBranch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzLibModuleServiceBase
     */
    public async FetchRoot_NoBranch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.ibzlib && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/ibzlibs/${context.ibzlib}/ibzlibmodules/fetchroot_nobranch`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/ibzlibmodules/fetchroot_nobranch`,tempData,isloading);
        return res;
    }

    /**
     * searchRoot_NoBranch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzLibModuleServiceBase
     */
    public async searchRoot_NoBranch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.ibzlib && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/ibzlibs/${context.ibzlib}/ibzlibmodules/searchroot_nobranch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/ibzlibmodules/searchroot_nobranch`,tempData,isloading);
    }

    /**
     * Select接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzLibModuleServiceBase
     */
    public async Select(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.ibzlib && context.ibzlibmodule){
            let res:any = await Http.getInstance().get(`/ibzlibs/${context.ibzlib}/ibzlibmodules/${context.ibzlibmodule}/select`,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().get(`/ibzlibmodules/${context.ibzlibmodule}/select`,isloading);
            
            return res;
    }
}