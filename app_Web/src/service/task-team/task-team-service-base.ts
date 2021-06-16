import { Environment } from '@/environments/environment';
import { Http } from '@/utils';
import { Util } from '@/utils';
import EntityService from '../entity-service';



/**
 * 任务团队服务对象基类
 *
 * @export
 * @class TaskTeamServiceBase
 * @extends {EntityServie}
 */
export default class TaskTeamServiceBase extends EntityService {

    /**
     * Creates an instance of  TaskTeamServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  TaskTeamServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     *
     * @memberof TaskTeamServiceBase
     */
    public initBasicData(){
        this.APPLYDEKEY ='taskteam';
        this.APPDEKEY = 'id';
        this.APPDENAME = 'taskteams';
        this.APPDETEXT = 'account';
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
     * @memberof TaskTeamServiceBase
     */
    public async CheckKey(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && context.taskteam){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/taskteams/${context.taskteam}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.taskteam){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/taskteams/${context.taskteam}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.taskteam){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/taskteams/${context.taskteam}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.taskteam){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/taskteams/${context.taskteam}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.taskteam){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/taskteams/${context.taskteam}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.taskteam){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/taskteams/${context.taskteam}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.taskteam){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/taskteams/${context.taskteam}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.task && context.taskteam){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/taskteams/${context.taskteam}/checkkey`,data,isloading);
            
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
     * @memberof TaskTeamServiceBase
     */
    public async Create(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
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
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/taskteams`,data,isloading);
            
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
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/taskteams`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let masterData:any = {};
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/taskteams`,data,isloading);
            
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
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/taskteams`,data,isloading);
            
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
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/taskteams`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && true){
            let masterData:any = {};
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/taskteams`,data,isloading);
            
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
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/taskteams`,data,isloading);
            
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
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/taskteams`,data,isloading);
            
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
     * @memberof TaskTeamServiceBase
     */
    public async Get(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && context.taskteam){
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/taskteams/${context.taskteam}`,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.taskteam){
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/taskteams/${context.taskteam}`,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.taskteam){
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/taskteams/${context.taskteam}`,isloading);
            
            return res;
        }
        if(context.project && context.task && context.taskteam){
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/taskteams/${context.taskteam}`,isloading);
            
            return res;
        }
        if(context.story && context.task && context.taskteam){
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/taskteams/${context.taskteam}`,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.taskteam){
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/taskteams/${context.taskteam}`,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.taskteam){
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/taskteams/${context.taskteam}`,isloading);
            
            return res;
        }
        if(context.task && context.taskteam){
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/taskteams/${context.taskteam}`,isloading);
            
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
     * @memberof TaskTeamServiceBase
     */
    public async GetDraft(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.taskteam) delete tempData.taskteam;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/taskteams/getdraft`,tempData,isloading);
            res.data.taskteam = data.taskteam;
            
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.taskteam) delete tempData.taskteam;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/taskteams/getdraft`,tempData,isloading);
            res.data.taskteam = data.taskteam;
            
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.taskteam) delete tempData.taskteam;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/taskteams/getdraft`,tempData,isloading);
            res.data.taskteam = data.taskteam;
            
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.taskteam) delete tempData.taskteam;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/taskteams/getdraft`,tempData,isloading);
            res.data.taskteam = data.taskteam;
            
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.taskteam) delete tempData.taskteam;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/taskteams/getdraft`,tempData,isloading);
            res.data.taskteam = data.taskteam;
            
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.taskteam) delete tempData.taskteam;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/taskteams/getdraft`,tempData,isloading);
            res.data.taskteam = data.taskteam;
            
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.taskteam) delete tempData.taskteam;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/taskteams/getdraft`,tempData,isloading);
            res.data.taskteam = data.taskteam;
            
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.taskteam) delete tempData.taskteam;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/taskteams/getdraft`,tempData,isloading);
            res.data.taskteam = data.taskteam;
            
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
     * @memberof TaskTeamServiceBase
     */
    public async Remove(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && context.taskteam){
            let res:any = await Http.getInstance().delete(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/taskteams/${context.taskteam}`,isloading);
            return res;
        }
        if(context.product && context.story && context.task && context.taskteam){
            let res:any = await Http.getInstance().delete(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/taskteams/${context.taskteam}`,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && context.taskteam){
            let res:any = await Http.getInstance().delete(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/taskteams/${context.taskteam}`,isloading);
            return res;
        }
        if(context.project && context.task && context.taskteam){
            let res:any = await Http.getInstance().delete(`/projects/${context.project}/tasks/${context.task}/taskteams/${context.taskteam}`,isloading);
            return res;
        }
        if(context.story && context.task && context.taskteam){
            let res:any = await Http.getInstance().delete(`/stories/${context.story}/tasks/${context.task}/taskteams/${context.taskteam}`,isloading);
            return res;
        }
        if(context.productplan && context.task && context.taskteam){
            let res:any = await Http.getInstance().delete(`/productplans/${context.productplan}/tasks/${context.task}/taskteams/${context.taskteam}`,isloading);
            return res;
        }
        if(context.projectmodule && context.task && context.taskteam){
            let res:any = await Http.getInstance().delete(`/projectmodules/${context.projectmodule}/tasks/${context.task}/taskteams/${context.taskteam}`,isloading);
            return res;
        }
        if(context.task && context.taskteam){
            let res:any = await Http.getInstance().delete(`/tasks/${context.task}/taskteams/${context.taskteam}`,isloading);
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
     * @memberof TaskTeamServiceBase
     */
    public async Save(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && context.taskteam){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/taskteams/${context.taskteam}/save`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.taskteam){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/taskteams/${context.taskteam}/save`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.taskteam){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/taskteams/${context.taskteam}/save`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.taskteam){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/taskteams/${context.taskteam}/save`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.taskteam){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/taskteams/${context.taskteam}/save`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.taskteam){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/taskteams/${context.taskteam}/save`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.taskteam){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/taskteams/${context.taskteam}/save`,data,isloading);
            
            return res;
        }
        if(context.task && context.taskteam){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/taskteams/${context.taskteam}/save`,data,isloading);
            
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
     * @memberof TaskTeamServiceBase
     */
    public async Update(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && context.taskteam){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/taskteams/${context.taskteam}`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.taskteam){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/taskteams/${context.taskteam}`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.taskteam){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/taskteams/${context.taskteam}`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.taskteam){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/tasks/${context.task}/taskteams/${context.taskteam}`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.taskteam){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/stories/${context.story}/tasks/${context.task}/taskteams/${context.taskteam}`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.taskteam){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/productplans/${context.productplan}/tasks/${context.task}/taskteams/${context.taskteam}`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.taskteam){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projectmodules/${context.projectmodule}/tasks/${context.task}/taskteams/${context.taskteam}`,data,isloading);
            
            return res;
        }
        if(context.task && context.taskteam){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/tasks/${context.task}/taskteams/${context.taskteam}`,data,isloading);
            
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
     * @memberof TaskTeamServiceBase
     */
    public async FetchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/taskteams/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/taskteams/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/taskteams/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/taskteams/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/taskteams/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/taskteams/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/taskteams/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/taskteams/fetchdefault`,tempData,isloading);
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
     * @memberof TaskTeamServiceBase
     */
    public async searchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/taskteams/searchdefault`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/taskteams/searchdefault`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/taskteams/searchdefault`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/taskteams/searchdefault`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/taskteams/searchdefault`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/taskteams/searchdefault`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/taskteams/searchdefault`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/taskteams/searchdefault`,tempData,isloading);
        }
    }

    /**
     * FetchTempDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskTeamServiceBase
     */
    public async FetchTempDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        // FetchTempDefault ---FETCHTEMP
        if(context.srfsessionkey && !Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            let result:any = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
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
     * @memberof TaskTeamServiceBase
     */
    public async Select(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && context.taskteam){
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/taskteams/${context.taskteam}/select`,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.taskteam){
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/taskteams/${context.taskteam}/select`,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.taskteam){
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/taskteams/${context.taskteam}/select`,isloading);
            
            return res;
        }
        if(context.project && context.task && context.taskteam){
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/taskteams/${context.taskteam}/select`,isloading);
            
            return res;
        }
        if(context.story && context.task && context.taskteam){
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/taskteams/${context.taskteam}/select`,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.taskteam){
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/taskteams/${context.taskteam}/select`,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.taskteam){
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/taskteams/${context.taskteam}/select`,isloading);
            
            return res;
        }
        if(context.task && context.taskteam){
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/taskteams/${context.taskteam}/select`,isloading);
            
            return res;
        }
    }
}