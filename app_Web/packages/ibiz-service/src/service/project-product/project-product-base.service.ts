import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProjectProduct, ProjectProduct } from '../../entities';
import keys from '../../entities/project-product/project-product-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 项目产品服务对象基类
 *
 * @export
 * @class ProjectProductBaseService
 * @extends {EntityBaseService}
 */
export class ProjectProductBaseService extends EntityBaseService<IProjectProduct> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'ProjectProduct';
    protected APPDENAMEPLURAL = 'ProjectProducts';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'productname';
    protected quickSearchFields = ['productname',];
    protected selectContextParam = {
    };

    newEntity(data: IProjectProduct): ProjectProduct {
        return new ProjectProduct(data);
    }

    async addLocal(context: IContext, entity: IProjectProduct): Promise<IProjectProduct | null> {
        return this.cache.add(context, new ProjectProduct(entity) as any);
    }

    async createLocal(context: IContext, entity: IProjectProduct): Promise<IProjectProduct | null> {
        return super.createLocal(context, new ProjectProduct(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProjectProduct> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IProjectProduct): Promise<IProjectProduct> {
        return super.updateLocal(context, new ProjectProduct(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProjectProduct = {}): Promise<IProjectProduct> {
        return new ProjectProduct(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectProductService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }

    protected getDefaultCond() {
        return this.condCache.get('default');
    }

    protected getRelationPlanCond() {
        if (!this.condCache.has('relationPlan')) {
            const strCond: any[] = ['AND', ['NOTEQ', 'PLAN','0']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('relationPlan', cond);
            }
        }
        return this.condCache.get('relationPlan');
    }

    protected getSimpleCond() {
        return this.condCache.get('simple');
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
     * @memberof ProjectProductService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/projectproducts/${_context.projectproduct}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectProductService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/projectproducts`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectProductService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/projectproducts/${_context.projectproduct}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectProductService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/projectproducts/${_context.projectproduct}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectProductService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/projectproducts/${_context.projectproduct}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectProductService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/projectproducts/getdraft`, _data);
        return res;
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectProductService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projectproducts/fetchdefault`, _data);
    }
    /**
     * FetchRelationPlan
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectProductService
     */
    async FetchRelationPlan(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projectproducts/fetchrelationplan`, _data);
    }
}
