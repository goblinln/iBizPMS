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
        return entity!;
    }

    async updateLocal(context: IContext, entity: ITestTask): Promise<ITestTask> {
        return super.updateLocal(context, new TestTask(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ITestTask = {}): Promise<ITestTask> {
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
        return this.http.post(`/testtasks/fetchmytesttaskpc`, _data);
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
     * ActivateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestTaskServiceBase
     */
    public async ActivateBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/testtasks/activatebatch`,_data);
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
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/testtasks/blockbatch`,_data);
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
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/testtasks/closebatch`,_data);
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
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/testtasks/linkcasebatch`,_data);
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
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/testtasks/startbatch`,_data);
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
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/testtasks/unlinkcasebatch`,_data);
    }
}
