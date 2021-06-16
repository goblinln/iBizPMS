import { Environment } from '@/environments/environment';
import { Http } from '@/utils';
import { Util } from '@/utils';
import EntityService from '../entity-service';



/**
 * 用例库服务对象基类
 *
 * @export
 * @class IbzLibServiceBase
 * @extends {EntityServie}
 */
export default class IbzLibServiceBase extends EntityService {

    /**
     * Creates an instance of  IbzLibServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzLibServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     *
     * @memberof IbzLibServiceBase
     */
    public initBasicData(){
        this.APPLYDEKEY ='ibzlib';
        this.APPDEKEY = 'id';
        this.APPDENAME = 'ibzlibs';
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
     * @memberof IbzLibServiceBase
     */
    public async CheckKey(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().post(`/ibzlibs/${context.ibzlib}/checkkey`,data,isloading);
            return res;
    }

    /**
     * Create接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzLibServiceBase
     */
    public async Create(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let masterData:any = {};
        let ibzcasesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcases'),'undefined')){
            ibzcasesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcases') as any);
            if(ibzcasesData && ibzcasesData.length && ibzcasesData.length > 0 && Environment.isStudioSystem === false){
                ibzcasesData.forEach((item:any) => {
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
        masterData.ibzcases = ibzcasesData;
        Object.assign(data,masterData);
        if(!data.srffrontuf || data.srffrontuf !== "1"){
            data[this.APPDEKEY] = null;
        }
        if(data.srffrontuf){
            delete data.srffrontuf;
        }
        let tempContext:any = JSON.parse(JSON.stringify(context));
        let res:any = await Http.getInstance().post(`/ibzlibs`,data,isloading);
        this.tempStorage.setItem(tempContext.srfsessionkey+'_ibzcases',JSON.stringify(res.data.ibzcases?res.data.ibzcases:[]));
        this.tempStorage.setItem(tempContext.srfsessionkey+'_ibzlibmodules',JSON.stringify(res.data.ibzlibmodules?res.data.ibzlibmodules:[]));
        
        return res;
    }

    /**
     * Get接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzLibServiceBase
     */
    public async Get(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().get(`/ibzlibs/${context.ibzlib}`,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibzcases',JSON.stringify(res.data.ibzcases?res.data.ibzcases:[]));

            return res;
    }

    /**
     * GetDraft接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzLibServiceBase
     */
    public async GetDraft(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        if(tempData.ibzlib) delete tempData.ibzlib;
        if(tempData.id) delete tempData.id;
        let res:any = await  Http.getInstance().get(`/ibzlibs/getdraft`,tempData,isloading);
        res.data.ibzlib = data.ibzlib;
                    this.tempStorage.setItem(context.srfsessionkey+'_ibzcases',JSON.stringify(res.data.ibzcases?res.data.ibzcases:[]));

        return res;
    }

    /**
     * Remove接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzLibServiceBase
     */
    public async Remove(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().delete(`/ibzlibs/${context.ibzlib}`,isloading);
            return res;
    }

    /**
     * Save接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzLibServiceBase
     */
    public async Save(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let masterData:any = {};
        let ibzcasesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcases'),'undefined')){
            ibzcasesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcases') as any);
            if(ibzcasesData && ibzcasesData.length && ibzcasesData.length > 0 && Environment.isStudioSystem === false){
                ibzcasesData.forEach((item:any) => {
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
        masterData.ibzcases = ibzcasesData;
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().post(`/ibzlibs/${context.ibzlib}/save`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibzcases',JSON.stringify(res.data.ibzcases?res.data.ibzcases:[]));

            return res;
    }

    /**
     * Update接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzLibServiceBase
     */
    public async Update(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let masterData:any = {};
        let ibzcasesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcases'),'undefined')){
            ibzcasesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcases') as any);
            if(ibzcasesData && ibzcasesData.length && ibzcasesData.length > 0 && Environment.isStudioSystem === false){
                ibzcasesData.forEach((item:any) => {
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
        masterData.ibzcases = ibzcasesData;
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().put(`/ibzlibs/${context.ibzlib}`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibzcases',JSON.stringify(res.data.ibzcases?res.data.ibzcases:[]));

            return res;
    }

    /**
     * FetchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzLibServiceBase
     */
    public async FetchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/ibzlibs/fetchdefault`,tempData,isloading);
        return res;
    }

    /**
     * searchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzLibServiceBase
     */
    public async searchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/ibzlibs/searchdefault`,tempData,isloading);
    }

    /**
     * Select接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzLibServiceBase
     */
    public async Select(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().get(`/ibzlibs/${context.ibzlib}/select`,isloading);
            
            return res;
    }
}