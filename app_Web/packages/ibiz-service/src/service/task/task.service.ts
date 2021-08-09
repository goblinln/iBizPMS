import { TaskBaseService } from './task-base.service';
import { HttpResponse } from 'ibiz-core';

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
     * Activate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async Activate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
            if (_context.product && _context.project && _context.task) {
                _data = await this.obtainMinor(_context, _data);
                _data = await this.beforeExecuteAction(_context,_data,'Activate');
                _data.taskteamnesteds = [];
                const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/tasks/${_context.task}/activate`, _data);
                return res;
            }
            if (_context.project && _context.task) {
                _data = await this.obtainMinor(_context, _data);
                _data = await this.beforeExecuteAction(_context,_data,'Activate');
                _data.taskteamnesteds = [];
                const res = await this.http.post(`/projects/${_context.project}/tasks/${_context.task}/activate`, _data);
                return res;
            }
            this.log.warn([`[Task]>>>[Activate函数]异常`]);
            return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
        } catch (error) {
            return this.handleResponseError(error);
        }
    }
    /**
     * AssignTo
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async AssignTo(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
            if (_context.product && _context.project && _context.task) {
                _data = await this.obtainMinor(_context, _data);
                _data = await this.beforeExecuteAction(_context,_data,'AssignTo');
                _data.taskteamnesteds = [];
                const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/tasks/${_context.task}/assignto`, _data);
                return res;
            }
            if (_context.project && _context.task) {
                _data = await this.obtainMinor(_context, _data);
                _data = await this.beforeExecuteAction(_context,_data,'AssignTo');
                _data.taskteamnesteds = [];
                const res = await this.http.post(`/projects/${_context.project}/tasks/${_context.task}/assignto`, _data);
                return res;
            }
            this.log.warn([`[Task]>>>[AssignTo函数]异常`]);
            return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
        } catch (error) {
            return this.handleResponseError(error);
        }
    }
    /**
     * Cancel
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async Cancel(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
            if (_context.product && _context.project && _context.task) {
                _data = await this.obtainMinor(_context, _data);
                _data = await this.beforeExecuteAction(_context,_data,'Cancel');
                _data.taskteamnesteds = [];
                const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/tasks/${_context.task}/cancel`, _data);
                return res;
            }
            if (_context.project && _context.task) {
                _data = await this.obtainMinor(_context, _data);
                _data = await this.beforeExecuteAction(_context,_data,'Cancel');
                _data.taskteamnesteds = [];
                const res = await this.http.post(`/projects/${_context.project}/tasks/${_context.task}/cancel`, _data);
                return res;
            }
            this.log.warn([`[Task]>>>[Cancel函数]异常`]);
            return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
        } catch (error) {
            return this.handleResponseError(error);
        }
    }
    /**
     * Close
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async Close(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
            if (_context.product && _context.project && _context.task) {
                _data = await this.obtainMinor(_context, _data);
                _data = await this.beforeExecuteAction(_context,_data,'Close');
                _data.taskteamnesteds = [];
                const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/tasks/${_context.task}/close`, _data);
                return res;
            }
            if (_context.project && _context.task) {
                _data = await this.obtainMinor(_context, _data);
                _data = await this.beforeExecuteAction(_context,_data,'Close');
                _data.taskteamnesteds = [];
                const res = await this.http.post(`/projects/${_context.project}/tasks/${_context.task}/close`, _data);
                return res;
            }
            this.log.warn([`[Task]>>>[Close函数]异常`]);
            return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
        } catch (error) {
            return this.handleResponseError(error);
        }
    }
    /**
     * ConfirmStoryChange
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async ConfirmStoryChange(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
            if (_context.product && _context.project && _context.task) {
                _data = await this.obtainMinor(_context, _data);
                _data = await this.beforeExecuteAction(_context,_data,'ConfirmStoryChange');
                _data.taskteamnesteds = [];
                const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/tasks/${_context.task}/confirmstorychange`, _data);
                return res;
            }
            if (_context.project && _context.task) {
                _data = await this.obtainMinor(_context, _data);
                _data = await this.beforeExecuteAction(_context,_data,'ConfirmStoryChange');
                _data.taskteamnesteds = [];
                const res = await this.http.post(`/projects/${_context.project}/tasks/${_context.task}/confirmstorychange`, _data);
                return res;
            }
            this.log.warn([`[Task]>>>[ConfirmStoryChange函数]异常`]);
            return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
        } catch (error) {
            return this.handleResponseError(error);
        }
    }

    /**
     * Finish
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async Finish(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
            if (_context.product && _context.project && _context.task) {
                _data = await this.obtainMinor(_context, _data);
                _data = await this.beforeExecuteAction(_context,_data,'Finish');
                _data.taskteamnesteds = [];
                const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/tasks/${_context.task}/finish`, _data);
                return res;
            }
            if (_context.project && _context.task) {
                _data = await this.obtainMinor(_context, _data);
                _data = await this.beforeExecuteAction(_context,_data,'Finish');
                _data.taskteamnesteds = [];
                const res = await this.http.post(`/projects/${_context.project}/tasks/${_context.task}/finish`, _data);
                return res;
            }
            this.log.warn([`[Task]>>>[Finish函数]异常`]);
            return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
        } catch (error) {
            return this.handleResponseError(error);
        }
    }

    /**
     * Pause
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async Pause(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
            if (_context.product && _context.project && _context.task) {
                _data = await this.obtainMinor(_context, _data);
                _data = await this.beforeExecuteAction(_context,_data,'Pause');
                _data.taskteamnesteds = [];
                const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/tasks/${_context.task}/pause`, _data);
                return res;
            }
            if (_context.project && _context.task) {
                _data = await this.obtainMinor(_context, _data);
                _data = await this.beforeExecuteAction(_context,_data,'Pause');
                _data.taskteamnesteds = [];
                const res = await this.http.post(`/projects/${_context.project}/tasks/${_context.task}/pause`, _data);
                return res;
            }
            this.log.warn([`[Task]>>>[Pause函数]异常`]);
            return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
        } catch (error) {
            return this.handleResponseError(error);
        }
    }

    /**
     * Restart
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async Restart(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
            if (_context.product && _context.project && _context.task) {
                _data = await this.obtainMinor(_context, _data);
                _data = await this.beforeExecuteAction(_context,_data,'Restart');
                _data.taskteamnesteds = [];
                const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/tasks/${_context.task}/restart`, _data);
                return res;
            }
            if (_context.project && _context.task) {
                _data = await this.obtainMinor(_context, _data);
                _data = await this.beforeExecuteAction(_context,_data,'Restart');
                _data.taskteamnesteds = [];
                const res = await this.http.post(`/projects/${_context.project}/tasks/${_context.task}/restart`, _data);
                return res;
            }
            this.log.warn([`[Task]>>>[Restart函数]异常`]);
            return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
        } catch (error) {
            return this.handleResponseError(error);
        }
    }
    /**
     * Start
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async Start(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
            if (_context.product && _context.project && _context.task) {
                _data = await this.obtainMinor(_context, _data);
                _data = await this.beforeExecuteAction(_context,_data,'Start');
                _data.taskteamnesteds = [];
                const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/tasks/${_context.task}/start`, _data);
                return res;
            }
            if (_context.project && _context.task) {
                _data = await this.obtainMinor(_context, _data);
                _data = await this.beforeExecuteAction(_context,_data,'Start');
                _data.taskteamnesteds = [];
                const res = await this.http.post(`/projects/${_context.project}/tasks/${_context.task}/start`, _data);
                return res;
            }
            this.log.warn([`[Task]>>>[Start函数]异常`]);
            return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
        } catch (error) {
            return this.handleResponseError(error);
        }
    }
    /**
     * TaskFavorites
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async TaskFavorites(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
            if (_context.product && _context.project && _context.task) {
                _data = await this.obtainMinor(_context, _data);
                _data = await this.beforeExecuteAction(_context,_data,'TaskFavorites');
                _data.taskteamnesteds = [];
                const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/tasks/${_context.task}/taskfavorites`, _data);
                return res;
            }
            if (_context.project && _context.task) {
                _data = await this.obtainMinor(_context, _data);
                _data = await this.beforeExecuteAction(_context,_data,'TaskFavorites');
                _data.taskteamnesteds = [];
                const res = await this.http.post(`/projects/${_context.project}/tasks/${_context.task}/taskfavorites`, _data);
                return res;
            }
            this.log.warn([`[Task]>>>[TaskFavorites函数]异常`]);
            return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
        } catch (error) {
            return this.handleResponseError(error);
        }
    }
    /**
     * TaskNFavorites
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async TaskNFavorites(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
            if (_context.product && _context.project && _context.task) {
                _data = await this.obtainMinor(_context, _data);
                _data = await this.beforeExecuteAction(_context,_data,'TaskNFavorites');
                _data.taskteamnesteds = [];
                const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/tasks/${_context.task}/tasknfavorites`, _data);
                return res;
            }
            if (_context.project && _context.task) {
                _data = await this.obtainMinor(_context, _data);
                _data = await this.beforeExecuteAction(_context,_data,'TaskNFavorites');
                _data.taskteamnesteds = [];
                const res = await this.http.post(`/projects/${_context.project}/tasks/${_context.task}/tasknfavorites`, _data);
                return res;
            }
            this.log.warn([`[Task]>>>[TaskNFavorites函数]异常`]);
            return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
        } catch (error) {
            return this.handleResponseError(error);
        }
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
