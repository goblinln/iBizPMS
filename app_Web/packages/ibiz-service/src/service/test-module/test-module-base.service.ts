import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ITestModule, TestModule } from '../../entities';
import keys from '../../entities/test-module/test-module-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 测试模块服务对象基类
 *
 * @export
 * @class TestModuleBaseService
 * @extends {EntityBaseService}
 */
export class TestModuleBaseService extends EntityBaseService<ITestModule> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'TestModule';
    protected APPDENAMEPLURAL = 'TestModules';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
        product: 'root',
    };

    newEntity(data: ITestModule): TestModule {
        return new TestModule(data);
    }

    async addLocal(context: IContext, entity: ITestModule): Promise<ITestModule | null> {
        return this.cache.add(context, new TestModule(entity) as any);
    }

    async createLocal(context: IContext, entity: ITestModule): Promise<ITestModule | null> {
        return super.createLocal(context, new TestModule(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ITestModule> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.root && entity.root !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(context, entity.root);
            if (data) {
                entity.rootname = data.name;
                entity.root = data.productsn;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: ITestModule): Promise<ITestModule> {
        return super.updateLocal(context, new TestModule(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ITestModule = {}): Promise<ITestModule> {
        if (_context.product && _context.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(_context, _context.product);
            if (data) {
                entity.rootname = data.name;
                entity.root = data.productsn;
            }
        }
        return new TestModule(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestModuleService
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
            const strCond: any[] = ['AND', ['EQ', 'TYPE','bug']];
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
            const strCond: any[] = ['AND', ['EQ', 'TYPE','story'], ['EQ', 'DELETED','0']];
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
            const strCond: any[] = ['AND', ['EQ', 'TYPE','story'], ['OR', ['ISNULL', 'PARENT',''], ['EQ', 'PARENT','0']]];
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

    protected getTestModuleCond() {
        if (!this.condCache.has('testModule')) {
            const strCond: any[] = ['AND', ['EQ', 'TYPE','story'], ['EQ', 'DELETED','0']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('testModule', cond);
            }
        }
        return this.condCache.get('testModule');
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
     * @memberof TestModuleService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.testmodule) {
            return this.http.get(`/products/${_context.product}/testmodules/${_context.testmodule}/select`);
        }
        return this.http.get(`/testmodules/${_context.testmodule}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestModuleService
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
            return this.http.post(`/products/${_context.product}/testmodules`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/testmodules`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestModuleService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.testmodule) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/testmodules/${_context.testmodule}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/testmodules/${_context.testmodule}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestModuleService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.testmodule) {
            return this.http.delete(`/products/${_context.product}/testmodules/${_context.testmodule}`);
        }
        return this.http.delete(`/testmodules/${_context.testmodule}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestModuleService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.testmodule) {
            const res = await this.http.get(`/products/${_context.product}/testmodules/${_context.testmodule}`);
            return res;
        }
        const res = await this.http.get(`/testmodules/${_context.testmodule}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestModuleService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/testmodules/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/testmodules/getdraft`, _data);
        return res;
    }
    /**
     * Fix
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestModuleService
     */
    async Fix(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.testmodule) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testmodules/${_context.testmodule}/fix`, _data);
        }
        return this.http.post(`/testmodules/${_context.testmodule}/fix`, _data);
    }
    /**
     * RemoveModule
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestModuleService
     */
    async RemoveModule(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.testmodule) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/testmodules/${_context.testmodule}/removemodule`, _data);
        }
        return this.http.put(`/testmodules/${_context.testmodule}/removemodule`, _data);
    }
    /**
     * FetchByPath
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestModuleService
     */
    async FetchByPath(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/testmodules/fetchbypath`, _data);
        }
        return this.http.post(`/testmodules/fetchbypath`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestModuleService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/testmodules/fetchdefault`, _data);
        }
        return this.http.post(`/testmodules/fetchdefault`, _data);
    }
    /**
     * FetchParentModule
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestModuleService
     */
    async FetchParentModule(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/testmodules/fetchparentmodule`, _data);
        }
        return this.http.post(`/testmodules/fetchparentmodule`, _data);
    }
    /**
     * FetchRoot
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestModuleService
     */
    async FetchRoot(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/testmodules/fetchroot`, _data);
        }
        return this.http.post(`/testmodules/fetchroot`, _data);
    }
    /**
     * FetchRoot_NoBranch
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestModuleService
     */
    async FetchRoot_NoBranch(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/testmodules/fetchroot_nobranch`, _data);
        }
        return this.http.post(`/testmodules/fetchroot_nobranch`, _data);
    }
    /**
     * FetchTestModule
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestModuleService
     */
    async FetchTestModule(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/testmodules/fetchtestmodule`, _data);
        }
        return this.http.post(`/testmodules/fetchtestmodule`, _data);
    }
}
