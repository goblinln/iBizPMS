import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProjectTestTask, ProjectTestTask } from '../../entities';
import keys from '../../entities/project-test-task/project-test-task-keys';
import { GetCurUserConcatLogic } from '../../logic/entity/project-test-task/get-cur-user-concat/get-cur-user-concat-logic';

/**
 * 测试版本服务对象基类
 *
 * @export
 * @class ProjectTestTaskBaseService
 * @extends {EntityBaseService}
 */
export class ProjectTestTaskBaseService extends EntityBaseService<IProjectTestTask> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'ProjectTestTask';
    protected APPDENAMEPLURAL = 'ProjectTestTasks';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
        project: 'project',
    };

    newEntity(data: IProjectTestTask): ProjectTestTask {
        return new ProjectTestTask(data);
    }

    async addLocal(context: IContext, entity: IProjectTestTask): Promise<IProjectTestTask | null> {
        return this.cache.add(context, new ProjectTestTask(entity) as any);
    }

    async createLocal(context: IContext, entity: IProjectTestTask): Promise<IProjectTestTask | null> {
        return super.createLocal(context, new ProjectTestTask(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProjectTestTask> {
        const entity = this.cache.get(context, srfKey);
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

    async updateLocal(context: IContext, entity: IProjectTestTask): Promise<IProjectTestTask> {
        return super.updateLocal(context, new ProjectTestTask(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProjectTestTask = {}): Promise<IProjectTestTask> {
        if (_context.project && _context.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(_context, _context.project);
            if (data) {
                entity.projecttname = data.name;
                entity.project = data.id;
            }
        }
        return new ProjectTestTask(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTestTaskService
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
     * UnlinkCase
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTestTaskService
     */
    async UnlinkCase(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projecttesttask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projecttesttasks/${_context.projecttesttask}/unlinkcase`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Close
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTestTaskService
     */
    async Close(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projecttesttask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projecttesttasks/${_context.projecttesttask}/close`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Start
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTestTaskService
     */
    async Start(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projecttesttask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projecttesttasks/${_context.projecttesttask}/start`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTestTaskService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/projecttesttasks/getdraft`, _data);
            return res;
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTestTaskService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/projecttesttasks/fetchdefault`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTestTaskService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projecttesttask) {
            const res = await this.http.get(`/projects/${_context.project}/projecttesttasks/${_context.projecttesttask}`);
            return res;
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Block
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTestTaskService
     */
    async Block(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projecttesttask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projecttesttasks/${_context.projecttesttask}/block`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTestTaskService
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
            return this.http.post(`/projects/${_context.project}/projecttesttasks`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * LinkCase
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTestTaskService
     */
    async LinkCase(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projecttesttask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projecttesttasks/${_context.projecttesttask}/linkcase`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTestTaskService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projecttesttask) {
            return this.http.delete(`/projects/${_context.project}/projecttesttasks/${_context.projecttesttask}`);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Activate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTestTaskService
     */
    async Activate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projecttesttask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projecttesttasks/${_context.projecttesttask}/activate`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTestTaskService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projecttesttask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/projecttesttasks/${_context.projecttesttask}`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * GetUserConcat
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTestTaskService
     */
    async GetUserConcat(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const appLogic = new GetCurUserConcatLogic(_context, _data);
        _data = await appLogic.onExecute();
        return new HttpResponse(_data);
    }

    /**
     * UnlinkCaseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectTestTaskServiceBase
     */
    public async UnlinkCaseBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projecttesttasks/unlinkcasebatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * CloseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectTestTaskServiceBase
     */
    public async CloseBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projecttesttasks/closebatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * StartBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectTestTaskServiceBase
     */
    public async StartBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projecttesttasks/startbatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * BlockBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectTestTaskServiceBase
     */
    public async BlockBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projecttesttasks/blockbatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * LinkCaseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectTestTaskServiceBase
     */
    public async LinkCaseBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projecttesttasks/linkcasebatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * ActivateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectTestTaskServiceBase
     */
    public async ActivateBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projecttesttasks/activatebatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
}
