import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ISubProductPlan, SubProductPlan } from '../../entities';
import keys from '../../entities/sub-product-plan/sub-product-plan-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 产品计划服务对象基类
 *
 * @export
 * @class SubProductPlanBaseService
 * @extends {EntityBaseService}
 */
export class SubProductPlanBaseService extends EntityBaseService<ISubProductPlan> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'SubProductPlan';
    protected APPDENAMEPLURAL = 'SubProductPlans';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'title';
    protected quickSearchFields = ['title','productplansn',];
    protected selectContextParam = {
        productplan: 'parent',
    };

    newEntity(data: ISubProductPlan): SubProductPlan {
        return new SubProductPlan(data);
    }

    async addLocal(context: IContext, entity: ISubProductPlan): Promise<ISubProductPlan | null> {
        return this.cache.add(context, new SubProductPlan(entity) as any);
    }

    async createLocal(context: IContext, entity: ISubProductPlan): Promise<ISubProductPlan | null> {
        return super.createLocal(context, new SubProductPlan(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ISubProductPlan> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.parent && entity.parent !== '') {
            const s = await ___ibz___.gs.getProductPlanService();
            const data = await s.getLocal2(context, entity.parent);
            if (data) {
                entity.parentname = data.title;
                entity.parent = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: ISubProductPlan): Promise<ISubProductPlan> {
        return super.updateLocal(context, new SubProductPlan(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ISubProductPlan = {}): Promise<ISubProductPlan> {
        if (_context.productplan && _context.productplan !== '') {
            const s = await ___ibz___.gs.getProductPlanService();
            const data = await s.getLocal2(_context, _context.productplan);
            if (data) {
                entity.parentname = data.title;
                entity.parent = data.id;
            }
        }
        return new SubProductPlan(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubProductPlanService
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
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubProductPlanService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan && _context.subproductplan) {
            return this.http.get(`/products/${_context.product}/productplans/${_context.productplan}/subproductplans/${_context.subproductplan}/select`);
        }
        if (_context.productplan && _context.subproductplan) {
            return this.http.get(`/productplans/${_context.productplan}/subproductplans/${_context.subproductplan}/select`);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
}
