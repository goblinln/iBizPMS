import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ITestRun, TestRun } from '../../entities';
import keys from '../../entities/test-run/test-run-keys';

/**
 * 测试运行服务对象基类
 *
 * @export
 * @class TestRunBaseService
 * @extends {EntityBaseService}
 */
export class TestRunBaseService extends EntityBaseService<ITestRun> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'TestRun';
    protected APPDENAMEPLURAL = 'TestRuns';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'id';
    protected quickSearchFields = ['id',];
    protected selectContextParam = {
        testtask: 'task',
    };

    newEntity(data: ITestRun): TestRun {
        return new TestRun(data);
    }

    async addLocal(context: IContext, entity: ITestRun): Promise<ITestRun | null> {
        return this.cache.add(context, new TestRun(entity) as any);
    }

    async createLocal(context: IContext, entity: ITestRun): Promise<ITestRun | null> {
        return super.createLocal(context, new TestRun(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ITestRun> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.task && entity.task !== '') {
            const s = await ___ibz___.gs.getTestTaskService();
            const data = await s.getLocal2(context, entity.task);
            if (data) {
                entity.task = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: ITestRun): Promise<ITestRun> {
        return super.updateLocal(context, new TestRun(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ITestRun = {}): Promise<ITestRun> {
        if (_context.testtask && _context.testtask !== '') {
            const s = await ___ibz___.gs.getTestTaskService();
            const data = await s.getLocal2(_context, _context.testtask);
            if (data) {
                entity.task = data.id;
            }
        }
        return new TestRun(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestRunService
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
     * @memberof TestRunService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask && _context.testrun) {
            return this.http.get(`/projects/${_context.project}/testtasks/${_context.testtask}/testruns/${_context.testrun}/select`);
        }
        if (_context.product && _context.testtask && _context.testrun) {
            return this.http.get(`/products/${_context.product}/testtasks/${_context.testtask}/testruns/${_context.testrun}/select`);
        }
        if (_context.testtask && _context.testrun) {
            return this.http.get(`/testtasks/${_context.testtask}/testruns/${_context.testrun}/select`);
        }
        return this.http.get(`/testruns/${_context.testrun}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestRunService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/testruns`, _data);
        }
        if (_context.product && _context.testtask && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/testruns`, _data);
        }
        if (_context.testtask && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/testtasks/${_context.testtask}/testruns`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/testruns`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestRunService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask && _context.testrun) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/testtasks/${_context.testtask}/testruns/${_context.testrun}`, _data);
        }
        if (_context.product && _context.testtask && _context.testrun) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/testtasks/${_context.testtask}/testruns/${_context.testrun}`, _data);
        }
        if (_context.testtask && _context.testrun) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/testtasks/${_context.testtask}/testruns/${_context.testrun}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/testruns/${_context.testrun}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestRunService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask && _context.testrun) {
            return this.http.delete(`/projects/${_context.project}/testtasks/${_context.testtask}/testruns/${_context.testrun}`);
        }
        if (_context.product && _context.testtask && _context.testrun) {
            return this.http.delete(`/products/${_context.product}/testtasks/${_context.testtask}/testruns/${_context.testrun}`);
        }
        if (_context.testtask && _context.testrun) {
            return this.http.delete(`/testtasks/${_context.testtask}/testruns/${_context.testrun}`);
        }
        return this.http.delete(`/testruns/${_context.testrun}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestRunService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask && _context.testrun) {
            const res = await this.http.get(`/projects/${_context.project}/testtasks/${_context.testtask}/testruns/${_context.testrun}`);
            return res;
        }
        if (_context.product && _context.testtask && _context.testrun) {
            const res = await this.http.get(`/products/${_context.product}/testtasks/${_context.testtask}/testruns/${_context.testrun}`);
            return res;
        }
        if (_context.testtask && _context.testrun) {
            const res = await this.http.get(`/testtasks/${_context.testtask}/testruns/${_context.testrun}`);
            return res;
        }
        const res = await this.http.get(`/testruns/${_context.testrun}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestRunService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/testtasks/${_context.testtask}/testruns/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.testtask && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/testtasks/${_context.testtask}/testruns/getdraft`, _data);
            return res;
        }
        if (_context.testtask && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/testtasks/${_context.testtask}/testruns/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/testruns/getdraft`, _data);
        return res;
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestRunService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask && true) {
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/testruns/fetchdefault`, _data);
        }
        if (_context.product && _context.testtask && true) {
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/testruns/fetchdefault`, _data);
        }
        if (_context.testtask && true) {
            return this.http.post(`/testtasks/${_context.testtask}/testruns/fetchdefault`, _data);
        }
        return this.http.post(`/testruns/fetchdefault`, _data);
    }
}