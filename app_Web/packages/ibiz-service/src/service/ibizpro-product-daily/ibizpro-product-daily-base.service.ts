import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbizproProductDaily, IbizproProductDaily } from '../../entities';
import keys from '../../entities/ibizpro-product-daily/ibizpro-product-daily-keys';

/**
 * 产品日报服务对象基类
 *
 * @export
 * @class IbizproProductDailyBaseService
 * @extends {EntityBaseService}
 */
export class IbizproProductDailyBaseService extends EntityBaseService<IIbizproProductDaily> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IbizproProductDaily';
    protected APPDENAMEPLURAL = 'IbizproProductDailies';
    protected APPDEKEY = 'ibizproproductdailyid';
    protected APPDETEXT = 'ibizproproductdailyname';
    protected quickSearchFields = ['ibizproproductdailyname',];
    protected selectContextParam = {
    };

    newEntity(data: IIbizproProductDaily): IbizproProductDaily {
        return new IbizproProductDaily(data);
    }

    async addLocal(context: IContext, entity: IIbizproProductDaily): Promise<IIbizproProductDaily | null> {
        return this.cache.add(context, new IbizproProductDaily(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbizproProductDaily): Promise<IIbizproProductDaily | null> {
        return super.createLocal(context, new IbizproProductDaily(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbizproProductDaily> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbizproProductDaily): Promise<IIbizproProductDaily> {
        return super.updateLocal(context, new IbizproProductDaily(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbizproProductDaily = {}): Promise<IIbizproProductDaily> {
        return new IbizproProductDaily(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProductDailyService
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
     * @memberof IbizproProductDailyService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibizproproductdailies/${_context.ibizproproductdaily}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProductDailyService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibizproproductdailies`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProductDailyService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibizproproductdailies/${_context.ibizproproductdaily}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProductDailyService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/ibizproproductdailies/${_context.ibizproproductdaily}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProductDailyService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/ibizproproductdailies/${_context.ibizproproductdaily}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProductDailyService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibizproproductdailies/getdraft`, _data);
        return res;
    }
    /**
     * ManualCreateDaily
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProductDailyService
     */
    async ManualCreateDaily(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibizproproductdailies/${_context.ibizproproductdaily}/manualcreatedaily`, _data);
    }
    /**
     * StatsProductDaily
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProductDailyService
     */
    async StatsProductDaily(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibizproproductdailies/${_context.ibizproproductdaily}/statsproductdaily`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProductDailyService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibizproproductdailies/fetchdefault`, _data);
    }
    /**
     * FetchProductDaily
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProductDailyService
     */
    async FetchProductDaily(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibizproproductdailies/fetchproductdaily`, _data);
    }

    /**
     * ManualCreateDailyBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbizproProductDailyServiceBase
     */
    public async ManualCreateDailyBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibizproproductdailies/manualcreatedailybatch`,_data);
    }

    /**
     * StatsProductDailyBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbizproProductDailyServiceBase
     */
    public async StatsProductDailyBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibizproproductdailies/statsproductdailybatch`,_data);
    }
}
