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
    protected quickSearchFields = ['name','code','productsn',];
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
            const strCond: any[] = ['AND', ['OR', ['NOTEQ', 'STATUS','closed']]];
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

    protected getCurDefaultCond() {
        return this.condCache.get('curDefault');
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
        return this.condCache.get('default');
    }

    protected getDeveloperQueryCond() {
        if (!this.condCache.has('developerQuery')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('developerQuery', cond);
            }
        }
        return this.condCache.get('developerQuery');
    }

    protected getESBulkCond() {
        return this.condCache.get('eSBulk');
    }

    protected getOpenQueryCond() {
        if (!this.condCache.has('openQuery')) {
            const strCond: any[] = ['AND', ['EQ', 'ACL','open']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('openQuery', cond);
            }
        }
        return this.condCache.get('openQuery');
    }

    protected getProductManagerQueryCond() {
        if (!this.condCache.has('productManagerQuery')) {
            const strCond: any[] = ['AND', ['AND', ['EQ', 'ACL','private'], ['OR', ['EQ', 'UPDATEBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'CREATEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'QD',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'RD',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'PO',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}]]]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('productManagerQuery', cond);
            }
        }
        return this.condCache.get('productManagerQuery');
    }

    protected getProductPMCond() {
        return this.condCache.get('productPM');
    }

    protected getProductTeamCond() {
        return this.condCache.get('productTeam');
    }

    protected getSimpleCond() {
        return this.condCache.get('simple');
    }

    protected getStoryCurProjectCond() {
        return this.condCache.get('storyCurProject');
    }

    protected getViewCond() {
        return this.condCache.get('view');
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
        const res = await this.http.get(`/products/${_context.product}/get`);
        return res;
    }
    /**
     * FetchCurDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductService
     */
    async FetchCurDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/products/fetchcurdefault`, _data);
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
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/products/${_context.product}/remove`);
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
        return this.http.post(`/products/fetchalllist`, _data);
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
        return this.http.put(`/products/${_context.product}/update`, _data);
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
     * FetchProductTeam
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductService
     */
    async FetchProductTeam(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/products/fetchproductteam`, _data);
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
     * CloseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductServiceBase
     */
    public async CloseBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/products/closebatch`,_data);
    }
}
