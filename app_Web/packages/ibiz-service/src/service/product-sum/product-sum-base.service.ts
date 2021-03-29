import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProductSum, ProductSum } from '../../entities';
import keys from '../../entities/product-sum/product-sum-keys';

/**
 * 产品汇总表服务对象基类
 *
 * @export
 * @class ProductSumBaseService
 * @extends {EntityBaseService}
 */
export class ProductSumBaseService extends EntityBaseService<IProductSum> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'ProductSum';
    protected APPDENAMEPLURAL = 'ProductSums';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
    };

    newEntity(data: IProductSum): ProductSum {
        return new ProductSum(data);
    }

    async addLocal(context: IContext, entity: IProductSum): Promise<IProductSum | null> {
        return this.cache.add(context, new ProductSum(entity) as any);
    }

    async createLocal(context: IContext, entity: IProductSum): Promise<IProductSum | null> {
        return super.createLocal(context, new ProductSum(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProductSum> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IProductSum): Promise<IProductSum> {
        return super.updateLocal(context, new ProductSum(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProductSum = {}): Promise<IProductSum> {
        return new ProductSum(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductSumService
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
     * @memberof ProductSumService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/productsums/${_context.productsum}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductSumService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/productsums`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductSumService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/productsums/${_context.productsum}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductSumService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/productsums/${_context.productsum}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductSumService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/productsums/${_context.productsum}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductSumService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/productsums/getdraft`, _data);
        return res;
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductSumService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/productsums/fetchdefault`, _data);
    }
    /**
     * FetchProductBugcnt_QA
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductSumService
     */
    async FetchProductBugcnt_QA(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/productsums/fetchproductbugcnt_qa`, _data);
    }
    /**
     * FetchProductCreateStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductSumService
     */
    async FetchProductCreateStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/productsums/fetchproductcreatestory`, _data);
    }
    /**
     * FetchProductStoryHoursSum
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductSumService
     */
    async FetchProductStoryHoursSum(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/productsums/fetchproductstoryhourssum`, _data);
    }
    /**
     * FetchProductStorySum
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductSumService
     */
    async FetchProductStorySum(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/productsums/fetchproductstorysum`, _data);
    }
    /**
     * FetchProductStorycntAndPlancnt
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductSumService
     */
    async FetchProductStorycntAndPlancnt(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/productsums/fetchproductstorycntandplancnt`, _data);
    }
    /**
     * FetchProductSumBugType
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductSumService
     */
    async FetchProductSumBugType(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/productsums/fetchproductsumbugtype`, _data);
    }
}