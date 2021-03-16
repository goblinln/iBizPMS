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
    protected quickSearchFields = ['title',];
    protected selectContextParam = {
        product: 'product',
    };

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
            return this.http.put(`/products/${_context.product}/productplans/${_context.productplan}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/productplans/${_context.productplan}`, _data);
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
            return this.http.delete(`/products/${_context.product}/productplans/${_context.productplan}`);
        }
        return this.http.delete(`/productplans/${_context.productplan}`);
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
            const res = await this.http.get(`/products/${_context.product}/productplans/${_context.productplan}`);
            return res;
        }
        const res = await this.http.get(`/productplans/${_context.productplan}`);
        return res;
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
     * EeActivePlan
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async EeActivePlan(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/eeactiveplan`, _data);
        }
        return this.http.post(`/productplans/${_context.productplan}/eeactiveplan`, _data);
    }
    /**
     * EeCancelPlan
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async EeCancelPlan(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/eecancelplan`, _data);
        }
        return this.http.post(`/productplans/${_context.productplan}/eecancelplan`, _data);
    }
    /**
     * EeClosePlan
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async EeClosePlan(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/eecloseplan`, _data);
        }
        return this.http.post(`/productplans/${_context.productplan}/eecloseplan`, _data);
    }
    /**
     * EeFinishPlan
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async EeFinishPlan(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/eefinishplan`, _data);
        }
        return this.http.post(`/productplans/${_context.productplan}/eefinishplan`, _data);
    }
    /**
     * EePausePlan
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async EePausePlan(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/eepauseplan`, _data);
        }
        return this.http.post(`/productplans/${_context.productplan}/eepauseplan`, _data);
    }
    /**
     * EeRestartPlan
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async EeRestartPlan(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/eerestartplan`, _data);
        }
        return this.http.post(`/productplans/${_context.productplan}/eerestartplan`, _data);
    }
    /**
     * EeStartPlan
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async EeStartPlan(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/eestartplan`, _data);
        }
        return this.http.post(`/productplans/${_context.productplan}/eestartplan`, _data);
    }
    /**
     * GetOldPlanName
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async GetOldPlanName(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/getoldplanname`, _data);
        }
        return this.http.post(`/productplans/${_context.productplan}/getoldplanname`, _data);
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
     * LinkTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async LinkTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/linktask`, _data);
        }
        return this.http.post(`/productplans/${_context.productplan}/linktask`, _data);
    }
    /**
     * MobProductPlanCounter
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async MobProductPlanCounter(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/productplans/${_context.productplan}/mobproductplancounter`, _data);
        }
        return this.http.put(`/productplans/${_context.productplan}/mobproductplancounter`, _data);
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
     * FetchChildPlan
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async FetchChildPlan(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.get(`/products/${_context.product}/productplans/fetchchildplan`, _data);
        }
        return this.http.get(`/productplans/fetchchildplan`, _data);
    }
    /**
     * FetchCurProductPlan
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async FetchCurProductPlan(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.get(`/products/${_context.product}/productplans/fetchcurproductplan`, _data);
        }
        return this.http.get(`/productplans/fetchcurproductplan`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.get(`/products/${_context.product}/productplans/fetchdefault`, _data);
        }
        return this.http.get(`/productplans/fetchdefault`, _data);
    }
    /**
     * FetchDefaultParent
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async FetchDefaultParent(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/productplans/fetchdefaultparent`, _data);
        }
        return this.http.post(`/productplans/fetchdefaultparent`, _data);
    }
    /**
     * FetchPlanCodeList
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async FetchPlanCodeList(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.get(`/products/${_context.product}/productplans/fetchplancodelist`, _data);
        }
        return this.http.get(`/productplans/fetchplancodelist`, _data);
    }
    /**
     * FetchPlanTasks
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async FetchPlanTasks(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.get(`/products/${_context.product}/productplans/fetchplantasks`, _data);
        }
        return this.http.get(`/productplans/fetchplantasks`, _data);
    }
    /**
     * FetchProjectApp
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async FetchProjectApp(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.get(`/products/${_context.product}/productplans/fetchprojectapp`, _data);
        }
        return this.http.get(`/productplans/fetchprojectapp`, _data);
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
        if (_context.product && true) {
            return this.http.get(`/products/${_context.product}/productplans/fetchprojectplan`, _data);
        }
        return this.http.get(`/productplans/fetchprojectplan`, _data);
    }
    /**
     * FetchRootPlan
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async FetchRootPlan(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.get(`/products/${_context.product}/productplans/fetchrootplan`, _data);
        }
        return this.http.get(`/productplans/fetchrootplan`, _data);
    }
    /**
     * FetchTaskPlan
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanService
     */
    async FetchTaskPlan(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.get(`/products/${_context.product}/productplans/fetchtaskplan`, _data);
        }
        return this.http.get(`/productplans/fetchtaskplan`, _data);
    }
}
