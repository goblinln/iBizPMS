import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProjectMonthly, ProjectMonthly } from '../../entities';
import keys from '../../entities/project-monthly/project-monthly-keys';

/**
 * 项目月报服务对象基类
 *
 * @export
 * @class ProjectMonthlyBaseService
 * @extends {EntityBaseService}
 */
export class ProjectMonthlyBaseService extends EntityBaseService<IProjectMonthly> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'ProjectMonthly';
    protected APPDENAMEPLURAL = 'ProjectMonthlies';
    protected APPDEKEY = 'ibizproprojectmonthlyid';
    protected APPDETEXT = 'ibizproprojectmonthlyname';
    protected quickSearchFields = ['ibizproprojectmonthlyname',];
    protected selectContextParam = {
    };

    newEntity(data: IProjectMonthly): ProjectMonthly {
        return new ProjectMonthly(data);
    }

    async addLocal(context: IContext, entity: IProjectMonthly): Promise<IProjectMonthly | null> {
        return this.cache.add(context, new ProjectMonthly(entity) as any);
    }

    async createLocal(context: IContext, entity: IProjectMonthly): Promise<IProjectMonthly | null> {
        return super.createLocal(context, new ProjectMonthly(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProjectMonthly> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IProjectMonthly): Promise<IProjectMonthly> {
        return super.updateLocal(context, new ProjectMonthly(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProjectMonthly = {}): Promise<IProjectMonthly> {
        return new ProjectMonthly(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectMonthlyService
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
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectMonthlyService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/projectmonthlies/${_context.projectmonthly}`, _data);
    }
    /**
     * AutoCreate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectMonthlyService
     */
    async AutoCreate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projectmonthlies/${_context.projectmonthly}/autocreate`, _data);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectMonthlyService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/projectmonthlies`, _data);
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectMonthlyService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/projectmonthlies/getdraft`, _data);
        return res;
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectMonthlyService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/projectmonthlies/${_context.projectmonthly}`);
        return res;
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectMonthlyService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projectmonthlies/fetchdefault`, _data);
    }

    /**
     * AutoCreateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectMonthlyServiceBase
     */
    public async AutoCreateBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/projectmonthlies/autocreatebatch`,_data);
    }
}
