import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProduct, Product } from '../../entities';
import keys from '../../entities/product/product-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';
import { CancelProductTopLogic } from '../../logic/entity/product/cancel-product-top/cancel-product-top-logic';
import { ProductTopLogic } from '../../logic/entity/product/product-top/product-top-logic';

/**
 * 产品服务对象基类
 *
 * @export
 * @class ProductBaseService
 * @extends {EntityBaseService}
 */
export class ProductBaseService extends EntityBaseService<IProduct> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'Product';
    protected APPDENAMEPLURAL = 'Products';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name','id','code',];
    protected selectContextParam = {
    };

    newEntity(data: IProduct): Product {
        return new Product(data);
    }

    async addLocal(context: IContext, entity: IProduct): Promise<IProduct | null> {
        return this.cache.add(context, new Product(entity) as any);
    }

    async createLocal(context: IContext, entity: IProduct): Promise<IProduct | null> {
        return super.createLocal(context, new Product(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProduct> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IProduct): Promise<IProduct> {
        return super.updateLocal(context, new Product(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProduct = {}): Promise<IProduct> {
        return new Product(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }

    protected getAllListCond() {
        if (!this.condCache.has('allList')) {
            const strCond: any[] = ['AND', ['OR', ['NOTEQ', 'STATUS','closed']], ['EQ', 'ORGID',{ type: 'SESSIONCONTEXT', value: 'SRFORGID'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('allList', cond);
            }
        }
        return this.condCache.get('allList');
    }

    protected getAllProductCond() {
        return this.condCache.get('allProduct');
    }

    protected getCheckNameOrCodeCond() {
        return this.condCache.get('checkNameOrCode');
    }

    protected getCurProjectCond() {
        return this.condCache.get('curProject');
    }

    protected getCurUerCond() {
        if (!this.condCache.has('curUer')) {
            const strCond: any[] = ['AND', ['OR', ['EQ', 'ACL','open']]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('curUer', cond);
            }
        }
        return this.condCache.get('curUer');
    }

    protected getDefaultCond() {
        if (!this.condCache.has('default')) {
            const strCond: any[] = ['AND', ['EQ', 'ORGID',{ type: 'SESSIONCONTEXT', value: 'srforgid'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('default', cond);
            }
        }
        return this.condCache.get('default');
    }

    protected getESBulkCond() {
        return this.condCache.get('eSBulk');
    }

    protected getProductPMCond() {
        return this.condCache.get('productPM');
    }

    protected getProductTeamCond() {
        return this.condCache.get('productTeam');
    }

    protected getStoryCurProjectCond() {
        return this.condCache.get('storyCurProject');
    }

    protected getViewCond() {
        return this.condCache.get('view');
    }
    /**
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/products/${_context.product}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/products`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/products/${_context.product}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/products/${_context.product}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/products/${_context.product}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/products/getdraft`, _data);
        return res;
    }
    /**
     * CancelProductTop
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductService
     */
    async CancelProductTop(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/products/${_context.product}/cancelproducttop`, _data);
    }
    /**
     * Close
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductService
     */
    async Close(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/products/${_context.product}/close`, _data);
    }
    /**
     * MobProductCounter
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductService
     */
    async MobProductCounter(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.put(`/products/${_context.product}/mobproductcounter`, _data);
    }
    /**
     * MobProductTestCounter
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductService
     */
    async MobProductTestCounter(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/products/${_context.product}/mobproducttestcounter`, _data);
    }
    /**
     * ProductTop
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductService
     */
    async ProductTop(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/products/${_context.product}/producttop`, _data);
    }
    /**
     * FetchAllList
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductService
     */
    async FetchAllList(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/products/fetchalllist`, _data);
    }
    /**
     * FetchAllProduct
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductService
     */
    async FetchAllProduct(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/products/fetchallproduct`, _data);
    }
    /**
     * FetchCheckNameOrCode
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductService
     */
    async FetchCheckNameOrCode(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/products/fetchchecknameorcode`, _data);
    }
    /**
     * FetchCurProject
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductService
     */
    async FetchCurProject(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/products/fetchcurproject`, _data);
    }
    /**
     * FetchCurUer
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductService
     */
    async FetchCurUer(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/products/fetchcuruer`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/products/fetchdefault`, _data);
    }
    /**
     * FetchESBulk
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductService
     */
    async FetchESBulk(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/products/fetchesbulk`, _data);
    }
    /**
     * FetchProductPM
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductService
     */
    async FetchProductPM(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/products/fetchproductpm`, _data);
    }
    /**
     * FetchProductTeam
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductService
     */
    async FetchProductTeam(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/products/fetchproductteam`, _data);
    }
    /**
     * FetchStoryCurProject
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductService
     */
    async FetchStoryCurProject(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/products/fetchstorycurproject`, _data);
    }
}