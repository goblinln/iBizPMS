import { IContext } from 'ibiz-core';
import { ITask, Task } from '../..';
import { GlobalService } from '../global.service';
import { TaskBaseService } from './task-base.service';
import { mergeDeepLeft } from 'ramda';
/**
 * 任务服务
 *
 * @export
 * @class TaskService
 * @extends {TaskBaseService}
 */
export class TaskService extends TaskBaseService {
    /**
     * Creates an instance of TaskService.
     * @memberof TaskService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('TaskService')) {
            return ___ibz___.sc.get('TaskService');
        }
        ___ibz___.sc.set('TaskService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {TaskService}
     * @memberof TaskService
     */
    static getInstance(): TaskService {
        if (!___ibz___.sc.has('TaskService')) {
            new TaskService();
        }
        return ___ibz___.sc.get('TaskService');
    }

    protected async obtainMinor(_context: IContext, _data: ITask = new Task()): Promise<ITask> {
        const res = await this.GetTemp(_context, _data);
        if (res.ok) {
            _data = mergeDeepLeft(_data, this.filterEntityData(res.data)) as any;
        }
        const minorIBZTaskTeamService: any  = await new GlobalService().getService('Ibztaskteam');
        let items = await minorIBZTaskTeamService.cache.getList(_context);
        if(items && items.length>0){
            _data.ibztaskestimates = items;
        }
        const minorIBZTaskestimatesService: any  = await new GlobalService().getService('IbzTaskestimate');
        let taskestimatems = await minorIBZTaskestimatesService.cache.getList(_context);
        if(taskestimatems && taskestimatems.length>0){
            _data.ibztaskestimates = taskestimatems;
        }
        return _data
    }

    public async Create(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && true){
            data = await this.obtainMinor(context, data);
            return this.http.post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks`,data);
        }
        if(context.product && context.story && true){
            data = await this.obtainMinor(context, data);
            return this.http.post(`/products/${context.product}/stories/${context.story}/tasks`,data);
        }
        if(context.product && context.productplan && true){
            data = await this.obtainMinor(context, data);
            return this.http.post(`/products/${context.product}/productplans/${context.productplan}/tasks`,data);
        }
        if(context.project && true){
            const minorIBZTaskTeamService: any  = await new GlobalService().getService('Ibztaskteam')
            data = await this.obtainMinor(context, data);
            //删除从实体【ibztaskestimate】数据主键
            let ibztaskestimatesData:any = data.ibztaskestimates;
            if (ibztaskestimatesData && ibztaskestimatesData.length > 0) {
                ibztaskestimatesData.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
                data.ibztaskestimates = ibztaskestimatesData;
            }
            //删除从实体【ibztaskteam】数据主键
            let ibztaskteamsData:any = data.ibztaskteams;
            if (ibztaskteamsData && ibztaskteamsData.length > 0) {
                for (const item of ibztaskteamsData) {
                    if (item.id) {
                        if (minorIBZTaskTeamService) {
                            await minorIBZTaskTeamService.removeLocal(context, item.id);
                        }
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                }
                data.ibztaskteams = ibztaskteamsData;
            }
            //删除从实体【subtask】数据主键
            let subtasksData:any = data.subtasks;
            if (subtasksData && subtasksData.length > 0) {
                subtasksData.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
                data.subtasks = subtasksData;
            }
            //删除从实体【taskestimate】数据主键
            let taskestimatesData:any = data.taskestimates;
            if (taskestimatesData && taskestimatesData.length > 0) {
                taskestimatesData.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
                data.taskestimates = taskestimatesData;
            }
            //删除从实体【ibztaskteam】数据主键
            let taskteamsData:any = data.taskteams;
            if (taskteamsData && taskteamsData.length > 0) {
                taskteamsData.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
                data.taskteams = taskteamsData;
            }
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let res:any = await this.http.post(`/projects/${context.project}/tasks`,data,isloading);
            return res;
        }
        if(context.story && true){
            data = await this.obtainMinor(context, data);
            return this.http.post(`/stories/${context.story}/tasks`,data);
        }
        if(context.productplan && true){
            data = await this.obtainMinor(context, data);
            return this.http.post(`/productplans/${context.productplan}/tasks`,data);
        }
        if(context.projectmodule && true){
            data = await this.obtainMinor(context, data);
            return this.http.post(`/projectmodules/${context.projectmodule}/tasks`,data);
        }
        return this.http.post(`/tasks`,data);
    }
}
export default TaskService;
