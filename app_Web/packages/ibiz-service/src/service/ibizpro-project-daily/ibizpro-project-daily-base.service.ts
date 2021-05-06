import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbizproProjectDaily, IbizproProjectDaily } from '../../entities';
import keys from '../../entities/ibizpro-project-daily/ibizpro-project-daily-keys';

/**
 * 项目日报服务对象基类
 *
 * @export
 * @class IbizproProjectDailyBaseService
 * @extends {EntityBaseService}
 */
export class IbizproProjectDailyBaseService extends EntityBaseService<IIbizproProjectDaily> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IbizproProjectDaily';
    protected APPDENAMEPLURAL = 'IbizproProjectDailies';
    protected APPDEKEY = 'ibizproprojectdailyid';
    protected APPDETEXT = 'ibizproprojectdailyname';
    protected quickSearchFields = ['ibizproprojectdailyname',];
    protected selectContextParam = {
    };

    newEntity(data: IIbizproProjectDaily): IbizproProjectDaily {
        return new IbizproProjectDaily(data);
    }

    async addLocal(context: IContext, entity: IIbizproProjectDaily): Promise<IIbizproProjectDaily | null> {
        return this.cache.add(context, new IbizproProjectDaily(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbizproProjectDaily): Promise<IIbizproProjectDaily | null> {
        return super.createLocal(context, new IbizproProjectDaily(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbizproProjectDaily> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbizproProjectDaily): Promise<IIbizproProjectDaily> {
        return super.updateLocal(context, new IbizproProjectDaily(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbizproProjectDaily = {}): Promise<IIbizproProjectDaily> {
        return new IbizproProjectDaily(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProjectDailyService
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
     * @memberof IbizproProjectDailyService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibizproprojectdailies/${_context.ibizproprojectdaily}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProjectDailyService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibizproprojectdailies`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProjectDailyService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibizproprojectdailies/${_context.ibizproprojectdaily}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProjectDailyService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/ibizproprojectdailies/${_context.ibizproprojectdaily}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProjectDailyService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/ibizproprojectdailies/${_context.ibizproprojectdaily}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProjectDailyService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibizproprojectdailies/getdraft`, _data);
        return res;
    }
    /**
     * SumProjectDaily
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProjectDailyService
     */
    async SumProjectDaily(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibizproprojectdailies/${_context.ibizproprojectdaily}/sumprojectdaily`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProjectDailyService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibizproprojectdailies/fetchdefault`, _data);
    }

    /**
     * SumProjectDailyBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbizproProjectDailyServiceBase
     */
    public async SumProjectDailyBatch(_context: any = {},_data: any = {}): Promise<IHttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return await this.http.post(`/ibizproprojectdailies/sumprojectdailybatch`,_data);
    }
}
