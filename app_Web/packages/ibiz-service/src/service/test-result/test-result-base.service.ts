import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ITestResult, TestResult } from '../../entities';
import keys from '../../entities/test-result/test-result-keys';

/**
 * 测试结果服务对象基类
 *
 * @export
 * @class TestResultBaseService
 * @extends {EntityBaseService}
 */
export class TestResultBaseService extends EntityBaseService<ITestResult> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'TestResult';
    protected APPDENAMEPLURAL = 'TestResults';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'title';
    protected quickSearchFields = ['title',];
    protected selectContextParam = {
        case: 'ibizcase',
        testrun: 'run',
    };

    newEntity(data: ITestResult): TestResult {
        return new TestResult(data);
    }

    async addLocal(context: IContext, entity: ITestResult): Promise<ITestResult | null> {
        return this.cache.add(context, new TestResult(entity) as any);
    }

    async createLocal(context: IContext, entity: ITestResult): Promise<ITestResult | null> {
        return super.createLocal(context, new TestResult(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ITestResult> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.ibizcase && entity.ibizcase !== '') {
            const s = await ___ibz___.gs.getCaseService();
            const data = await s.getLocal2(context, entity.ibizcase);
            if (data) {
                entity.product = data.product;
                entity.ibizcase = data.id;
                entity.case = data;
            }
        }
        if (entity && entity.run && entity.run !== '') {
            const s = await ___ibz___.gs.getTestRunService();
            const data = await s.getLocal2(context, entity.run);
            if (data) {
                entity.run = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: ITestResult): Promise<ITestResult> {
        return super.updateLocal(context, new TestResult(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ITestResult = {}): Promise<ITestResult> {
        if (_context.case && _context.case !== '') {
            const s = await ___ibz___.gs.getCaseService();
            const data = await s.getLocal2(_context, _context.case);
            if (data) {
                entity.product = data.product;
                entity.ibizcase = data.id;
                entity.case = data;
            }
        }
        if (_context.testrun && _context.testrun !== '') {
            const s = await ___ibz___.gs.getTestRunService();
            const data = await s.getLocal2(_context, _context.testrun);
            if (data) {
                entity.run = data.id;
            }
        }
        return new TestResult(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestResultService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }
    /**
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestResultService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.testtask && _context.testrun && _context.testresult) {
            return this.http.get(`/testtasks/${_context.testtask}/testruns/${_context.testrun}/testresults/${_context.testresult}/select`);
        }
        if (_context.case && _context.testresult) {
            return this.http.get(`/cases/${_context.case}/testresults/${_context.testresult}/select`);
        }
        if (_context.testrun && _context.testresult) {
            return this.http.get(`/testruns/${_context.testrun}/testresults/${_context.testresult}/select`);
        }
        return this.http.get(`/testresults/${_context.testresult}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestResultService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.testtask && _context.testrun && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/testtasks/${_context.testtask}/testruns/${_context.testrun}/testresults`, _data);
        }
        if (_context.case && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/cases/${_context.case}/testresults`, _data);
        }
        if (_context.testrun && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/testruns/${_context.testrun}/testresults`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/testresults`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestResultService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.testtask && _context.testrun && _context.testresult) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/testtasks/${_context.testtask}/testruns/${_context.testrun}/testresults/${_context.testresult}`, _data);
        }
        if (_context.case && _context.testresult) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/cases/${_context.case}/testresults/${_context.testresult}`, _data);
        }
        if (_context.testrun && _context.testresult) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/testruns/${_context.testrun}/testresults/${_context.testresult}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/testresults/${_context.testresult}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestResultService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.testtask && _context.testrun && _context.testresult) {
            return this.http.delete(`/testtasks/${_context.testtask}/testruns/${_context.testrun}/testresults/${_context.testresult}`);
        }
        if (_context.case && _context.testresult) {
            return this.http.delete(`/cases/${_context.case}/testresults/${_context.testresult}`);
        }
        if (_context.testrun && _context.testresult) {
            return this.http.delete(`/testruns/${_context.testrun}/testresults/${_context.testresult}`);
        }
        return this.http.delete(`/testresults/${_context.testresult}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestResultService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.testtask && _context.testrun && _context.testresult) {
            const res = await this.http.get(`/testtasks/${_context.testtask}/testruns/${_context.testrun}/testresults/${_context.testresult}`);
            return res;
        }
        if (_context.case && _context.testresult) {
            const res = await this.http.get(`/cases/${_context.case}/testresults/${_context.testresult}`);
            return res;
        }
        if (_context.testrun && _context.testresult) {
            const res = await this.http.get(`/testruns/${_context.testrun}/testresults/${_context.testresult}`);
            return res;
        }
        const res = await this.http.get(`/testresults/${_context.testresult}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestResultService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.testtask && _context.testrun && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/testtasks/${_context.testtask}/testruns/${_context.testrun}/testresults/getdraft`, _data);
            return res;
        }
        if (_context.case && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/cases/${_context.case}/testresults/getdraft`, _data);
            return res;
        }
        if (_context.testrun && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/testruns/${_context.testrun}/testresults/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/testresults/getdraft`, _data);
        return res;
    }
    /**
     * FetchCurTestRun
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestResultService
     */
    async FetchCurTestRun(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.testtask && _context.testrun && true) {
            return this.http.post(`/testtasks/${_context.testtask}/testruns/${_context.testrun}/testresults/fetchcurtestrun`, _data);
        }
        if (_context.case && true) {
            return this.http.post(`/cases/${_context.case}/testresults/fetchcurtestrun`, _data);
        }
        if (_context.testrun && true) {
            return this.http.post(`/testruns/${_context.testrun}/testresults/fetchcurtestrun`, _data);
        }
        return this.http.post(`/testresults/fetchcurtestrun`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestResultService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.testtask && _context.testrun && true) {
            return this.http.post(`/testtasks/${_context.testtask}/testruns/${_context.testrun}/testresults/fetchdefault`, _data);
        }
        if (_context.case && true) {
            return this.http.post(`/cases/${_context.case}/testresults/fetchdefault`, _data);
        }
        if (_context.testrun && true) {
            return this.http.post(`/testruns/${_context.testrun}/testresults/fetchdefault`, _data);
        }
        return this.http.post(`/testresults/fetchdefault`, _data);
    }
}
