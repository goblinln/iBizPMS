import { Http,Util,HttpResponse } from '@/ibiz-core/utils';
import  { EntityService }  from '@/ibiz-core';
import { ProjectTaskQCntLogic } from './project-task-qcnt-logic';



/**
 * 项目服务对象基类
 *
 * @export
 * @class ProjectServiceBase
 * @extends {EntityServie}
 */
export class ProjectServiceBase extends EntityService {

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
        this.APPNAME = 'mob';
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
            let res:any = Http.getInstance().get(`/projects/${context.project}/select`,isloading);
            
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
        let ibzprojectteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzprojectteams'),'undefined')){
            ibzprojectteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzprojectteams') as any);
            if(ibzprojectteamsData && ibzprojectteamsData.length && ibzprojectteamsData.length > 0){
                ibzprojectteamsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) item.id = null;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzprojectteams = ibzprojectteamsData;
        let projectteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_projectteams'),'undefined')){
            projectteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_projectteams') as any);
            if(projectteamsData && projectteamsData.length && projectteamsData.length > 0){
                projectteamsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) item.id = null;
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
            if(tasksData && tasksData.length && tasksData.length > 0){
                tasksData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) item.id = null;
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
        this.tempStorage.setItem(tempContext.srfsessionkey+'_ibzprojectteams',JSON.stringify(res.data.ibzprojectteams?res.data.ibzprojectteams:[]));
        this.tempStorage.setItem(tempContext.srfsessionkey+'_projectmodules',JSON.stringify(res.data.projectmodules?res.data.projectmodules:[]));
        this.tempStorage.setItem(tempContext.srfsessionkey+'_projectteams',JSON.stringify(res.data.projectteams?res.data.projectteams:[]));
        this.tempStorage.setItem(tempContext.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));
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
        let ibzprojectteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzprojectteams'),'undefined')){
            ibzprojectteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzprojectteams') as any);
            if(ibzprojectteamsData && ibzprojectteamsData.length && ibzprojectteamsData.length > 0){
                ibzprojectteamsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) item.id = null;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzprojectteams = ibzprojectteamsData;
        let projectteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_projectteams'),'undefined')){
            projectteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_projectteams') as any);
            if(projectteamsData && projectteamsData.length && projectteamsData.length > 0){
                projectteamsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) item.id = null;
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
            if(tasksData && tasksData.length && tasksData.length > 0){
                tasksData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) item.id = null;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.tasks = tasksData;
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().put(`/projects/${context.project}`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibzprojectteams',JSON.stringify(res.data.ibzprojectteams?res.data.ibzprojectteams:[]));
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
            let res:any = Http.getInstance().delete(`/projects/${context.project}`,isloading);
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
                        this.tempStorage.setItem(context.srfsessionkey+'_ibzprojectteams',JSON.stringify(res.data.ibzprojectteams?res.data.ibzprojectteams:[]));
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
        let res:any = await  Http.getInstance().get(`/projects/getdraft`,isloading);
        res.data.project = data.project;
                    this.tempStorage.setItem(context.srfsessionkey+'_ibzprojectteams',JSON.stringify(res.data.ibzprojectteams?res.data.ibzprojectteams:[]));
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
            let res:any = Http.getInstance().post(`/projects/${context.project}/activate`,data,isloading);
            return res;
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
            let res:any = Http.getInstance().post(`/projects/${context.project}/batchunlinkstory`,data,isloading);
            return res;
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
            let res:any = Http.getInstance().post(`/projects/${context.project}/cancelprojecttop`,data,isloading);
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
            let res:any = Http.getInstance().post(`/projects/${context.project}/checkkey`,data,isloading);
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
            let res:any = Http.getInstance().post(`/projects/${context.project}/close`,data,isloading);
            return res;
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
            let res:any = Http.getInstance().post(`/projects/${context.project}/linkstory`,data,isloading);
            return res;
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
            let res:any = Http.getInstance().post(`/projects/${context.project}/managemembers`,data,isloading);
            return res;
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
            let res:any = Http.getInstance().post(`/projects/${context.project}/mobprojectcount`,data,isloading);
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
            let res:any = Http.getInstance().post(`/projects/${context.project}/pmseeprojectalltaskcount`,data,isloading);
            return res;
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
            let res:any = Http.getInstance().post(`/projects/${context.project}/pmseeprojecttodotaskcount`,data,isloading);
            return res;
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
            let res:any = Http.getInstance().post(`/projects/${context.project}/projecttaskqcnt`,data,isloading);
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
            let res:any = Http.getInstance().post(`/projects/${context.project}/projecttop`,data,isloading);
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
            let res:any = Http.getInstance().post(`/projects/${context.project}/putoff`,data,isloading);
            return res;
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
        let ibzprojectteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzprojectteams'),'undefined')){
            ibzprojectteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzprojectteams') as any);
            if(ibzprojectteamsData && ibzprojectteamsData.length && ibzprojectteamsData.length > 0){
                ibzprojectteamsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) item.id = null;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzprojectteams = ibzprojectteamsData;
        let projectteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_projectteams'),'undefined')){
            projectteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_projectteams') as any);
            if(projectteamsData && projectteamsData.length && projectteamsData.length > 0){
                projectteamsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) item.id = null;
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
            if(tasksData && tasksData.length && tasksData.length > 0){
                tasksData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) item.id = null;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.tasks = tasksData;
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().post(`/projects/${context.project}/save`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibzprojectteams',JSON.stringify(res.data.ibzprojectteams?res.data.ibzprojectteams:[]));
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
            let res:any = Http.getInstance().post(`/projects/${context.project}/start`,data,isloading);
            return res;
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
            let res:any = Http.getInstance().post(`/projects/${context.project}/suspend`,data,isloading);
            return res;
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
            let res:any = Http.getInstance().post(`/projects/${context.project}/unlinkmember`,data,isloading);
            return res;
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
            let res:any = Http.getInstance().post(`/projects/${context.project}/unlinkstory`,data,isloading);
            return res;
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
            let res:any = Http.getInstance().put(`/projects/${context.project}/updateorder`,data,isloading);
            return res;
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
        let res:any = Http.getInstance().get(`/projects/fetchbugproject`,tempData,isloading);
        return res;
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
        let res:any = Http.getInstance().get(`/projects/fetchcurplanproject`,tempData,isloading);
        return res;
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
        let res:any = Http.getInstance().post(`/projects/fetchcurproduct`,tempData,isloading);
        return res;
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
        let res:any = Http.getInstance().post(`/projects/fetchcuruser`,tempData,isloading);
        return res;
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
        let res:any = Http.getInstance().post(`/projects/fetchcurusersa`,tempData,isloading);
        return res;
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
        let res:any = Http.getInstance().get(`/projects/fetchcurusertodo`,tempData,isloading);
        return res;
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
        let res:any = Http.getInstance().get(`/projects/fetchdefault`,tempData,isloading);
        return res;
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
        let res:any = Http.getInstance().get(`/projects/fetchesbulk`,tempData,isloading);
        return res;
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
        let res:any = Http.getInstance().get(`/projects/fetchinvolvedproject`,tempData,isloading);
        return res;
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
        let res:any = Http.getInstance().post(`/projects/fetchinvolvedproject_storytaskbug`,tempData,isloading);
        return res;
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
        let res:any = Http.getInstance().post(`/projects/fetchmyproject`,tempData,isloading);
        return res;
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
        let res:any = Http.getInstance().get(`/projects/fetchprojectteam`,tempData,isloading);
        return res;
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
        let res:any = Http.getInstance().get(`/projects/fetchstoryproject`,tempData,isloading);
        return res;
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
        let res:any = Http.getInstance().get(`/projects/fetchundoneproject`,tempData,isloading);
        return res;
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