import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ITestCase, TestCase } from '../../entities';
import keys from '../../entities/test-case/test-case-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';
import { GetCaseStepByIdVersionLogic } from '../../logic/entity/test-case/get-case-step-by-id-version/get-case-step-by-id-version-logic';

/**
 * 测试用例服务对象基类
 *
 * @export
 * @class TestCaseBaseService
 * @extends {EntityBaseService}
 */
export class TestCaseBaseService extends EntityBaseService<ITestCase> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Mob';
    protected APPDENAME = 'TestCase';
    protected APPDENAMEPLURAL = 'TestCases';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'title';
    protected quickSearchFields = ['title',];
    protected selectContextParam = {
        test: 'product',
    };

    newEntity(data: ITestCase): TestCase {
        return new TestCase(data);
    }

    async addLocal(context: IContext, entity: ITestCase): Promise<ITestCase | null> {
        return this.cache.add(context, new TestCase(entity) as any);
    }

    async createLocal(context: IContext, entity: ITestCase): Promise<ITestCase | null> {
        return super.createLocal(context, new TestCase(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ITestCase> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.product && entity.product !== '') {
            const s = await ___ibz___.gs.getTestService();
            const data = await s.getLocal2(context, entity.product);
            if (data) {
                entity.productname = data.name;
                entity.product = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: ITestCase): Promise<ITestCase> {
        return super.updateLocal(context, new TestCase(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ITestCase = {}): Promise<ITestCase> {
        if (_context.test && _context.test !== '') {
            const s = await ___ibz___.gs.getTestService();
            const data = await s.getLocal2(_context, _context.test);
            if (data) {
                entity.productname = data.name;
                entity.product = data.id;
            }
        }
        return new TestCase(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestCaseService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }

    protected getBatchNewCond() {
        if (!this.condCache.has('batchNew')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('batchNew', cond);
            }
        }
        return this.condCache.get('batchNew');
    }

    protected getCurOpenedCaseCond() {
        if (!this.condCache.has('curOpenedCase')) {
            const strCond: any[] = ['AND', ['EQ', 'OPENEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('curOpenedCase', cond);
            }
        }
        return this.condCache.get('curOpenedCase');
    }

    protected getCurSuiteCond() {
        return this.condCache.get('curSuite');
    }

    protected getCurTestTaskCond() {
        return this.condCache.get('curTestTask');
    }

    protected getDefaultCond() {
        return this.condCache.get('default');
    }

    protected getESBulkCond() {
        return this.condCache.get('eSBulk');
    }

    protected getModuleRePortCaseCond() {
        if (!this.condCache.has('moduleRePortCase')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('moduleRePortCase', cond);
            }
        }
        return this.condCache.get('moduleRePortCase');
    }

    protected getModuleRePortCaseEntryCond() {
        if (!this.condCache.has('moduleRePortCaseEntry')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('moduleRePortCaseEntry', cond);
            }
        }
        return this.condCache.get('moduleRePortCaseEntry');
    }

    protected getModuleRePortCase_ProjectCond() {
        if (!this.condCache.has('moduleRePortCase_Project')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('moduleRePortCase_Project', cond);
            }
        }
        return this.condCache.get('moduleRePortCase_Project');
    }

    protected getMyCreateOrUpdateCond() {
        if (!this.condCache.has('myCreateOrUpdate')) {
            const strCond: any[] = ['AND', ['OR', ['EQ', 'OPENEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'REVIEWEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'LASTEDITEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}]]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('myCreateOrUpdate', cond);
            }
        }
        return this.condCache.get('myCreateOrUpdate');
    }

    protected getMyFavoriteCond() {
        if (!this.condCache.has('myFavorite')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('myFavorite', cond);
            }
        }
        return this.condCache.get('myFavorite');
    }

    protected getMyReProductCond() {
        if (!this.condCache.has('myReProduct')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('myReProduct', cond);
            }
        }
        return this.condCache.get('myReProduct');
    }

    protected getNotCurTestSuiteCond() {
        return this.condCache.get('notCurTestSuite');
    }

    protected getNotCurTestTaskCond() {
        return this.condCache.get('notCurTestTask');
    }

    protected getNotCurTestTaskProjectCond() {
        return this.condCache.get('notCurTestTaskProject');
    }

    protected getRePortCaseCond() {
        if (!this.condCache.has('rePortCase')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('rePortCase', cond);
            }
        }
        return this.condCache.get('rePortCase');
    }

    protected getRePortCaseEntryCond() {
        if (!this.condCache.has('rePortCaseEntry')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('rePortCaseEntry', cond);
            }
        }
        return this.condCache.get('rePortCaseEntry');
    }

    protected getRePortCase_ProjectCond() {
        if (!this.condCache.has('rePortCase_Project')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('rePortCase_Project', cond);
            }
        }
        return this.condCache.get('rePortCase_Project');
    }

    protected getRunERRePortCaseCond() {
        if (!this.condCache.has('runERRePortCase')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('runERRePortCase', cond);
            }
        }
        return this.condCache.get('runERRePortCase');
    }

    protected getRunERRePortCaseEntryCond() {
        if (!this.condCache.has('runERRePortCaseEntry')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('runERRePortCaseEntry', cond);
            }
        }
        return this.condCache.get('runERRePortCaseEntry');
    }

    protected getRunERRePortCase_ProjectCond() {
        if (!this.condCache.has('runERRePortCase_Project')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('runERRePortCase_Project', cond);
            }
        }
        return this.condCache.get('runERRePortCase_Project');
    }

    protected getRunRePortCaseCond() {
        if (!this.condCache.has('runRePortCase')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('runRePortCase', cond);
            }
        }
        return this.condCache.get('runRePortCase');
    }

    protected getRunRePortCaseEntryCond() {
        if (!this.condCache.has('runRePortCaseEntry')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('runRePortCaseEntry', cond);
            }
        }
        return this.condCache.get('runRePortCaseEntry');
    }

    protected getRunRePortCase_ProjectCond() {
        if (!this.condCache.has('runRePortCase_Project')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('runRePortCase_Project', cond);
            }
        }
        return this.condCache.get('runRePortCase_Project');
    }

    protected getViewCond() {
        return this.condCache.get('view');
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestCaseService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.test && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/tests/${_context.test}/testcases/getdraft`, _data);
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
     * @memberof TestCaseService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.test && _context.testcase) {
            const res = await this.http.get(`/tests/${_context.test}/testcases/${_context.testcase}`);
            return res;
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestCaseService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.test && _context.testcase) {
            return this.http.delete(`/tests/${_context.test}/testcases/${_context.testcase}`);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestCaseService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.test && _context.testcase) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/tests/${_context.test}/testcases/${_context.testcase}`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestCaseService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.test && true) {
            return this.http.post(`/tests/${_context.test}/testcases/fetchdefault`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestCaseService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.test && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/tests/${_context.test}/testcases`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
}
