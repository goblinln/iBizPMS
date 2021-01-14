import { Http } from '@/utils';
import { Util } from '@/utils';
import EntityService from '../entity-service';



/**
 * 任务预计服务对象基类
 *
 * @export
 * @class TaskEstimateServiceBase
 * @extends {EntityServie}
 */
export default class TaskEstimateServiceBase extends EntityService {

    /**
     * Creates an instance of  TaskEstimateServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  TaskEstimateServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     *
     * @memberof TaskEstimateServiceBase
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
     * @memberof TaskEstimateServiceBase
     */
    public async Select(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productmodule && context.story && context.task && context.taskestimate){
            let res:any = Http.getInstance().get(`/products/${context.product}/productmodules/${context.productmodule}/stories/${context.story}/tasks/${context.task}/taskestimates/${context.taskestimate}/select`,isloading);
            
            return res;
        }
        if(context.project && context.projectmodule && context.task && context.taskestimate){
            let res:any = Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/taskestimates/${context.taskestimate}/select`,isloading);
            
            return res;
        }
        if(context.productmodule && context.story && context.task && context.taskestimate){
            let res:any = Http.getInstance().get(`/productmodules/${context.productmodule}/stories/${context.story}/tasks/${context.task}/taskestimates/${context.taskestimate}/select`,isloading);
            
            return res;
        }
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
        if(context.projectmodule && context.task && context.taskestimate){
            let res:any = Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/taskestimates/${context.taskestimate}/select`,isloading);
            
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
     * @memberof TaskEstimateServiceBase
     */
    public async Create(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productmodule && context.story && context.task && true){
            let masterData:any = {};
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/products/${context.product}/productmodules/${context.productmodule}/stories/${context.story}/tasks/${context.task}/taskestimates`,data,isloading);
            
            return res;
        }
        if(context.project && context.projectmodule && context.task && true){
            let masterData:any = {};
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/taskestimates`,data,isloading);
            
