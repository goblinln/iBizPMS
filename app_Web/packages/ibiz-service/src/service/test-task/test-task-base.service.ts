import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ITestTask, TestTask } from '../../entities';
import keys from '../../entities/test-task/test-task-keys';
import { GetCurUserConcatLogic } from '../../logic/entity/test-task/get-cur-user-concat/get-cur-user-concat-logic';

/**
 * 测试版本服务对象基类
 *
 * @export
 * @class TestTaskBaseService
 * @extends {EntityBaseService}
 */
export class TestTaskBaseService extends EntityBaseService<ITestTask> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'TestTask';
    protected APPDENAMEPLURAL = 'TestTasks';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
        product: 'product',
        project: 'project',
    };

    newEntity(data: ITestTask): TestTask {
        return new TestTask(data);
    }

    async addLocal(context: IContext, entity: ITestTask): Promise<ITestTask | null> {
        return this.cache.add(context, new TestTask(entity) as any);
    }

    async createLocal(context: IContext, entity: ITestTask): Promise<ITestTask | null> {
        return super.createLocal(context, new TestTask(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ITestTask> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.product && entity.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(context, entity.product);
            if (data) {
                entity.productname = data.name;
                entity.product = data.id;
            }
        }
        if (entity && entity.project && entity.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(context, entity.project);
            if (data) {
                entity.projecttname = data.name;
                entity.project = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: ITestTask): Promise<ITestTask> {
        return super.updateLocal(context, new TestTask(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ITestTask = {}): Promise<ITestTask> {
        if (_context.product && _context.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(_context, _context.product);
            if (data) {
                entity.productname = data.name;
                entity.product = data.id;
            }
        }
        if (_context.project && _context.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(_context, _context.project);
            if (data) {
                entity.projecttname = data.name;
                entity.project = data.id;
            }
        }
        return new TestTask(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestTaskService
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
     * @memberof TestTaskService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask) {
            return this.http.get(`/projects/${_context.project}/testtasks/${_context.testtask}/select`);
        }
        if (_context.product && _context.testtask) {
            return this.http.get(`/products/${_context.product}/testtasks/${_context.testtask}/select`);
        }
        return this.http.get(`/testtasks/${_context.testtask}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestTaskService
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
            return this.http.post(`/projects/${_context.project}/testtasks`, _data);
        }
        if (_context.product && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/testtasks`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/testtasks`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestTaskService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/testtasks/${_context.testtask}`, _data);
        }
        if (_context.product && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/testtasks/${_context.testtask}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/testtasks/${_context.testtask}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestTaskService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask) {
            return this.http.delete(`/projects/${_context.project}/testtasks/${_context.testtask}`);
        }
        if (_context.product && _context.testtask) {
            return this.http.delete(`/products/${_context.product}/testtasks/${_context.testtask}`);
        }
        return this.http.delete(`/testtasks/${_context.testtask}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestTaskService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask) {
            const res = await this.http.get(`/projects/${_context.project}/testtasks/${_context.testtask}`);
            return res;
        }
        if (_context.product && _context.testtask) {
            const res = await this.http.get(`/products/${_context.product}/testtasks/${_context.testtask}`);
            return res;
        }
        const res = await this.http.get(`/testtasks/${_context.testtask}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestTaskService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/testtasks/getdraft`, _data);
            return res;
        }
        if (_context.product && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/testtasks/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/testtasks/getdraft`, _data);
        return res;
    }
    /**
     * Activate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestTaskService
     */
    async Activate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/activate`, _data);
        }
        if (_context.product && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/activate`, _data);
        }
        return this.http.post(`/testtasks/${_context.testtask}/activate`, _data);
    }
    /**
     * Block
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestTaskService
     */
    async Block(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/block`, _data);
        }
        if (_context.product && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/block`, _data);
        }
        return this.http.post(`/testtasks/${_context.testtask}/block`, _data);
    }
    /**
     * Close
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestTaskService
     */
    async Close(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/close`, _data);
        }
        if (_context.product && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/close`, _data);
        }
        return this.http.post(`/testtasks/${_context.testtask}/close`, _data);
    }
    /**
     * LinkCase
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestTaskService
     */
    async LinkCase(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/linkcase`, _data);
        }
        if (_context.product && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/linkcase`, _data);
        }
        return this.http.post(`/testtasks/${_context.testtask}/linkcase`, _data);
    }
    /**
     * MobTestTaskCounter
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestTaskService
     */
    async MobTestTaskCounter(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/mobtesttaskcounter`, _data);
        }
        if (_context.product && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/mobtesttaskcounter`, _data);
        }
        return this.http.post(`/testtasks/${_context.testtask}/mobtesttaskcounter`, _data);
    }
    /**
     * Start
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestTaskService
     */
    async Start(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/start`, _data);
        }
        if (_context.product && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/start`, _data);
        }
        return this.http.post(`/testtasks/${_context.testtask}/start`, _data);
    }
    /**
     * UnlinkCase
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestTaskService
     */
    async UnlinkCase(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/unlinkcase`, _data);
        }
        if (_context.product && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/unlinkcase`, _data);
        }
        return this.http.post(`/testtasks/${_context.testtask}/unlinkcase`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestTaskService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/testtasks/fetchdefault`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/testtasks/fetchdefault`, _data);
        }
        return this.http.post(`/testtasks/fetchdefault`, _data);
    }
    /**
     * FetchMyTestTaskPc
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestTaskService
     */
    async FetchMyTestTaskPc(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.get(`/projects/${_context.project}/testtasks/fetchmytesttaskpc`, _data);
        }
        if (_context.product && true) {
            return this.http.get(`/products/${_context.product}/testtasks/fetchmytesttaskpc`, _data);
        }
        return this.http.get(`/testtasks/fetchmytesttaskpc`, _data);
    }
    /**
     * GetUserConcat
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestTaskService
     */
    async GetUserConcat(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const appLogic = new GetCurUserConcatLogic(_context, _data);
        _data = await appLogic.onExecute();
        return new HttpResponse(_data);
    }
}