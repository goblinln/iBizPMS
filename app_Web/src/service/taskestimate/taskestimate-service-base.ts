import { Http,Util } from '@/utils';
import EntityService from '../entity-service';



/**
 * 任务预计服务对象基类
 *
 * @export
 * @class TaskestimateServiceBase
 * @extends {EntityServie}
 */
export default class TaskestimateServiceBase extends EntityService {

    /**
     * Creates an instance of  TaskestimateServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  TaskestimateServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     *
     * @memberof TaskestimateServiceBase
     */
    public initBasicData(){
        this.APPLYDEKEY ='taskestimate';
        this.APPDEKEY = 'id';
        this.APPDENAME = 'taskestimates';
        this.APPDETEXT = 'id';
        this.APPNAME = 'web';
        this.SYSTEMNAME = 'pms';
    }

// 实体接口

    /**
     * Select接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskestimateServiceBase
     */
    public async Select(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task && context.taskestimate){
            let res:any = Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/taskestimates/${context.taskestimate}/select`,isloading);
            
            return res;
        }
        if(context.project && context.task && context.taskestimate){
            let res:any = Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/taskestimates/${context.taskestimate}/select`,isloading);
            
            return res;
        }
        if(context.story && context.task && context.taskestimate){
            let res:any = Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/taskestimates/${context.taskestimate}/select`,isloading);
            
            return res;
        }
        if(context.task && context.taskestimate){
            let res:any = Http.getInstance().get(`/tasks/${context.task}/taskestimates/${context.taskestimate}/select`,isloading);
            
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
     * @memberof TaskestimateServiceBase
     */
    public async Create(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task && true){
            let masterData:any = {};
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/taskestimates`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && true){
            let masterData:any = {};
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/taskestimates`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && true){
            let masterData:any = {};
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/taskestimates`,data,isloading);
            
            return res;
        }
        if(context.task && true){
            let masterData:any = {};
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/taskestimates`,data,isloading);
            
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
     * @memberof TaskestimateServiceBase
     */
    public async Update(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task && context.taskestimate){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/taskestimates/${context.taskestimate}`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.taskestimate){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/tasks/${context.task}/taskestimates/${context.taskestimate}`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.taskestimate){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/stories/${context.story}/tasks/${context.task}/taskestimates/${context.taskestimate}`,data,isloading);
            
            return res;
        }
        if(context.task && context.taskestimate){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/tasks/${context.task}/taskestimates/${context.taskestimate}`,data,isloading);
            
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
     * @memberof TaskestimateServiceBase
     */
    public async Remove(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task && context.taskestimate){
            let res:any = Http.getInstance().delete(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/taskestimates/${context.taskestimate}`,isloading);
            return res;
        }
        if(context.project && context.task && context.taskestimate){
            let res:any = Http.getInstance().delete(`/projects/${context.project}/tasks/${context.task}/taskestimates/${context.taskestimate}`,isloading);
            return res;
        }
        if(context.story && context.task && context.taskestimate){
            let res:any = Http.getInstance().delete(`/stories/${context.story}/tasks/${context.task}/taskestimates/${context.taskestimate}`,isloading);
            return res;
        }
        if(context.task && context.taskestimate){
            let res:any = Http.getInstance().delete(`/tasks/${context.task}/taskestimates/${context.taskestimate}`,isloading);
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
     * @memberof TaskestimateServiceBase
     */
    public async Get(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task && context.taskestimate){
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/taskestimates/${context.taskestimate}`,isloading);
            
            return res;
        }
        if(context.project && context.task && context.taskestimate){
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/taskestimates/${context.taskestimate}`,isloading);
            
            return res;
        }
        if(context.story && context.task && context.taskestimate){
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/taskestimates/${context.taskestimate}`,isloading);
            
            return res;
        }
        if(context.task && context.taskestimate){
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/taskestimates/${context.taskestimate}`,isloading);
            
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
     * @memberof TaskestimateServiceBase
     */
    public async GetDraft(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task && true){
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/taskestimates/getdraft`,isloading);
            res.data.taskestimate = data.taskestimate;
            
            return res;
        }
        if(context.project && context.task && true){
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/taskestimates/getdraft`,isloading);
            res.data.taskestimate = data.taskestimate;
            
            return res;
        }
        if(context.story && context.task && true){
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/taskestimates/getdraft`,isloading);
            res.data.taskestimate = data.taskestimate;
            
            return res;
        }
        if(context.task && true){
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/taskestimates/getdraft`,isloading);
            res.data.taskestimate = data.taskestimate;
            
            return res;
        }
    }

    /**
     * CheckKey接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskestimateServiceBase
     */
    public async CheckKey(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task && context.taskestimate){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/taskestimates/${context.taskestimate}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.taskestimate){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/taskestimates/${context.taskestimate}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.taskestimate){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/taskestimates/${context.taskestimate}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.task && context.taskestimate){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/taskestimates/${context.taskestimate}/checkkey`,data,isloading);
            
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
     * @memberof TaskestimateServiceBase
     */
    public async Save(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task && context.taskestimate){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/taskestimates/${context.taskestimate}/save`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.taskestimate){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/taskestimates/${context.taskestimate}/save`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.taskestimate){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/taskestimates/${context.taskestimate}/save`,data,isloading);
            
            return res;
        }
        if(context.task && context.taskestimate){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/taskestimates/${context.taskestimate}/save`,data,isloading);
            
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
     * @memberof TaskestimateServiceBase
     */
    public async FetchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/taskestimates/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/taskestimates/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/taskestimates/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/tasks/${context.task}/taskestimates/fetchdefault`,tempData,isloading);
            return res;
        }
    }
}