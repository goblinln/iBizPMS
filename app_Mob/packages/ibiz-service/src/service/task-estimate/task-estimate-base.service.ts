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
    protected APPNAME = 'Mob';
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
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskEstimateService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.task && _context.taskestimate) {
            return this.http.get(`/projects/${_context.project}/tasks/${_context.task}/taskestimates/${_context.taskestimate}/select`);
        }
        if (_context.task && _context.taskestimate) {
            return this.http.get(`/tasks/${_context.task}/taskestimates/${_context.taskestimate}/select`);
        }
        return this.http.get(`/taskestimates/${_context.taskestimate}/select`);
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
        if (_context.task && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/tasks/${_context.task}/taskestimates`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/taskestimates`, _data);
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
        if (_context.project && _context.task && _context.taskestimate) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/tasks/${_context.task}/taskestimates/${_context.taskestimate}`, _data);
        }
        if (_context.task && _context.taskestimate) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/tasks/${_context.task}/taskestimates/${_context.taskestimate}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/taskestimates/${_context.taskestimate}`, _data);
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
        if (_context.project && _context.task && _context.taskestimate) {
            return this.http.delete(`/projects/${_context.project}/tasks/${_context.task}/taskestimates/${_context.taskestimate}`);
        }
        if (_context.task && _context.taskestimate) {
            return this.http.delete(`/tasks/${_context.task}/taskestimates/${_context.taskestimate}`);
        }
        return this.http.delete(`/taskestimates/${_context.taskestimate}`);
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
        if (_context.project && _context.task && _context.taskestimate) {
            const res = await this.http.get(`/projects/${_context.project}/tasks/${_context.task}/taskestimates/${_context.taskestimate}`);
            return res;
        }
        if (_context.task && _context.taskestimate) {
            const res = await this.http.get(`/tasks/${_context.task}/taskestimates/${_context.taskestimate}`);
            return res;
        }
        const res = await this.http.get(`/taskestimates/${_context.taskestimate}`);
        return res;
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
        if (_context.project && _context.task && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/tasks/${_context.task}/taskestimates/getdraft`, _data);
            return res;
        }
        if (_context.task && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/tasks/${_context.task}/taskestimates/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/taskestimates/getdraft`, _data);
        return res;
    }
    /**
     * PMEvaluation
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskEstimateService
     */
    async PMEvaluation(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.task && _context.taskestimate) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/taskestimates/${_context.taskestimate}/pmevaluation`, _data);
        }
        if (_context.task && _context.taskestimate) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/taskestimates/${_context.taskestimate}/pmevaluation`, _data);
        }
        return this.http.post(`/taskestimates/${_context.taskestimate}/pmevaluation`, _data);
    }
    /**
     * FetchActionMonth
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskEstimateService
     */
    async FetchActionMonth(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/taskestimates/fetchactionmonth`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/taskestimates/fetchactionmonth`, _data);
        }
        return this.http.post(`/taskestimates/fetchactionmonth`, _data);
    }
    /**
     * FetchActionYear
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskEstimateService
     */
    async FetchActionYear(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/taskestimates/fetchactionyear`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/taskestimates/fetchactionyear`, _data);
        }
        return this.http.post(`/taskestimates/fetchactionyear`, _data);
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
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/taskestimates/fetchdefault`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/taskestimates/fetchdefault`, _data);
        }
        return this.http.post(`/taskestimates/fetchdefault`, _data);
    }
    /**
     * FetchDefaults
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskEstimateService
     */
    async FetchDefaults(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/taskestimates/fetchdefaults`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/taskestimates/fetchdefaults`, _data);
        }
        return this.http.post(`/taskestimates/fetchdefaults`, _data);
    }
    /**
     * FetchProjectActionMonth
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskEstimateService
     */
    async FetchProjectActionMonth(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/taskestimates/fetchprojectactionmonth`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/taskestimates/fetchprojectactionmonth`, _data);
        }
        return this.http.post(`/taskestimates/fetchprojectactionmonth`, _data);
    }
    /**
     * FetchProjectActionYear
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskEstimateService
     */
    async FetchProjectActionYear(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/taskestimates/fetchprojectactionyear`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/taskestimates/fetchprojectactionyear`, _data);
        }
        return this.http.post(`/taskestimates/fetchprojectactionyear`, _data);
    }
    /**
     * FetchProjectTaskEstimate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskEstimateService
     */
    async FetchProjectTaskEstimate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/taskestimates/fetchprojecttaskestimate`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/taskestimates/fetchprojecttaskestimate`, _data);
        }
        return this.http.post(`/taskestimates/fetchprojecttaskestimate`, _data);
    }

    /**
     * PMEvaluationBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskEstimateServiceBase
     */
    public async PMEvaluationBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && _context.task && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/taskestimates/pmevaluationbatch`,_data);
        }
        if(_context.task && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/taskestimates/pmevaluationbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/taskestimates/pmevaluationbatch`,_data);
    }
}
