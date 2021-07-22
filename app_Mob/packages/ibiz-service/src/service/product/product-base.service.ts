import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProduct, Product } from '../../entities';
import keys from '../../entities/product/product-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

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
    protected APPNAME = 'Mob';
    protected APPDENAME = 'Product';
    protected APPDENAMEPLURAL = 'Products';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Mob/PSAPPDATAENTITIES/Product.json';
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

    protected getAccountCond() {
        return this.condCache.get('account');
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

    protected getMyCond() {
        return this.condCache.get('my');
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
     * CancelProductTop
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductService
     */
    async CancelProductTop(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/products/${_context.product}/cancelproducttop`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        const res = await this.http.post(`/products/${_context.product}/close`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        const res = await this.http.post(`/products`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        const res = await this.http.get(`/products/${_context.product}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/products/getdraft`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        const res = await this.http.post(`/products/${_context.product}/producttop`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        const res = await this.http.delete(`/products/${_context.product}`);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
        const res = await this.http.put(`/products/${_context.product}`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        const res = await this.http.post(`/products/fetchcurdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchCurDefault');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        const res = await this.http.post(`/products/fetchcurproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchCurProject');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        const res = await this.http.post(`/products/closebatch`,_data);
        return res;
    }
}
