import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbizproProductMonthly, IbizproProductMonthly } from '../../entities';
import keys from '../../entities/ibizpro-product-monthly/ibizpro-product-monthly-keys';

/**
 * 产品月报服务对象基类
 *
 * @export
 * @class IbizproProductMonthlyBaseService
 * @extends {EntityBaseService}
 */
export class IbizproProductMonthlyBaseService extends EntityBaseService<IIbizproProductMonthly> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IbizproProductMonthly';
    protected APPDENAMEPLURAL = 'IbizproProductMonthlies';
    protected APPDEKEY = 'ibizproproductmonthlyid';
    protected APPDETEXT = 'ibizproproductmonthlyname';
    protected quickSearchFields = ['ibizproproductmonthlyname',];
    protected selectContextParam = {
    };

    newEntity(data: IIbizproProductMonthly): IbizproProductMonthly {
        return new IbizproProductMonthly(data);
    }

    async addLocal(context: IContext, entity: IIbizproProductMonthly): Promise<IIbizproProductMonthly | null> {
        return this.cache.add(context, new IbizproProductMonthly(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbizproProductMonthly): Promise<IIbizproProductMonthly | null> {
        return super.createLocal(context, new IbizproProductMonthly(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbizproProductMonthly> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbizproProductMonthly): Promise<IIbizproProductMonthly> {
        return super.updateLocal(context, new IbizproProductMonthly(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbizproProductMonthly = {}): Promise<IIbizproProductMonthly> {
        return new IbizproProductMonthly(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProductMonthlyService
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
     * @memberof IbizproProductMonthlyService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibizproproductmonthlies/${_context.ibizproproductmonthly}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProductMonthlyService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibizproproductmonthlies`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProductMonthlyService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibizproproductmonthlies/${_context.ibizproproductmonthly}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProductMonthlyService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/ibizproproductmonthlies/${_context.ibizproproductmonthly}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProductMonthlyService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/ibizproproductmonthlies/${_context.ibizproproductmonthly}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProductMonthlyService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibizproproductmonthlies/getdraft`, _data);
        return res;
    }
    /**
     * ManualCreateMonthly
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProductMonthlyService
     */
    async ManualCreateMonthly(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibizproproductmonthlies/${_context.ibizproproductmonthly}/manualcreatemonthly`, _data);
    }
    /**
     * StatsProductMonthly
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProductMonthlyService
     */
    async StatsProductMonthly(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibizproproductmonthlies/${_context.ibizproproductmonthly}/statsproductmonthly`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProductMonthlyService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibizproproductmonthlies/fetchdefault`, _data);
    }
}