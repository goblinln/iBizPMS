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
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/IbzproProjectUserTask.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'id';
    protected quickSearchFields = ['id',];
    protected selectContextParam = {
    };

    constructor(opts?: any) {
        super(opts, 'IbzproProjectUserTask');
    }

    newEntity(data: IIbzproProjectUserTask): IbzproProjectUserTask {
        return new IbzproProjectUserTask(data);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbzproProjectUserTask> {
        const entity = await super.getLocal(context, srfKey);
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
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzproProjectUserTaskService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        const res = await this.http.post(`/ibzproprojectusertasks`, _data);
        return res;
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
     * @memberof IbzproProjectUserTaskService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.get(`/ibzproprojectusertasks/${encodeURIComponent(_context.ibzproprojectusertask)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
        return res;
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
     * @memberof IbzproProjectUserTaskService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibzproprojectusertasks/getdraft`, _data);
        return res;
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
     * @memberof IbzproProjectUserTaskService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.delete(`/ibzproprojectusertasks/${encodeURIComponent(_context.ibzproprojectusertask)}`);
        return res;
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
     * @memberof IbzproProjectUserTaskService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
        const res = await this.http.put(`/ibzproprojectusertasks/${encodeURIComponent(_context.ibzproprojectusertask)}`, _data);
        return res;
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
     * @memberof IbzproProjectUserTaskService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/ibzproprojectusertasks/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        const res = await this.http.post(`/ibzproprojectusertasks/fetchprojectdailytask`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProjectDailyTask');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        const res = await this.http.post(`/ibzproprojectusertasks/fetchprojectmonthlytask`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProjectMonthlyTask');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        const res = await this.http.post(`/ibzproprojectusertasks/fetchprojectweeklytask`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProjectWeeklyTask');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        const res = await this.http.get(`/ibzproprojectusertasks/${encodeURIComponent(_context.ibzproprojectusertask)}/select`);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
}
