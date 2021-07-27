import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ITestCase, TestCase } from '../../entities';
import keys from '../../entities/test-case/test-case-keys';
import { clone, mergeDeepLeft } from 'ramda';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

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
    protected APPNAME = 'Web';
    protected APPDENAME = 'TestCase';
    protected APPDENAMEPLURAL = 'TestCases';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/TestCase.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'title';
    protected quickSearchFields = ['title',];
    protected selectContextParam = {
        test: 'product',
    };

    constructor(opts?: any) {
        super(opts, 'TestCase');
    }

    newEntity(data: ITestCase): TestCase {
        return new TestCase(data);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ITestCase> {
        const entity = await super.getLocal(context, srfKey);
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

    protected async fillMinor(_context: IContext, _data: ITestCase): Promise<any> {
        if (_data.testcasestepnesteds) {
            await this.setMinorLocal('TestCaseStepNested', _context, _data.testcasestepnesteds);
            delete _data.testcasestepnesteds;
        }
        this.addLocal(_context, _data);
        return _data;
    }

    protected async obtainMinor(_context: IContext, _data: ITestCase = new TestCase()): Promise<ITestCase> {
        const res = await this.GetTemp(_context, _data);
        if (res.ok) {
            _data = mergeDeepLeft(_data, this.filterEntityData(res.data)) as any;
        }
        const testcasestepnestedsList = await this.getMinorLocal('TestCaseStepNested', _context, { ibizcase: _data.id });
        if (testcasestepnestedsList?.length > 0) {
            _data.testcasestepnesteds = testcasestepnestedsList;
        } else {
           _data.testcasestepnesteds = [];
        }
        return _data;
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
        const oldData = clone(data);
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
            {
                let items: any[] = [];
                const s = await ___ibz___.gs.getTestCaseStepNestedService();
                items = await s.selectLocal(context, { ibizcase: oldData.id });
                if (items) {
                    for (let i = 0; i < items.length; i++) {
                        const item = items[i];
                        const res = await s.DeepCopyTemp({ ...context, testcase: entity.srfkey }, item);
                        if (!res.ok) {
                            throw new Error(
                                `「TestCase(${oldData.srfkey})」关联实体「TestCaseStepNested(${item.srfkey})」拷贝失败。`,
                            );
                        }
                    }
                }
            }
        }
        return new HttpResponse(entity);
    }

    protected getAccountCond() {
        return this.condCache.get('account');
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

    protected getMyCond() {
        return this.condCache.get('my');
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
     * CaseFavorite
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestCaseService
     */
    async CaseFavorite(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.test && _context.testcase) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'CaseFavorite');
            const res = await this.http.post(`/tests/${_context.test}/testcases/${_context.testcase}/casefavorite`, _data);
            return res;
        }
    this.log.warn([`[TestCase]>>>[CaseFavorite函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * CaseNFavorite
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestCaseService
     */
    async CaseNFavorite(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.test && _context.testcase) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'CaseNFavorite');
            const res = await this.http.post(`/tests/${_context.test}/testcases/${_context.testcase}/casenfavorite`, _data);
            return res;
        }
    this.log.warn([`[TestCase]>>>[CaseNFavorite函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.test && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/tests/${_context.test}/testcases`, _data);
            return res;
        }
    this.log.warn([`[TestCase]>>>[Create函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.test && _context.testcase) {
            const res = await this.http.get(`/tests/${_context.test}/testcases/${_context.testcase}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
            return res;
        }
    this.log.warn([`[TestCase]>>>[Get函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.test && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/tests/${_context.test}/testcases/getdraft`, _data);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
            return res;
        }
    this.log.warn([`[TestCase]>>>[GetDraft函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.test && _context.testcase) {
            const res = await this.http.delete(`/tests/${_context.test}/testcases/${_context.testcase}`);
            return res;
        }
    this.log.warn([`[TestCase]>>>[Remove函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * RunCases
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestCaseService
     */
    async RunCases(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.test && _context.testcase) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'RunCases');
            const res = await this.http.post(`/tests/${_context.test}/testcases/${_context.testcase}/runcases`, _data);
            return res;
        }
    this.log.warn([`[TestCase]>>>[RunCases函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * TestRunCases
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestCaseService
     */
    async TestRunCases(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.test && _context.testcase) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'TestRunCases');
            const res = await this.http.post(`/tests/${_context.test}/testcases/${_context.testcase}/testruncases`, _data);
            return res;
        }
    this.log.warn([`[TestCase]>>>[TestRunCases函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.test && _context.testcase) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/tests/${_context.test}/testcases/${_context.testcase}`, _data);
            return res;
        }
    this.log.warn([`[TestCase]>>>[Update函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * testsuitelinkCase
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestCaseService
     */
    async testsuitelinkCase(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.test && _context.testcase) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'testsuitelinkCase');
            const res = await this.http.post(`/tests/${_context.test}/testcases/${_context.testcase}/testsuitelinkcase`, _data);
            return res;
        }
    this.log.warn([`[TestCase]>>>[testsuitelinkCase函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchCurSuite
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestCaseService
     */
    async FetchCurSuite(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.test && true) {
            const res = await this.http.post(`/tests/${_context.test}/testcases/fetchcursuite`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchCurSuite');
            return res;
        }
    this.log.warn([`[TestCase]>>>[FetchCurSuite函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchCurTestTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestCaseService
     */
    async FetchCurTestTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.test && true) {
            const res = await this.http.post(`/tests/${_context.test}/testcases/fetchcurtesttask`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchCurTestTask');
            return res;
        }
    this.log.warn([`[TestCase]>>>[FetchCurTestTask函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.test && true) {
            const res = await this.http.post(`/tests/${_context.test}/testcases/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
    this.log.warn([`[TestCase]>>>[FetchDefault函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchNotCurTestSuite
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestCaseService
     */
    async FetchNotCurTestSuite(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.test && true) {
            const res = await this.http.post(`/tests/${_context.test}/testcases/fetchnotcurtestsuite`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchNotCurTestSuite');
            return res;
        }
    this.log.warn([`[TestCase]>>>[FetchNotCurTestSuite函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchNotCurTestTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestCaseService
     */
    async FetchNotCurTestTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.test && true) {
            const res = await this.http.post(`/tests/${_context.test}/testcases/fetchnotcurtesttask`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchNotCurTestTask');
            return res;
        }
    this.log.warn([`[TestCase]>>>[FetchNotCurTestTask函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }

    /**
     * testsuitelinkCaseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestCaseServiceBase
     */
    public async testsuitelinkCaseBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.test && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/tests/${_context.test}/testcases/testsuitelinkcasebatch`,_data);
            return res;
        }
        this.log.warn([`[TestCase]>>>[testsuitelinkCaseBatch函数]异常`]);
        return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
    }
}
