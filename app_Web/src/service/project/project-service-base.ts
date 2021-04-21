import { Environment } from '@/environments/environment';
import { Http } from '@/utils';
import { Util } from '@/utils';
import EntityService from '../entity-service';
import ProjectTaskQCntLogic from '@/service/project/project-task-qcnt-logic';



/**
 * 项目服务对象基类
 *
 * @export
 * @class ProjectServiceBase
 * @extends {EntityServie}
 */
export default class ProjectServiceBase extends EntityService {

    /**
     * Creates an instance of  ProjectServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     *
     * @memberof ProjectServiceBase
     */
    public initBasicData(){
        this.APPLYDEKEY ='project';
        this.APPDEKEY = 'id';
        this.APPDENAME = 'projects';
        this.APPDETEXT = 'name';
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
     * @memberof ProjectServiceBase
     */
    public async Select(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().get(`/projects/${context.project}/select`,isloading);
            
            return res;
    }

    /**
     * Create接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async Create(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let masterData:any = {};
        let projectteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_projectteams'),'undefined')){
            projectteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_projectteams') as any);
            if(projectteamsData && projectteamsData.length && projectteamsData.length > 0 && Environment.isStudioSystem === false){
                projectteamsData.forEach((item:any) => {
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
        masterData.projectteams = projectteamsData;
        let tasksData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_tasks'),'undefined')){
            tasksData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_tasks') as any);
            if(tasksData && tasksData.length && tasksData.length > 0 && Environment.isStudioSystem === false){
                tasksData.forEach((item:any) => {
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
        masterData.tasks = tasksData;
        Object.assign(data,masterData);
        if(!data.srffrontuf || data.srffrontuf !== "1"){
            data[this.APPDEKEY] = null;
        }
        if(data.srffrontuf){
            delete data.srffrontuf;
        }
        let tempContext:any = JSON.parse(JSON.stringify(context));
        let res:any = await Http.getInstance().post(`/projects`,data,isloading);
        this.tempStorage.setItem(tempContext.srfsessionkey+'_bugs',JSON.stringify(res.data.bugs?res.data.bugs:[]));
        this.tempStorage.setItem(tempContext.srfsessionkey+'_builds',JSON.stringify(res.data.builds?res.data.builds:[]));
        this.tempStorage.setItem(tempContext.srfsessionkey+'_burns',JSON.stringify(res.data.burns?res.data.burns:[]));
        this.tempStorage.setItem(tempContext.srfsessionkey+'_projectmodules',JSON.stringify(res.data.projectmodules?res.data.projectmodules:[]));
        this.tempStorage.setItem(tempContext.srfsessionkey+'_projectproducts',JSON.stringify(res.data.projectproducts?res.data.projectproducts:[]));
        this.tempStorage.setItem(tempContext.srfsessionkey+'_projectteams',JSON.stringify(res.data.projectteams?res.data.projectteams:[]));
        this.tempStorage.setItem(tempContext.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));
        this.tempStorage.setItem(tempContext.srfsessionkey+'_testreports',JSON.stringify(res.data.testreports?res.data.testreports:[]));
        this.tempStorage.setItem(tempContext.srfsessionkey+'_testtasks',JSON.stringify(res.data.testtasks?res.data.testtasks:[]));
        
        return res;
    }

    /**
     * Update接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async Update(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let masterData:any = {};
        let projectteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_projectteams'),'undefined')){
            projectteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_projectteams') as any);
            if(projectteamsData && projectteamsData.length && projectteamsData.length > 0 && Environment.isStudioSystem === false){
                projectteamsData.forEach((item:any) => {
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
        masterData.projectteams = projectteamsData;
        let tasksData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_tasks'),'undefined')){
            tasksData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_tasks') as any);
            if(tasksData && tasksData.length && tasksData.length > 0 && Environment.isStudioSystem === false){
                tasksData.forEach((item:any) => {
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
        masterData.tasks = tasksData;
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().put(`/projects/${context.project}`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_projectteams',JSON.stringify(res.data.projectteams?res.data.projectteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
    }

    /**
     * Remove接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async Remove(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().delete(`/projects/${context.project}`,isloading);
            return res;
    }

    /**
     * Get接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async Get(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().get(`/projects/${context.project}`,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_projectteams',JSON.stringify(res.data.projectteams?res.data.projectteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
    }

    /**
     * GetDraft接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async GetDraft(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        if(tempData.project) delete tempData.project;
        if(tempData.id) delete tempData.id;
        let res:any = await  Http.getInstance().get(`/projects/getdraft`,tempData,isloading);
        res.data.project = data.project;
                    this.tempStorage.setItem(context.srfsessionkey+'_projectteams',JSON.stringify(res.data.projectteams?res.data.projectteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

        return res;
    }

    /**
     * Activate接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async Activate(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().post(`/projects/${context.project}/activate`,data,isloading);
            return res;
    }

    /**
     * ActivateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async ActivateBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projects/activatebatch`,tempData,isloading);
    }

    /**
     * BatchUnlinkStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async BatchUnlinkStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().post(`/projects/${context.project}/batchunlinkstory`,data,isloading);
            return res;
    }

    /**
     * BatchUnlinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async BatchUnlinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projects/batchunlinkstorybatch`,tempData,isloading);
    }

    /**
     * CancelProjectTop接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async CancelProjectTop(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().post(`/projects/${context.project}/cancelprojecttop`,data,isloading);
            return res;
    }

    /**
     * CheckKey接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async CheckKey(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().post(`/projects/${context.project}/checkkey`,data,isloading);
            return res;
    }

    /**
     * Close接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async Close(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().post(`/projects/${context.project}/close`,data,isloading);
            return res;
    }

    /**
     * CloseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async CloseBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projects/closebatch`,tempData,isloading);
    }

    /**
     * LinkStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async LinkStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().post(`/projects/${context.project}/linkstory`,data,isloading);
            return res;
    }

    /**
     * LinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async LinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projects/linkstorybatch`,tempData,isloading);
    }

    /**
     * ManageMembers接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async ManageMembers(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().post(`/projects/${context.project}/managemembers`,data,isloading);
            return res;
    }

    /**
     * ManageMembersBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async ManageMembersBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projects/managemembersbatch`,tempData,isloading);
    }

    /**
     * MobProjectCount接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async MobProjectCount(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().post(`/projects/${context.project}/mobprojectcount`,data,isloading);
            return res;
    }

    /**
     * PmsEeProjectAllTaskCount接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async PmsEeProjectAllTaskCount(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().post(`/projects/${context.project}/pmseeprojectalltaskcount`,data,isloading);
            return res;
    }

    /**
     * PmsEeProjectAllTaskCountBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async PmsEeProjectAllTaskCountBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projects/pmseeprojectalltaskcountbatch`,tempData,isloading);
    }

    /**
     * PmsEeProjectTodoTaskCount接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async PmsEeProjectTodoTaskCount(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().post(`/projects/${context.project}/pmseeprojecttodotaskcount`,data,isloading);
            return res;
    }

    /**
     * PmsEeProjectTodoTaskCountBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async PmsEeProjectTodoTaskCountBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projects/pmseeprojecttodotaskcountbatch`,tempData,isloading);
    }

    /**
     * ProjectTaskQCnt接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async ProjectTaskQCnt(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projecttaskqcnt`,data,isloading);
            return res;
    }

    /**
     * ProjectTop接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async ProjectTop(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projecttop`,data,isloading);
            return res;
    }

    /**
     * Putoff接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async Putoff(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().post(`/projects/${context.project}/putoff`,data,isloading);
            return res;
    }

    /**
     * PutoffBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async PutoffBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projects/putoffbatch`,tempData,isloading);
    }

    /**
     * Save接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async Save(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let masterData:any = {};
        let projectteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_projectteams'),'undefined')){
            projectteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_projectteams') as any);
            if(projectteamsData && projectteamsData.length && projectteamsData.length > 0 && Environment.isStudioSystem === false){
                projectteamsData.forEach((item:any) => {
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
        masterData.projectteams = projectteamsData;
        let tasksData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_tasks'),'undefined')){
            tasksData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_tasks') as any);
            if(tasksData && tasksData.length && tasksData.length > 0 && Environment.isStudioSystem === false){
                tasksData.forEach((item:any) => {
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
        masterData.tasks = tasksData;
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().post(`/projects/${context.project}/save`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_projectteams',JSON.stringify(res.data.projectteams?res.data.projectteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
    }

    /**
     * Start接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async Start(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().post(`/projects/${context.project}/start`,data,isloading);
            return res;
    }

    /**
     * StartBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async StartBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projects/startbatch`,tempData,isloading);
    }

    /**
     * Suspend接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async Suspend(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().post(`/projects/${context.project}/suspend`,data,isloading);
            return res;
    }

    /**
     * SuspendBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async SuspendBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projects/suspendbatch`,tempData,isloading);
    }

    /**
     * UnlinkMember接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async UnlinkMember(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().post(`/projects/${context.project}/unlinkmember`,data,isloading);
            return res;
    }

    /**
     * UnlinkMemberBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async UnlinkMemberBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projects/unlinkmemberbatch`,tempData,isloading);
    }

    /**
     * UnlinkStory接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async UnlinkStory(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().post(`/projects/${context.project}/unlinkstory`,data,isloading);
            return res;
    }

    /**
     * UnlinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async UnlinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projects/unlinkstorybatch`,tempData,isloading);
    }

    /**
     * UpdateOrder接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async UpdateOrder(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await Http.getInstance().put(`/projects/${context.project}/updateorder`,data,isloading);
            return res;
    }

    /**
     * UpdateOrderBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async UpdateOrderBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projects/updateorderbatch`,tempData,isloading);
    }

    /**
     * FetchBugProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async FetchBugProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/projects/fetchbugproject`,tempData,isloading);
        return res;
    }

    /**
     * searchBugProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async searchBugProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projects/searchbugproject`,tempData,isloading);
    }

    /**
     * FetchCurPlanProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async FetchCurPlanProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/projects/fetchcurplanproject`,tempData,isloading);
        return res;
    }

    /**
     * searchCurPlanProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async searchCurPlanProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projects/searchcurplanproject`,tempData,isloading);
    }

    /**
     * FetchCurProduct接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async FetchCurProduct(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/projects/fetchcurproduct`,tempData,isloading);
        return res;
    }

    /**
     * searchCurProduct接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async searchCurProduct(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projects/searchcurproduct`,tempData,isloading);
    }

    /**
     * FetchCurUser接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async FetchCurUser(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/projects/fetchcuruser`,tempData,isloading);
        return res;
    }

    /**
     * searchCurUser接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async searchCurUser(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projects/searchcuruser`,tempData,isloading);
    }

    /**
     * FetchCurUserSa接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async FetchCurUserSa(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/projects/fetchcurusersa`,tempData,isloading);
        return res;
    }

    /**
     * searchCurUserSa接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async searchCurUserSa(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projects/searchcurusersa`,tempData,isloading);
    }

    /**
     * FetchCurUserTodo接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async FetchCurUserTodo(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/projects/fetchcurusertodo`,tempData,isloading);
        return res;
    }

    /**
     * searchCurUserTodo接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async searchCurUserTodo(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projects/searchcurusertodo`,tempData,isloading);
    }

    /**
     * FetchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async FetchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/projects/fetchdefault`,tempData,isloading);
        return res;
    }

    /**
     * searchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async searchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projects/searchdefault`,tempData,isloading);
    }

    /**
     * FetchESBulk接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async FetchESBulk(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/projects/fetchesbulk`,tempData,isloading);
        return res;
    }

    /**
     * searchESBulk接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async searchESBulk(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projects/searchesbulk`,tempData,isloading);
    }

    /**
     * FetchInvolvedProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async FetchInvolvedProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/projects/fetchinvolvedproject`,tempData,isloading);
        return res;
    }

    /**
     * searchInvolvedProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async searchInvolvedProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projects/searchinvolvedproject`,tempData,isloading);
    }

    /**
     * FetchInvolvedProject_StoryTaskBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async FetchInvolvedProject_StoryTaskBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/projects/fetchinvolvedproject_storytaskbug`,tempData,isloading);
        return res;
    }

    /**
     * searchInvolvedProject_StoryTaskBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async searchInvolvedProject_StoryTaskBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projects/searchinvolvedproject_storytaskbug`,tempData,isloading);
    }

    /**
     * FetchMyProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async FetchMyProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/projects/fetchmyproject`,tempData,isloading);
        return res;
    }

    /**
     * searchMyProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async searchMyProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projects/searchmyproject`,tempData,isloading);
    }

    /**
     * FetchProjectTeam接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async FetchProjectTeam(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/projects/fetchprojectteam`,tempData,isloading);
        return res;
    }

    /**
     * searchProjectTeam接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async searchProjectTeam(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projects/searchprojectteam`,tempData,isloading);
    }

    /**
     * FetchStoryProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async FetchStoryProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/projects/fetchstoryproject`,tempData,isloading);
        return res;
    }

    /**
     * searchStoryProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async searchStoryProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projects/searchstoryproject`,tempData,isloading);
    }

    /**
     * FetchUnDoneProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async FetchUnDoneProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/projects/fetchundoneproject`,tempData,isloading);
        return res;
    }

    /**
     * searchUnDoneProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async searchUnDoneProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projects/searchundoneproject`,tempData,isloading);
    }

    /**
     * ReturnEdit接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async ReturnEdit(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * UpdateCycle接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async UpdateCycle(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * UpdateProjectCycle接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async UpdateProjectCycle(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempBugProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async FetchTempBugProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempCurPlanProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async FetchTempCurPlanProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempCurProduct接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async FetchTempCurProduct(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempCurUser接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async FetchTempCurUser(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempCurUserSa接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async FetchTempCurUserSa(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempCurUserTodo接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async FetchTempCurUserTodo(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async FetchTempDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempESBulk接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async FetchTempESBulk(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempInvolvedProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async FetchTempInvolvedProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempInvolvedProject_StoryTaskBug接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async FetchTempInvolvedProject_StoryTaskBug(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempMyProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async FetchTempMyProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempProjectTeam接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async FetchTempProjectTeam(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempStoryProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async FetchTempStoryProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempUnDoneProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async FetchTempUnDoneProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }
}