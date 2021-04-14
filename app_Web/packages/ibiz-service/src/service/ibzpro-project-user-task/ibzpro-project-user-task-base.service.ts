import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbzproProjectUserTask, IbzproProjectUserTask } from '../../entities';
import keys from '../../entities/ibzpro-project-user-task/ibzpro-project-user-task-keys';

/**
 * 项目汇报用户任务服务对象基类
 *
 * @export
 * @class IbzproProjectUserTaskBaseService
 * @extends {EntityBaseService}
 */
export class IbzproProjectUserTaskBaseService extends EntityBaseService<IIbzproProjectUserTask> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IbzproProjectUserTask';
    protected APPDENAMEPLURAL = 'IbzproProjectUserTasks';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'id';
    protected quickSearchFields = ['id',];
    protected selectContextParam = {
    };

    newEntity(data: IIbzproProjectUserTask): IbzproProjectUserTask {
        return new IbzproProjectUserTask(data);
    }

    async addLocal(context: IContext, entity: IIbzproProjectUserTask): Promise<IIbzproProjectUserTask | null> {
        return this.cache.add(context, new IbzproProjectUserTask(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbzproProjectUserTask): Promise<IIbzproProjectUserTask | null> {
        return super.createLocal(context, new IbzproProjectUserTask(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbzproProjectUserTask> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbzproProjectUserTask): Promise<IIbzproProjectUserTask> {
        return super.updateLocal(context, new IbzproProjectUserTask(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbzproProjectUserTask = {}): Promise<IIbzproProjectUserTask> {
        return new IbzproProjectUserTask(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzproProjectUserTaskService
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
     * @memberof IbzproProjectUserTaskService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzproprojectusertasks/${_context.ibzproprojectusertask}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzproProjectUserTaskService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibzproprojectusertasks`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzproProjectUserTaskService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibzproprojectusertasks/${_context.ibzproprojectusertask}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzproProjectUserTaskService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/ibzproprojectusertasks/${_context.ibzproprojectusertask}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzproProjectUserTaskService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/ibzproprojectusertasks/${_context.ibzproprojectusertask}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzproProjectUserTaskService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibzproprojectusertasks/getdraft`, _data);
        return res;
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzproProjectUserTaskService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzproprojectusertasks/fetchdefault`, _data);
    }
    /**
     * FetchProjectDailyTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzproProjectUserTaskService
     */
    async FetchProjectDailyTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzproprojectusertasks/fetchprojectdailytask`, _data);
    }
    /**
     * FetchProjectMonthlyTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzproProjectUserTaskService
     */
    async FetchProjectMonthlyTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzproprojectusertasks/fetchprojectmonthlytask`, _data);
    }
    /**
     * FetchProjectWeeklyTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzproProjectUserTaskService
     */
    async FetchProjectWeeklyTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzproprojectusertasks/fetchprojectweeklytask`, _data);
    }
}