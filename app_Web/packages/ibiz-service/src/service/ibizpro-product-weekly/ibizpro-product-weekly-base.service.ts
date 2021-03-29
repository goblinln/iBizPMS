import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbizproProductWeekly, IbizproProductWeekly } from '../../entities';
import keys from '../../entities/ibizpro-product-weekly/ibizpro-product-weekly-keys';

/**
 * 产品周报服务对象基类
 *
 * @export
 * @class IbizproProductWeeklyBaseService
 * @extends {EntityBaseService}
 */
export class IbizproProductWeeklyBaseService extends EntityBaseService<IIbizproProductWeekly> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IbizproProductWeekly';
    protected APPDENAMEPLURAL = 'IbizproProductWeeklies';
    protected APPDEKEY = 'ibizpro_productweeklyid';
    protected APPDETEXT = 'ibizproproductweeklyname';
    protected quickSearchFields = ['ibizproproductweeklyname',];
    protected selectContextParam = {
    };

    newEntity(data: IIbizproProductWeekly): IbizproProductWeekly {
        return new IbizproProductWeekly(data);
    }

    async addLocal(context: IContext, entity: IIbizproProductWeekly): Promise<IIbizproProductWeekly | null> {
        return this.cache.add(context, new IbizproProductWeekly(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbizproProductWeekly): Promise<IIbizproProductWeekly | null> {
        return super.createLocal(context, new IbizproProductWeekly(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbizproProductWeekly> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbizproProductWeekly): Promise<IIbizproProductWeekly> {
        return super.updateLocal(context, new IbizproProductWeekly(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbizproProductWeekly = {}): Promise<IIbizproProductWeekly> {
        return new IbizproProductWeekly(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProductWeeklyService
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
     * @memberof IbizproProductWeeklyService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibizproproductweeklies/${_context.ibizproproductweekly}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProductWeeklyService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibizproproductweeklies`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProductWeeklyService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibizproproductweeklies/${_context.ibizproproductweekly}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProductWeeklyService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/ibizproproductweeklies/${_context.ibizproproductweekly}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProductWeeklyService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/ibizproproductweeklies/${_context.ibizproproductweekly}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProductWeeklyService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibizproproductweeklies/getdraft`, _data);
        return res;
    }
    /**
     * SumProductWeekly
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProductWeeklyService
     */
    async SumProductWeekly(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibizproproductweeklies/${_context.ibizproproductweekly}/sumproductweekly`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProductWeeklyService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibizproproductweeklies/fetchdefault`, _data);
    }
}
