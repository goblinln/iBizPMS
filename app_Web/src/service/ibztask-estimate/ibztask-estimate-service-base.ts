import { Environment } from '@/environments/environment';
import { Http } from '@/utils';
import { Util } from '@/utils';
import EntityService from '../entity-service';



/**
 * 任务预计服务对象基类
 *
 * @export
 * @class IBZTaskEstimateServiceBase
 * @extends {EntityServie}
 */
export default class IBZTaskEstimateServiceBase extends EntityService {

    /**
     * Creates an instance of  IBZTaskEstimateServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZTaskEstimateServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     *
     * @memberof IBZTaskEstimateServiceBase
     */
    public initBasicData(){
        this.APPLYDEKEY ='ibztaskestimate';
        this.APPDEKEY = 'id';
        this.APPDENAME = 'ibztaskestimates';
        this.APPDETEXT = 'id';
        this.APPNAME = 'web';
        this.SYSTEMNAME = 'pms';
    }

// 实体接口

    /**
     * PMEvaluation接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async PMEvaluation(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task &&  true){
            return await Http.getInstance().post(`projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/ibztaskestimates/pmevaluation`,data,isloading);
        }
        if(context.product && context.story && context.task &&  true){
            return await Http.getInstance().post(`products/${context.product}/stories/${context.story}/tasks/${context.task}/ibztaskestimates/pmevaluation`,data,isloading);
        }
        if(context.product && context.productplan && context.task &&  true){
            return await Http.getInstance().post(`products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/ibztaskestimates/pmevaluation`,data,isloading);
        }
        if(context.project && context.task &&  true){
            return await Http.getInstance().post(`projects/${context.project}/tasks/${context.task}/ibztaskestimates/pmevaluation`,data,isloading);
        }
        if(context.story && context.task &&  true){
            return await Http.getInstance().post(`stories/${context.story}/tasks/${context.task}/ibztaskestimates/pmevaluation`,data,isloading);
        }
        if(context.productplan && context.task &&  true){
            return await Http.getInstance().post(`productplans/${context.productplan}/tasks/${context.task}/ibztaskestimates/pmevaluation`,data,isloading);
        }
        if(context.projectmodule && context.task &&  true){
            return await Http.getInstance().post(`projectmodules/${context.projectmodule}/tasks/${context.task}/ibztaskestimates/pmevaluation`,data,isloading);
        }
        if(context.task &&  true){
            return await Http.getInstance().post(`tasks/${context.task}/ibztaskestimates/pmevaluation`,data,isloading);
        }
    }

    /**
     * FetchActionDate接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async FetchActionDate(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchActionDate ---FETCH
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
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
     * searchActionDate接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async searchActionDate(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchActionDateByProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async FetchActionDateByProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchActionDateByProject ---FETCH
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
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
     * searchActionDateByProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async searchActionDateByProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchActionMonth接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async FetchActionMonth(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchActionMonth ---FETCH
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
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
     * searchActionMonth接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async searchActionMonth(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchActionYear接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async FetchActionYear(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchActionYear ---FETCH
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
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
     * searchActionYear接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async searchActionYear(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchAllAccounts接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async FetchAllAccounts(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchAllAccounts ---FETCH
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
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
     * searchAllAccounts接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async searchAllAccounts(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchAllProjects接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async FetchAllProjects(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchAllProjects ---FETCH
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
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
     * searchAllProjects接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async searchAllProjects(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async FetchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchDefault ---FETCH
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
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
     * searchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async searchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchDefaults接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async FetchDefaults(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchDefaults ---FETCH
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
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
     * searchDefaults接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async searchDefaults(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchMyAccounts接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async FetchMyAccounts(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchMyAccounts ---FETCH
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
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
     * searchMyAccounts接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async searchMyAccounts(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchMyActionDate接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async FetchMyActionDate(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchMyActionDate ---FETCH
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
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
     * searchMyActionDate接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async searchMyActionDate(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchPersonalEstimate接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async FetchPersonalEstimate(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchPersonalEstimate ---FETCH
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
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
     * searchPersonalEstimate接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async searchPersonalEstimate(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchProjectActionMonth接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async FetchProjectActionMonth(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchProjectActionMonth ---FETCH
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
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
     * searchProjectActionMonth接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async searchProjectActionMonth(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchProjectActionYear接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async FetchProjectActionYear(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchProjectActionYear ---FETCH
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
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
     * searchProjectActionYear接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async searchProjectActionYear(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchProjectMemberLog接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async FetchProjectMemberLog(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchProjectMemberLog ---FETCH
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
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
     * searchProjectMemberLog接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async searchProjectMemberLog(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchProjectTaskEstimate接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async FetchProjectTaskEstimate(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchProjectTaskEstimate ---FETCH
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
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
     * searchProjectTaskEstimate接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async searchProjectTaskEstimate(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchSomeoneJoinAllPros接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async FetchSomeoneJoinAllPros(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchSomeoneJoinAllPros ---FETCH
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
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
     * searchSomeoneJoinAllPros接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async searchSomeoneJoinAllPros(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempActionDate接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async FetchTempActionDate(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchTempActionDate ---FETCHTEMP
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
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
     * FetchTempActionDateByProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async FetchTempActionDateByProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchTempActionDateByProject ---FETCHTEMP
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
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
     * FetchTempActionMonth接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async FetchTempActionMonth(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchTempActionMonth ---FETCHTEMP
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
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
     * FetchTempActionYear接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async FetchTempActionYear(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchTempActionYear ---FETCHTEMP
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
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
     * FetchTempAllAccounts接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async FetchTempAllAccounts(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchTempAllAccounts ---FETCHTEMP
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
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
     * FetchTempAllProjects接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async FetchTempAllProjects(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchTempAllProjects ---FETCHTEMP
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
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
     * FetchTempDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async FetchTempDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchTempDefault ---FETCHTEMP
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
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
     * FetchTempDefaults接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async FetchTempDefaults(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchTempDefaults ---FETCHTEMP
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
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
     * FetchTempMyAccounts接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async FetchTempMyAccounts(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchTempMyAccounts ---FETCHTEMP
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
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
     * FetchTempMyActionDate接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async FetchTempMyActionDate(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchTempMyActionDate ---FETCHTEMP
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
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
     * FetchTempPersonalEstimate接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async FetchTempPersonalEstimate(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchTempPersonalEstimate ---FETCHTEMP
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
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
     * FetchTempProjectActionMonth接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async FetchTempProjectActionMonth(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchTempProjectActionMonth ---FETCHTEMP
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
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
     * FetchTempProjectActionYear接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async FetchTempProjectActionYear(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchTempProjectActionYear ---FETCHTEMP
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
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
     * FetchTempProjectMemberLog接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async FetchTempProjectMemberLog(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchTempProjectMemberLog ---FETCHTEMP
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
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
     * FetchTempProjectTaskEstimate接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async FetchTempProjectTaskEstimate(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchTempProjectTaskEstimate ---FETCHTEMP
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
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
     * FetchTempSomeoneJoinAllPros接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTaskEstimateServiceBase
     */
    public async FetchTempSomeoneJoinAllPros(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchTempSomeoneJoinAllPros ---FETCHTEMP
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(result){
                return {"status":200,"data":result};
            }else{
                return {"status":200,"data":[]};
            } 
        }else{
            return {"status":200,"data":[]};
        }
    }
}