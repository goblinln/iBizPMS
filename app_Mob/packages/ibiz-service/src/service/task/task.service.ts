import { GlobalService } from '..';
import { ITask, Task } from '../..';
import { HttpResponse, IContext } from 'ibiz-core';
import { TaskBaseService } from './task-base.service';

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
    constructor(opts?: any) {
        const { context: context, tag: cacheKey } = opts;
        super(context);
        if (___ibz___.sc.has(cacheKey)) {
            return ___ibz___.sc.get(cacheKey);
        }
        ___ibz___.sc.set(cacheKey, this);
    }

    /**
     * 获取实例
     *
     * @static
     * @param 应用上下文
     * @return {*}  {TaskService}
     * @memberof TaskService
     */
    static getInstance(context?: any): TaskService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}TaskService` : `TaskService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new TaskService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
    protected async obtainMinor(_context: IContext, _data: ITask = new Task()): Promise<ITask> {
        const res = await this.GetTemp(_context, _data);
        if (res.ok) {
            _data = mergeDeepLeft(_data, this.filterEntityData(res.data)) as any;
        }
        const minorIBZTaskTeamService: any = await new GlobalService().getService('Ibztaskteam');
        let items = await minorIBZTaskTeamService.cache.getList(_context);
        if (items && items.length > 0) {
            _data.ibztaskteams = items;
        }
        const minorIBZTaskestimatesService: any = await new GlobalService().getService('IbzTaskestimate');
        let ibztaskestimates = await minorIBZTaskestimatesService.cache.getList(_context);
        if (ibztaskestimates && ibztaskestimates.length > 0) {
            _data.ibztaskestimates = ibztaskestimates;
        }
        return _data
    }

    public async Create(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        if (context.project && context.projectmodule && true) {
            data = await this.obtainMinor(context, data);
            return this.http.post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks`, data);
        }
        if (context.product && context.story && true) {
            data = await this.obtainMinor(context, data);
            return this.http.post(`/products/${context.product}/stories/${context.story}/tasks`, data);
        }
        if (context.product && context.productplan && true) {
            data = await this.obtainMinor(context, data);
            return this.http.post(`/products/${context.product}/productplans/${context.productplan}/tasks`, data);
        }
        if (context.project && true) {
            const minorIBZTaskTeamService: any = await new GlobalService().getService('Ibztaskteam')
            data = await this.obtainMinor(context, data);
            //删除从实体【ibztaskestimate】数据主键
            let ibztaskestimatesData: any = data.ibztaskestimates;
            if (ibztaskestimatesData && ibztaskestimatesData.length > 0) {
                debugger
                ibztaskestimatesData.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if (item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
                data.ibztaskestimates = ibztaskestimatesData;
            }
            //删除从实体【ibztaskteam】数据主键
            let ibztaskteamsData: any = data.ibztaskteams;
            if (ibztaskteamsData && ibztaskteamsData.length > 0) {
                for (const item of ibztaskteamsData) {
                    if (item.id) {
                        if (minorIBZTaskTeamService) {
                            await minorIBZTaskTeamService.removeLocal(context, item.id);
                        }
                        item.id = null;
                        if (item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                }
                data.ibztaskteams = ibztaskteamsData;
            }
            //删除从实体【subtask】数据主键
            let subtasksData: any = data.subtasks;
            if (subtasksData && subtasksData.length > 0) {
                subtasksData.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if (item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
                data.subtasks = subtasksData;
            }
            //删除从实体【taskestimate】数据主键
            let taskestimatesData: any = data.taskestimates;
            if (taskestimatesData && taskestimatesData.length > 0) {
                taskestimatesData.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if (item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
                data.taskestimates = taskestimatesData;
            }
            //删除从实体【ibztaskteam】数据主键
            let taskteamsData: any = data.taskteams;
            if (taskteamsData && taskteamsData.length > 0) {
                taskteamsData.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if (item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
                data.taskteams = taskteamsData;
            }
            if (!data.srffrontuf || data.srffrontuf !== "1") {
                data[this.APPDEKEY] = null;
            }
            if (data.srffrontuf) {
                delete data.srffrontuf;
            }
            let res: any = await this.http.post(`/projects/${context.project}/tasks`, data, isloading);
            return res;
        }
        if (context.story && true) {
            data = await this.obtainMinor(context, data);
            return this.http.post(`/stories/${context.story}/tasks`, data);
        }
        if (context.productplan && true) {
            data = await this.obtainMinor(context, data);
            return this.http.post(`/productplans/${context.productplan}/tasks`, data);
        }
        if (context.projectmodule && true) {
            data = await this.obtainMinor(context, data);
            return this.http.post(`/projectmodules/${context.projectmodule}/tasks`, data);
        }
        return this.http.post(`/tasks`, data);
    }

    /**
     * RecordEstimate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async RecordEstimate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
            _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/recordestimate`, _data);
        }
        if (_context.product && _context.story && _context.task) {
            _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/recordestimate`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
            _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/recordestimate`, _data);
        }
        if (_context.project && _context.task) {
            _data = await this.obtainMinor(_context, _data);
            let ibztaskestimatesData: any = _data.ibztaskestimates;
            if (ibztaskestimatesData && ibztaskestimatesData.length > 0) {
                ibztaskestimatesData.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if (item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
                _data.ibztaskestimates = ibztaskestimatesData;
            }
            //删除从实体【taskestimate】数据主键
            let taskestimatesData: any = _data.taskestimates;
            if (taskestimatesData && taskestimatesData.length > 0) {
                taskestimatesData.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if (item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
                _data.taskestimates = taskestimatesData;
            }
            //删除从实体【ibztaskteam】数据主键
            let taskteamsData: any = _data.taskteams;
            if (taskteamsData && taskteamsData.length > 0) {
                taskteamsData.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if (item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
                _data.taskteams = taskteamsData;
            }
            if (!_data.srffrontuf || _data.srffrontuf !== "1") {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/recordestimate`, _data);
        }
        if (_context.story && _context.task) {
            _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/recordestimate`, _data);
        }
        if (_context.productplan && _context.task) {
            _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/recordestimate`, _data);
        }
        if (_context.projectmodule && _context.task) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/recordestimate`, _data);
        }
        return this.http.post(`/tasks/${_context.task}/recordestimate`, _data);
    }
}
export default TaskService;
function mergeDeepLeft(_data: any, arg1: ITask): any {
    throw new Error('Function not implemented.');
}