            return res;
        }
        if(context.productmodule && context.story && context.task && true){
            let masterData:any = {};
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/productmodules/${context.productmodule}/stories/${context.story}/tasks/${context.task}/taskestimates`,data,isloading);
            
            return res;
        }
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
        if(context.projectmodule && context.task && true){
            let masterData:any = {};
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/taskestimates`,data,isloading);
            
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
     * @memberof TaskEstimateServiceBase
     */
    public async Update(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productmodule && context.story && context.task && context.taskestimate){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/productmodules/${context.productmodule}/stories/${context.story}/tasks/${context.task}/taskestimates/${context.taskestimate}`,data,isloading);
            
            return res;
        }
        if(context.project && context.projectmodule && context.task && context.taskestimate){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/taskestimates/${context.taskestimate}`,data,isloading);
            
            return res;
        }
        if(context.productmodule && context.story && context.task && context.taskestimate){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/productmodules/${context.productmodule}/stories/${context.story}/tasks/${context.task}/taskestimates/${context.taskestimate}`,data,isloading);
            
            return res;
        }
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
        if(context.projectmodule && context.task && context.taskestimate){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projectmodules/${context.projectmodule}/tasks/${context.task}/taskestimates/${context.taskestimate}`,data,isloading);
            
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
     * @memberof TaskEstimateServiceBase
     */
    public async Remove(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productmodule && context.story && context.task && context.taskestimate){
            let res:any = Http.getInstance().delete(`/products/${context.product}/productmodules/${context.productmodule}/stories/${context.story}/tasks/${context.task}/taskestimates/${context.taskestimate}`,isloading);
            return res;
        }
        if(context.project && context.projectmodule && context.task && context.taskestimate){
            let res:any = Http.getInstance().delete(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/taskestimates/${context.taskestimate}`,isloading);
            return res;
        }
        if(context.productmodule && context.story && context.task && context.taskestimate){
            let res:any = Http.getInstance().delete(`/productmodules/${context.productmodule}/stories/${context.story}/tasks/${context.task}/taskestimates/${context.taskestimate}`,isloading);
            return res;
        }
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
        if(context.projectmodule && context.task && context.taskestimate){
            let res:any = Http.getInstance().delete(`/projectmodules/${context.projectmodule}/tasks/${context.task}/taskestimates/${context.taskestimate}`,isloading);
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
     * @memberof TaskEstimateServiceBase
     */
    public async Get(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productmodule && context.story && context.task && context.taskestimate){
            let res:any = await Http.getInstance().get(`/products/${context.product}/productmodules/${context.productmodule}/stories/${context.story}/tasks/${context.task}/taskestimates/${context.taskestimate}`,isloading);
            
            return res;
        }
        if(context.project && context.projectmodule && context.task && context.taskestimate){
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/taskestimates/${context.taskestimate}`,isloading);
            
            return res;
        }
        if(context.productmodule && context.story && context.task && context.taskestimate){
            let res:any = await Http.getInstance().get(`/productmodules/${context.productmodule}/stories/${context.story}/tasks/${context.task}/taskestimates/${context.taskestimate}`,isloading);
            
            return res;
        }
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
        if(context.projectmodule && context.task && context.taskestimate){
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/taskestimates/${context.taskestimate}`,isloading);
            
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
     * @memberof TaskEstimateServiceBase
     */
    public async GetDraft(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productmodule && context.story && context.task && true){
            let res:any = await Http.getInstance().get(`/products/${context.product}/productmodules/${context.productmodule}/stories/${context.story}/tasks/${context.task}/taskestimates/getdraft`,isloading);
            res.data.taskestimate = data.taskestimate;
            
            return res;
        }
        if(context.project && context.projectmodule && context.task && true){
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/taskestimates/getdraft`,isloading);
            res.data.taskestimate = data.taskestimate;
            
            return res;
        }
        if(context.productmodule && context.story && context.task && true){
            let res:any = await Http.getInstance().get(`/productmodules/${context.productmodule}/stories/${context.story}/tasks/${context.task}/taskestimates/getdraft`,isloading);
            res.data.taskestimate = data.taskestimate;
            
            return res;
        }
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
        if(context.projectmodule && context.task && true){
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/taskestimates/getdraft`,isloading);
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
     * @memberof TaskEstimateServiceBase
     */
    public async CheckKey(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productmodule && context.story && context.task && context.taskestimate){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productmodules/${context.productmodule}/stories/${context.story}/tasks/${context.task}/taskestimates/${context.taskestimate}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.project && context.projectmodule && context.task && context.taskestimate){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/taskestimates/${context.taskestimate}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.productmodule && context.story && context.task && context.taskestimate){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productmodules/${context.productmodule}/stories/${context.story}/tasks/${context.task}/taskestimates/${context.taskestimate}/checkkey`,data,isloading);
            
            return res;
        }
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
        if(context.projectmodule && context.task && context.taskestimate){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/taskestimates/${context.taskestimate}/checkkey`,data,isloading);
            
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
     * @memberof TaskEstimateServiceBase
     */
    public async Save(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productmodule && context.story && context.task && context.taskestimate){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productmodules/${context.productmodule}/stories/${context.story}/tasks/${context.task}/taskestimates/${context.taskestimate}/save`,data,isloading);
            
            return res;
        }
        if(context.project && context.projectmodule && context.task && context.taskestimate){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/taskestimates/${context.taskestimate}/save`,data,isloading);
            
            return res;
        }
        if(context.productmodule && context.story && context.task && context.taskestimate){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productmodules/${context.productmodule}/stories/${context.story}/tasks/${context.task}/taskestimates/${context.taskestimate}/save`,data,isloading);
            
            return res;
        }
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
        if(context.projectmodule && context.task && context.taskestimate){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/taskestimates/${context.taskestimate}/save`,data,isloading);
            
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
     * @memberof TaskEstimateServiceBase
     */
    public async FetchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productmodule && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/products/${context.product}/productmodules/${context.productmodule}/stories/${context.story}/tasks/${context.task}/taskestimates/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/taskestimates/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.productmodule && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/productmodules/${context.productmodule}/stories/${context.story}/tasks/${context.task}/taskestimates/fetchdefault`,tempData,isloading);
            return res;
        }
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
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/taskestimates/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/tasks/${context.task}/taskestimates/fetchdefault`,tempData,isloading);
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
     * @memberof TaskEstimateServiceBase
     */
    public async searchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productmodule && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productmodules/${context.productmodule}/stories/${context.story}/tasks/${context.task}/taskestimates/searchdefault`,tempData,isloading);
        }
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/taskestimates/searchdefault`,tempData,isloading);
        }
        if(context.productmodule && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productmodules/${context.productmodule}/stories/${context.story}/tasks/${context.task}/taskestimates/searchdefault`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/taskestimates/searchdefault`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/taskestimates/searchdefault`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/taskestimates/searchdefault`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/taskestimates/searchdefault`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/taskestimates/searchdefault`,tempData,isloading);
        }
    }

    /**
     * FetchDefaults接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskEstimateServiceBase
     */
    public async FetchDefaults(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productmodule && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/products/${context.product}/productmodules/${context.productmodule}/stories/${context.story}/tasks/${context.task}/taskestimates/fetchdefaults`,tempData,isloading);
            return res;
        }
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/taskestimates/fetchdefaults`,tempData,isloading);
            return res;
        }
        if(context.productmodule && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/productmodules/${context.productmodule}/stories/${context.story}/tasks/${context.task}/taskestimates/fetchdefaults`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/taskestimates/fetchdefaults`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/taskestimates/fetchdefaults`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/taskestimates/fetchdefaults`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/taskestimates/fetchdefaults`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/tasks/${context.task}/taskestimates/fetchdefaults`,tempData,isloading);
            return res;
        }
    }

    /**
     * searchDefaults接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskEstimateServiceBase
     */
    public async searchDefaults(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.productmodule && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productmodules/${context.productmodule}/stories/${context.story}/tasks/${context.task}/taskestimates/searchdefaults`,tempData,isloading);
        }
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/taskestimates/searchdefaults`,tempData,isloading);
        }
        if(context.productmodule && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productmodules/${context.productmodule}/stories/${context.story}/tasks/${context.task}/taskestimates/searchdefaults`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/taskestimates/searchdefaults`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/taskestimates/searchdefaults`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/taskestimates/searchdefaults`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/taskestimates/searchdefaults`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/taskestimates/searchdefaults`,tempData,isloading);
        }
    }

    /**
     * FetchTempDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskEstimateServiceBase
     */
    public async FetchTempDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchTempDefault ---FETCHTEMP
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
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
     * @memberof TaskEstimateServiceBase
     */
    public async FetchTempDefaults(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchTempDefaults ---FETCHTEMP
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
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