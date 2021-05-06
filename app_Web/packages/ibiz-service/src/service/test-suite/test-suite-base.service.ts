import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ITestSuite, TestSuite } from '../../entities';
import keys from '../../entities/test-suite/test-suite-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 测试套件服务对象基类
 *
 * @export
 * @class TestSuiteBaseService
 * @extends {EntityBaseService}
 */
export class TestSuiteBaseService extends EntityBaseService<ITestSuite> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'TestSuite';
    protected APPDENAMEPLURAL = 'TestSuites';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
        product: 'product',
    };

    newEntity(data: ITestSuite): TestSuite {
        return new TestSuite(data);
    }

    async addLocal(context: IContext, entity: ITestSuite): Promise<ITestSuite | null> {
        return this.cache.add(context, new TestSuite(entity) as any);
    }

    async createLocal(context: IContext, entity: ITestSuite): Promise<ITestSuite | null> {
        return super.createLocal(context, new TestSuite(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ITestSuite> {
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

    async updateLocal(context: IContext, entity: ITestSuite): Promise<ITestSuite> {
        return super.updateLocal(context, new TestSuite(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ITestSuite = {}): Promise<ITestSuite> {
        if (_context.product && _context.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(_context, _context.product);
            if (data) {
                entity.product = data.id;
            }
        }
        return new TestSuite(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestSuiteService
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

    protected getPublicTestSuiteCond() {
        if (!this.condCache.has('publicTestSuite')) {
            const strCond: any[] = ['AND', ['OR', ['EQ', 'TYPE','public'], ['AND', ['EQ', 'TYPE','private'], ['EQ', 'ADDEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}]]], ['EQ', 'PRODUCT',{ type: 'WEBCONTEXT', value: 'srfparentkey'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('publicTestSuite', cond);
            }
        }
        return this.condCache.get('publicTestSuite');
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
     * @memberof TestSuiteService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.testsuite) {
            return this.http.get(`/products/${_context.product}/testsuites/${_context.testsuite}/select`);
        }
        return this.http.get(`/testsuites/${_context.testsuite}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestSuiteService
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
            return this.http.post(`/products/${_context.product}/testsuites`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/testsuites`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestSuiteService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.testsuite) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/testsuites/${_context.testsuite}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/testsuites/${_context.testsuite}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestSuiteService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.testsuite) {
            return this.http.delete(`/products/${_context.product}/testsuites/${_context.testsuite}`);
        }
        return this.http.delete(`/testsuites/${_context.testsuite}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestSuiteService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.testsuite) {
            const res = await this.http.get(`/products/${_context.product}/testsuites/${_context.testsuite}`);
            return res;
        }
        const res = await this.http.get(`/testsuites/${_context.testsuite}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestSuiteService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/testsuites/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/testsuites/getdraft`, _data);
        return res;
    }
    /**
     * LinkCase
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestSuiteService
     */
    async LinkCase(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.testsuite) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/linkcase`, _data);
        }
        return this.http.post(`/testsuites/${_context.testsuite}/linkcase`, _data);
    }
    /**
     * MobTestSuiteCount
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestSuiteService
     */
    async MobTestSuiteCount(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.testsuite) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/mobtestsuitecount`, _data);
        }
        return this.http.post(`/testsuites/${_context.testsuite}/mobtestsuitecount`, _data);
    }
    /**
     * UnlinkCase
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestSuiteService
     */
    async UnlinkCase(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.testsuite) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/unlinkcase`, _data);
        }
        return this.http.post(`/testsuites/${_context.testsuite}/unlinkcase`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestSuiteService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/testsuites/fetchdefault`, _data);
        }
        return this.http.post(`/testsuites/fetchdefault`, _data);
    }
    /**
     * FetchPublicTestSuite
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestSuiteService
     */
    async FetchPublicTestSuite(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/testsuites/fetchpublictestsuite`, _data);
        }
        return this.http.post(`/testsuites/fetchpublictestsuite`, _data);
    }

    /**
     * LinkCaseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestSuiteServiceBase
     */
    public async LinkCaseBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/testsuites/linkcasebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/testsuites/linkcasebatch`,tempData,isloading);
    }

    /**
     * UnlinkCaseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestSuiteServiceBase
     */
    public async UnlinkCaseBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/testsuites/unlinkcasebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/testsuites/unlinkcasebatch`,tempData,isloading);
    }
}
