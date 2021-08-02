import { Environment } from '@/environments/environment';
import { Http } from '@/utils';
import { Util } from '@/utils';
import EntityService from '../entity-service';
import GetUserConcatLogic from '@/service/sub-task/get-user-concat-logic';



/**
 * 任务服务对象基类
 *
 * @export
 * @class SubTaskServiceBase
 * @extends {EntityServie}
 */
export default class SubTaskServiceBase extends EntityService {

    /**
     * Creates an instance of  SubTaskServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  SubTaskServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     *
     * @memberof SubTaskServiceBase
     */
    public initBasicData(){
        this.APPLYDEKEY ='subtask';
        this.APPDEKEY = 'id';
        this.APPDENAME = 'subtasks';
        this.APPDETEXT = 'name';
        this.APPNAME = 'web';
        this.SYSTEMNAME = 'pms';
    }

// 实体接口

    /**
     * Activate接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async Activate(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * AssignTo接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async AssignTo(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * CalcTime接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async CalcTime(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * Cancel接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async Cancel(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * Close接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async Close(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * ConfirmStoryChange接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async ConfirmStoryChange(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * Create接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
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
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks`,data,isloading);
            
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
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks`,data,isloading);
            
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
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks`,data,isloading);
            
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
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks`,data,isloading);
            
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
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks`,data,isloading);
            
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
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks`,data,isloading);
            
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
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks`,data,isloading);
            
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
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/subtasks`,data,isloading);
            
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
        let res:any = await Http.getInstance().post(`/subtasks`,data,isloading);
        
        return res;
    }

    /**
     * DeleteEstimate接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async DeleteEstimate(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * EditEstimate接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async EditEstimate(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * Finish接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async Finish(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * GetDraft接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async GetDraft(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.subtask) delete tempData.subtask;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/getdraft`,tempData,isloading);
            res.data.subtask = data.subtask;
            
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.subtask) delete tempData.subtask;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/getdraft`,tempData,isloading);
            res.data.subtask = data.subtask;
            
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.subtask) delete tempData.subtask;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/getdraft`,tempData,isloading);
            res.data.subtask = data.subtask;
            
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.subtask) delete tempData.subtask;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/getdraft`,tempData,isloading);
            res.data.subtask = data.subtask;
            
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.subtask) delete tempData.subtask;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/getdraft`,tempData,isloading);
            res.data.subtask = data.subtask;
            
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.subtask) delete tempData.subtask;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/getdraft`,tempData,isloading);
            res.data.subtask = data.subtask;
            
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.subtask) delete tempData.subtask;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/getdraft`,tempData,isloading);
            res.data.subtask = data.subtask;
            
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.subtask) delete tempData.subtask;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/getdraft`,tempData,isloading);
            res.data.subtask = data.subtask;
            
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        if(tempData.subtask) delete tempData.subtask;
        if(tempData.id) delete tempData.id;
        let res:any = await  Http.getInstance().get(`/subtasks/getdraft`,tempData,isloading);
        res.data.subtask = data.subtask;
        
        return res;
    }

    /**
     * GetNextTeamUser接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async GetNextTeamUser(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * GetTeamUserLeftActivity接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async GetTeamUserLeftActivity(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * GetTeamUserLeftStart接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async GetTeamUserLeftStart(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * GetUserConcat接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async GetUserConcat(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let appLogic:GetUserConcatLogic = new GetUserConcatLogic({context:JSON.parse(JSON.stringify(context)),data:JSON.parse(JSON.stringify(data))});
        const res = await appLogic.onExecute(context,data,isloading?true:false);
        return {status:200,data:res};
    }

    /**
     * OtherUpdate接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async OtherUpdate(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * Pause接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async Pause(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * RecordEstimate接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async RecordEstimate(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * RecordTimZeroLeftAfterContinue接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async RecordTimZeroLeftAfterContinue(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * RecordTimateZeroLeft接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async RecordTimateZeroLeft(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * RecordTimateZeroLeftAfterStart接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async RecordTimateZeroLeftAfterStart(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * Restart接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async Restart(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * SendMessage接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async SendMessage(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * SendMsgPreProcess接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async SendMsgPreProcess(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * Start接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async Start(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * TaskForward接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async TaskForward(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchAssignedToMyTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchAssignedToMyTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchAssignedToMyTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchAssignedToMyTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchassignedtomytask`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchassignedtomytask`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchassignedtomytask`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchassignedtomytask`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchassignedtomytask`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchassignedtomytask`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchassignedtomytask`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchassignedtomytask`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchassignedtomytask`,tempData,isloading);
    }

    /**
     * FetchAssignedToMyTaskEE接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchAssignedToMyTaskEE(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchAssignedToMyTaskEE接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchAssignedToMyTaskEE(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchassignedtomytaskee`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchassignedtomytaskee`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchassignedtomytaskee`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchassignedtomytaskee`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchassignedtomytaskee`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchassignedtomytaskee`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchassignedtomytaskee`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchassignedtomytaskee`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchassignedtomytaskee`,tempData,isloading);
    }

    /**
     * FetchAssignedToMyTaskPc接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchAssignedToMyTaskPc(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchAssignedToMyTaskPc接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchAssignedToMyTaskPc(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchassignedtomytaskpc`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchassignedtomytaskpc`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchassignedtomytaskpc`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchassignedtomytaskpc`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchassignedtomytaskpc`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchassignedtomytaskpc`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchassignedtomytaskpc`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchassignedtomytaskpc`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchassignedtomytaskpc`,tempData,isloading);
    }

    /**
     * FetchBugTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchBugTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchBugTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchBugTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchbugtask`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchbugtask`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchbugtask`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchbugtask`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchbugtask`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchbugtask`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchbugtask`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchbugtask`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchbugtask`,tempData,isloading);
    }

    /**
     * FetchByModule接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchByModule(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchByModule接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchByModule(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchbymodule`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchbymodule`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchbymodule`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchbymodule`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchbymodule`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchbymodule`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchbymodule`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchbymodule`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchbymodule`,tempData,isloading);
    }

    /**
     * FetchByModuleEE接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchByModuleEE(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchByModuleEE接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchByModuleEE(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchbymoduleee`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchbymoduleee`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchbymoduleee`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchbymoduleee`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchbymoduleee`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchbymoduleee`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchbymoduleee`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchbymoduleee`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchbymoduleee`,tempData,isloading);
    }

    /**
     * FetchChildDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchChildDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchChildDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchChildDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchchilddefault`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchchilddefault`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchchilddefault`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchchilddefault`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchchilddefault`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchchilddefault`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchchilddefault`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchchilddefault`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchchilddefault`,tempData,isloading);
    }

    /**
     * FetchChildDefaultMore接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchChildDefaultMore(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchChildDefaultMore接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchChildDefaultMore(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchchilddefaultmore`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchchilddefaultmore`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchchilddefaultmore`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchchilddefaultmore`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchchilddefaultmore`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchchilddefaultmore`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchchilddefaultmore`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchchilddefaultmore`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchchilddefaultmore`,tempData,isloading);
    }

    /**
     * FetchChildTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchChildTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchChildTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchChildTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchchildtask`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchchildtask`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchchildtask`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchchildtask`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchchildtask`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchchildtask`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchchildtask`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchchildtask`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchchildtask`,tempData,isloading);
    }

    /**
     * FetchChildTaskTree接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchChildTaskTree(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchChildTaskTree接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchChildTaskTree(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchchildtasktree`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchchildtasktree`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchchildtasktree`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchchildtasktree`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchchildtasktree`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchchildtasktree`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchchildtasktree`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchchildtasktree`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchchildtasktree`,tempData,isloading);
    }

    /**
     * FetchCurFinishTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchCurFinishTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchCurFinishTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchCurFinishTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchcurfinishtask`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchcurfinishtask`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchcurfinishtask`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchcurfinishtask`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchcurfinishtask`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchcurfinishtask`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchcurfinishtask`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchcurfinishtask`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchcurfinishtask`,tempData,isloading);
    }

    /**
     * FetchCurPersonTasks接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchCurPersonTasks(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchCurPersonTasks接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchCurPersonTasks(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchcurpersontasks`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchcurpersontasks`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchcurpersontasks`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchcurpersontasks`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchcurpersontasks`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchcurpersontasks`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchcurpersontasks`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchcurpersontasks`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchcurpersontasks`,tempData,isloading);
    }

    /**
     * FetchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchdefault`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchdefault`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchdefault`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchdefault`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchdefault`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchdefault`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchdefault`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchdefault`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchdefault`,tempData,isloading);
    }

    /**
     * FetchDefaultRow接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchDefaultRow(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchDefaultRow接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchDefaultRow(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchdefaultrow`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchdefaultrow`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchdefaultrow`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchdefaultrow`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchdefaultrow`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchdefaultrow`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchdefaultrow`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchdefaultrow`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchdefaultrow`,tempData,isloading);
    }

    /**
     * FetchESBulk接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchESBulk(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchESBulk接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchESBulk(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchesbulk`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchesbulk`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchesbulk`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchesbulk`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchesbulk`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchesbulk`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchesbulk`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchesbulk`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchesbulk`,tempData,isloading);
    }

    /**
     * FetchMyAgentTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchMyAgentTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchMyAgentTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchMyAgentTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchmyagenttask`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchmyagenttask`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchmyagenttask`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchmyagenttask`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchmyagenttask`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchmyagenttask`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchmyagenttask`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchmyagenttask`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchmyagenttask`,tempData,isloading);
    }

    /**
     * FetchMyAllTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchMyAllTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchMyAllTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchMyAllTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchmyalltask`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchmyalltask`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchmyalltask`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchmyalltask`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchmyalltask`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchmyalltask`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchmyalltask`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchmyalltask`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchmyalltask`,tempData,isloading);
    }

    /**
     * FetchMyAllTaskEE接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchMyAllTaskEE(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchMyAllTaskEE接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchMyAllTaskEE(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchmyalltaskee`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchmyalltaskee`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchmyalltaskee`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchmyalltaskee`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchmyalltaskee`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchmyalltaskee`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchmyalltaskee`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchmyalltaskee`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchmyalltaskee`,tempData,isloading);
    }

    /**
     * FetchMyCompleteTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchMyCompleteTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchMyCompleteTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchMyCompleteTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchmycompletetask`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchmycompletetask`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchmycompletetask`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchmycompletetask`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchmycompletetask`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchmycompletetask`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchmycompletetask`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchmycompletetask`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchmycompletetask`,tempData,isloading);
    }

    /**
     * FetchMyCompleteTaskMobDaily接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchMyCompleteTaskMobDaily(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchMyCompleteTaskMobDaily接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchMyCompleteTaskMobDaily(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchmycompletetaskmobdaily`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchmycompletetaskmobdaily`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchmycompletetaskmobdaily`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchmycompletetaskmobdaily`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchmycompletetaskmobdaily`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchmycompletetaskmobdaily`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchmycompletetaskmobdaily`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchmycompletetaskmobdaily`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchmycompletetaskmobdaily`,tempData,isloading);
    }

    /**
     * FetchMyCompleteTaskMobMonthly接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchMyCompleteTaskMobMonthly(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchMyCompleteTaskMobMonthly接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchMyCompleteTaskMobMonthly(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchmycompletetaskmobmonthly`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchmycompletetaskmobmonthly`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchmycompletetaskmobmonthly`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchmycompletetaskmobmonthly`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchmycompletetaskmobmonthly`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchmycompletetaskmobmonthly`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchmycompletetaskmobmonthly`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchmycompletetaskmobmonthly`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchmycompletetaskmobmonthly`,tempData,isloading);
    }

    /**
     * FetchMyCompleteTaskMonthlyZS接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchMyCompleteTaskMonthlyZS(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchMyCompleteTaskMonthlyZS接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchMyCompleteTaskMonthlyZS(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchmycompletetaskmonthlyzs`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchmycompletetaskmonthlyzs`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchmycompletetaskmonthlyzs`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchmycompletetaskmonthlyzs`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchmycompletetaskmonthlyzs`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchmycompletetaskmonthlyzs`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchmycompletetaskmonthlyzs`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchmycompletetaskmonthlyzs`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchmycompletetaskmonthlyzs`,tempData,isloading);
    }

    /**
     * FetchMyCompleteTaskZS接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchMyCompleteTaskZS(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchMyCompleteTaskZS接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchMyCompleteTaskZS(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchmycompletetaskzs`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchmycompletetaskzs`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchmycompletetaskzs`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchmycompletetaskzs`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchmycompletetaskzs`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchmycompletetaskzs`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchmycompletetaskzs`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchmycompletetaskzs`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchmycompletetaskzs`,tempData,isloading);
    }

    /**
     * FetchMyFavorites接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchMyFavorites(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchMyFavorites接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchMyFavorites(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchmyfavorites`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchmyfavorites`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchmyfavorites`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchmyfavorites`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchmyfavorites`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchmyfavorites`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchmyfavorites`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchmyfavorites`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchmyfavorites`,tempData,isloading);
    }

    /**
     * FetchMyPlansTaskMobMonthly接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchMyPlansTaskMobMonthly(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchMyPlansTaskMobMonthly接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchMyPlansTaskMobMonthly(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchmyplanstaskmobmonthly`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchmyplanstaskmobmonthly`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchmyplanstaskmobmonthly`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchmyplanstaskmobmonthly`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchmyplanstaskmobmonthly`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchmyplanstaskmobmonthly`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchmyplanstaskmobmonthly`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchmyplanstaskmobmonthly`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchmyplanstaskmobmonthly`,tempData,isloading);
    }

    /**
     * FetchMyProjectTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchMyProjectTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchMyProjectTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchMyProjectTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchmyprojecttask`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchmyprojecttask`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchmyprojecttask`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchmyprojecttask`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchmyprojecttask`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchmyprojecttask`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchmyprojecttask`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchmyprojecttask`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchmyprojecttask`,tempData,isloading);
    }

    /**
     * FetchMyTomorrowPlanTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchMyTomorrowPlanTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchMyTomorrowPlanTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchMyTomorrowPlanTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchmytomorrowplantask`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchmytomorrowplantask`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchmytomorrowplantask`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchmytomorrowplantask`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchmytomorrowplantask`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchmytomorrowplantask`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchmytomorrowplantask`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchmytomorrowplantask`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchmytomorrowplantask`,tempData,isloading);
    }

    /**
     * FetchMyTomorrowPlanTaskMobDaily接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchMyTomorrowPlanTaskMobDaily(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchMyTomorrowPlanTaskMobDaily接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchMyTomorrowPlanTaskMobDaily(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchmytomorrowplantaskmobdaily`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchmytomorrowplantaskmobdaily`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchmytomorrowplantaskmobdaily`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchmytomorrowplantaskmobdaily`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchmytomorrowplantaskmobdaily`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchmytomorrowplantaskmobdaily`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchmytomorrowplantaskmobdaily`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchmytomorrowplantaskmobdaily`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchmytomorrowplantaskmobdaily`,tempData,isloading);
    }

    /**
     * FetchNextWeekCompleteTaskMobZS接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchNextWeekCompleteTaskMobZS(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchNextWeekCompleteTaskMobZS接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchNextWeekCompleteTaskMobZS(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchnextweekcompletetaskmobzs`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchnextweekcompletetaskmobzs`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchnextweekcompletetaskmobzs`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchnextweekcompletetaskmobzs`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchnextweekcompletetaskmobzs`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchnextweekcompletetaskmobzs`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchnextweekcompletetaskmobzs`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchnextweekcompletetaskmobzs`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchnextweekcompletetaskmobzs`,tempData,isloading);
    }

    /**
     * FetchNextWeekCompleteTaskZS接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchNextWeekCompleteTaskZS(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchNextWeekCompleteTaskZS接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchNextWeekCompleteTaskZS(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchnextweekcompletetaskzs`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchnextweekcompletetaskzs`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchnextweekcompletetaskzs`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchnextweekcompletetaskzs`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchnextweekcompletetaskzs`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchnextweekcompletetaskzs`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchnextweekcompletetaskzs`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchnextweekcompletetaskzs`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchnextweekcompletetaskzs`,tempData,isloading);
    }

    /**
     * FetchNextWeekPlanCompleteTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchNextWeekPlanCompleteTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchNextWeekPlanCompleteTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchNextWeekPlanCompleteTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchnextweekplancompletetask`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchnextweekplancompletetask`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchnextweekplancompletetask`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchnextweekplancompletetask`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchnextweekplancompletetask`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchnextweekplancompletetask`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchnextweekplancompletetask`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchnextweekplancompletetask`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchnextweekplancompletetask`,tempData,isloading);
    }

    /**
     * FetchPersonnalTasks接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchPersonnalTasks(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchPersonnalTasks接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchPersonnalTasks(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchpersonnaltasks`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchpersonnaltasks`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchpersonnaltasks`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchpersonnaltasks`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchpersonnaltasks`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchpersonnaltasks`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchpersonnaltasks`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchpersonnaltasks`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchpersonnaltasks`,tempData,isloading);
    }

    /**
     * FetchPlanTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchPlanTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchPlanTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchPlanTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchplantask`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchplantask`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchplantask`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchplantask`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchplantask`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchplantask`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchplantask`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchplantask`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchplantask`,tempData,isloading);
    }

    /**
     * FetchProjectAppTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchProjectAppTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchProjectAppTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchProjectAppTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchprojectapptask`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchprojectapptask`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchprojectapptask`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchprojectapptask`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchprojectapptask`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchprojectapptask`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchprojectapptask`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchprojectapptask`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchprojectapptask`,tempData,isloading);
    }

    /**
     * FetchProjectMemberTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchProjectMemberTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchProjectMemberTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchProjectMemberTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchprojectmembertask`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchprojectmembertask`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchprojectmembertask`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchprojectmembertask`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchprojectmembertask`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchprojectmembertask`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchprojectmembertask`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchprojectmembertask`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchprojectmembertask`,tempData,isloading);
    }

    /**
     * FetchProjectTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchProjectTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchProjectTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchProjectTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchprojecttask`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchprojecttask`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchprojecttask`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchprojecttask`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchprojecttask`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchprojecttask`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchprojecttask`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchprojecttask`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchprojecttask`,tempData,isloading);
    }

    /**
     * FetchProjectTaskEE接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchProjectTaskEE(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchProjectTaskEE接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchProjectTaskEE(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchprojecttaskee`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchprojecttaskee`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchprojecttaskee`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchprojecttaskee`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchprojecttaskee`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchprojecttaskee`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchprojecttaskee`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchprojecttaskee`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchprojecttaskee`,tempData,isloading);
    }

    /**
     * FetchRootTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchRootTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchRootTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchRootTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchroottask`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchroottask`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchroottask`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchroottask`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchroottask`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchroottask`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchroottask`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchroottask`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchroottask`,tempData,isloading);
    }

    /**
     * FetchTaskLinkPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTaskLinkPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchTaskLinkPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchTaskLinkPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchtasklinkplan`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchtasklinkplan`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchtasklinkplan`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchtasklinkplan`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchtasklinkplan`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchtasklinkplan`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchtasklinkplan`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchtasklinkplan`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchtasklinkplan`,tempData,isloading);
    }

    /**
     * FetchThisMonthCompleteTaskChoice接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchThisMonthCompleteTaskChoice(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchThisMonthCompleteTaskChoice接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchThisMonthCompleteTaskChoice(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchthismonthcompletetaskchoice`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchthismonthcompletetaskchoice`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchthismonthcompletetaskchoice`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchthismonthcompletetaskchoice`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchthismonthcompletetaskchoice`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchthismonthcompletetaskchoice`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchthismonthcompletetaskchoice`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchthismonthcompletetaskchoice`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchthismonthcompletetaskchoice`,tempData,isloading);
    }

    /**
     * FetchThisWeekCompleteTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchThisWeekCompleteTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchThisWeekCompleteTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchThisWeekCompleteTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchthisweekcompletetask`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchthisweekcompletetask`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchthisweekcompletetask`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchthisweekcompletetask`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchthisweekcompletetask`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchthisweekcompletetask`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchthisweekcompletetask`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchthisweekcompletetask`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchthisweekcompletetask`,tempData,isloading);
    }

    /**
     * FetchThisWeekCompleteTaskChoice接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchThisWeekCompleteTaskChoice(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchThisWeekCompleteTaskChoice接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchThisWeekCompleteTaskChoice(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchthisweekcompletetaskchoice`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchthisweekcompletetaskchoice`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchthisweekcompletetaskchoice`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchthisweekcompletetaskchoice`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchthisweekcompletetaskchoice`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchthisweekcompletetaskchoice`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchthisweekcompletetaskchoice`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchthisweekcompletetaskchoice`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchthisweekcompletetaskchoice`,tempData,isloading);
    }

    /**
     * FetchThisWeekCompleteTaskMobZS接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchThisWeekCompleteTaskMobZS(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchThisWeekCompleteTaskMobZS接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchThisWeekCompleteTaskMobZS(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchthisweekcompletetaskmobzs`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchthisweekcompletetaskmobzs`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchthisweekcompletetaskmobzs`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchthisweekcompletetaskmobzs`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchthisweekcompletetaskmobzs`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchthisweekcompletetaskmobzs`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchthisweekcompletetaskmobzs`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchthisweekcompletetaskmobzs`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchthisweekcompletetaskmobzs`,tempData,isloading);
    }

    /**
     * FetchThisWeekCompleteTaskZS接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchThisWeekCompleteTaskZS(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchThisWeekCompleteTaskZS接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchThisWeekCompleteTaskZS(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchthisweekcompletetaskzs`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchthisweekcompletetaskzs`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchthisweekcompletetaskzs`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchthisweekcompletetaskzs`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchthisweekcompletetaskzs`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchthisweekcompletetaskzs`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchthisweekcompletetaskzs`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchthisweekcompletetaskzs`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchthisweekcompletetaskzs`,tempData,isloading);
    }

    /**
     * FetchTodoListTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTodoListTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchTodoListTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchTodoListTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchtodolisttask`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchtodolisttask`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchtodolisttask`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchtodolisttask`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchtodolisttask`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchtodolisttask`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchtodolisttask`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchtodolisttask`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchtodolisttask`,tempData,isloading);
    }

    /**
     * FetchTypeGroup接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTypeGroup(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchTypeGroup接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchTypeGroup(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchtypegroup`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchtypegroup`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchtypegroup`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchtypegroup`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchtypegroup`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchtypegroup`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchtypegroup`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchtypegroup`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchtypegroup`,tempData,isloading);
    }

    /**
     * FetchTypeGroupPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTypeGroupPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * searchTypeGroupPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async searchTypeGroupPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchtypegroupplan`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/searchtypegroupplan`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchtypegroupplan`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/searchtypegroupplan`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/searchtypegroupplan`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/searchtypegroupplan`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/searchtypegroupplan`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/searchtypegroupplan`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/searchtypegroupplan`,tempData,isloading);
    }

    /**
     * FetchTempAssignedToMyTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempAssignedToMyTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempAssignedToMyTaskEE接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempAssignedToMyTaskEE(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempAssignedToMyTaskPc接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempAssignedToMyTaskPc(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempBugTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempBugTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempByModule接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempByModule(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempByModuleEE接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempByModuleEE(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempChildDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempChildDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempChildDefaultMore接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempChildDefaultMore(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempChildTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempChildTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempChildTaskTree接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempChildTaskTree(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempCurFinishTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempCurFinishTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempCurPersonTasks接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempCurPersonTasks(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempDefaultRow接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempDefaultRow(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempESBulk接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempESBulk(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempMyAgentTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempMyAgentTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempMyAllTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempMyAllTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempMyAllTaskEE接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempMyAllTaskEE(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempMyCompleteTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempMyCompleteTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempMyCompleteTaskMobDaily接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempMyCompleteTaskMobDaily(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempMyCompleteTaskMobMonthly接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempMyCompleteTaskMobMonthly(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempMyCompleteTaskMonthlyZS接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempMyCompleteTaskMonthlyZS(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempMyCompleteTaskZS接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempMyCompleteTaskZS(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempMyFavorites接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempMyFavorites(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempMyPlansTaskMobMonthly接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempMyPlansTaskMobMonthly(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempMyProjectTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempMyProjectTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempMyTomorrowPlanTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempMyTomorrowPlanTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempMyTomorrowPlanTaskMobDaily接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempMyTomorrowPlanTaskMobDaily(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempNextWeekCompleteTaskMobZS接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempNextWeekCompleteTaskMobZS(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempNextWeekCompleteTaskZS接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempNextWeekCompleteTaskZS(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempNextWeekPlanCompleteTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempNextWeekPlanCompleteTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempPersonnalTasks接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempPersonnalTasks(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempPlanTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempPlanTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempProjectAppTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempProjectAppTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempProjectMemberTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempProjectMemberTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempProjectTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempProjectTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempProjectTaskEE接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempProjectTaskEE(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempRootTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempRootTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempTaskLinkPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempTaskLinkPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempThisMonthCompleteTaskChoice接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempThisMonthCompleteTaskChoice(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempThisWeekCompleteTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempThisWeekCompleteTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempThisWeekCompleteTaskChoice接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempThisWeekCompleteTaskChoice(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempThisWeekCompleteTaskMobZS接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempThisWeekCompleteTaskMobZS(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempThisWeekCompleteTaskZS接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempThisWeekCompleteTaskZS(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempTodoListTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempTodoListTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempTypeGroup接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempTypeGroup(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempTypeGroupPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FetchTempTypeGroupPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }
}