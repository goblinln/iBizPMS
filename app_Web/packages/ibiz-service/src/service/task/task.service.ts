import { IHttpResponse, HttpResponse } from 'ibiz-core';
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

    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.project && true) {
            _data = await this.obtainMinor(_context, _data);
            //删除从实体【taskteamnesteds】数据主键
            let taskteamsData:any = _data.taskteamnesteds;
            if (taskteamsData && taskteamsData.length > 0) {
                taskteamsData.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
                _data.taskteamnesteds = taskteamsData;
            }
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/projects/${_context.project}/tasks`, _data);
        }
        if (_context.project && true) {
            _data = await this.obtainMinor(_context, _data);
            //删除从实体【taskteamnesteds】数据主键
            let taskteamsData:any = _data.taskteamnesteds;
            if (taskteamsData && taskteamsData.length > 0) {
                taskteamsData.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
                _data.taskteamnesteds = taskteamsData;
            }
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/tasks`, _data);
        }
        this.log.warn([`[Task]>>>[Create函数]异常`]);
        return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.project && _context.task) {
            _data = await this.obtainMinor(_context, _data);
            //删除从实体【taskteamnesteds】数据主键
            let taskteamsData:any = _data.taskteamnesteds;
            if (taskteamsData && taskteamsData.length > 0) {
                taskteamsData.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
                _data.taskteamnesteds = taskteamsData;
            }
            return this.http.put(`/products/${_context.product}/projects/${_context.project}/tasks/${_context.task}`, _data);
        }
        if (_context.project && _context.task) {
            _data = await this.obtainMinor(_context, _data);
            //删除从实体【taskteamnesteds】数据主键
            let taskteamsData:any = _data.taskteamnesteds;
            if (taskteamsData && taskteamsData.length > 0) {
                taskteamsData.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
                _data.taskteamnesteds = taskteamsData;
            }
            return this.http.put(`/projects/${_context.project}/tasks/${_context.task}`, _data);
        }
        this.log.warn([`[Task]>>>[Update函数]异常`]);
        return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
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

}
export default TaskService;
