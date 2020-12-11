import { Http,Util,HttpResponse } from '@/ibiz-core/utils';
import  { EntityService }  from '@/ibiz-core';
import { GetUserConcatLogic } from './get-user-concat-logic';



/**
 * 任务服务对象基类
 *
 * @export
 * @class TaskServiceBase
 * @extends {EntityServie}
 */
export class TaskServiceBase extends EntityService {

    /**
     * Creates an instance of  TaskServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  TaskServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     *
     * @memberof TaskServiceBase
     */
    public initBasicData(){
        this.APPLYDEKEY ='task';
        this.APPDEKEY = 'id';
        this.APPDENAME = 'tasks';
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
     * @memberof TaskServiceBase
     */
    public async Select(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task){
            let res:any = Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/select`,isloading);
            
            return res;
        }
        if(context.project && context.task){
            let res:any = Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}/select`,isloading);
            
            return res;
        }
        if(context.story && context.task){
            let res:any = Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}/select`,isloading);
            
            return res;
        }
            let res:any = Http.getInstance().get(`/tasks/${context.task}/select`,isloading);
            
            return res;
    }

    /**
     * Create接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async Create(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks`,data,isloading);
            this.tempStorage.setItem(tempContext.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(tempContext.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(tempContext.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(tempContext.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));
            
            return res;
        }
        if(context.project && true){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks`,data,isloading);
            this.tempStorage.setItem(tempContext.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(tempContext.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(tempContext.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(tempContext.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));
            
            return res;
        }
        if(context.story && true){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks`,data,isloading);
            this.tempStorage.setItem(tempContext.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(tempContext.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(tempContext.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(tempContext.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));
            
            return res;
        }
        let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
        Object.assign(data,masterData);
        if(!data.srffrontuf || data.srffrontuf !== "1"){
            data[this.APPDEKEY] = null;
        }
        if(data.srffrontuf){
            delete data.srffrontuf;
        }
        let tempContext:any = JSON.parse(JSON.stringify(context));
        let res:any = await Http.getInstance().post(`/tasks`,data,isloading);
        this.tempStorage.setItem(tempContext.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
        this.tempStorage.setItem(tempContext.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
        this.tempStorage.setItem(tempContext.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
        this.tempStorage.setItem(tempContext.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));
        
        return res;
    }

    /**
     * Update接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async Update(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/stories/${context.story}/tasks/${context.task}`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.project && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/tasks/${context.task}`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/stories/${context.story}/tasks/${context.task}`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().put(`/tasks/${context.task}`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
    }

    /**
     * Remove接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async Remove(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task){
            let res:any = Http.getInstance().delete(`/products/${context.product}/stories/${context.story}/tasks/${context.task}`,isloading);
            return res;
        }
        if(context.project && context.task){
            let res:any = Http.getInstance().delete(`/projects/${context.project}/tasks/${context.task}`,isloading);
            return res;
        }
        if(context.story && context.task){
            let res:any = Http.getInstance().delete(`/stories/${context.story}/tasks/${context.task}`,isloading);
            return res;
        }
            let res:any = Http.getInstance().delete(`/tasks/${context.task}`,isloading);
            return res;
    }

    /**
     * Get接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async Get(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task){
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/${context.task}`,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.project && context.task){
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/${context.task}`,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.story && context.task){
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/${context.task}`,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
            let res:any = await Http.getInstance().get(`/tasks/${context.task}`,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
    }

    /**
     * GetDraft接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async GetDraft(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/getdraft`,isloading);
            res.data.task = data.task;
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.project && true){
            let res:any = await Http.getInstance().get(`/projects/${context.project}/tasks/getdraft`,isloading);
            res.data.task = data.task;
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.story && true){
            let res:any = await Http.getInstance().get(`/stories/${context.story}/tasks/getdraft`,isloading);
            res.data.task = data.task;
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        let res:any = await  Http.getInstance().get(`/tasks/getdraft`,isloading);
        res.data.task = data.task;
                    this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

        return res;
    }

    /**
     * Activate接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async Activate(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/activate`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.project && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/activate`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/activate`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
            let res:any = Http.getInstance().post(`/tasks/${context.task}/activate`,data,isloading);
            return res;
    }

    /**
     * AssignTo接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async AssignTo(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/assignto`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.project && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/assignto`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/assignto`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
            let res:any = Http.getInstance().post(`/tasks/${context.task}/assignto`,data,isloading);
            return res;
    }

    /**
     * Cancel接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async Cancel(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/cancel`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.project && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/cancel`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/cancel`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
            let res:any = Http.getInstance().post(`/tasks/${context.task}/cancel`,data,isloading);
            return res;
    }

    /**
     * CheckKey接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async CheckKey(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/checkkey`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.project && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/checkkey`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/checkkey`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
            let res:any = Http.getInstance().post(`/tasks/${context.task}/checkkey`,data,isloading);
            return res;
    }

    /**
     * Close接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async Close(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/close`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.project && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/close`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/close`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
            let res:any = Http.getInstance().post(`/tasks/${context.task}/close`,data,isloading);
            return res;
    }

    /**
     * ConfirmStoryChange接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async ConfirmStoryChange(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/confirmstorychange`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.project && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/confirmstorychange`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/confirmstorychange`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
            let res:any = Http.getInstance().post(`/tasks/${context.task}/confirmstorychange`,data,isloading);
            return res;
    }

    /**
     * DeleteEstimate接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async DeleteEstimate(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/deleteestimate`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.project && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/deleteestimate`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/deleteestimate`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
            let res:any = Http.getInstance().post(`/tasks/${context.task}/deleteestimate`,data,isloading);
            return res;
    }

    /**
     * EditEstimate接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async EditEstimate(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/editestimate`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.project && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/editestimate`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/editestimate`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
            let res:any = Http.getInstance().post(`/tasks/${context.task}/editestimate`,data,isloading);
            return res;
    }

    /**
     * Finish接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async Finish(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/finish`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.project && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/finish`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/finish`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
            let res:any = Http.getInstance().post(`/tasks/${context.task}/finish`,data,isloading);
            return res;
    }

    /**
     * GetNextTeamUser接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async GetNextTeamUser(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/getnextteamuser`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.project && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/tasks/${context.task}/getnextteamuser`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/stories/${context.story}/tasks/${context.task}/getnextteamuser`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
            let res:any = Http.getInstance().put(`/tasks/${context.task}/getnextteamuser`,data,isloading);
            return res;
    }

    /**
     * GetTeamUserLeftActivity接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async GetTeamUserLeftActivity(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/getteamuserleftactivity`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.project && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/tasks/${context.task}/getteamuserleftactivity`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/stories/${context.story}/tasks/${context.task}/getteamuserleftactivity`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
            let res:any = Http.getInstance().put(`/tasks/${context.task}/getteamuserleftactivity`,data,isloading);
            return res;
    }

    /**
     * GetTeamUserLeftStart接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async GetTeamUserLeftStart(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/getteamuserleftstart`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.project && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/tasks/${context.task}/getteamuserleftstart`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/stories/${context.story}/tasks/${context.task}/getteamuserleftstart`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
            let res:any = Http.getInstance().put(`/tasks/${context.task}/getteamuserleftstart`,data,isloading);
            return res;
    }

    /**
     * GetUsernames接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async GetUsernames(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/getusernames`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.project && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/tasks/${context.task}/getusernames`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/stories/${context.story}/tasks/${context.task}/getusernames`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
            let res:any = Http.getInstance().put(`/tasks/${context.task}/getusernames`,data,isloading);
            return res;
    }

    /**
     * OtherUpdate接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async OtherUpdate(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/otherupdate`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.project && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/tasks/${context.task}/otherupdate`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/stories/${context.story}/tasks/${context.task}/otherupdate`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
            let res:any = Http.getInstance().put(`/tasks/${context.task}/otherupdate`,data,isloading);
            return res;
    }

    /**
     * Pause接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async Pause(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/pause`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.project && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/pause`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/pause`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
            let res:any = Http.getInstance().post(`/tasks/${context.task}/pause`,data,isloading);
            return res;
    }

    /**
     * RecordEstimate接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async RecordEstimate(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/recordestimate`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.project && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/recordestimate`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/recordestimate`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
            let res:any = Http.getInstance().post(`/tasks/${context.task}/recordestimate`,data,isloading);
            return res;
    }

    /**
     * Restart接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async Restart(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/restart`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.project && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/restart`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/restart`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
            let res:any = Http.getInstance().post(`/tasks/${context.task}/restart`,data,isloading);
            return res;
    }

    /**
     * Save接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async Save(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/save`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.project && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/save`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/save`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().post(`/tasks/${context.task}/save`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
    }

    /**
     * SendMessage接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async SendMessage(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/sendmessage`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.project && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/sendmessage`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/sendmessage`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
            let res:any = Http.getInstance().post(`/tasks/${context.task}/sendmessage`,data,isloading);
            return res;
    }

    /**
     * SendMsgPreProcess接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async SendMsgPreProcess(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/sendmsgpreprocess`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.project && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/sendmsgpreprocess`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/sendmsgpreprocess`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
            let res:any = Http.getInstance().post(`/tasks/${context.task}/sendmsgpreprocess`,data,isloading);
            return res;
    }

    /**
     * Start接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async Start(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/start`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.project && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/start`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/start`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
            let res:any = Http.getInstance().post(`/tasks/${context.task}/start`,data,isloading);
            return res;
    }

    /**
     * TaskFavorites接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async TaskFavorites(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/taskfavorites`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.project && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/taskfavorites`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/taskfavorites`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
            let res:any = Http.getInstance().post(`/tasks/${context.task}/taskfavorites`,data,isloading);
            return res;
    }

    /**
     * TaskForward接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async TaskForward(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/taskforward`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.project && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/taskforward`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/taskforward`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
            let res:any = Http.getInstance().post(`/tasks/${context.task}/taskforward`,data,isloading);
            return res;
    }

    /**
     * TaskNFavorites接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async TaskNFavorites(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/tasknfavorites`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.project && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/projects/${context.project}/tasks/${context.task}/tasknfavorites`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/tasks/${context.task}/tasknfavorites`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
            let res:any = Http.getInstance().post(`/tasks/${context.task}/tasknfavorites`,data,isloading);
            return res;
    }

    /**
     * UpdateStoryVersion接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async UpdateStoryVersion(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/stories/${context.story}/tasks/${context.task}/updatestoryversion`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.project && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/projects/${context.project}/tasks/${context.task}/updatestoryversion`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
        if(context.story && context.task){
            let masterData:any = {};
        let ibztaskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates'),'undefined')){
            ibztaskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskestimates') as any);
            if(ibztaskestimatesData && ibztaskestimatesData.length && ibztaskestimatesData.length > 0){
                ibztaskestimatesData.forEach((item:any) => {
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
        masterData.ibztaskestimates = ibztaskestimatesData;
        let ibztaskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams'),'undefined')){
            ibztaskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibztaskteams') as any);
            if(ibztaskteamsData && ibztaskteamsData.length && ibztaskteamsData.length > 0){
                ibztaskteamsData.forEach((item:any) => {
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
        masterData.ibztaskteams = ibztaskteamsData;
        let taskestimatesData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates'),'undefined')){
            taskestimatesData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskestimates') as any);
            if(taskestimatesData && taskestimatesData.length && taskestimatesData.length > 0){
                taskestimatesData.forEach((item:any) => {
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
        masterData.taskestimates = taskestimatesData;
        let taskteamsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_taskteams'),'undefined')){
            taskteamsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_taskteams') as any);
            if(taskteamsData && taskteamsData.length && taskteamsData.length > 0){
                taskteamsData.forEach((item:any) => {
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
        masterData.taskteams = taskteamsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/stories/${context.story}/tasks/${context.task}/updatestoryversion`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_ibztaskestimates',JSON.stringify(res.data.ibztaskestimates?res.data.ibztaskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibztaskteams',JSON.stringify(res.data.ibztaskteams?res.data.ibztaskteams:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskestimates',JSON.stringify(res.data.taskestimates?res.data.taskestimates:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_taskteams',JSON.stringify(res.data.taskteams?res.data.taskteams:[]));

            return res;
        }
            let res:any = Http.getInstance().put(`/tasks/${context.task}/updatestoryversion`,data,isloading);
            return res;
    }

    /**
     * FetchAssignedToMyTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchAssignedToMyTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/fetchassignedtomytask`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/projects/${context.project}/tasks/fetchassignedtomytask`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/stories/${context.story}/tasks/fetchassignedtomytask`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = Http.getInstance().get(`/tasks/fetchassignedtomytask`,tempData,isloading);
        return res;
    }

    /**
     * FetchAssignedToMyTaskPc接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchAssignedToMyTaskPc(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/fetchassignedtomytaskpc`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/projects/${context.project}/tasks/fetchassignedtomytaskpc`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/stories/${context.story}/tasks/fetchassignedtomytaskpc`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = Http.getInstance().get(`/tasks/fetchassignedtomytaskpc`,tempData,isloading);
        return res;
    }

    /**
     * FetchBugTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchBugTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/fetchbugtask`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/projects/${context.project}/tasks/fetchbugtask`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/stories/${context.story}/tasks/fetchbugtask`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = Http.getInstance().get(`/tasks/fetchbugtask`,tempData,isloading);
        return res;
    }

    /**
     * FetchByModule接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchByModule(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/fetchbymodule`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().post(`/projects/${context.project}/tasks/fetchbymodule`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().post(`/stories/${context.story}/tasks/fetchbymodule`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = Http.getInstance().post(`/tasks/fetchbymodule`,tempData,isloading);
        return res;
    }

    /**
     * FetchChildTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchChildTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/fetchchildtask`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/projects/${context.project}/tasks/fetchchildtask`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/stories/${context.story}/tasks/fetchchildtask`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = Http.getInstance().get(`/tasks/fetchchildtask`,tempData,isloading);
        return res;
    }

    /**
     * FetchChildTaskTree接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchChildTaskTree(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/fetchchildtasktree`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/projects/${context.project}/tasks/fetchchildtasktree`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/stories/${context.story}/tasks/fetchchildtasktree`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = Http.getInstance().get(`/tasks/fetchchildtasktree`,tempData,isloading);
        return res;
    }

    /**
     * FetchCurFinishTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchCurFinishTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/fetchcurfinishtask`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/projects/${context.project}/tasks/fetchcurfinishtask`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/stories/${context.story}/tasks/fetchcurfinishtask`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = Http.getInstance().get(`/tasks/fetchcurfinishtask`,tempData,isloading);
        return res;
    }

    /**
     * FetchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().post(`/projects/${context.project}/tasks/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().post(`/stories/${context.story}/tasks/fetchdefault`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = Http.getInstance().post(`/tasks/fetchdefault`,tempData,isloading);
        return res;
    }

    /**
     * FetchDefaultRow接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchDefaultRow(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/fetchdefaultrow`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/projects/${context.project}/tasks/fetchdefaultrow`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/stories/${context.story}/tasks/fetchdefaultrow`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = Http.getInstance().get(`/tasks/fetchdefaultrow`,tempData,isloading);
        return res;
    }

    /**
     * FetchMyCompleteTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchMyCompleteTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/fetchmycompletetask`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/projects/${context.project}/tasks/fetchmycompletetask`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/stories/${context.story}/tasks/fetchmycompletetask`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = Http.getInstance().get(`/tasks/fetchmycompletetask`,tempData,isloading);
        return res;
    }

    /**
     * FetchMyCompleteTaskMobDaily接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchMyCompleteTaskMobDaily(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/fetchmycompletetaskmobdaily`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/projects/${context.project}/tasks/fetchmycompletetaskmobdaily`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/stories/${context.story}/tasks/fetchmycompletetaskmobdaily`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = Http.getInstance().get(`/tasks/fetchmycompletetaskmobdaily`,tempData,isloading);
        return res;
    }

    /**
     * FetchMyCompleteTaskMobMonthly接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchMyCompleteTaskMobMonthly(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/fetchmycompletetaskmobmonthly`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/projects/${context.project}/tasks/fetchmycompletetaskmobmonthly`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/stories/${context.story}/tasks/fetchmycompletetaskmobmonthly`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = Http.getInstance().get(`/tasks/fetchmycompletetaskmobmonthly`,tempData,isloading);
        return res;
    }

    /**
     * FetchMyCompleteTaskMonthlyZS接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchMyCompleteTaskMonthlyZS(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/fetchmycompletetaskmonthlyzs`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/projects/${context.project}/tasks/fetchmycompletetaskmonthlyzs`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/stories/${context.story}/tasks/fetchmycompletetaskmonthlyzs`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = Http.getInstance().get(`/tasks/fetchmycompletetaskmonthlyzs`,tempData,isloading);
        return res;
    }

    /**
     * FetchMyCompleteTaskZS接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchMyCompleteTaskZS(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/fetchmycompletetaskzs`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/projects/${context.project}/tasks/fetchmycompletetaskzs`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/stories/${context.story}/tasks/fetchmycompletetaskzs`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = Http.getInstance().get(`/tasks/fetchmycompletetaskzs`,tempData,isloading);
        return res;
    }

    /**
     * FetchMyFavorites接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchMyFavorites(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/fetchmyfavorites`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().post(`/projects/${context.project}/tasks/fetchmyfavorites`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().post(`/stories/${context.story}/tasks/fetchmyfavorites`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = Http.getInstance().post(`/tasks/fetchmyfavorites`,tempData,isloading);
        return res;
    }

    /**
     * FetchMyPlansTaskMobMonthly接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchMyPlansTaskMobMonthly(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/fetchmyplanstaskmobmonthly`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/projects/${context.project}/tasks/fetchmyplanstaskmobmonthly`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/stories/${context.story}/tasks/fetchmyplanstaskmobmonthly`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = Http.getInstance().get(`/tasks/fetchmyplanstaskmobmonthly`,tempData,isloading);
        return res;
    }

    /**
     * FetchMyTomorrowPlanTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchMyTomorrowPlanTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/fetchmytomorrowplantask`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/projects/${context.project}/tasks/fetchmytomorrowplantask`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/stories/${context.story}/tasks/fetchmytomorrowplantask`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = Http.getInstance().get(`/tasks/fetchmytomorrowplantask`,tempData,isloading);
        return res;
    }

    /**
     * FetchMyTomorrowPlanTaskMobDaily接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchMyTomorrowPlanTaskMobDaily(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/fetchmytomorrowplantaskmobdaily`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/projects/${context.project}/tasks/fetchmytomorrowplantaskmobdaily`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/stories/${context.story}/tasks/fetchmytomorrowplantaskmobdaily`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = Http.getInstance().get(`/tasks/fetchmytomorrowplantaskmobdaily`,tempData,isloading);
        return res;
    }

    /**
     * FetchNextWeekCompleteTaskMobZS接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchNextWeekCompleteTaskMobZS(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/fetchnextweekcompletetaskmobzs`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/projects/${context.project}/tasks/fetchnextweekcompletetaskmobzs`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/stories/${context.story}/tasks/fetchnextweekcompletetaskmobzs`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = Http.getInstance().get(`/tasks/fetchnextweekcompletetaskmobzs`,tempData,isloading);
        return res;
    }

    /**
     * FetchNextWeekCompleteTaskZS接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchNextWeekCompleteTaskZS(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/fetchnextweekcompletetaskzs`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/projects/${context.project}/tasks/fetchnextweekcompletetaskzs`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/stories/${context.story}/tasks/fetchnextweekcompletetaskzs`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = Http.getInstance().get(`/tasks/fetchnextweekcompletetaskzs`,tempData,isloading);
        return res;
    }

    /**
     * FetchNextWeekPlanCompleteTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchNextWeekPlanCompleteTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/fetchnextweekplancompletetask`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/projects/${context.project}/tasks/fetchnextweekplancompletetask`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/stories/${context.story}/tasks/fetchnextweekplancompletetask`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = Http.getInstance().get(`/tasks/fetchnextweekplancompletetask`,tempData,isloading);
        return res;
    }

    /**
     * FetchProjectTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchProjectTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/fetchprojecttask`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().post(`/projects/${context.project}/tasks/fetchprojecttask`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().post(`/stories/${context.story}/tasks/fetchprojecttask`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = Http.getInstance().post(`/tasks/fetchprojecttask`,tempData,isloading);
        return res;
    }

    /**
     * FetchRootTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchRootTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/fetchroottask`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/projects/${context.project}/tasks/fetchroottask`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/stories/${context.story}/tasks/fetchroottask`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = Http.getInstance().get(`/tasks/fetchroottask`,tempData,isloading);
        return res;
    }

    /**
     * FetchThisMonthCompleteTaskChoice接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchThisMonthCompleteTaskChoice(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/fetchthismonthcompletetaskchoice`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/projects/${context.project}/tasks/fetchthismonthcompletetaskchoice`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/stories/${context.story}/tasks/fetchthismonthcompletetaskchoice`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = Http.getInstance().get(`/tasks/fetchthismonthcompletetaskchoice`,tempData,isloading);
        return res;
    }

    /**
     * FetchThisWeekCompleteTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchThisWeekCompleteTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/fetchthisweekcompletetask`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/projects/${context.project}/tasks/fetchthisweekcompletetask`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/stories/${context.story}/tasks/fetchthisweekcompletetask`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = Http.getInstance().get(`/tasks/fetchthisweekcompletetask`,tempData,isloading);
        return res;
    }

    /**
     * FetchThisWeekCompleteTaskChoice接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchThisWeekCompleteTaskChoice(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/fetchthisweekcompletetaskchoice`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/projects/${context.project}/tasks/fetchthisweekcompletetaskchoice`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/stories/${context.story}/tasks/fetchthisweekcompletetaskchoice`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = Http.getInstance().get(`/tasks/fetchthisweekcompletetaskchoice`,tempData,isloading);
        return res;
    }

    /**
     * FetchThisWeekCompleteTaskMobZS接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchThisWeekCompleteTaskMobZS(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/fetchthisweekcompletetaskmobzs`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/projects/${context.project}/tasks/fetchthisweekcompletetaskmobzs`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/stories/${context.story}/tasks/fetchthisweekcompletetaskmobzs`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = Http.getInstance().get(`/tasks/fetchthisweekcompletetaskmobzs`,tempData,isloading);
        return res;
    }

    /**
     * FetchThisWeekCompleteTaskZS接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchThisWeekCompleteTaskZS(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/fetchthisweekcompletetaskzs`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/projects/${context.project}/tasks/fetchthisweekcompletetaskzs`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/stories/${context.story}/tasks/fetchthisweekcompletetaskzs`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = Http.getInstance().get(`/tasks/fetchthisweekcompletetaskzs`,tempData,isloading);
        return res;
    }

    /**
     * FetchTodoListTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchTodoListTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/fetchtodolisttask`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/projects/${context.project}/tasks/fetchtodolisttask`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/stories/${context.story}/tasks/fetchtodolisttask`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = Http.getInstance().get(`/tasks/fetchtodolisttask`,tempData,isloading);
        return res;
    }

    /**
     * FetchTypeGroup接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchTypeGroup(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/products/${context.product}/stories/${context.story}/tasks/fetchtypegroup`,tempData,isloading);
            return res;
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/projects/${context.project}/tasks/fetchtypegroup`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = Http.getInstance().get(`/stories/${context.story}/tasks/fetchtypegroup`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = Http.getInstance().get(`/tasks/fetchtypegroup`,tempData,isloading);
        return res;
    }

    /**
     * CalcTime接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async CalcTime(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * GetUserConcat接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async GetUserConcat(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let appLogic:GetUserConcatLogic = new GetUserConcatLogic({context:JSON.parse(JSON.stringify(context)),data:JSON.parse(JSON.stringify(data))});
        const res = await appLogic.onExecute(context,data,isloading?true:false);
        return {status:200,data:res};
    }

    /**
     * FetchTempAssignedToMyTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchTempAssignedToMyTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempAssignedToMyTaskPc接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
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
     * @memberof TaskServiceBase
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
     * @memberof TaskServiceBase
     */
    public async FetchTempByModule(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempChildTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
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
     * @memberof TaskServiceBase
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
     * @memberof TaskServiceBase
     */
    public async FetchTempCurFinishTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
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
     * @memberof TaskServiceBase
     */
    public async FetchTempDefaultRow(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempMyCompleteTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
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
     * @memberof TaskServiceBase
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
     * @memberof TaskServiceBase
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
     * @memberof TaskServiceBase
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
     * @memberof TaskServiceBase
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
     * @memberof TaskServiceBase
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
     * @memberof TaskServiceBase
     */
    public async FetchTempMyPlansTaskMobMonthly(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempMyTomorrowPlanTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
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
     * @memberof TaskServiceBase
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
     * @memberof TaskServiceBase
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
     * @memberof TaskServiceBase
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
     * @memberof TaskServiceBase
     */
    public async FetchTempNextWeekPlanCompleteTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempProjectTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchTempProjectTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempRootTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FetchTempRootTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempThisMonthCompleteTaskChoice接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
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
     * @memberof TaskServiceBase
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
     * @memberof TaskServiceBase
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
     * @memberof TaskServiceBase
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
     * @memberof TaskServiceBase
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
     * @memberof TaskServiceBase
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
     * @memberof TaskServiceBase
     */
    public async FetchTempTypeGroup(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }
}