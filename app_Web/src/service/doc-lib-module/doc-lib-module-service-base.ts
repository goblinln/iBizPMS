import { Environment } from '@/environments/environment';
import { Http } from '@/utils';
import { Util } from '@/utils';
import EntityService from '../entity-service';



/**
 * 文档库分类服务对象基类
 *
 * @export
 * @class DocLibModuleServiceBase
 * @extends {EntityServie}
 */
export default class DocLibModuleServiceBase extends EntityService {

    /**
     * Creates an instance of  DocLibModuleServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  DocLibModuleServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     *
     * @memberof DocLibModuleServiceBase
     */
    public initBasicData(){
        this.APPLYDEKEY ='doclibmodule';
        this.APPDEKEY = 'id';
        this.APPDENAME = 'doclibmodules';
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
     * @memberof DocLibModuleServiceBase
     */
    public async CheckKey(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().post(`/doclibmodules/${context.doclibmodule}/checkkey`,data,isloading);
            return res;
    }

    /**
     * Collect接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async Collect(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().post(`/doclibmodules/${context.doclibmodule}/collect`,data,isloading);
            return res;
    }

    /**
     * CollectBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async CollectBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/doclibmodules/collectbatch`,tempData,isloading);
    }

    /**
     * Create接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async Create(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let masterData:any = {};
        Object.assign(data,masterData);
        if(!data.srffrontuf || data.srffrontuf !== "1"){
            data[this.APPDEKEY] = null;
        }
        if(data.srffrontuf){
            delete data.srffrontuf;
        }
        let tempContext:any = JSON.parse(JSON.stringify(context));
        let res:any = await Http.getInstance().post(`/doclibmodules`,data,isloading);
        
        return res;
    }

    /**
     * DocLibModuleNFavorite接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async DocLibModuleNFavorite(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().post(`/doclibmodules/${context.doclibmodule}/doclibmodulenfavorite`,data,isloading);
            return res;
    }

    /**
     * DoclibModuleFavorite接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async DoclibModuleFavorite(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().post(`/doclibmodules/${context.doclibmodule}/doclibmodulefavorite`,data,isloading);
            return res;
    }

    /**
     * Fix接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async Fix(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().post(`/doclibmodules/${context.doclibmodule}/fix`,data,isloading);
            return res;
    }

    /**
     * Get接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async Get(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().get(`/doclibmodules/${context.doclibmodule}`,isloading);
            
            return res;
    }

    /**
     * GetDraft接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async GetDraft(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        if(tempData.doclibmodule) delete tempData.doclibmodule;
        if(tempData.id) delete tempData.id;
        let res:any = await  Http.getInstance().get(`/doclibmodules/getdraft`,tempData,isloading);
        res.data.doclibmodule = data.doclibmodule;
        
        return res;
    }

    /**
     * Remove接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async Remove(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().delete(`/doclibmodules/${context.doclibmodule}`,isloading);
            return res;
    }

    /**
     * Save接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async Save(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let masterData:any = {};
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().post(`/doclibmodules/${context.doclibmodule}/save`,data,isloading);
            
            return res;
    }

    /**
     * UnCollect接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async UnCollect(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().post(`/doclibmodules/${context.doclibmodule}/uncollect`,data,isloading);
            return res;
    }

    /**
     * UnCollectBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async UnCollectBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/doclibmodules/uncollectbatch`,tempData,isloading);
    }

    /**
     * Update接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async Update(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let masterData:any = {};
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().put(`/doclibmodules/${context.doclibmodule}`,data,isloading);
            
            return res;
    }

    /**
     * FetchAllDocLibModule_Custom接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async FetchAllDocLibModule_Custom(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/doclibmodules/fetchalldoclibmodule_custom`,tempData,isloading);
        return res;
    }

    /**
     * searchAllDocLibModule_Custom接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async searchAllDocLibModule_Custom(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/doclibmodules/searchalldoclibmodule_custom`,tempData,isloading);
    }

    /**
     * FetchAllDoclibModule接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async FetchAllDoclibModule(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/doclibmodules/fetchalldoclibmodule`,tempData,isloading);
        return res;
    }

    /**
     * searchAllDoclibModule接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async searchAllDoclibModule(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/doclibmodules/searchalldoclibmodule`,tempData,isloading);
    }

    /**
     * FetchChildModuleByParent接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async FetchChildModuleByParent(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/doclibmodules/fetchchildmodulebyparent`,tempData,isloading);
        return res;
    }

    /**
     * searchChildModuleByParent接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async searchChildModuleByParent(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/doclibmodules/searchchildmodulebyparent`,tempData,isloading);
    }

    /**
     * FetchChildModuleByRealParent接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async FetchChildModuleByRealParent(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/doclibmodules/fetchchildmodulebyrealparent`,tempData,isloading);
        return res;
    }

    /**
     * searchChildModuleByRealParent接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async searchChildModuleByRealParent(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/doclibmodules/searchchildmodulebyrealparent`,tempData,isloading);
    }

    /**
     * FetchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async FetchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/doclibmodules/fetchdefault`,tempData,isloading);
        return res;
    }

    /**
     * searchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async searchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/doclibmodules/searchdefault`,tempData,isloading);
    }

    /**
     * FetchMyFavourites接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async FetchMyFavourites(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/doclibmodules/fetchmyfavourites`,tempData,isloading);
        return res;
    }

    /**
     * searchMyFavourites接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async searchMyFavourites(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/doclibmodules/searchmyfavourites`,tempData,isloading);
    }

    /**
     * FetchParentModule接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async FetchParentModule(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/doclibmodules/fetchparentmodule`,tempData,isloading);
        return res;
    }

    /**
     * searchParentModule接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async searchParentModule(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/doclibmodules/searchparentmodule`,tempData,isloading);
    }

    /**
     * FetchRootModuleMuLu接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async FetchRootModuleMuLu(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/doclibmodules/fetchrootmodulemulu`,tempData,isloading);
        return res;
    }

    /**
     * searchRootModuleMuLu接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async searchRootModuleMuLu(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/doclibmodules/searchrootmodulemulu`,tempData,isloading);
    }

    /**
     * FetchRootModuleMuLuByRoot接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async FetchRootModuleMuLuByRoot(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/doclibmodules/fetchrootmodulemulubyroot`,tempData,isloading);
        return res;
    }

    /**
     * searchRootModuleMuLuByRoot接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async searchRootModuleMuLuByRoot(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/doclibmodules/searchrootmodulemulubyroot`,tempData,isloading);
    }

    /**
     * FetchRootModuleMuLuBysrfparentkey接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async FetchRootModuleMuLuBysrfparentkey(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/doclibmodules/fetchrootmodulemulubysrfparentkey`,tempData,isloading);
        return res;
    }

    /**
     * searchRootModuleMuLuBysrfparentkey接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async searchRootModuleMuLuBysrfparentkey(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/doclibmodules/searchrootmodulemulubysrfparentkey`,tempData,isloading);
    }

    /**
     * Select接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof DocLibModuleServiceBase
     */
    public async Select(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().get(`/doclibmodules/${context.doclibmodule}/select`,isloading);
            
            return res;
    }
}