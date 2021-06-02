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
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskEstimateService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.account && _context.project && _context.task && _context.taskestimate) {
            return this.http.delete(`/accounts/${_context.account}/projects/${_context.project}/tasks/${_context.task}/taskestimates/${_context.taskestimate}`);
        }
        if (_context.project && _context.task && _context.taskestimate) {
            return this.http.delete(`/projects/${_context.project}/tasks/${_context.task}/taskestimates/${_context.taskestimate}`);
        }
        if (_context.account && _context.task && _context.taskestimate) {
            return this.http.delete(`/accounts/${_context.account}/tasks/${_context.task}/taskestimates/${_context.taskestimate}`);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.account && _context.project && _context.task && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/accounts/${_context.account}/projects/${_context.project}/tasks/${_context.task}/taskestimates/getdraft`, _data);
            return res;
        }
        if (_context.project && _context.task && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/tasks/${_context.task}/taskestimates/getdraft`, _data);
            return res;
        }
        if (_context.account && _context.task && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/accounts/${_context.account}/tasks/${_context.task}/taskestimates/getdraft`, _data);
            return res;
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.account && _context.project && _context.task && true) {
            return this.http.post(`/accounts/${_context.account}/projects/${_context.project}/tasks/${_context.task}/taskestimates/fetchdefault`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/taskestimates/fetchdefault`, _data);
        }
        if (_context.account && _context.task && true) {
            return this.http.post(`/accounts/${_context.account}/tasks/${_context.task}/taskestimates/fetchdefault`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.account && _context.project && _context.task && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/accounts/${_context.account}/projects/${_context.project}/tasks/${_context.task}/taskestimates`, _data);
        }
        if (_context.project && _context.task && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/taskestimates`, _data);
        }
        if (_context.account && _context.task && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/accounts/${_context.account}/tasks/${_context.task}/taskestimates`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.account && _context.project && _context.task && _context.taskestimate) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/accounts/${_context.account}/projects/${_context.project}/tasks/${_context.task}/taskestimates/${_context.taskestimate}`, _data);
        }
        if (_context.project && _context.task && _context.taskestimate) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/tasks/${_context.task}/taskestimates/${_context.taskestimate}`, _data);
        }
        if (_context.account && _context.task && _context.taskestimate) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/accounts/${_context.account}/tasks/${_context.task}/taskestimates/${_context.taskestimate}`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.account && _context.project && _context.task && _context.taskestimate) {
            const res = await this.http.get(`/accounts/${_context.account}/projects/${_context.project}/tasks/${_context.task}/taskestimates/${_context.taskestimate}`);
            return res;
        }
        if (_context.project && _context.task && _context.taskestimate) {
            const res = await this.http.get(`/projects/${_context.project}/tasks/${_context.task}/taskestimates/${_context.taskestimate}`);
            return res;
        }
        if (_context.account && _context.task && _context.taskestimate) {
            const res = await this.http.get(`/accounts/${_context.account}/tasks/${_context.task}/taskestimates/${_context.taskestimate}`);
            return res;
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
}
