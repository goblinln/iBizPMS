import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProjectWeekly, ProjectWeekly } from '../../entities';
import keys from '../../entities/project-weekly/project-weekly-keys';

/**
 * 项目周报服务对象基类
 *
 * @export
 * @class ProjectWeeklyBaseService
 * @extends {EntityBaseService}
 */
export class ProjectWeeklyBaseService extends EntityBaseService<IProjectWeekly> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'ProjectWeekly';
    protected APPDENAMEPLURAL = 'ProjectWeeklies';
    protected APPDEKEY = 'projectweeklyid';
    protected APPDETEXT = 'projectweeklyname';
    protected quickSearchFields = ['projectweeklyname',];
    protected selectContextParam = {
    };

    newEntity(data: IProjectWeekly): ProjectWeekly {
        return new ProjectWeekly(data);
    }

    async addLocal(context: IContext, entity: IProjectWeekly): Promise<IProjectWeekly | null> {
        return this.cache.add(context, new ProjectWeekly(entity) as any);
    }

    async createLocal(context: IContext, entity: IProjectWeekly): Promise<IProjectWeekly | null> {
        return super.createLocal(context, new ProjectWeekly(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProjectWeekly> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IProjectWeekly): Promise<IProjectWeekly> {
        return super.updateLocal(context, new ProjectWeekly(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProjectWeekly = {}): Promise<IProjectWeekly> {
        return new ProjectWeekly(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectWeeklyService
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
     * Summary
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectWeeklyService
     */
    async Summary(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projectweeklies/${_context.projectweekly}/summary`, _data);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectWeeklyService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/projectweeklies/${_context.projectweekly}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectWeeklyService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/projectweeklies/getdraft`, _data);
        return res;
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectWeeklyService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/projectweeklies/${_context.projectweekly}`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectWeeklyService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projectweeklies/fetchdefault`, _data);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectWeeklyService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/projectweeklies`, _data);
    }

    /**
     * SummaryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectWeeklyServiceBase
     */
    public async SummaryBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/projectweeklies/summarybatch`,_data);
    }
}
