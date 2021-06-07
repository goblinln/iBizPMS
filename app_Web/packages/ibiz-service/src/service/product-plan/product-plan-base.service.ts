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
     * FetchProductQuery
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async FetchProductQuery(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/productplans/fetchproductquery`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/productplans/fetchproductquery`, _data);
        }
    this.log.warn([`[ProductPlan]>>>[FetchProductQuery函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.project && _context.productplan) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/productplans/${_context.productplan}/unlinkstory`, _data);
        }
        if (_context.product && _context.productplan) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/unlinkstory`, _data);
        }
    this.log.warn([`[ProductPlan]>>>[UnlinkStory函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.project && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/productplans`, _data);
        }
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
    this.log.warn([`[ProductPlan]>>>[Create函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.project && _context.productplan) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/productplans/${_context.productplan}/unlinkbug`, _data);
        }
        if (_context.product && _context.productplan) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/unlinkbug`, _data);
        }
    this.log.warn([`[ProductPlan]>>>[UnlinkBug函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.project && _context.productplan) {
            return this.http.delete(`/projects/${_context.project}/productplans/${_context.productplan}`);
        }
        if (_context.product && _context.productplan) {
            return this.http.delete(`/products/${_context.product}/productplans/${_context.productplan}`);
        }
    this.log.warn([`[ProductPlan]>>>[Remove函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.project && _context.productplan) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/productplans/${_context.productplan}`, _data);
        }
        if (_context.product && _context.productplan) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/productplans/${_context.productplan}`, _data);
        }
    this.log.warn([`[ProductPlan]>>>[Update函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.project && _context.productplan) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/productplans/${_context.productplan}/linkbug`, _data);
        }
        if (_context.product && _context.productplan) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/linkbug`, _data);
        }
    this.log.warn([`[ProductPlan]>>>[LinkBug函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.project && _context.productplan) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/productplans/${_context.productplan}/importplantemplet`, _data);
        }
        if (_context.product && _context.productplan) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/importplantemplet`, _data);
        }
    this.log.warn([`[ProductPlan]>>>[ImportPlanTemplet函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.project && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/productplans/getdraft`, _data);
            return res;
        }
        if (_context.product && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/productplans/getdraft`, _data);
            return res;
        }
    this.log.warn([`[ProductPlan]>>>[GetDraft函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.project && _context.productplan) {
            const res = await this.http.get(`/projects/${_context.project}/productplans/${_context.productplan}`);
            return res;
        }
        if (_context.product && _context.productplan) {
            const res = await this.http.get(`/products/${_context.product}/productplans/${_context.productplan}`);
            return res;
        }
    this.log.warn([`[ProductPlan]>>>[Get函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchProjectPlan
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async FetchProjectPlan(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/productplans/fetchprojectplan`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/productplans/fetchprojectplan`, _data);
        }
    this.log.warn([`[ProductPlan]>>>[FetchProjectPlan函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.project && _context.productplan) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/productplans/${_context.productplan}/linkstory`, _data);
        }
        if (_context.product && _context.productplan) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/linkstory`, _data);
        }
    this.log.warn([`[ProductPlan]>>>[LinkStory函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
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
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/productplans/unlinkstorybatch`,_data);
        }
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/unlinkstorybatch`,_data);
        }
        this.log.warn([`[ProductPlan]>>>[UnlinkStoryBatch函数]异常`]);
        return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
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
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/productplans/unlinkbugbatch`,_data);
        }
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/unlinkbugbatch`,_data);
        }
        this.log.warn([`[ProductPlan]>>>[UnlinkBugBatch函数]异常`]);
        return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
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
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/productplans/linkbugbatch`,_data);
        }
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/linkbugbatch`,_data);
        }
        this.log.warn([`[ProductPlan]>>>[LinkBugBatch函数]异常`]);
        return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
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
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/productplans/importplantempletbatch`,_data);
        }
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/importplantempletbatch`,_data);
        }
        this.log.warn([`[ProductPlan]>>>[ImportPlanTempletBatch函数]异常`]);
        return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
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
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/productplans/linkstorybatch`,_data);
        }
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/linkstorybatch`,_data);
        }
        this.log.warn([`[ProductPlan]>>>[LinkStoryBatch函数]异常`]);
        return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
    }
}
