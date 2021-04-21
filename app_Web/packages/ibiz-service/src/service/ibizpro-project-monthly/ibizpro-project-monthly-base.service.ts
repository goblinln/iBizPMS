import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbizproProjectMonthly, IbizproProjectMonthly } from '../../entities';
import keys from '../../entities/ibizpro-project-monthly/ibizpro-project-monthly-keys';

/**
 * 项目月报服务对象基类
 *
 * @export
 * @class IbizproProjectMonthlyBaseService
 * @extends {EntityBaseService}
 */
export class IbizproProjectMonthlyBaseService extends EntityBaseService<IIbizproProjectMonthly> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IbizproProjectMonthly';
    protected APPDENAMEPLURAL = 'IbizproProjectMonthlies';
    protected APPDEKEY = 'ibizproprojectmonthlyid';
    protected APPDETEXT = 'ibizproprojectmonthlyname';
    protected quickSearchFields = ['ibizproprojectmonthlyname',];
    protected selectContextParam = {
    };

    newEntity(data: IIbizproProjectMonthly): IbizproProjectMonthly {
        return new IbizproProjectMonthly(data);
    }

    async addLocal(context: IContext, entity: IIbizproProjectMonthly): Promise<IIbizproProjectMonthly | null> {
        return this.cache.add(context, new IbizproProjectMonthly(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbizproProjectMonthly): Promise<IIbizproProjectMonthly | null> {
        return super.createLocal(context, new IbizproProjectMonthly(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbizproProjectMonthly> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbizproProjectMonthly): Promise<IIbizproProjectMonthly> {
        return super.updateLocal(context, new IbizproProjectMonthly(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbizproProjectMonthly = {}): Promise<IIbizproProjectMonthly> {
        return new IbizproProjectMonthly(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProjectMonthlyService
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
     * @memberof IbizproProjectMonthlyService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibizproprojectmonthlies/${_context.ibizproprojectmonthly}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProjectMonthlyService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibizproprojectmonthlies`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProjectMonthlyService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibizproprojectmonthlies/${_context.ibizproprojectmonthly}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProjectMonthlyService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/ibizproprojectmonthlies/${_context.ibizproprojectmonthly}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProjectMonthlyService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/ibizproprojectmonthlies/${_context.ibizproprojectmonthly}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProjectMonthlyService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibizproprojectmonthlies/getdraft`, _data);
        return res;
    }
    /**
     * ManualCreateMonthly
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProjectMonthlyService
     */
    async ManualCreateMonthly(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibizproprojectmonthlies/${_context.ibizproprojectmonthly}/manualcreatemonthly`, _data);
    }
    /**
     * SumProjectMonthly
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProjectMonthlyService
     */
    async SumProjectMonthly(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibizproprojectmonthlies/${_context.ibizproprojectmonthly}/sumprojectmonthly`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProjectMonthlyService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibizproprojectmonthlies/fetchdefault`, _data);
    }
}
