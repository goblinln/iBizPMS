import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ITestTask, TestTask } from '../../entities';
import keys from '../../entities/test-task/test-task-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';
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
        test: 'product',
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
            const s = await ___ibz___.gs.getTestService();
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
        if (_context.test && _context.test !== '') {
            const s = await ___ibz___.gs.getTestService();
            const data = await s.getLocal2(_context, _context.test);
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

    protected getAccountCond() {
        return this.condCache.get('account');
    }

    protected getDefaultCond() {
        return this.condCache.get('default');
    }

    protected getMyCond() {
        if (!this.condCache.has('my')) {
            const strCond: any[] = ['AND', ['OR', ['EQ', 'OWNER',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'CREATEBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'UPDATEBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}]]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('my', cond);
            }
        }
        return this.condCache.get('my');
    }

    protected getMyTestTaskPcCond() {
        return this.condCache.get('myTestTaskPc');
    }

    protected getSimpleCond() {
        return this.condCache.get('simple');
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
     * @memberof TestTaskService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/testtasks/getdraft`, _data);
            return res;
        }
        if (_context.test && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/tests/${_context.test}/testtasks/getdraft`, _data);
            return res;
        }
    this.log.warn([`[TestTask]>>>[GetDraft函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.test && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tests/${_context.test}/testtasks/${_context.testtask}/linkcase`, _data);
        }
    this.log.warn([`[TestTask]>>>[LinkCase函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.test && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tests/${_context.test}/testtasks/${_context.testtask}/close`, _data);
        }
    this.log.warn([`[TestTask]>>>[Close函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchProjectTestTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestTaskService
     */
    async FetchProjectTestTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/testtasks/fetchprojecttesttask`, _data);
        }
        if (_context.test && true) {
            return this.http.post(`/tests/${_context.test}/testtasks/fetchprojecttesttask`, _data);
        }
    this.log.warn([`[TestTask]>>>[FetchProjectTestTask函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.test && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/tests/${_context.test}/testtasks/${_context.testtask}`, _data);
        }
    this.log.warn([`[TestTask]>>>[Update函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.test && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tests/${_context.test}/testtasks/${_context.testtask}/activate`, _data);
        }
    this.log.warn([`[TestTask]>>>[Activate函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.test && _context.testtask) {
            const res = await this.http.get(`/tests/${_context.test}/testtasks/${_context.testtask}`);
            return res;
        }
    this.log.warn([`[TestTask]>>>[Get函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.test && true) {
            return this.http.post(`/tests/${_context.test}/testtasks/fetchdefault`, _data);
        }
    this.log.warn([`[TestTask]>>>[FetchDefault函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.test && _context.testtask) {
            return this.http.delete(`/tests/${_context.test}/testtasks/${_context.testtask}`);
        }
    this.log.warn([`[TestTask]>>>[Remove函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.test && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tests/${_context.test}/testtasks/${_context.testtask}/block`, _data);
        }
    this.log.warn([`[TestTask]>>>[Block函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.test && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/tests/${_context.test}/testtasks`, _data);
        }
    this.log.warn([`[TestTask]>>>[Create函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.test && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tests/${_context.test}/testtasks/${_context.testtask}/unlinkcase`, _data);
        }
    this.log.warn([`[TestTask]>>>[UnlinkCase函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
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
        if (_context.test && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tests/${_context.test}/testtasks/${_context.testtask}/start`, _data);
        }
    this.log.warn([`[TestTask]>>>[Start函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
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

    /**
     * LinkCaseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
     */
    public async LinkCaseBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/linkcasebatch`,_data);
        }
        if(_context.test && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tests/${_context.test}/testtasks/linkcasebatch`,_data);
        }
        this.log.warn([`[TestTask]>>>[LinkCaseBatch函数]异常`]);
        return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * CloseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
     */
    public async CloseBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/closebatch`,_data);
        }
        if(_context.test && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tests/${_context.test}/testtasks/closebatch`,_data);
        }
        this.log.warn([`[TestTask]>>>[CloseBatch函数]异常`]);
        return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * ActivateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
     */
    public async ActivateBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/activatebatch`,_data);
        }
        if(_context.test && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tests/${_context.test}/testtasks/activatebatch`,_data);
        }
        this.log.warn([`[TestTask]>>>[ActivateBatch函数]异常`]);
        return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * BlockBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
     */
    public async BlockBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/blockbatch`,_data);
        }
        if(_context.test && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tests/${_context.test}/testtasks/blockbatch`,_data);
        }
        this.log.warn([`[TestTask]>>>[BlockBatch函数]异常`]);
        return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * UnlinkCaseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
     */
    public async UnlinkCaseBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/unlinkcasebatch`,_data);
        }
        if(_context.test && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tests/${_context.test}/testtasks/unlinkcasebatch`,_data);
        }
        this.log.warn([`[TestTask]>>>[UnlinkCaseBatch函数]异常`]);
        return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * StartBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
     */
    public async StartBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/startbatch`,_data);
        }
        if(_context.test && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tests/${_context.test}/testtasks/startbatch`,_data);
        }
        this.log.warn([`[TestTask]>>>[StartBatch函数]异常`]);
        return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
    }
}
