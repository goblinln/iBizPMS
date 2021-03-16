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
    };

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
        if (_context.product && _context.story && _context.case && _context.testresult) {
            return this.http.get(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/testresults/${_context.testresult}/select`);
        }
        if (_context.story && _context.case && _context.testresult) {
            return this.http.get(`/stories/${_context.story}/cases/${_context.case}/testresults/${_context.testresult}/select`);
        }
        if (_context.product && _context.case && _context.testresult) {
            return this.http.get(`/products/${_context.product}/cases/${_context.case}/testresults/${_context.testresult}/select`);
        }
        if (_context.case && _context.testresult) {
            return this.http.get(`/cases/${_context.case}/testresults/${_context.testresult}/select`);
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
        if (_context.product && _context.story && _context.case && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/testresults`, _data);
        }
        if (_context.story && _context.case && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/testresults`, _data);
        }
        if (_context.product && _context.case && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/testresults`, _data);
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
        if (_context.product && _context.story && _context.case && _context.testresult) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/testresults/${_context.testresult}`, _data);
        }
        if (_context.story && _context.case && _context.testresult) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/stories/${_context.story}/cases/${_context.case}/testresults/${_context.testresult}`, _data);
        }
        if (_context.product && _context.case && _context.testresult) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/cases/${_context.case}/testresults/${_context.testresult}`, _data);
        }
        if (_context.case && _context.testresult) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/cases/${_context.case}/testresults/${_context.testresult}`, _data);
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
        if (_context.product && _context.story && _context.case && _context.testresult) {
            return this.http.delete(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/testresults/${_context.testresult}`);
        }
        if (_context.story && _context.case && _context.testresult) {
            return this.http.delete(`/stories/${_context.story}/cases/${_context.case}/testresults/${_context.testresult}`);
        }
        if (_context.product && _context.case && _context.testresult) {
            return this.http.delete(`/products/${_context.product}/cases/${_context.case}/testresults/${_context.testresult}`);
        }
        if (_context.case && _context.testresult) {
            return this.http.delete(`/cases/${_context.case}/testresults/${_context.testresult}`);
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
        if (_context.product && _context.story && _context.case && _context.testresult) {
            const res = await this.http.get(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/testresults/${_context.testresult}`);
            return res;
        }
        if (_context.story && _context.case && _context.testresult) {
            const res = await this.http.get(`/stories/${_context.story}/cases/${_context.case}/testresults/${_context.testresult}`);
            return res;
        }
        if (_context.product && _context.case && _context.testresult) {
            const res = await this.http.get(`/products/${_context.product}/cases/${_context.case}/testresults/${_context.testresult}`);
            return res;
        }
        if (_context.case && _context.testresult) {
            const res = await this.http.get(`/cases/${_context.case}/testresults/${_context.testresult}`);
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
        if (_context.product && _context.story && _context.case && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/testresults/getdraft`, _data);
            return res;
        }
        if (_context.story && _context.case && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/stories/${_context.story}/cases/${_context.case}/testresults/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.case && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/cases/${_context.case}/testresults/getdraft`, _data);
            return res;
        }
        if (_context.case && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/cases/${_context.case}/testresults/getdraft`, _data);
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
        if (_context.product && _context.story && _context.case && true) {
            return this.http.get(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/testresults/fetchcurtestrun`, _data);
        }
        if (_context.story && _context.case && true) {
            return this.http.get(`/stories/${_context.story}/cases/${_context.case}/testresults/fetchcurtestrun`, _data);
        }
        if (_context.product && _context.case && true) {
            return this.http.get(`/products/${_context.product}/cases/${_context.case}/testresults/fetchcurtestrun`, _data);
        }
        if (_context.case && true) {
            return this.http.get(`/cases/${_context.case}/testresults/fetchcurtestrun`, _data);
        }
        return this.http.get(`/testresults/fetchcurtestrun`, _data);
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
        if (_context.product && _context.story && _context.case && true) {
            return this.http.get(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/testresults/fetchdefault`, _data);
        }
        if (_context.story && _context.case && true) {
            return this.http.get(`/stories/${_context.story}/cases/${_context.case}/testresults/fetchdefault`, _data);
        }
        if (_context.product && _context.case && true) {
            return this.http.get(`/products/${_context.product}/cases/${_context.case}/testresults/fetchdefault`, _data);
        }
        if (_context.case && true) {
            return this.http.get(`/cases/${_context.case}/testresults/fetchdefault`, _data);
        }
        return this.http.get(`/testresults/fetchdefault`, _data);
    }
}
