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
        product: 'product',
        project: 'project',
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
        if (entity && entity.product && entity.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(context, entity.product);
            if (data) {
                entity.productname = data.name;
                entity.product = data.id;
                entity.product = data;
            }
        }
        if (entity && entity.project && entity.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(context, entity.project);
            if (data) {
                entity.projectname = data.name;
                entity.project = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IProjectProduct): Promise<IProjectProduct> {
        return super.updateLocal(context, new ProjectProduct(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProjectProduct = {}): Promise<IProjectProduct> {
        if (_context.product && _context.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(_context, _context.product);
            if (data) {
                entity.productname = data.name;
                entity.product = data.id;
                entity.product = data;
            }
        }
        if (_context.project && _context.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(_context, _context.project);
            if (data) {
                entity.projectname = data.name;
                entity.project = data.id;
            }
        }
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
        if (_context.project && _context.projectproduct) {
            return this.http.get(`/projects/${_context.project}/projectproducts/${_context.projectproduct}/select`);
        }
        if (_context.product && _context.projectproduct) {
            return this.http.get(`/products/${_context.product}/projectproducts/${_context.projectproduct}/select`);
        }
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
        if (_context.project && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/projectproducts`, _data);
        }
        if (_context.product && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/projectproducts`, _data);
        }
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
        if (_context.project && _context.projectproduct) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/projectproducts/${_context.projectproduct}`, _data);
        }
        if (_context.product && _context.projectproduct) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/projectproducts/${_context.projectproduct}`, _data);
        }
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
        if (_context.project && _context.projectproduct) {
            return this.http.delete(`/projects/${_context.project}/projectproducts/${_context.projectproduct}`);
        }
        if (_context.product && _context.projectproduct) {
            return this.http.delete(`/products/${_context.product}/projectproducts/${_context.projectproduct}`);
        }
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
        if (_context.project && _context.projectproduct) {
            const res = await this.http.get(`/projects/${_context.project}/projectproducts/${_context.projectproduct}`);
            return res;
        }
        if (_context.product && _context.projectproduct) {
            const res = await this.http.get(`/products/${_context.product}/projectproducts/${_context.projectproduct}`);
            return res;
        }
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
        if (_context.project && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/projectproducts/getdraft`, _data);
            return res;
        }
        if (_context.product && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/projectproducts/getdraft`, _data);
            return res;
        }
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
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/projectproducts/fetchdefault`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/projectproducts/fetchdefault`, _data);
        }
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
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/projectproducts/fetchrelationplan`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/projectproducts/fetchrelationplan`, _data);
        }
        return this.http.post(`/projectproducts/fetchrelationplan`, _data);
    }
}
