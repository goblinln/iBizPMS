import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ITestTask, TestTask } from '../../entities';
import keys from '../../entities/test-task/test-task-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

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
    protected APPNAME = 'Mob';
    protected APPDENAME = 'TestTask';
    protected APPDENAMEPLURAL = 'TestTasks';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Mob/PSAPPDATAENTITIES/TestTask.json';
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
     * Activate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestTaskService
     */
    async Activate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Activate');
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/testtasks/${_context.testtask}/activate`, _data);
            return res;
        }
        if (_context.project && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Activate');
            const res = await this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/activate`, _data);
            return res;
        }
        if (_context.test && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Activate');
            const res = await this.http.post(`/tests/${_context.test}/testtasks/${_context.testtask}/activate`, _data);
            return res;
        }
    this.log.warn([`[TestTask]>>>[Activate函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Block');
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/testtasks/${_context.testtask}/block`, _data);
            return res;
        }
        if (_context.project && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Block');
            const res = await this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/block`, _data);
            return res;
        }
        if (_context.test && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Block');
            const res = await this.http.post(`/tests/${_context.test}/testtasks/${_context.testtask}/block`, _data);
            return res;
        }
    this.log.warn([`[TestTask]>>>[Block函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Close');
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/testtasks/${_context.testtask}/close`, _data);
            return res;
        }
        if (_context.project && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Close');
            const res = await this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/close`, _data);
            return res;
        }
        if (_context.test && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Close');
            const res = await this.http.post(`/tests/${_context.test}/testtasks/${_context.testtask}/close`, _data);
            return res;
        }
    this.log.warn([`[TestTask]>>>[Close函数]异常`]);
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
     * @memberof TestTaskService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/testtasks`, _data);
            return res;
        }
        if (_context.project && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/projects/${_context.project}/testtasks`, _data);
            return res;
        }
        if (_context.test && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/tests/${_context.test}/testtasks`, _data);
            return res;
        }
    this.log.warn([`[TestTask]>>>[Create函数]异常`]);
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
     * @memberof TestTaskService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.testtask) {
            const res = await this.http.get(`/products/${_context.product}/projects/${_context.project}/testtasks/${_context.testtask}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.project && _context.testtask) {
            const res = await this.http.get(`/projects/${_context.project}/testtasks/${_context.testtask}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.test && _context.testtask) {
            const res = await this.http.get(`/tests/${_context.test}/testtasks/${_context.testtask}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
    this.log.warn([`[TestTask]>>>[Get函数]异常`]);
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
     * @memberof TestTaskService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/projects/${_context.project}/testtasks/getdraft`, _data);
            return res;
        }
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
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
            _data =  await this.executeAppDELogic('GetCurUserConcat',_context,_data);
            return new HttpResponse(_data, {
                ok: true,
                status: 200
            });
        }catch (error) {
            return new HttpResponse({message:error.message}, {
                ok: false,
                status: 500,
            });
        }
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
        try {
        if (_context.product && _context.project && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'LinkCase');
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/testtasks/${_context.testtask}/linkcase`, _data);
            return res;
        }
        if (_context.project && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'LinkCase');
            const res = await this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/linkcase`, _data);
            return res;
        }
        if (_context.test && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'LinkCase');
            const res = await this.http.post(`/tests/${_context.test}/testtasks/${_context.testtask}/linkcase`, _data);
            return res;
        }
    this.log.warn([`[TestTask]>>>[LinkCase函数]异常`]);
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
     * @memberof TestTaskService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.testtask) {
            const res = await this.http.delete(`/products/${_context.product}/projects/${_context.project}/testtasks/${_context.testtask}`);
            return res;
        }
        if (_context.project && _context.testtask) {
            const res = await this.http.delete(`/projects/${_context.project}/testtasks/${_context.testtask}`);
            return res;
        }
        if (_context.test && _context.testtask) {
            const res = await this.http.delete(`/tests/${_context.test}/testtasks/${_context.testtask}`);
            return res;
        }
    this.log.warn([`[TestTask]>>>[Remove函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Start');
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/testtasks/${_context.testtask}/start`, _data);
            return res;
        }
        if (_context.project && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Start');
            const res = await this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/start`, _data);
            return res;
        }
        if (_context.test && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Start');
            const res = await this.http.post(`/tests/${_context.test}/testtasks/${_context.testtask}/start`, _data);
            return res;
        }
    this.log.warn([`[TestTask]>>>[Start函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'UnlinkCase');
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/testtasks/${_context.testtask}/unlinkcase`, _data);
            return res;
        }
        if (_context.project && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'UnlinkCase');
            const res = await this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/unlinkcase`, _data);
            return res;
        }
        if (_context.test && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'UnlinkCase');
            const res = await this.http.post(`/tests/${_context.test}/testtasks/${_context.testtask}/unlinkcase`, _data);
            return res;
        }
    this.log.warn([`[TestTask]>>>[UnlinkCase函数]异常`]);
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
     * @memberof TestTaskService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/products/${_context.product}/projects/${_context.project}/testtasks/${_context.testtask}`, _data);
            return res;
        }
        if (_context.project && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/projects/${_context.project}/testtasks/${_context.testtask}`, _data);
            return res;
        }
        if (_context.test && _context.testtask) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/tests/${_context.test}/testtasks/${_context.testtask}`, _data);
            return res;
        }
    this.log.warn([`[TestTask]>>>[Update函数]异常`]);
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
     * @memberof TestTaskService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && true) {
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/testtasks/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.project && true) {
            const res = await this.http.post(`/projects/${_context.project}/testtasks/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.test && true) {
            const res = await this.http.post(`/tests/${_context.test}/testtasks/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
    this.log.warn([`[TestTask]>>>[FetchDefault函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && true) {
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/testtasks/fetchprojecttesttask`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProjectTestTask');
            return res;
        }
        if (_context.project && true) {
            const res = await this.http.post(`/projects/${_context.project}/testtasks/fetchprojecttesttask`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProjectTestTask');
            return res;
        }
        if (_context.test && true) {
            const res = await this.http.post(`/tests/${_context.test}/testtasks/fetchprojecttesttask`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProjectTestTask');
            return res;
        }
    this.log.warn([`[TestTask]>>>[FetchProjectTestTask函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        if(_context.product && _context.project && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/testtasks/activatebatch`,_data);
            return res;
        }
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/projects/${_context.project}/testtasks/activatebatch`,_data);
            return res;
        }
        if(_context.test && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/tests/${_context.test}/testtasks/activatebatch`,_data);
            return res;
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
        if(_context.product && _context.project && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/testtasks/blockbatch`,_data);
            return res;
        }
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/projects/${_context.project}/testtasks/blockbatch`,_data);
            return res;
        }
        if(_context.test && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/tests/${_context.test}/testtasks/blockbatch`,_data);
            return res;
        }
        this.log.warn([`[TestTask]>>>[BlockBatch函数]异常`]);
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
        if(_context.product && _context.project && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/testtasks/closebatch`,_data);
            return res;
        }
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/projects/${_context.project}/testtasks/closebatch`,_data);
            return res;
        }
        if(_context.test && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/tests/${_context.test}/testtasks/closebatch`,_data);
            return res;
        }
        this.log.warn([`[TestTask]>>>[CloseBatch函数]异常`]);
        return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
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
        if(_context.product && _context.project && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/testtasks/linkcasebatch`,_data);
            return res;
        }
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/projects/${_context.project}/testtasks/linkcasebatch`,_data);
            return res;
        }
        if(_context.test && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/tests/${_context.test}/testtasks/linkcasebatch`,_data);
            return res;
        }
        this.log.warn([`[TestTask]>>>[LinkCaseBatch函数]异常`]);
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
        if(_context.product && _context.project && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/testtasks/startbatch`,_data);
            return res;
        }
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/projects/${_context.project}/testtasks/startbatch`,_data);
            return res;
        }
        if(_context.test && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/tests/${_context.test}/testtasks/startbatch`,_data);
            return res;
        }
        this.log.warn([`[TestTask]>>>[StartBatch函数]异常`]);
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
        if(_context.product && _context.project && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/testtasks/unlinkcasebatch`,_data);
            return res;
        }
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/projects/${_context.project}/testtasks/unlinkcasebatch`,_data);
            return res;
        }
        if(_context.test && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/tests/${_context.test}/testtasks/unlinkcasebatch`,_data);
            return res;
        }
        this.log.warn([`[TestTask]>>>[UnlinkCaseBatch函数]异常`]);
        return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
    }
}
