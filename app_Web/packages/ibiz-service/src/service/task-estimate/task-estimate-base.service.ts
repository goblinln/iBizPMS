import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ITaskEstimate, TaskEstimate } from '../../entities';
import keys from '../../entities/task-estimate/task-estimate-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 任务预计服务对象基类
 *
 * @export
 * @class TaskEstimateBaseService
 * @extends {EntityBaseService}
 */
export class TaskEstimateBaseService extends EntityBaseService<ITaskEstimate> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'TaskEstimate';
    protected APPDENAMEPLURAL = 'TaskEstimates';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/TaskEstimate.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'id';
    protected quickSearchFields = ['id',];
    protected selectContextParam = {
        task: 'task',
    };

    newEntity(data: ITaskEstimate): TaskEstimate {
        return new TaskEstimate(data);
    }

    async addLocal(context: IContext, entity: ITaskEstimate): Promise<ITaskEstimate | null> {
        return this.cache.add(context, new TaskEstimate(entity) as any);
    }

    async createLocal(context: IContext, entity: ITaskEstimate): Promise<ITaskEstimate | null> {
        return super.createLocal(context, new TaskEstimate(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ITaskEstimate> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.task && entity.task !== '') {
            const s = await ___ibz___.gs.getTaskService();
            const data = await s.getLocal2(context, entity.task);
            if (data) {
                entity.taskname = data.name;
                entity.task = data.id;
                entity.task = data;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: ITaskEstimate): Promise<ITaskEstimate> {
        return super.updateLocal(context, new TaskEstimate(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ITaskEstimate = {}): Promise<ITaskEstimate> {
        if (_context.task && _context.task !== '') {
            const s = await ___ibz___.gs.getTaskService();
            const data = await s.getLocal2(_context, _context.task);
            if (data) {
                entity.taskname = data.name;
                entity.task = data.id;
                entity.task = data;
            }
        }
        return new TaskEstimate(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskEstimateService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }

    protected getActionMonthCond() {
        return this.condCache.get('actionMonth');
    }

    protected getActionYearCond() {
        return this.condCache.get('actionYear');
    }

    protected getDefaultCond() {
        return this.condCache.get('default');
    }

    protected getDefaultsCond() {
        if (!this.condCache.has('defaults')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('defaults', cond);
            }
        }
        return this.condCache.get('defaults');
    }

    protected getProjectActionMonthCond() {
        return this.condCache.get('projectActionMonth');
    }

    protected getProjectActionYearCond() {
        return this.condCache.get('projectActionYear');
    }

    protected getProjectTaskEstimateCond() {
        if (!this.condCache.has('projectTaskEstimate')) {
            const strCond: any[] = ['AND', ['EQ', 'PROJECT',{ type: 'WEBCONTEXT', value: 'project'}], ['EQ', 'DELETED','0']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('projectTaskEstimate', cond);
            }
        }
        return this.condCache.get('projectTaskEstimate');
    }

    protected getViewCond() {
        return this.condCache.get('view');
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskEstimateService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.task && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/tasks/${_context.task}/taskestimates`, _data);
        res.data = await this.afterExecuteAction(_context,res?.data);
            return res;
        }
        if (_context.project && _context.task && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/projects/${_context.project}/tasks/${_context.task}/taskestimates`, _data);
        res.data = await this.afterExecuteAction(_context,res?.data);
            return res;
        }
    this.log.warn([`[TaskEstimate]>>>[Create函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskEstimateService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.task && _context.taskestimate) {
            const res = await this.http.get(`/products/${_context.product}/projects/${_context.project}/tasks/${_context.task}/taskestimates/${_context.taskestimate}`);
        res.data = await this.afterExecuteAction(_context,res?.data);
            return res;
        }
        if (_context.project && _context.task && _context.taskestimate) {
            const res = await this.http.get(`/projects/${_context.project}/tasks/${_context.task}/taskestimates/${_context.taskestimate}`);
        res.data = await this.afterExecuteAction(_context,res?.data);
            return res;
        }
    this.log.warn([`[TaskEstimate]>>>[Get函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskEstimateService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.task && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/projects/${_context.project}/tasks/${_context.task}/taskestimates/getdraft`, _data);
            return res;
        }
        if (_context.project && _context.task && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/tasks/${_context.task}/taskestimates/getdraft`, _data);
            return res;
        }
    this.log.warn([`[TaskEstimate]>>>[GetDraft函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskEstimateService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.task && _context.taskestimate) {
            const res = await this.http.delete(`/products/${_context.product}/projects/${_context.project}/tasks/${_context.task}/taskestimates/${_context.taskestimate}`);
            return res;
        }
        if (_context.project && _context.task && _context.taskestimate) {
            const res = await this.http.delete(`/projects/${_context.project}/tasks/${_context.task}/taskestimates/${_context.taskestimate}`);
            return res;
        }
    this.log.warn([`[TaskEstimate]>>>[Remove函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskEstimateService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.task && _context.taskestimate) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data);
            const res = await this.http.put(`/products/${_context.product}/projects/${_context.project}/tasks/${_context.task}/taskestimates/${_context.taskestimate}`, _data);
        res.data = await this.afterExecuteAction(_context,res?.data);
            return res;
        }
        if (_context.project && _context.task && _context.taskestimate) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data);
            const res = await this.http.put(`/projects/${_context.project}/tasks/${_context.task}/taskestimates/${_context.taskestimate}`, _data);
        res.data = await this.afterExecuteAction(_context,res?.data);
            return res;
        }
    this.log.warn([`[TaskEstimate]>>>[Update函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskEstimateService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.task && true) {
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/tasks/${_context.task}/taskestimates/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.project && _context.task && true) {
            const res = await this.http.post(`/projects/${_context.project}/tasks/${_context.task}/taskestimates/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
    this.log.warn([`[TaskEstimate]>>>[FetchDefault函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
}
