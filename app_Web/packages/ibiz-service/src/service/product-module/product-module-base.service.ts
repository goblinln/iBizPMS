import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProductModule, ProductModule } from '../../entities';
import keys from '../../entities/product-module/product-module-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 需求模块服务对象基类
 *
 * @export
 * @class ProductModuleBaseService
 * @extends {EntityBaseService}
 */
export class ProductModuleBaseService extends EntityBaseService<IProductModule> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'ProductModule';
    protected APPDENAMEPLURAL = 'ProductModules';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
        product: 'root',
    };

    newEntity(data: IProductModule): ProductModule {
        return new ProductModule(data);
    }

    async addLocal(context: IContext, entity: IProductModule): Promise<IProductModule | null> {
        return this.cache.add(context, new ProductModule(entity) as any);
    }

    async createLocal(context: IContext, entity: IProductModule): Promise<IProductModule | null> {
        return super.createLocal(context, new ProductModule(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProductModule> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.root && entity.root !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(context, entity.root);
            if (data) {
                entity.rootname = data.name;
                entity.root = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IProductModule): Promise<IProductModule> {
        return super.updateLocal(context, new ProductModule(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProductModule = {}): Promise<IProductModule> {
        if (_context.product && _context.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(_context, _context.product);
            if (data) {
                entity.rootname = data.name;
                entity.root = data.id;
            }
        }
        return new ProductModule(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductModuleService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }

    protected getByPathCond() {
        if (!this.condCache.has('byPath')) {
            const strCond: any[] = ['AND', ['EQ', 'TYPE','story']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('byPath', cond);
            }
        }
        return this.condCache.get('byPath');
    }

    protected getDefaultCond() {
        if (!this.condCache.has('default')) {
            const strCond: any[] = ['AND', ['EQ', 'TYPE','story']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('default', cond);
            }
        }
        return this.condCache.get('default');
    }

    protected getParentModuleCond() {
        return this.condCache.get('parentModule');
    }

    protected getRootCond() {
        if (!this.condCache.has('root')) {
            const strCond: any[] = ['AND', ['EQ', 'TYPE','story'], ['OR', ['ISNULL', 'PARENT',''], ['EQ', 'PARENT','0']], ['NOTEQ', 'BRANCH','0']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('root', cond);
            }
        }
        return this.condCache.get('root');
    }

    protected getRoot_NoBranchCond() {
        if (!this.condCache.has('root_NoBranch')) {
            const strCond: any[] = ['AND', ['EQ', 'TYPE','story'], ['EQ', 'BRANCH','0'], ['OR', ['ISNULL', 'PARENT',''], ['EQ', 'PARENT','0']]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('root_NoBranch', cond);
            }
        }
        return this.condCache.get('root_NoBranch');
    }

    protected getStoryModuleCond() {
        if (!this.condCache.has('storyModule')) {
            const strCond: any[] = ['AND', ['EQ', 'TYPE','story']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('storyModule', cond);
            }
        }
        return this.condCache.get('storyModule');
    }

    protected getViewCond() {
        if (!this.condCache.has('view')) {
            const strCond: any[] = ['AND', ['EQ', 'TYPE','story']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('view', cond);
            }
        }
        return this.condCache.get('view');
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductModuleService
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
            return this.http.post(`/products/${_context.product}/productmodules`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/productmodules`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductModuleService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productmodule) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/productmodules/${_context.productmodule}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/productmodules/${_context.productmodule}`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductModuleService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/productmodules/fetchdefault`, _data);
        }
        return this.http.post(`/productmodules/fetchdefault`, _data);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductModuleService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productmodule) {
            const res = await this.http.get(`/products/${_context.product}/productmodules/${_context.productmodule}`);
            return res;
        }
        const res = await this.http.get(`/productmodules/${_context.productmodule}`);
        return res;
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductModuleService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productmodule) {
            return this.http.delete(`/products/${_context.product}/productmodules/${_context.productmodule}`);
        }
        return this.http.delete(`/productmodules/${_context.productmodule}`);
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductModuleService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/productmodules/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/productmodules/getdraft`, _data);
        return res;
    }
}
