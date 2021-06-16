import { Environment } from '@/environments/environment';
import { Http } from '@/utils';
import { Util } from '@/utils';
import EntityService from '../entity-service';



/**
 * 用例库用例步骤服务对象基类
 *
 * @export
 * @class IbzLibCasestepsServiceBase
 * @extends {EntityServie}
 */
export default class IbzLibCasestepsServiceBase extends EntityService {

    /**
     * Creates an instance of  IbzLibCasestepsServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzLibCasestepsServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     *
     * @memberof IbzLibCasestepsServiceBase
     */
    public initBasicData(){
        this.APPLYDEKEY ='ibzlibcasesteps';
        this.APPDEKEY = 'id';
        this.APPDENAME = 'ibzlibcasesteps';
        this.APPDETEXT = 'expect';
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
     * @memberof IbzLibCasestepsServiceBase
     */
    public async CheckKey(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.ibzlib && context.ibzcase && context.ibzlibcasesteps){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/ibzlibs/${context.ibzlib}/ibzcases/${context.ibzcase}/ibzlibcasesteps/${context.ibzlibcasesteps}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.ibzcase && context.ibzlibcasesteps){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/ibzcases/${context.ibzcase}/ibzlibcasesteps/${context.ibzlibcasesteps}/checkkey`,data,isloading);
            
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
     * @memberof IbzLibCasestepsServiceBase
     */
    public async Create(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.ibzlib && context.ibzcase && true){
            let masterData:any = {};
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/ibzlibs/${context.ibzlib}/ibzcases/${context.ibzcase}/ibzlibcasesteps`,data,isloading);
            
            return res;
        }
        if(context.ibzcase && true){
            let masterData:any = {};
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/ibzcases/${context.ibzcase}/ibzlibcasesteps`,data,isloading);
            
            return res;
        }
    }

    /**
     * Get接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzLibCasestepsServiceBase
     */
    public async Get(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.ibzlib && context.ibzcase && context.ibzlibcasesteps){
            let res:any = await Http.getInstance().get(`/ibzlibs/${context.ibzlib}/ibzcases/${context.ibzcase}/ibzlibcasesteps/${context.ibzlibcasesteps}`,isloading);
            
            return res;
        }
        if(context.ibzcase && context.ibzlibcasesteps){
            let res:any = await Http.getInstance().get(`/ibzcases/${context.ibzcase}/ibzlibcasesteps/${context.ibzlibcasesteps}`,isloading);
            
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
     * @memberof IbzLibCasestepsServiceBase
     */
    public async GetDraft(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.ibzlib && context.ibzcase && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.ibzlibcasesteps) delete tempData.ibzlibcasesteps;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/ibzlibs/${context.ibzlib}/ibzcases/${context.ibzcase}/ibzlibcasesteps/getdraft`,tempData,isloading);
            res.data.ibzlibcasesteps = data.ibzlibcasesteps;
            
            return res;
        }
        if(context.ibzcase && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.ibzlibcasesteps) delete tempData.ibzlibcasesteps;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/ibzcases/${context.ibzcase}/ibzlibcasesteps/getdraft`,tempData,isloading);
            res.data.ibzlibcasesteps = data.ibzlibcasesteps;
            
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
     * @memberof IbzLibCasestepsServiceBase
     */
    public async Remove(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.ibzlib && context.ibzcase && context.ibzlibcasesteps){
            let res:any = await Http.getInstance().delete(`/ibzlibs/${context.ibzlib}/ibzcases/${context.ibzcase}/ibzlibcasesteps/${context.ibzlibcasesteps}`,isloading);
            return res;
        }
        if(context.ibzcase && context.ibzlibcasesteps){
            let res:any = await Http.getInstance().delete(`/ibzcases/${context.ibzcase}/ibzlibcasesteps/${context.ibzlibcasesteps}`,isloading);
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
     * @memberof IbzLibCasestepsServiceBase
     */
    public async Save(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.ibzlib && context.ibzcase && context.ibzlibcasesteps){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/ibzlibs/${context.ibzlib}/ibzcases/${context.ibzcase}/ibzlibcasesteps/${context.ibzlibcasesteps}/save`,data,isloading);
            
            return res;
        }
        if(context.ibzcase && context.ibzlibcasesteps){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/ibzcases/${context.ibzcase}/ibzlibcasesteps/${context.ibzlibcasesteps}/save`,data,isloading);
            
            return res;
        }
    }

    /**
     * Update接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzLibCasestepsServiceBase
     */
    public async Update(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.ibzlib && context.ibzcase && context.ibzlibcasesteps){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/ibzlibs/${context.ibzlib}/ibzcases/${context.ibzcase}/ibzlibcasesteps/${context.ibzlibcasesteps}`,data,isloading);
            
            return res;
        }
        if(context.ibzcase && context.ibzlibcasesteps){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/ibzcases/${context.ibzcase}/ibzlibcasesteps/${context.ibzlibcasesteps}`,data,isloading);
            
            return res;
        }
    }

    /**
     * FetchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzLibCasestepsServiceBase
     */
    public async FetchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.ibzlib && context.ibzcase && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/ibzlibs/${context.ibzlib}/ibzcases/${context.ibzcase}/ibzlibcasesteps/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.ibzcase && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/ibzcases/${context.ibzcase}/ibzlibcasesteps/fetchdefault`,tempData,isloading);
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
     * @memberof IbzLibCasestepsServiceBase
     */
    public async searchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.ibzlib && context.ibzcase && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/ibzlibs/${context.ibzlib}/ibzcases/${context.ibzcase}/ibzlibcasesteps/searchdefault`,tempData,isloading);
        }
        if(context.ibzcase && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/ibzcases/${context.ibzcase}/ibzlibcasesteps/searchdefault`,tempData,isloading);
        }
    }

    /**
     * FetchTempDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzLibCasestepsServiceBase
     */
    public async FetchTempDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchTempDefault ---FETCHTEMP
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzlibcasesteps'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzlibcasesteps') as any);
            if(result){
                return {"status":200,"data":result};
            }else{
                return {"status":200,"data":[]};
            } 
        }else{
            return {"status":200,"data":[]};
        }
    }

    /**
     * Select接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzLibCasestepsServiceBase
     */
    public async Select(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.ibzlib && context.ibzcase && context.ibzlibcasesteps){
            let res:any = await Http.getInstance().get(`/ibzlibs/${context.ibzlib}/ibzcases/${context.ibzcase}/ibzlibcasesteps/${context.ibzlibcasesteps}/select`,isloading);
            
            return res;
        }
        if(context.ibzcase && context.ibzlibcasesteps){
            let res:any = await Http.getInstance().get(`/ibzcases/${context.ibzcase}/ibzlibcasesteps/${context.ibzlibcasesteps}/select`,isloading);
            
            return res;
        }
    }
}