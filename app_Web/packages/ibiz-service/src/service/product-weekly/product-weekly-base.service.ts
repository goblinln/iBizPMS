import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProductWeekly, ProductWeekly } from '../../entities';
import keys from '../../entities/product-weekly/product-weekly-keys';

/**
 * 产品周报服务对象基类
 *
 * @export
 * @class ProductWeeklyBaseService
 * @extends {EntityBaseService}
 */
export class ProductWeeklyBaseService extends EntityBaseService<IProductWeekly> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'ProductWeekly';
    protected APPDENAMEPLURAL = 'ProductWeeklies';
    protected APPDEKEY = 'ibizpro_productweeklyid';
    protected APPDETEXT = 'ibizproproductweeklyname';
    protected quickSearchFields = ['ibizproproductweeklyname',];
    protected selectContextParam = {
    };

    newEntity(data: IProductWeekly): ProductWeekly {
        return new ProductWeekly(data);
    }

    async addLocal(context: IContext, entity: IProductWeekly): Promise<IProductWeekly | null> {
        return this.cache.add(context, new ProductWeekly(entity) as any);
    }

    async createLocal(context: IContext, entity: IProductWeekly): Promise<IProductWeekly | null> {
        return super.createLocal(context, new ProductWeekly(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProductWeekly> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IProductWeekly): Promise<IProductWeekly> {
        return super.updateLocal(context, new ProductWeekly(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProductWeekly = {}): Promise<IProductWeekly> {
        return new ProductWeekly(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductWeeklyService
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
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductWeeklyService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/productweeklies`, _data);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductWeeklyService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/productweeklies/${_context.productweekly}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductWeeklyService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/productweeklies/getdraft`, _data);
        return res;
    }
    /**
     * Summary
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductWeeklyService
     */
    async Summary(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/productweeklies/${_context.productweekly}/summary`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductWeeklyService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/productweeklies/${_context.productweekly}`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductWeeklyService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/productweeklies/fetchdefault`, _data);
    }

    /**
     * SummaryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductWeeklyServiceBase
     */
    public async SummaryBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/productweeklies/summarybatch`,_data);
    }
}
