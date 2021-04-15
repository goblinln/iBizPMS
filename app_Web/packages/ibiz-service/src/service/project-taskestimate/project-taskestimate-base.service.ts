import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProjectTaskestimate, ProjectTaskestimate } from '../../entities';
import keys from '../../entities/project-taskestimate/project-taskestimate-keys';

/**
 * 项目工时统计服务对象基类
 *
 * @export
 * @class ProjectTaskestimateBaseService
 * @extends {EntityBaseService}
 */
export class ProjectTaskestimateBaseService extends EntityBaseService<IProjectTaskestimate> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'ProjectTaskestimate';
    protected APPDENAMEPLURAL = 'ProjectTaskestimates';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'date';
    protected quickSearchFields = ['date',];
    protected selectContextParam = {
    };

    newEntity(data: IProjectTaskestimate): ProjectTaskestimate {
        return new ProjectTaskestimate(data);
    }

    async addLocal(context: IContext, entity: IProjectTaskestimate): Promise<IProjectTaskestimate | null> {
        return this.cache.add(context, new ProjectTaskestimate(entity) as any);
    }

    async createLocal(context: IContext, entity: IProjectTaskestimate): Promise<IProjectTaskestimate | null> {
        return super.createLocal(context, new ProjectTaskestimate(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProjectTaskestimate> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IProjectTaskestimate): Promise<IProjectTaskestimate> {
        return super.updateLocal(context, new ProjectTaskestimate(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProjectTaskestimate = {}): Promise<IProjectTaskestimate> {
        return new ProjectTaskestimate(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTaskestimateService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }
    /**
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTaskestimateService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/projecttaskestimates/${_context.projecttaskestimate}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTaskestimateService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/projecttaskestimates`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTaskestimateService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/projecttaskestimates/${_context.projecttaskestimate}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTaskestimateService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/projecttaskestimates/${_context.projecttaskestimate}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTaskestimateService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/projecttaskestimates/${_context.projecttaskestimate}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTaskestimateService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/projecttaskestimates/getdraft`, _data);
        return res;
    }
    /**
     * FetchAccountDetail
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTaskestimateService
     */
    async FetchAccountDetail(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projecttaskestimates/fetchaccountdetail`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTaskestimateService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projecttaskestimates/fetchdefault`, _data);
    }
}