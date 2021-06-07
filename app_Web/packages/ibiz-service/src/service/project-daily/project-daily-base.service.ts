import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProjectDaily, ProjectDaily } from '../../entities';
import keys from '../../entities/project-daily/project-daily-keys';

/**
 * 项目日报服务对象基类
 *
 * @export
 * @class ProjectDailyBaseService
 * @extends {EntityBaseService}
 */
export class ProjectDailyBaseService extends EntityBaseService<IProjectDaily> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'ProjectDaily';
    protected APPDENAMEPLURAL = 'ProjectDailies';
    protected APPDEKEY = 'ibizproprojectdailyid';
    protected APPDETEXT = 'ibizproprojectdailyname';
    protected quickSearchFields = ['ibizproprojectdailyname',];
    protected selectContextParam = {
    };

    newEntity(data: IProjectDaily): ProjectDaily {
        return new ProjectDaily(data);
    }

    async addLocal(context: IContext, entity: IProjectDaily): Promise<IProjectDaily | null> {
        return this.cache.add(context, new ProjectDaily(entity) as any);
    }

    async createLocal(context: IContext, entity: IProjectDaily): Promise<IProjectDaily | null> {
        return super.createLocal(context, new ProjectDaily(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProjectDaily> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IProjectDaily): Promise<IProjectDaily> {
        return super.updateLocal(context, new ProjectDaily(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProjectDaily = {}): Promise<IProjectDaily> {
        return new ProjectDaily(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectDailyService
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
     * @memberof ProjectDailyService
     */
    async Summary(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projectdailies/${_context.projectdaily}/summary`, _data);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectDailyService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/projectdailies/${_context.projectdaily}`);
        return res;
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectDailyService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/projectdailies/${_context.projectdaily}`, _data);
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectDailyService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/projectdailies/getdraft`, _data);
        return res;
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectDailyService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/projectdailies`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectDailyService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projectdailies/fetchdefault`, _data);
    }

    /**
     * SummaryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectDailyServiceBase
     */
    public async SummaryBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/projectdailies/summarybatch`,_data);
    }
}
