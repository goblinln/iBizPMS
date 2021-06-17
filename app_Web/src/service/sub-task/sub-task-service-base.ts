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
        if(context.project && context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/activate`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/activate`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/activate`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}/activate`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/activate`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/activate`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/activate`,data,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/subtasks/${context.subtask}/activate`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/subtasks/${context.subtask}/activate`,data,isloading);
            return res;
    }

    /**
     * ActivateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async ActivateBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/activatebatch`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/activatebatch`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/activatebatch`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/activatebatch`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/activatebatch`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/activatebatch`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/activatebatch`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/activatebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/activatebatch`,tempData,isloading);
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
        if(context.project && context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/assignto`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/assignto`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/assignto`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}/assignto`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/assignto`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/assignto`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/assignto`,data,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/subtasks/${context.subtask}/assignto`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/subtasks/${context.subtask}/assignto`,data,isloading);
            return res;
    }

    /**
     * AssignToBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async AssignToBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/assigntobatch`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/assigntobatch`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/assigntobatch`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/assigntobatch`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/assigntobatch`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/assigntobatch`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/assigntobatch`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/assigntobatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/assigntobatch`,tempData,isloading);
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
        if(context.project && context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/cancel`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/cancel`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/cancel`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}/cancel`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/cancel`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/cancel`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/cancel`,data,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/subtasks/${context.subtask}/cancel`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/subtasks/${context.subtask}/cancel`,data,isloading);
            return res;
    }

    /**
     * CancelBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async CancelBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/cancelbatch`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/cancelbatch`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/cancelbatch`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/cancelbatch`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/cancelbatch`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/cancelbatch`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/cancelbatch`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/cancelbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/cancelbatch`,tempData,isloading);
    }

    /**
     * CheckKey接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async CheckKey(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/checkkey`,data,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/subtasks/${context.subtask}/checkkey`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/subtasks/${context.subtask}/checkkey`,data,isloading);
            return res;
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
        if(context.project && context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/close`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/close`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/close`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}/close`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/close`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/close`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/close`,data,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/subtasks/${context.subtask}/close`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/subtasks/${context.subtask}/close`,data,isloading);
            return res;
    }

    /**
     * CloseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async CloseBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/closebatch`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/closebatch`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/closebatch`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/closebatch`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/closebatch`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/closebatch`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/closebatch`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/closebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/closebatch`,tempData,isloading);
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
        if(context.project && context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/confirmstorychange`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/confirmstorychange`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/confirmstorychange`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}/confirmstorychange`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/confirmstorychange`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/confirmstorychange`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/confirmstorychange`,data,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/subtasks/${context.subtask}/confirmstorychange`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/subtasks/${context.subtask}/confirmstorychange`,data,isloading);
            return res;
    }

    /**
     * ConfirmStoryChangeBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async ConfirmStoryChangeBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/confirmstorychangebatch`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/confirmstorychangebatch`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/confirmstorychangebatch`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/confirmstorychangebatch`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/confirmstorychangebatch`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/confirmstorychangebatch`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/confirmstorychangebatch`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/confirmstorychangebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/confirmstorychangebatch`,tempData,isloading);
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
     * CreateCycleTasks接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async CreateCycleTasks(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/createcycletasks`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/createcycletasks`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/createcycletasks`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}/createcycletasks`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/createcycletasks`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/createcycletasks`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/createcycletasks`,data,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/subtasks/${context.subtask}/createcycletasks`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/subtasks/${context.subtask}/createcycletasks`,data,isloading);
            return res;
    }

    /**
     * CreateCycleTasksBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async CreateCycleTasksBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/createcycletasksbatch`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/createcycletasksbatch`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/createcycletasksbatch`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/createcycletasksbatch`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/createcycletasksbatch`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/createcycletasksbatch`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/createcycletasksbatch`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/createcycletasksbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/createcycletasksbatch`,tempData,isloading);
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
        if(context.project && context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/deleteestimate`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/deleteestimate`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/deleteestimate`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}/deleteestimate`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/deleteestimate`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/deleteestimate`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/deleteestimate`,data,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/subtasks/${context.subtask}/deleteestimate`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/subtasks/${context.subtask}/deleteestimate`,data,isloading);
            return res;
    }

    /**
     * DeleteEstimateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async DeleteEstimateBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/deleteestimatebatch`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/deleteestimatebatch`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/deleteestimatebatch`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/deleteestimatebatch`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/deleteestimatebatch`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/deleteestimatebatch`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/deleteestimatebatch`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/deleteestimatebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/deleteestimatebatch`,tempData,isloading);
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
        if(context.project && context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/editestimate`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/editestimate`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/editestimate`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}/editestimate`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/editestimate`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/editestimate`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/editestimate`,data,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/subtasks/${context.subtask}/editestimate`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/subtasks/${context.subtask}/editestimate`,data,isloading);
            return res;
    }

    /**
     * EditEstimateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async EditEstimateBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/editestimatebatch`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/editestimatebatch`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/editestimatebatch`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/editestimatebatch`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/editestimatebatch`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/editestimatebatch`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/editestimatebatch`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/editestimatebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/editestimatebatch`,tempData,isloading);
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
        if(context.project && context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/finish`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/finish`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/finish`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}/finish`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/finish`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/finish`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/finish`,data,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/subtasks/${context.subtask}/finish`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/subtasks/${context.subtask}/finish`,data,isloading);
            return res;
    }

    /**
     * FinishBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async FinishBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/finishbatch`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/finishbatch`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/finishbatch`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/finishbatch`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/finishbatch`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/finishbatch`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/finishbatch`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/finishbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/finishbatch`,tempData,isloading);
    }

    /**
     * Get接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async Get(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && context.subtask){
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}`,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}`,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}`,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}`,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}`,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}`,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}`,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/${context.subtask}`,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().get(`/subtasks/${context.subtask}`,isloading);
            
            return res;
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
        if(context.project && context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/getnextteamuser`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/getnextteamuser`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/getnextteamuser`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}/getnextteamuser`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/getnextteamuser`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/getnextteamuser`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/getnextteamuser`,data,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/tasks/${context.task}/subtasks/${context.subtask}/getnextteamuser`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().put(`/subtasks/${context.subtask}/getnextteamuser`,data,isloading);
            return res;
    }

    /**
     * GetNextTeamUserBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async GetNextTeamUserBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/getnextteamuserbatch`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/getnextteamuserbatch`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/getnextteamuserbatch`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/getnextteamuserbatch`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/getnextteamuserbatch`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/getnextteamuserbatch`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/getnextteamuserbatch`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/getnextteamuserbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/getnextteamuserbatch`,tempData,isloading);
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
        if(context.project && context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/getteamuserleftactivity`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/getteamuserleftactivity`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/getteamuserleftactivity`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}/getteamuserleftactivity`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/getteamuserleftactivity`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/getteamuserleftactivity`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/getteamuserleftactivity`,data,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/tasks/${context.task}/subtasks/${context.subtask}/getteamuserleftactivity`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().put(`/subtasks/${context.subtask}/getteamuserleftactivity`,data,isloading);
            return res;
    }

    /**
     * GetTeamUserLeftActivityBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async GetTeamUserLeftActivityBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/getteamuserleftactivitybatch`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/getteamuserleftactivitybatch`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/getteamuserleftactivitybatch`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/getteamuserleftactivitybatch`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/getteamuserleftactivitybatch`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/getteamuserleftactivitybatch`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/getteamuserleftactivitybatch`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/getteamuserleftactivitybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/getteamuserleftactivitybatch`,tempData,isloading);
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
        if(context.project && context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/getteamuserleftstart`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/getteamuserleftstart`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/getteamuserleftstart`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}/getteamuserleftstart`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/getteamuserleftstart`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/getteamuserleftstart`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/getteamuserleftstart`,data,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/tasks/${context.task}/subtasks/${context.subtask}/getteamuserleftstart`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().put(`/subtasks/${context.subtask}/getteamuserleftstart`,data,isloading);
            return res;
    }

    /**
     * GetTeamUserLeftStartBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async GetTeamUserLeftStartBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/getteamuserleftstartbatch`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/getteamuserleftstartbatch`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/getteamuserleftstartbatch`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/getteamuserleftstartbatch`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/getteamuserleftstartbatch`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/getteamuserleftstartbatch`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/getteamuserleftstartbatch`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/getteamuserleftstartbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/getteamuserleftstartbatch`,tempData,isloading);
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
     * GetUsernames接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async GetUsernames(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/getusernames`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/getusernames`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/getusernames`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}/getusernames`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/getusernames`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/getusernames`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/getusernames`,data,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/tasks/${context.task}/subtasks/${context.subtask}/getusernames`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().put(`/subtasks/${context.subtask}/getusernames`,data,isloading);
            return res;
    }

    /**
     * LinkPlan接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async LinkPlan(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/linkplan`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/linkplan`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/linkplan`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}/linkplan`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/linkplan`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/linkplan`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/linkplan`,data,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/subtasks/${context.subtask}/linkplan`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/subtasks/${context.subtask}/linkplan`,data,isloading);
            return res;
    }

    /**
     * LinkPlanBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async LinkPlanBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/linkplanbatch`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/linkplanbatch`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/linkplanbatch`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/linkplanbatch`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/linkplanbatch`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/linkplanbatch`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/linkplanbatch`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/linkplanbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/linkplanbatch`,tempData,isloading);
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
        if(context.project && context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/otherupdate`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/otherupdate`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/otherupdate`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}/otherupdate`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/otherupdate`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/otherupdate`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/otherupdate`,data,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/tasks/${context.task}/subtasks/${context.subtask}/otherupdate`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().put(`/subtasks/${context.subtask}/otherupdate`,data,isloading);
            return res;
    }

    /**
     * OtherUpdateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async OtherUpdateBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/otherupdatebatch`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/otherupdatebatch`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/otherupdatebatch`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/otherupdatebatch`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/otherupdatebatch`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/otherupdatebatch`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/otherupdatebatch`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/otherupdatebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/otherupdatebatch`,tempData,isloading);
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
        if(context.project && context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/pause`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/pause`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/pause`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}/pause`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/pause`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/pause`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/pause`,data,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/subtasks/${context.subtask}/pause`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/subtasks/${context.subtask}/pause`,data,isloading);
            return res;
    }

    /**
     * PauseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async PauseBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/pausebatch`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/pausebatch`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/pausebatch`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/pausebatch`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/pausebatch`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/pausebatch`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/pausebatch`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/pausebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/pausebatch`,tempData,isloading);
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
        if(context.project && context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/recordestimate`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/recordestimate`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/recordestimate`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}/recordestimate`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/recordestimate`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/recordestimate`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/recordestimate`,data,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/subtasks/${context.subtask}/recordestimate`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/subtasks/${context.subtask}/recordestimate`,data,isloading);
            return res;
    }

    /**
     * RecordEstimateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async RecordEstimateBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/recordestimatebatch`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/recordestimatebatch`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/recordestimatebatch`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/recordestimatebatch`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/recordestimatebatch`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/recordestimatebatch`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/recordestimatebatch`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/recordestimatebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/recordestimatebatch`,tempData,isloading);
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
        if(context.project && context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/recordtimzeroleftaftercontinue`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/recordtimzeroleftaftercontinue`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/recordtimzeroleftaftercontinue`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}/recordtimzeroleftaftercontinue`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/recordtimzeroleftaftercontinue`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/recordtimzeroleftaftercontinue`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/recordtimzeroleftaftercontinue`,data,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/subtasks/${context.subtask}/recordtimzeroleftaftercontinue`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/subtasks/${context.subtask}/recordtimzeroleftaftercontinue`,data,isloading);
            return res;
    }

    /**
     * RecordTimZeroLeftAfterContinueBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async RecordTimZeroLeftAfterContinueBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/recordtimzeroleftaftercontinuebatch`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/recordtimzeroleftaftercontinuebatch`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/recordtimzeroleftaftercontinuebatch`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/recordtimzeroleftaftercontinuebatch`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/recordtimzeroleftaftercontinuebatch`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/recordtimzeroleftaftercontinuebatch`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/recordtimzeroleftaftercontinuebatch`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/recordtimzeroleftaftercontinuebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/recordtimzeroleftaftercontinuebatch`,tempData,isloading);
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
        if(context.project && context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/recordtimatezeroleft`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/recordtimatezeroleft`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/recordtimatezeroleft`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}/recordtimatezeroleft`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/recordtimatezeroleft`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/recordtimatezeroleft`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/recordtimatezeroleft`,data,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/subtasks/${context.subtask}/recordtimatezeroleft`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/subtasks/${context.subtask}/recordtimatezeroleft`,data,isloading);
            return res;
    }

    /**
     * RecordTimateZeroLeftBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async RecordTimateZeroLeftBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/recordtimatezeroleftbatch`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/recordtimatezeroleftbatch`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/recordtimatezeroleftbatch`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/recordtimatezeroleftbatch`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/recordtimatezeroleftbatch`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/recordtimatezeroleftbatch`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/recordtimatezeroleftbatch`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/recordtimatezeroleftbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/recordtimatezeroleftbatch`,tempData,isloading);
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
        if(context.project && context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/recordtimatezeroleftafterstart`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/recordtimatezeroleftafterstart`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/recordtimatezeroleftafterstart`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}/recordtimatezeroleftafterstart`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/recordtimatezeroleftafterstart`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/recordtimatezeroleftafterstart`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/recordtimatezeroleftafterstart`,data,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/subtasks/${context.subtask}/recordtimatezeroleftafterstart`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/subtasks/${context.subtask}/recordtimatezeroleftafterstart`,data,isloading);
            return res;
    }

    /**
     * RecordTimateZeroLeftAfterStartBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async RecordTimateZeroLeftAfterStartBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/recordtimatezeroleftafterstartbatch`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/recordtimatezeroleftafterstartbatch`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/recordtimatezeroleftafterstartbatch`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/recordtimatezeroleftafterstartbatch`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/recordtimatezeroleftafterstartbatch`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/recordtimatezeroleftafterstartbatch`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/recordtimatezeroleftafterstartbatch`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/recordtimatezeroleftafterstartbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/recordtimatezeroleftafterstartbatch`,tempData,isloading);
    }

    /**
     * Remove接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async Remove(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && context.subtask){
            let res:any = await Http.getInstance().delete(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}`,isloading);
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let res:any = await Http.getInstance().delete(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}`,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let res:any = await Http.getInstance().delete(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}`,isloading);
            return res;
        }
        if(context.project && context.task && context.subtask){
            let res:any = await Http.getInstance().delete(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}`,isloading);
            return res;
        }
        if(context.story && context.task && context.subtask){
            let res:any = await Http.getInstance().delete(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}`,isloading);
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let res:any = await Http.getInstance().delete(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}`,isloading);
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let res:any = await Http.getInstance().delete(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}`,isloading);
            return res;
        }
        if(context.task && context.subtask){
            let res:any = await Http.getInstance().delete(`/tasks/${context.task}/subtasks/${context.subtask}`,isloading);
            return res;
        }
            let res:any = await Http.getInstance().delete(`/subtasks/${context.subtask}`,isloading);
            return res;
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
        if(context.project && context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/restart`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/restart`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/restart`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}/restart`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/restart`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/restart`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/restart`,data,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/subtasks/${context.subtask}/restart`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/subtasks/${context.subtask}/restart`,data,isloading);
            return res;
    }

    /**
     * RestartBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async RestartBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/restartbatch`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/restartbatch`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/restartbatch`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/restartbatch`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/restartbatch`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/restartbatch`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/restartbatch`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/restartbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/restartbatch`,tempData,isloading);
    }

    /**
     * Save接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async Save(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/save`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/save`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/save`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}/save`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/save`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/save`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/save`,data,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/subtasks/${context.subtask}/save`,data,isloading);
            
            return res;
        }
        let masterData:any = {};
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().post(`/subtasks/${context.subtask}/save`,data,isloading);
            
            return res;
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
        if(context.project && context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/sendmessage`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/sendmessage`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/sendmessage`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}/sendmessage`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/sendmessage`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/sendmessage`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/sendmessage`,data,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/subtasks/${context.subtask}/sendmessage`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/subtasks/${context.subtask}/sendmessage`,data,isloading);
            return res;
    }

    /**
     * SendMessageBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async SendMessageBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/sendmessagebatch`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/sendmessagebatch`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/sendmessagebatch`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/sendmessagebatch`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/sendmessagebatch`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/sendmessagebatch`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/sendmessagebatch`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/sendmessagebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/sendmessagebatch`,tempData,isloading);
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
        if(context.project && context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/sendmsgpreprocess`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/sendmsgpreprocess`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/sendmsgpreprocess`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}/sendmsgpreprocess`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/sendmsgpreprocess`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/sendmsgpreprocess`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/sendmsgpreprocess`,data,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/subtasks/${context.subtask}/sendmsgpreprocess`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/subtasks/${context.subtask}/sendmsgpreprocess`,data,isloading);
            return res;
    }

    /**
     * SendMsgPreProcessBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async SendMsgPreProcessBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/sendmsgpreprocessbatch`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/sendmsgpreprocessbatch`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/sendmsgpreprocessbatch`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/sendmsgpreprocessbatch`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/sendmsgpreprocessbatch`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/sendmsgpreprocessbatch`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/sendmsgpreprocessbatch`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/sendmsgpreprocessbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/sendmsgpreprocessbatch`,tempData,isloading);
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
        if(context.project && context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/start`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/start`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/start`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}/start`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/start`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/start`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/start`,data,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/subtasks/${context.subtask}/start`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/subtasks/${context.subtask}/start`,data,isloading);
            return res;
    }

    /**
     * StartBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async StartBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/startbatch`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/startbatch`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/startbatch`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/startbatch`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/startbatch`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/startbatch`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/startbatch`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/startbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/startbatch`,tempData,isloading);
    }

    /**
     * TaskFavorites接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async TaskFavorites(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/taskfavorites`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/taskfavorites`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/taskfavorites`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}/taskfavorites`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/taskfavorites`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/taskfavorites`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/taskfavorites`,data,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/subtasks/${context.subtask}/taskfavorites`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/subtasks/${context.subtask}/taskfavorites`,data,isloading);
            return res;
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
        if(context.project && context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/taskforward`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/taskforward`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/taskforward`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}/taskforward`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/taskforward`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/taskforward`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/taskforward`,data,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/subtasks/${context.subtask}/taskforward`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/subtasks/${context.subtask}/taskforward`,data,isloading);
            return res;
    }

    /**
     * TaskForwardBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async TaskForwardBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/taskforwardbatch`,tempData,isloading);
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/taskforwardbatch`,tempData,isloading);
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/taskforwardbatch`,tempData,isloading);
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/taskforwardbatch`,tempData,isloading);
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/taskforwardbatch`,tempData,isloading);
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/taskforwardbatch`,tempData,isloading);
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/taskforwardbatch`,tempData,isloading);
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/tasks/${context.task}/subtasks/taskforwardbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/subtasks/taskforwardbatch`,tempData,isloading);
    }

    /**
     * TaskNFavorites接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async TaskNFavorites(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/tasknfavorites`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/tasknfavorites`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/tasknfavorites`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}/tasknfavorites`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/tasknfavorites`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/tasknfavorites`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/tasknfavorites`,data,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/subtasks/${context.subtask}/tasknfavorites`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().post(`/subtasks/${context.subtask}/tasknfavorites`,data,isloading);
            return res;
    }

    /**
     * Update接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async Update(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}`,data,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/tasks/${context.task}/subtasks/${context.subtask}`,data,isloading);
            
            return res;
        }
        let masterData:any = {};
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().put(`/subtasks/${context.subtask}`,data,isloading);
            
            return res;
    }

    /**
     * UpdateStoryVersion接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async UpdateStoryVersion(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/updatestoryversion`,data,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/updatestoryversion`,data,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/updatestoryversion`,data,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}/updatestoryversion`,data,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/updatestoryversion`,data,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/updatestoryversion`,data,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/updatestoryversion`,data,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let masterData:any = {};
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/tasks/${context.task}/subtasks/${context.subtask}/updatestoryversion`,data,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().put(`/subtasks/${context.subtask}/updatestoryversion`,data,isloading);
            return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchassignedtomytask`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchassignedtomytask`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchassignedtomytask`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchassignedtomytask`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchassignedtomytask`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchassignedtomytask`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchassignedtomytask`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchassignedtomytask`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchassignedtomytask`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchassignedtomytaskee`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchassignedtomytaskee`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchassignedtomytaskee`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchassignedtomytaskee`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchassignedtomytaskee`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchassignedtomytaskee`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchassignedtomytaskee`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchassignedtomytaskee`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchassignedtomytaskee`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchassignedtomytaskpc`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchassignedtomytaskpc`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchassignedtomytaskpc`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchassignedtomytaskpc`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchassignedtomytaskpc`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchassignedtomytaskpc`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchassignedtomytaskpc`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchassignedtomytaskpc`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchassignedtomytaskpc`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchbugtask`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchbugtask`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchbugtask`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchbugtask`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchbugtask`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchbugtask`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchbugtask`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchbugtask`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchbugtask`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchbymodule`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchbymodule`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchbymodule`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchbymodule`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchbymodule`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchbymodule`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchbymodule`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/subtasks/fetchbymodule`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/subtasks/fetchbymodule`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchbymoduleee`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchbymoduleee`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchbymoduleee`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchbymoduleee`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchbymoduleee`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchbymoduleee`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchbymoduleee`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchbymoduleee`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchbymoduleee`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchchilddefault`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchchilddefault`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchchilddefault`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchchilddefault`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchchilddefault`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchchilddefault`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchchilddefault`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchchilddefault`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchchilddefault`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchchilddefaultmore`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchchilddefaultmore`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchchilddefaultmore`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchchilddefaultmore`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchchilddefaultmore`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchchilddefaultmore`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchchilddefaultmore`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchchilddefaultmore`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchchilddefaultmore`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchchildtask`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchchildtask`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchchildtask`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchchildtask`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchchildtask`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchchildtask`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchchildtask`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchchildtask`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchchildtask`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchchildtasktree`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchchildtasktree`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchchildtasktree`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchchildtasktree`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchchildtasktree`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchchildtasktree`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchchildtasktree`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchchildtasktree`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchchildtasktree`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchcurfinishtask`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchcurfinishtask`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchcurfinishtask`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchcurfinishtask`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchcurfinishtask`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchcurfinishtask`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchcurfinishtask`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchcurfinishtask`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchcurfinishtask`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchcurpersontasks`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchcurpersontasks`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchcurpersontasks`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchcurpersontasks`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchcurpersontasks`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchcurpersontasks`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchcurpersontasks`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchcurpersontasks`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchcurpersontasks`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/subtasks/fetchdefault`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/subtasks/fetchdefault`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchdefaultrow`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchdefaultrow`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchdefaultrow`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchdefaultrow`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchdefaultrow`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchdefaultrow`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchdefaultrow`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchdefaultrow`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchdefaultrow`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchesbulk`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchesbulk`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchesbulk`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchesbulk`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchesbulk`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchesbulk`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchesbulk`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchesbulk`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchesbulk`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchmyagenttask`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchmyagenttask`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchmyagenttask`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchmyagenttask`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchmyagenttask`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchmyagenttask`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchmyagenttask`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchmyagenttask`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchmyagenttask`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchmyalltask`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchmyalltask`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchmyalltask`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchmyalltask`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchmyalltask`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchmyalltask`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchmyalltask`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/subtasks/fetchmyalltask`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/subtasks/fetchmyalltask`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchmyalltaskee`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchmyalltaskee`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchmyalltaskee`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchmyalltaskee`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchmyalltaskee`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchmyalltaskee`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchmyalltaskee`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchmyalltaskee`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchmyalltaskee`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchmycompletetask`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchmycompletetask`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchmycompletetask`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchmycompletetask`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchmycompletetask`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchmycompletetask`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchmycompletetask`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchmycompletetask`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchmycompletetask`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchmycompletetaskmobdaily`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchmycompletetaskmobdaily`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchmycompletetaskmobdaily`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchmycompletetaskmobdaily`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchmycompletetaskmobdaily`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchmycompletetaskmobdaily`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchmycompletetaskmobdaily`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchmycompletetaskmobdaily`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchmycompletetaskmobdaily`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchmycompletetaskmobmonthly`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchmycompletetaskmobmonthly`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchmycompletetaskmobmonthly`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchmycompletetaskmobmonthly`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchmycompletetaskmobmonthly`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchmycompletetaskmobmonthly`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchmycompletetaskmobmonthly`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchmycompletetaskmobmonthly`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchmycompletetaskmobmonthly`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchmycompletetaskmonthlyzs`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchmycompletetaskmonthlyzs`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchmycompletetaskmonthlyzs`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchmycompletetaskmonthlyzs`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchmycompletetaskmonthlyzs`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchmycompletetaskmonthlyzs`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchmycompletetaskmonthlyzs`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchmycompletetaskmonthlyzs`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchmycompletetaskmonthlyzs`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchmycompletetaskzs`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchmycompletetaskzs`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchmycompletetaskzs`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchmycompletetaskzs`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchmycompletetaskzs`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchmycompletetaskzs`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchmycompletetaskzs`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchmycompletetaskzs`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchmycompletetaskzs`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchmyfavorites`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchmyfavorites`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchmyfavorites`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchmyfavorites`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchmyfavorites`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchmyfavorites`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchmyfavorites`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/subtasks/fetchmyfavorites`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/subtasks/fetchmyfavorites`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchmyplanstaskmobmonthly`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchmyplanstaskmobmonthly`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchmyplanstaskmobmonthly`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchmyplanstaskmobmonthly`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchmyplanstaskmobmonthly`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchmyplanstaskmobmonthly`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchmyplanstaskmobmonthly`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchmyplanstaskmobmonthly`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchmyplanstaskmobmonthly`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchmyprojecttask`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchmyprojecttask`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchmyprojecttask`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchmyprojecttask`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchmyprojecttask`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchmyprojecttask`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchmyprojecttask`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchmyprojecttask`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchmyprojecttask`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchmytomorrowplantask`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchmytomorrowplantask`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchmytomorrowplantask`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchmytomorrowplantask`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchmytomorrowplantask`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchmytomorrowplantask`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchmytomorrowplantask`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchmytomorrowplantask`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchmytomorrowplantask`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchmytomorrowplantaskmobdaily`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchmytomorrowplantaskmobdaily`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchmytomorrowplantaskmobdaily`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchmytomorrowplantaskmobdaily`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchmytomorrowplantaskmobdaily`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchmytomorrowplantaskmobdaily`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchmytomorrowplantaskmobdaily`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchmytomorrowplantaskmobdaily`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchmytomorrowplantaskmobdaily`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchnextweekcompletetaskmobzs`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchnextweekcompletetaskmobzs`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchnextweekcompletetaskmobzs`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchnextweekcompletetaskmobzs`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchnextweekcompletetaskmobzs`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchnextweekcompletetaskmobzs`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchnextweekcompletetaskmobzs`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchnextweekcompletetaskmobzs`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchnextweekcompletetaskmobzs`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchnextweekcompletetaskzs`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchnextweekcompletetaskzs`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchnextweekcompletetaskzs`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchnextweekcompletetaskzs`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchnextweekcompletetaskzs`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchnextweekcompletetaskzs`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchnextweekcompletetaskzs`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchnextweekcompletetaskzs`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchnextweekcompletetaskzs`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchnextweekplancompletetask`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchnextweekplancompletetask`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchnextweekplancompletetask`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchnextweekplancompletetask`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchnextweekplancompletetask`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchnextweekplancompletetask`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchnextweekplancompletetask`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchnextweekplancompletetask`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchnextweekplancompletetask`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchpersonnaltasks`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchpersonnaltasks`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchpersonnaltasks`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchpersonnaltasks`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchpersonnaltasks`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchpersonnaltasks`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchpersonnaltasks`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchpersonnaltasks`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchpersonnaltasks`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchplantask`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchplantask`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchplantask`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchplantask`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchplantask`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchplantask`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchplantask`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/subtasks/fetchplantask`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/subtasks/fetchplantask`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchprojectapptask`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchprojectapptask`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchprojectapptask`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchprojectapptask`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchprojectapptask`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchprojectapptask`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchprojectapptask`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/subtasks/fetchprojectapptask`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/subtasks/fetchprojectapptask`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchprojectmembertask`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchprojectmembertask`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchprojectmembertask`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchprojectmembertask`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchprojectmembertask`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchprojectmembertask`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchprojectmembertask`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchprojectmembertask`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchprojectmembertask`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchprojecttask`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchprojecttask`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchprojecttask`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchprojecttask`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchprojecttask`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchprojecttask`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchprojecttask`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/tasks/${context.task}/subtasks/fetchprojecttask`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/subtasks/fetchprojecttask`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchprojecttaskee`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchprojecttaskee`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchprojecttaskee`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchprojecttaskee`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchprojecttaskee`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchprojecttaskee`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchprojecttaskee`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchprojecttaskee`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchprojecttaskee`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchroottask`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchroottask`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchroottask`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchroottask`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchroottask`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchroottask`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchroottask`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchroottask`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchroottask`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchtasklinkplan`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchtasklinkplan`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchtasklinkplan`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchtasklinkplan`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchtasklinkplan`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchtasklinkplan`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchtasklinkplan`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchtasklinkplan`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchtasklinkplan`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchthismonthcompletetaskchoice`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchthismonthcompletetaskchoice`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchthismonthcompletetaskchoice`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchthismonthcompletetaskchoice`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchthismonthcompletetaskchoice`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchthismonthcompletetaskchoice`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchthismonthcompletetaskchoice`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchthismonthcompletetaskchoice`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchthismonthcompletetaskchoice`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchthisweekcompletetask`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchthisweekcompletetask`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchthisweekcompletetask`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchthisweekcompletetask`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchthisweekcompletetask`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchthisweekcompletetask`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchthisweekcompletetask`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchthisweekcompletetask`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchthisweekcompletetask`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchthisweekcompletetaskchoice`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchthisweekcompletetaskchoice`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchthisweekcompletetaskchoice`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchthisweekcompletetaskchoice`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchthisweekcompletetaskchoice`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchthisweekcompletetaskchoice`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchthisweekcompletetaskchoice`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchthisweekcompletetaskchoice`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchthisweekcompletetaskchoice`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchthisweekcompletetaskmobzs`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchthisweekcompletetaskmobzs`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchthisweekcompletetaskmobzs`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchthisweekcompletetaskmobzs`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchthisweekcompletetaskmobzs`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchthisweekcompletetaskmobzs`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchthisweekcompletetaskmobzs`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchthisweekcompletetaskmobzs`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchthisweekcompletetaskmobzs`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchthisweekcompletetaskzs`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchthisweekcompletetaskzs`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchthisweekcompletetaskzs`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchthisweekcompletetaskzs`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchthisweekcompletetaskzs`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchthisweekcompletetaskzs`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchthisweekcompletetaskzs`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchthisweekcompletetaskzs`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchthisweekcompletetaskzs`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchtodolisttask`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchtodolisttask`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchtodolisttask`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchtodolisttask`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchtodolisttask`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchtodolisttask`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchtodolisttask`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchtodolisttask`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchtodolisttask`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchtypegroup`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchtypegroup`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchtypegroup`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchtypegroup`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchtypegroup`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchtypegroup`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchtypegroup`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchtypegroup`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchtypegroup`,tempData,isloading);
        return res;
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
        if(context.project && context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchtypegroupplan`,tempData,isloading);
            return res;
        }
        if(context.product && context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/fetchtypegroupplan`,tempData,isloading);
            return res;
        }
        if(context.product && context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchtypegroupplan`,tempData,isloading);
            return res;
        }
        if(context.project && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/fetchtypegroupplan`,tempData,isloading);
            return res;
        }
        if(context.story && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/fetchtypegroupplan`,tempData,isloading);
            return res;
        }
        if(context.productplan && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/fetchtypegroupplan`,tempData,isloading);
            return res;
        }
        if(context.projectmodule && context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/fetchtypegroupplan`,tempData,isloading);
            return res;
        }
        if(context.task && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/fetchtypegroupplan`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/subtasks/fetchtypegroupplan`,tempData,isloading);
        return res;
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

    /**
     * Select接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubTaskServiceBase
     */
    public async Select(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && context.task && context.subtask){
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/select`,isloading);
            
            return res;
        }
        if(context.product && context.story && context.task && context.subtask){
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/select`,isloading);
            
            return res;
        }
        if(context.product && context.productplan && context.task && context.subtask){
            let res:any = await Http.getInstance().get(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/select`,isloading);
            
            return res;
        }
        if(context.project && context.task && context.subtask){
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/subtasks/${context.subtask}/select`,isloading);
            
            return res;
        }
        if(context.story && context.task && context.subtask){
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/subtasks/${context.subtask}/select`,isloading);
            
            return res;
        }
        if(context.productplan && context.task && context.subtask){
            let res:any = await Http.getInstance().get(`/productplans/${context.productplan}/tasks/${context.task}/subtasks/${context.subtask}/select`,isloading);
            
            return res;
        }
        if(context.projectmodule && context.task && context.subtask){
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/tasks/${context.task}/subtasks/${context.subtask}/select`,isloading);
            
            return res;
        }
        if(context.task && context.subtask){
            let res:any = await Http.getInstance().get(`/tasks/${context.task}/subtasks/${context.subtask}/select`,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().get(`/subtasks/${context.subtask}/select`,isloading);
            
            return res;
    }
}