import { Environment } from '@/environments/environment';
import { Http } from '@/utils';
import { Util } from '@/utils';
import EntityService from '../entity-service';



/**
 * 任务模块服务对象基类
 *
 * @export
 * @class ProjectModuleServiceBase
 * @extends {EntityServie}
 */
export default class ProjectModuleServiceBase extends EntityService {

    /**
     * Creates an instance of  ProjectModuleServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectModuleServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     *
     * @memberof ProjectModuleServiceBase
     */
    public initBasicData(){
        this.APPLYDEKEY ='projectmodule';
        this.APPDEKEY = 'id';
        this.APPDENAME = 'projectmodules';
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
     * @memberof ProjectModuleServiceBase
     */
    public async CheckKey(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule){
            let masterData:any = {};
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
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/checkkey`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/checkkey`,data,isloading);
            return res;
    }

    /**
     * Create接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectModuleServiceBase
     */
    public async Create(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let masterData:any = {};
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
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules`,data,isloading);
            this.tempStorage.setItem(tempContext.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));
            
            return res;
        }
        let masterData:any = {};
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
        let res:any = await Http.getInstance().post(`/projectmodules`,data,isloading);
        this.tempStorage.setItem(tempContext.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));
        
        return res;
    }

    /**
     * Fix接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectModuleServiceBase
     */
    public async Fix(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule){
            let masterData:any = {};
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
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/fix`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/projectmodules/${context.projectmodule}/fix`,data,isloading);
            return res;
    }

    /**
     * Get接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectModuleServiceBase
     */
    public async Get(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule){
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}`,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
        }
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}`,isloading);
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
     * @memberof ProjectModuleServiceBase
     */
    public async GetDraft(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.projectmodule) delete tempData.projectmodule;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/getdraft`,tempData,isloading);
            res.data.projectmodule = data.projectmodule;
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        if(tempData.projectmodule) delete tempData.projectmodule;
        if(tempData.id) delete tempData.id;
        let res:any = await  Http.getInstance().get(`/projectmodules/getdraft`,tempData,isloading);
        res.data.projectmodule = data.projectmodule;
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
     * @memberof ProjectModuleServiceBase
     */
    public async Remove(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule){
            let res:any = await Http.getInstance().delete(`/projects/${context.project}/projectmodules/${context.projectmodule}`,isloading);
            return res;
        }
            let res:any = await Http.getInstance().delete(`/projectmodules/${context.projectmodule}`,isloading);
            return res;
    }

    /**
     * RemoveModule接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectModuleServiceBase
     */
    public async RemoveModule(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule){
            let masterData:any = {};
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
            let res:any = await Http.getInstance().put(`/projects/${context.project}/projectmodules/${context.projectmodule}/removemodule`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
        }
            let res:any = await Http.getInstance().put(`/projectmodules/${context.projectmodule}/removemodule`,data,isloading);
            return res;
    }

    /**
     * Save接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectModuleServiceBase
     */
    public async Save(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule){
            let masterData:any = {};
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
            let res:any = await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/save`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
        }
        let masterData:any = {};
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
            let res:any = await  Http.getInstance().post(`/projectmodules/${context.projectmodule}/save`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
    }

    /**
     * Update接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectModuleServiceBase
     */
    public async Update(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule){
            let masterData:any = {};
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
            let res:any = await Http.getInstance().put(`/projects/${context.project}/projectmodules/${context.projectmodule}`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
        }
        let masterData:any = {};
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
            let res:any = await  Http.getInstance().put(`/projectmodules/${context.projectmodule}`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));

            return res;
    }

    /**
     * FetchByPath接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectModuleServiceBase
     */
    public async FetchByPath(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/fetchbypath`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/projectmodules/fetchbypath`,tempData,isloading);
        return res;
    }

    /**
     * searchByPath接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectModuleServiceBase
     */
    public async searchByPath(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/searchbypath`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projectmodules/searchbypath`,tempData,isloading);
    }

    /**
     * FetchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectModuleServiceBase
     */
    public async FetchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/fetchdefault`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/projectmodules/fetchdefault`,tempData,isloading);
        return res;
    }

    /**
     * searchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectModuleServiceBase
     */
    public async searchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/searchdefault`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projectmodules/searchdefault`,tempData,isloading);
    }

    /**
     * FetchParentModule接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectModuleServiceBase
     */
    public async FetchParentModule(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/fetchparentmodule`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/projectmodules/fetchparentmodule`,tempData,isloading);
        return res;
    }

    /**
     * searchParentModule接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectModuleServiceBase
     */
    public async searchParentModule(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/searchparentmodule`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projectmodules/searchparentmodule`,tempData,isloading);
    }

    /**
     * FetchRoot接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectModuleServiceBase
     */
    public async FetchRoot(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/fetchroot`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/projectmodules/fetchroot`,tempData,isloading);
        return res;
    }

    /**
     * searchRoot接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectModuleServiceBase
     */
    public async searchRoot(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/searchroot`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projectmodules/searchroot`,tempData,isloading);
    }

    /**
     * FetchRoot_NoBranch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectModuleServiceBase
     */
    public async FetchRoot_NoBranch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/fetchroot_nobranch`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/projectmodules/fetchroot_nobranch`,tempData,isloading);
        return res;
    }

    /**
     * searchRoot_NoBranch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectModuleServiceBase
     */
    public async searchRoot_NoBranch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/searchroot_nobranch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projectmodules/searchroot_nobranch`,tempData,isloading);
    }

    /**
     * FetchRoot_Task接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectModuleServiceBase
     */
    public async FetchRoot_Task(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/fetchroot_task`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/projectmodules/fetchroot_task`,tempData,isloading);
        return res;
    }

    /**
     * searchRoot_Task接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectModuleServiceBase
     */
    public async searchRoot_Task(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/searchroot_task`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projectmodules/searchroot_task`,tempData,isloading);
    }

    /**
     * FetchTaskModules接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectModuleServiceBase
     */
    public async FetchTaskModules(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/fetchtaskmodules`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/projectmodules/fetchtaskmodules`,tempData,isloading);
        return res;
    }

    /**
     * searchTaskModules接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectModuleServiceBase
     */
    public async searchTaskModules(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/searchtaskmodules`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/projectmodules/searchtaskmodules`,tempData,isloading);
    }

    /**
     * Select接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectModuleServiceBase
     */
    public async Select(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule){
            let res:any = await Http.getInstance().get(`/projects/${context.project}/projectmodules/${context.projectmodule}/select`,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().get(`/projectmodules/${context.projectmodule}/select`,isloading);
            
            return res;
    }
}