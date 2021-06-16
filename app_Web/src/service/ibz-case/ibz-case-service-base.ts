import { Environment } from '@/environments/environment';
import { Http } from '@/utils';
import { Util } from '@/utils';
import EntityService from '../entity-service';



/**
 * 测试用例服务对象基类
 *
 * @export
 * @class IbzCaseServiceBase
 * @extends {EntityServie}
 */
export default class IbzCaseServiceBase extends EntityService {

    /**
     * Creates an instance of  IbzCaseServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzCaseServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     *
     * @memberof IbzCaseServiceBase
     */
    public initBasicData(){
        this.APPLYDEKEY ='ibzcase';
        this.APPDEKEY = 'id';
        this.APPDENAME = 'ibzcases';
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
     * @memberof IbzCaseServiceBase
     */
    public async CheckKey(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.ibzlib && context.ibzcase){
            let masterData:any = {};
        let ibzlibcasesteptmpsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzlibcasesteptmps'),'undefined')){
            ibzlibcasesteptmpsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzlibcasesteptmps') as any);
            if(ibzlibcasesteptmpsData && ibzlibcasesteptmpsData.length && ibzlibcasesteptmpsData.length > 0 && Environment.isStudioSystem === false){
                ibzlibcasesteptmpsData.forEach((item:any) => {
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
        masterData.ibzlibcasesteptmps = ibzlibcasesteptmpsData;
        let ibzlibcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzlibcasesteps'),'undefined')){
            ibzlibcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzlibcasesteps') as any);
            if(ibzlibcasestepsData && ibzlibcasestepsData.length && ibzlibcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzlibcasestepsData.forEach((item:any) => {
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
        masterData.ibzlibcasesteps = ibzlibcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/ibzlibs/${context.ibzlib}/ibzcases/${context.ibzcase}/checkkey`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibzlibcasesteptmps',JSON.stringify(res.data.ibzlibcasesteptmps?res.data.ibzlibcasesteptmps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzlibcasesteps',JSON.stringify(res.data.ibzlibcasesteps?res.data.ibzlibcasesteps:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/ibzcases/${context.ibzcase}/checkkey`,data,isloading);
            return res;
    }

    /**
     * Create接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzCaseServiceBase
     */
    public async Create(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.ibzlib && true){
            let masterData:any = {};
        let ibzlibcasesteptmpsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzlibcasesteptmps'),'undefined')){
            ibzlibcasesteptmpsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzlibcasesteptmps') as any);
            if(ibzlibcasesteptmpsData && ibzlibcasesteptmpsData.length && ibzlibcasesteptmpsData.length > 0 && Environment.isStudioSystem === false){
                ibzlibcasesteptmpsData.forEach((item:any) => {
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
        masterData.ibzlibcasesteptmps = ibzlibcasesteptmpsData;
        let ibzlibcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzlibcasesteps'),'undefined')){
            ibzlibcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzlibcasesteps') as any);
            if(ibzlibcasestepsData && ibzlibcasestepsData.length && ibzlibcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzlibcasestepsData.forEach((item:any) => {
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
        masterData.ibzlibcasesteps = ibzlibcasestepsData;
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/ibzlibs/${context.ibzlib}/ibzcases`,data,isloading);
            this.tempStorage.setItem(tempContext.srfsessionkey+'_ibzlibcasesteptmps',JSON.stringify(res.data.ibzlibcasesteptmps?res.data.ibzlibcasesteptmps:[]));
            this.tempStorage.setItem(tempContext.srfsessionkey+'_ibzlibcasesteps',JSON.stringify(res.data.ibzlibcasesteps?res.data.ibzlibcasesteps:[]));
            
            return res;
        }
        let masterData:any = {};
        let ibzlibcasesteptmpsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzlibcasesteptmps'),'undefined')){
            ibzlibcasesteptmpsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzlibcasesteptmps') as any);
            if(ibzlibcasesteptmpsData && ibzlibcasesteptmpsData.length && ibzlibcasesteptmpsData.length > 0 && Environment.isStudioSystem === false){
                ibzlibcasesteptmpsData.forEach((item:any) => {
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
        masterData.ibzlibcasesteptmps = ibzlibcasesteptmpsData;
        let ibzlibcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzlibcasesteps'),'undefined')){
            ibzlibcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzlibcasesteps') as any);
            if(ibzlibcasestepsData && ibzlibcasestepsData.length && ibzlibcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzlibcasestepsData.forEach((item:any) => {
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
        masterData.ibzlibcasesteps = ibzlibcasestepsData;
        Object.assign(data,masterData);
        if(!data.srffrontuf || data.srffrontuf !== "1"){
            data[this.APPDEKEY] = null;
        }
        if(data.srffrontuf){
            delete data.srffrontuf;
        }
        let tempContext:any = JSON.parse(JSON.stringify(context));
        let res:any = await Http.getInstance().post(`/ibzcases`,data,isloading);
        this.tempStorage.setItem(tempContext.srfsessionkey+'_ibzlibcasesteptmps',JSON.stringify(res.data.ibzlibcasesteptmps?res.data.ibzlibcasesteptmps:[]));
        this.tempStorage.setItem(tempContext.srfsessionkey+'_ibzlibcasesteps',JSON.stringify(res.data.ibzlibcasesteps?res.data.ibzlibcasesteps:[]));
        
        return res;
    }

    /**
     * Get接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzCaseServiceBase
     */
    public async Get(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.ibzlib && context.ibzcase){
            let res:any = await Http.getInstance().get(`/ibzlibs/${context.ibzlib}/ibzcases/${context.ibzcase}`,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibzlibcasesteptmps',JSON.stringify(res.data.ibzlibcasesteptmps?res.data.ibzlibcasesteptmps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzlibcasesteps',JSON.stringify(res.data.ibzlibcasesteps?res.data.ibzlibcasesteps:[]));

            return res;
        }
            let res:any = await Http.getInstance().get(`/ibzcases/${context.ibzcase}`,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibzlibcasesteptmps',JSON.stringify(res.data.ibzlibcasesteptmps?res.data.ibzlibcasesteptmps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzlibcasesteps',JSON.stringify(res.data.ibzlibcasesteps?res.data.ibzlibcasesteps:[]));

            return res;
    }

    /**
     * GetDraft接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzCaseServiceBase
     */
    public async GetDraft(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.ibzlib && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.ibzcase) delete tempData.ibzcase;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/ibzlibs/${context.ibzlib}/ibzcases/getdraft`,tempData,isloading);
            res.data.ibzcase = data.ibzcase;
                        this.tempStorage.setItem(context.srfsessionkey+'_ibzlibcasesteptmps',JSON.stringify(res.data.ibzlibcasesteptmps?res.data.ibzlibcasesteptmps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzlibcasesteps',JSON.stringify(res.data.ibzlibcasesteps?res.data.ibzlibcasesteps:[]));

            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        if(tempData.ibzcase) delete tempData.ibzcase;
        if(tempData.id) delete tempData.id;
        let res:any = await  Http.getInstance().get(`/ibzcases/getdraft`,tempData,isloading);
        res.data.ibzcase = data.ibzcase;
                    this.tempStorage.setItem(context.srfsessionkey+'_ibzlibcasesteptmps',JSON.stringify(res.data.ibzlibcasesteptmps?res.data.ibzlibcasesteptmps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzlibcasesteps',JSON.stringify(res.data.ibzlibcasesteps?res.data.ibzlibcasesteps:[]));

        return res;
    }

    /**
     * Remove接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzCaseServiceBase
     */
    public async Remove(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.ibzlib && context.ibzcase){
            let res:any = await Http.getInstance().delete(`/ibzlibs/${context.ibzlib}/ibzcases/${context.ibzcase}`,isloading);
            return res;
        }
            let res:any = await Http.getInstance().delete(`/ibzcases/${context.ibzcase}`,isloading);
            return res;
    }

    /**
     * Save接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzCaseServiceBase
     */
    public async Save(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.ibzlib && context.ibzcase){
            let masterData:any = {};
        let ibzlibcasesteptmpsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzlibcasesteptmps'),'undefined')){
            ibzlibcasesteptmpsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzlibcasesteptmps') as any);
            if(ibzlibcasesteptmpsData && ibzlibcasesteptmpsData.length && ibzlibcasesteptmpsData.length > 0 && Environment.isStudioSystem === false){
                ibzlibcasesteptmpsData.forEach((item:any) => {
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
        masterData.ibzlibcasesteptmps = ibzlibcasesteptmpsData;
        let ibzlibcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzlibcasesteps'),'undefined')){
            ibzlibcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzlibcasesteps') as any);
            if(ibzlibcasestepsData && ibzlibcasestepsData.length && ibzlibcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzlibcasestepsData.forEach((item:any) => {
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
        masterData.ibzlibcasesteps = ibzlibcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/ibzlibs/${context.ibzlib}/ibzcases/${context.ibzcase}/save`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibzlibcasesteptmps',JSON.stringify(res.data.ibzlibcasesteptmps?res.data.ibzlibcasesteptmps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzlibcasesteps',JSON.stringify(res.data.ibzlibcasesteps?res.data.ibzlibcasesteps:[]));

            return res;
        }
        let masterData:any = {};
        let ibzlibcasesteptmpsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzlibcasesteptmps'),'undefined')){
            ibzlibcasesteptmpsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzlibcasesteptmps') as any);
            if(ibzlibcasesteptmpsData && ibzlibcasesteptmpsData.length && ibzlibcasesteptmpsData.length > 0 && Environment.isStudioSystem === false){
                ibzlibcasesteptmpsData.forEach((item:any) => {
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
        masterData.ibzlibcasesteptmps = ibzlibcasesteptmpsData;
        let ibzlibcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzlibcasesteps'),'undefined')){
            ibzlibcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzlibcasesteps') as any);
            if(ibzlibcasestepsData && ibzlibcasestepsData.length && ibzlibcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzlibcasestepsData.forEach((item:any) => {
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
        masterData.ibzlibcasesteps = ibzlibcasestepsData;
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().post(`/ibzcases/${context.ibzcase}/save`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibzlibcasesteptmps',JSON.stringify(res.data.ibzlibcasesteptmps?res.data.ibzlibcasesteptmps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzlibcasesteps',JSON.stringify(res.data.ibzlibcasesteps?res.data.ibzlibcasesteps:[]));

            return res;
    }

    /**
     * Update接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzCaseServiceBase
     */
    public async Update(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.ibzlib && context.ibzcase){
            let masterData:any = {};
        let ibzlibcasesteptmpsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzlibcasesteptmps'),'undefined')){
            ibzlibcasesteptmpsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzlibcasesteptmps') as any);
            if(ibzlibcasesteptmpsData && ibzlibcasesteptmpsData.length && ibzlibcasesteptmpsData.length > 0 && Environment.isStudioSystem === false){
                ibzlibcasesteptmpsData.forEach((item:any) => {
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
        masterData.ibzlibcasesteptmps = ibzlibcasesteptmpsData;
        let ibzlibcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzlibcasesteps'),'undefined')){
            ibzlibcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzlibcasesteps') as any);
            if(ibzlibcasestepsData && ibzlibcasestepsData.length && ibzlibcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzlibcasestepsData.forEach((item:any) => {
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
        masterData.ibzlibcasesteps = ibzlibcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/ibzlibs/${context.ibzlib}/ibzcases/${context.ibzcase}`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibzlibcasesteptmps',JSON.stringify(res.data.ibzlibcasesteptmps?res.data.ibzlibcasesteptmps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzlibcasesteps',JSON.stringify(res.data.ibzlibcasesteps?res.data.ibzlibcasesteps:[]));

            return res;
        }
        let masterData:any = {};
        let ibzlibcasesteptmpsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzlibcasesteptmps'),'undefined')){
            ibzlibcasesteptmpsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzlibcasesteptmps') as any);
            if(ibzlibcasesteptmpsData && ibzlibcasesteptmpsData.length && ibzlibcasesteptmpsData.length > 0 && Environment.isStudioSystem === false){
                ibzlibcasesteptmpsData.forEach((item:any) => {
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
        masterData.ibzlibcasesteptmps = ibzlibcasesteptmpsData;
        let ibzlibcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzlibcasesteps'),'undefined')){
            ibzlibcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzlibcasesteps') as any);
            if(ibzlibcasestepsData && ibzlibcasestepsData.length && ibzlibcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzlibcasestepsData.forEach((item:any) => {
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
        masterData.ibzlibcasesteps = ibzlibcasestepsData;
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().put(`/ibzcases/${context.ibzcase}`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibzlibcasesteptmps',JSON.stringify(res.data.ibzlibcasesteptmps?res.data.ibzlibcasesteptmps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzlibcasesteps',JSON.stringify(res.data.ibzlibcasesteps?res.data.ibzlibcasesteps:[]));

            return res;
    }

    /**
     * FetchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzCaseServiceBase
     */
    public async FetchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.ibzlib && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/ibzlibs/${context.ibzlib}/ibzcases/fetchdefault`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/ibzcases/fetchdefault`,tempData,isloading);
        return res;
    }

    /**
     * searchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzCaseServiceBase
     */
    public async searchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.ibzlib && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/ibzlibs/${context.ibzlib}/ibzcases/searchdefault`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/ibzcases/searchdefault`,tempData,isloading);
    }

    /**
     * FetchTempDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzCaseServiceBase
     */
    public async FetchTempDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * Select接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzCaseServiceBase
     */
    public async Select(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.ibzlib && context.ibzcase){
            let res:any = await Http.getInstance().get(`/ibzlibs/${context.ibzlib}/ibzcases/${context.ibzcase}/select`,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().get(`/ibzcases/${context.ibzcase}/select`,isloading);
            
            return res;
    }
}