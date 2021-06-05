import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ITestCaseLibModule, TestCaseLibModule } from '../../entities';
import keys from '../../entities/test-case-lib-module/test-case-lib-module-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 用例库模块服务对象基类
 *
 * @export
 * @class TestCaseLibModuleBaseService
 * @extends {EntityBaseService}
 */
export class TestCaseLibModuleBaseService extends EntityBaseService<ITestCaseLibModule> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'TestCaseLibModule';
    protected APPDENAMEPLURAL = 'TestCaseLibModules';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
        testcaselib: 'root',
    };

    newEntity(data: ITestCaseLibModule): TestCaseLibModule {
        return new TestCaseLibModule(data);
    }

    async addLocal(context: IContext, entity: ITestCaseLibModule): Promise<ITestCaseLibModule | null> {
        return this.cache.add(context, new TestCaseLibModule(entity) as any);
    }

    async createLocal(context: IContext, entity: ITestCaseLibModule): Promise<ITestCaseLibModule | null> {
        return super.createLocal(context, new TestCaseLibModule(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ITestCaseLibModule> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.root && entity.root !== '') {
            const s = await ___ibz___.gs.getTestCaseLibService();
            const data = await s.getLocal2(context, entity.root);
            if (data) {
                entity.root = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: ITestCaseLibModule): Promise<ITestCaseLibModule> {
        return super.updateLocal(context, new TestCaseLibModule(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ITestCaseLibModule = {}): Promise<ITestCaseLibModule> {
        if (_context.testcaselib && _context.testcaselib !== '') {
            const s = await ___ibz___.gs.getTestCaseLibService();
            const data = await s.getLocal2(_context, _context.testcaselib);
            if (data) {
                entity.root = data.id;
            }
        }
        return new TestCaseLibModule(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestCaseLibModuleService
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
        if (!this.condCache.has('default')) {
            const strCond: any[] = ['AND', ['EQ', 'TYPE','caselib']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('default', cond);
            }
        }
        return this.condCache.get('default');
    }

    protected getRoot_NoBranchCond() {
        if (!this.condCache.has('root_NoBranch')) {
            const strCond: any[] = ['AND', ['EQ', 'TYPE','caselib'], ['OR', ['ISNULL', 'PARENT',''], ['EQ', 'PARENT','0']]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('root_NoBranch', cond);
            }
        }
        return this.condCache.get('root_NoBranch');
    }

    protected getViewCond() {
        return this.condCache.get('view');
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestCaseLibModuleService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.testcaselib && true) {
            return this.http.post(`/testcaselibs/${_context.testcaselib}/testcaselibmodules/fetchdefault`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestCaseLibModuleService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.testcaselib && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/testcaselibs/${_context.testcaselib}/testcaselibmodules/getdraft`, _data);
            return res;
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestCaseLibModuleService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.testcaselib && _context.testcaselibmodule) {
            const res = await this.http.get(`/testcaselibs/${_context.testcaselib}/testcaselibmodules/${_context.testcaselibmodule}`);
            return res;
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestCaseLibModuleService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.testcaselib && _context.testcaselibmodule) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/testcaselibs/${_context.testcaselib}/testcaselibmodules/${_context.testcaselibmodule}`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestCaseLibModuleService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.testcaselib && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/testcaselibs/${_context.testcaselib}/testcaselibmodules`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestCaseLibModuleService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.testcaselib && _context.testcaselibmodule) {
            return this.http.delete(`/testcaselibs/${_context.testcaselib}/testcaselibmodules/${_context.testcaselibmodule}`);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
}
