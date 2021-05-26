import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProductPlan, ProductPlan } from '../../entities';
import keys from '../../entities/product-plan/product-plan-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 产品计划服务对象基类
 *
 * @export
 * @class ProductPlanBaseService
 * @extends {EntityBaseService}
 */
export class ProductPlanBaseService extends EntityBaseService<IProductPlan> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'ProductPlan';
    protected APPDENAMEPLURAL = 'ProductPlans';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'title';
    protected quickSearchFields = ['title','productplansn',];
    protected selectContextParam = {
        product: 'product',
    };

    newEntity(data: IProductPlan): ProductPlan {
        return new ProductPlan(data);
    }

    async addLocal(context: IContext, entity: IProductPlan): Promise<IProductPlan | null> {
        return this.cache.add(context, new ProductPlan(entity) as any);
    }

    async createLocal(context: IContext, entity: IProductPlan): Promise<IProductPlan | null> {
        return super.createLocal(context, new ProductPlan(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProductPlan> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.product && entity.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(context, entity.product);
            if (data) {
                entity.product = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IProductPlan): Promise<IProductPlan> {
        return super.updateLocal(context, new ProductPlan(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProductPlan = {}): Promise<IProductPlan> {
        if (_context.product && _context.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(_context, _context.product);
            if (data) {
                entity.product = data.id;
            }
        }
        return new ProductPlan(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }

    protected getChildPlanCond() {
        return this.condCache.get('childPlan');
    }

    protected getCurProductPlanCond() {
        return this.condCache.get('curProductPlan');
    }

    protected getDefaultCond() {
        return this.condCache.get('default');
    }

    protected getDefaultParentCond() {
        if (!this.condCache.has('defaultParent')) {
            const strCond: any[] = ['AND', ['OR', ['LTANDEQ', 'PARENT','0']]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('defaultParent', cond);
            }
        }
        return this.condCache.get('defaultParent');
    }

    protected getGetListCond() {
        if (!this.condCache.has('getList')) {
            const strCond: any[] = ['AND', ['OR', ['EQ', 'PRODUCT',{ type: 'DATACONTEXT', value: 'product'}], ['EQ', 'PRODUCT',{ type: 'DATACONTEXT', value: 'products'}]]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('getList', cond);
            }
        }
        return this.condCache.get('getList');
    }

    protected getPlanCodeListCond() {
        return this.condCache.get('planCodeList');
    }

    protected getPlanTasksCond() {
        return this.condCache.get('planTasks');
    }

    protected getProductQueryCond() {
        return this.condCache.get('productQuery');
    }

    protected getProjectAppCond() {
        if (!this.condCache.has('projectApp')) {
            const strCond: any[] = ['AND', ['EQ', 'PRODUCT',{ type: 'WEBCONTEXT', value: 'product'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('projectApp', cond);
            }
        }
        return this.condCache.get('projectApp');
    }

    protected getProjectPlanCond() {
        return this.condCache.get('projectPlan');
    }

    protected getRootPlanCond() {
        return this.condCache.get('rootPlan');
    }

    protected getTaskPlanCond() {
        if (!this.condCache.has('taskPlan')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('taskPlan', cond);
            }
        }
        return this.condCache.get('taskPlan');
    }

    protected getViewCond() {
        return this.condCache.get('view');
    }
    /**
     * BatchUnlinkBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async BatchUnlinkBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/batchunlinkbug`, _data);
        }
        return this.http.post(`/productplans/${_context.productplan}/batchunlinkbug`, _data);
    }
    /**
     * BatchLinkStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async BatchLinkStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/batchlinkstory`, _data);
        }
        return this.http.post(`/productplans/${_context.productplan}/batchlinkstory`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan) {
            return this.http.delete(`/products/${_context.product}/productplans/${_context.productplan}/remove`);
        }
        return this.http.delete(`/productplans/${_context.productplan}/remove`);
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/productplans/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/productplans/getdraft`, _data);
        return res;
    }
    /**
     * LinkStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async LinkStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/linkstory`, _data);
        }
        return this.http.post(`/productplans/${_context.productplan}/linkstory`, _data);
    }
    /**
     * FetchProductQuery
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async FetchProductQuery(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/productplans/fetchproductquery`, _data);
        }
        return this.http.post(`/productplans/fetchproductquery`, _data);
    }
    /**
     * UnlinkStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async UnlinkStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/unlinkstory`, _data);
        }
        return this.http.post(`/productplans/${_context.productplan}/unlinkstory`, _data);
    }
    /**
     * ImportPlanTemplet
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async ImportPlanTemplet(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/importplantemplet`, _data);
        }
        return this.http.post(`/productplans/${_context.productplan}/importplantemplet`, _data);
    }
    /**
     * BatchLinkBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async BatchLinkBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/batchlinkbug`, _data);
        }
        return this.http.post(`/productplans/${_context.productplan}/batchlinkbug`, _data);
    }
    /**
     * BatchUnlinkStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async BatchUnlinkStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/batchunlinkstory`, _data);
        }
        return this.http.post(`/productplans/${_context.productplan}/batchunlinkstory`, _data);
    }
    /**
     * UnlinkBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async UnlinkBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/unlinkbug`, _data);
        }
        return this.http.post(`/productplans/${_context.productplan}/unlinkbug`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/productplans/${_context.productplan}/update`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/productplans/${_context.productplan}/update`, _data);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/productplans`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/productplans`, _data);
    }
    /**
     * LinkBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async LinkBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/linkbug`, _data);
        }
        return this.http.post(`/productplans/${_context.productplan}/linkbug`, _data);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan) {
            const res = await this.http.get(`/products/${_context.product}/productplans/${_context.productplan}/get`);
            return res;
        }
        const res = await this.http.get(`/productplans/${_context.productplan}/get`);
        return res;
    }
    /**
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan) {
            return this.http.get(`/products/${_context.product}/productplans/${_context.productplan}/select`);
        }
        return this.http.get(`/productplans/${_context.productplan}/select`);
    }

    /**
     * BatchUnlinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async BatchUnlinkBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/batchunlinkbugbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/productplans/batchunlinkbugbatch`,_data);
    }

    /**
     * BatchLinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async BatchLinkStoryBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/batchlinkstorybatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/productplans/batchlinkstorybatch`,_data);
    }

    /**
     * LinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async LinkStoryBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/linkstorybatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/productplans/linkstorybatch`,_data);
    }

    /**
     * UnlinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async UnlinkStoryBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/unlinkstorybatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/productplans/unlinkstorybatch`,_data);
    }

    /**
     * ImportPlanTempletBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async ImportPlanTempletBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/importplantempletbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/productplans/importplantempletbatch`,_data);
    }

    /**
     * BatchLinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async BatchLinkBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/batchlinkbugbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/productplans/batchlinkbugbatch`,_data);
    }

    /**
     * BatchUnlinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async BatchUnlinkStoryBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/batchunlinkstorybatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/productplans/batchunlinkstorybatch`,_data);
    }

    /**
     * UnlinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async UnlinkBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/unlinkbugbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/productplans/unlinkbugbatch`,_data);
    }

    /**
     * LinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanServiceBase
     */
    public async LinkBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/linkbugbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/productplans/linkbugbatch`,_data);
    }
}
