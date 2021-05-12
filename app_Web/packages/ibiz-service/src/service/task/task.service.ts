import { IHttpResponse } from 'ibiz-core';
import { TaskBaseService } from './task-base.service';

/**
 * 实体服务对象基类
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

    /**
     * TaskFavorites
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<IHttpResponse>}
     * @memberof TaskService
     */
    async TaskFavorites(_context: any = {}, _data: any = {}): Promise<IHttpResponse> {
        if(_context.project && _context.projectmodule && _context.task){
            _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/taskfavorites`,_data);
        }
        if(_context.product && _context.story && _context.task){
            _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/taskfavorites`,_data);
        }
        if(_context.product && _context.productplan && _context.task){
            _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/taskfavorites`,_data);
        }
        if(_context.project && _context.task){
            _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/taskfavorites`,_data);
        }
        if(_context.story && _context.task){
            _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/taskfavorites`,_data);
        }
        if(_context.productplan && _context.task){
            _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/taskfavorites`,_data);
        }
        if(_context.projectmodule && _context.task){
            _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/taskfavorites`,_data);
        }
        return this.http.post(`/tasks/${_context.task}/taskfavorites`,_data,);
    }

    /**
     * TaskNFavorites
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<IHttpResponse>}
     * @memberof TaskService
     */
    async TaskNFavorites(_context: any = {}, _data: any = {}): Promise<IHttpResponse> {
        if(_context.project && _context.projectmodule && _context.task){
            _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/tasknfavorites`,_data);
        }
        if(_context.product && _context.story && _context.task){
            _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/tasknfavorites`,_data);
        }
        if(_context.product && _context.productplan && _context.task){
            _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/tasknfavorites`,_data);
        }
        if(_context.project && _context.task){
            _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/tasknfavorites`,_data);
        }
        if(_context.story && _context.task){
            _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/tasknfavorites`,_data);
        }
        if(_context.productplan && _context.task){
            _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/tasknfavorites`,_data);
        }
        if(_context.projectmodule && _context.task){
            _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/tasknfavorites`,_data);
        }
        return this.http.post(`/tasks/${_context.task}/tasknfavorites`,_data);
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
            const minorIBZTaskTeamService: any = await ___ibz___.gs[`getIBZTaskTeamService`]();
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
        if(context.project && context.projectmodule && context.task){
            data = await this.obtainMinor(context, data);
            return this.http.put(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/${context.task}`,data,isloading);
        }
        if(context.product && context.story && context.task){
            data = await this.obtainMinor(context, data);
            return this.http.put(`/products/${context.product}/stories/${context.story}/tasks/${context.task}`,data,isloading);
        }
        if(context.product && context.productplan && context.task){
            data = await this.obtainMinor(context, data);
            return this.http.put(`/products/${context.product}/productplans/${context.productplan}/tasks/${context.task}`,data,isloading);
        }
        if(context.project && context.task){
            data = await this.obtainMinor(context, data);
            return this.http.put(`/projects/${context.project}/tasks/${context.task}`,data,isloading);
        }
        if(context.story && context.task){
            data = await this.obtainMinor(context, data);
            return this.http.put(`/stories/${context.story}/tasks/${context.task}`,data,isloading);
        }
        if(context.productplan && context.task){
            data = await this.obtainMinor(context, data);
            return this.http.put(`/productplans/${context.productplan}/tasks/${context.task}`,data,isloading);
        }
        if(context.projectmodule && context.task){
            data = await this.obtainMinor(context, data);
            return this.http.put(`/projectmodules/${context.projectmodule}/tasks/${context.task}`,data,isloading);
        }
        const minorIBZTaskTeamService: any = await ___ibz___.gs[`getIBZTaskTeamService`]();
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
        return this.http.put(`/tasks/${context.task}`,data,isloading);
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
        data.totaltime = data.consumed + data.currentconsumed;
        data.mytotaltime = (data.myconsumed != null ? data.myconsumed : data.consumed) + data.currentconsumed;
        return {
            status: 200,
            data
        }
    }

    /**
     * RecordEstimate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async RecordEstimate(_context: any = {}, _data: any = {}): Promise<any> {
        if (_context.project && _context.projectmodule && _context.task) {
            _data = await this.obtainMinor(_context, _data);
            if (_data.ibztaskestimates && _data.ibztaskestimates.length > 0) {
                _data.ibztaskestimates.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
            }
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/recordestimate`, _data);
        }
        if (_context.product && _context.story && _context.task) {
            _data = await this.obtainMinor(_context, _data);
            if (_data.ibztaskestimates && _data.ibztaskestimates.length > 0) {
                _data.ibztaskestimates.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
            }
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/recordestimate`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
            _data = await this.obtainMinor(_context, _data);
            if (_data.ibztaskestimates && _data.ibztaskestimates.length > 0) {
                _data.ibztaskestimates.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
            }
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/recordestimate`, _data);
        }
        if (_context.project && _context.task) {
            _data = await this.obtainMinor(_context, _data);
            if (_data.ibztaskestimates && _data.ibztaskestimates.length > 0) {
                _data.ibztaskestimates.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
            }
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/recordestimate`, _data);
        }
        if (_context.story && _context.task) {
            _data = await this.obtainMinor(_context, _data);
            if (_data.ibztaskestimates && _data.ibztaskestimates.length > 0) {
                _data.ibztaskestimates.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
            }
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/recordestimate`, _data);
        }
        if (_context.productplan && _context.task) {
            _data = await this.obtainMinor(_context, _data);
            if (_data.ibztaskestimates && _data.ibztaskestimates.length > 0) {
                _data.ibztaskestimates.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
            }
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/recordestimate`, _data);
        }
        if (_context.projectmodule && _context.task) {
            _data = await this.obtainMinor(_context, _data);
            if (_data.ibztaskestimates && _data.ibztaskestimates.length > 0) {
                _data.ibztaskestimates.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
            }
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/recordestimate`, _data);
        }
        return this.http.post(`/tasks/${_context.task}/recordestimate`, _data);
    }

    /**
     * TaskForward
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async TaskForward(_context: any = {}, _data: any = {}): Promise<any> {
        if (_context.project && _context.projectmodule && _context.task) {
            _data = await this.obtainMinor(_context, _data);
            if (_data.ibztaskestimates && _data.ibztaskestimates.length > 0) {
                _data.ibztaskestimates.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
            }
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/taskforward`, _data);
        }
        if (_context.product && _context.story && _context.task) {
            _data = await this.obtainMinor(_context, _data);
            if (_data.ibztaskestimates && _data.ibztaskestimates.length > 0) {
                _data.ibztaskestimates.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
            }
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/taskforward`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
            _data = await this.obtainMinor(_context, _data);
            if (_data.ibztaskestimates && _data.ibztaskestimates.length > 0) {
                _data.ibztaskestimates.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
            }
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/taskforward`, _data);
        }
        if (_context.project && _context.task) {
            _data = await this.obtainMinor(_context, _data);
            if (_data.ibztaskestimates && _data.ibztaskestimates.length > 0) {
                _data.ibztaskestimates.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
            }
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/taskforward`, _data);
        }
        if (_context.story && _context.task) {
            _data = await this.obtainMinor(_context, _data);
            if (_data.ibztaskestimates && _data.ibztaskestimates.length > 0) {
                _data.ibztaskestimates.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
            }
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/taskforward`, _data);
        }
        if (_context.productplan && _context.task) {
            _data = await this.obtainMinor(_context, _data);
            if (_data.ibztaskestimates && _data.ibztaskestimates.length > 0) {
                _data.ibztaskestimates.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
            }
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/taskforward`, _data);
        }
        if (_context.projectmodule && _context.task) {
            _data = await this.obtainMinor(_context, _data);
            if (_data.ibztaskestimates && _data.ibztaskestimates.length > 0) {
                _data.ibztaskestimates.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
            }
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/taskforward`, _data);
        }
        return this.http.post(`/tasks/${_context.task}/taskforward`, _data);
    }
}
export default TaskService;
