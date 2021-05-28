import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProjectTaskEstimate, ProjectTaskEstimate } from '../../entities';
import keys from '../../entities/project-task-estimate/project-task-estimate-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 任务预计服务对象基类
 *
 * @export
 * @class ProjectTaskEstimateBaseService
 * @extends {EntityBaseService}
 */
export class ProjectTaskEstimateBaseService extends EntityBaseService<IProjectTaskEstimate> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'ProjectTaskEstimate';
    protected APPDENAMEPLURAL = 'ProjectTaskEstimates';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'id';
    protected quickSearchFields = ['id',];
    protected selectContextParam = {
        projecttask: 'task',
    };

    newEntity(data: IProjectTaskEstimate): ProjectTaskEstimate {
        return new ProjectTaskEstimate(data);
    }

    async addLocal(context: IContext, entity: IProjectTaskEstimate): Promise<IProjectTaskEstimate | null> {
        return this.cache.add(context, new ProjectTaskEstimate(entity) as any);
    }

    async createLocal(context: IContext, entity: IProjectTaskEstimate): Promise<IProjectTaskEstimate | null> {
        return super.createLocal(context, new ProjectTaskEstimate(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProjectTaskEstimate> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.task && entity.task !== '') {
            const s = await ___ibz___.gs.getProjectTaskService();
            const data = await s.getLocal2(context, entity.task);
            if (data) {
                entity.taskname = data.name;
                entity.task = data.id;
                entity.projecttask = data;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IProjectTaskEstimate): Promise<IProjectTaskEstimate> {
        return super.updateLocal(context, new ProjectTaskEstimate(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProjectTaskEstimate = {}): Promise<IProjectTaskEstimate> {
        if (_context.projecttask && _context.projecttask !== '') {
            const s = await ___ibz___.gs.getProjectTaskService();
            const data = await s.getLocal2(_context, _context.projecttask);
            if (data) {
                entity.taskname = data.name;
                entity.task = data.id;
                entity.projecttask = data;
            }
        }
        return new ProjectTaskEstimate(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTaskEstimateService
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
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTaskEstimateService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projecttask && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/projecttasks/${_context.projecttask}/projecttaskestimates/getdraft`, _data);
            return res;
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTaskEstimateService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projecttask && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/projecttasks/${_context.projecttask}/projecttaskestimates`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTaskEstimateService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projecttask && true) {
            return this.http.post(`/projects/${_context.project}/projecttasks/${_context.projecttask}/projecttaskestimates/fetchdefault`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTaskEstimateService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projecttask && _context.projecttaskestimate) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/projecttasks/${_context.projecttask}/projecttaskestimates/${_context.projecttaskestimate}`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTaskEstimateService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projecttask && _context.projecttaskestimate) {
            const res = await this.http.get(`/projects/${_context.project}/projecttasks/${_context.projecttask}/projecttaskestimates/${_context.projecttaskestimate}`);
            return res;
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTaskEstimateService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projecttask && _context.projecttaskestimate) {
            return this.http.delete(`/projects/${_context.project}/projecttasks/${_context.projecttask}/projecttaskestimates/${_context.projecttaskestimate}`);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
}
