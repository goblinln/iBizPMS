import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ITaskStats, TaskStats } from '../../entities';
import keys from '../../entities/task-stats/task-stats-keys';

/**
 * 任务统计服务对象基类
 *
 * @export
 * @class TaskStatsBaseService
 * @extends {EntityBaseService}
 */
export class TaskStatsBaseService extends EntityBaseService<ITaskStats> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'TaskStats';
    protected APPDENAMEPLURAL = 'TaskStats';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/TaskStats.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
    };

    newEntity(data: ITaskStats): TaskStats {
        return new TaskStats(data);
    }

    async addLocal(context: IContext, entity: ITaskStats): Promise<ITaskStats | null> {
        return this.cache.add(context, new TaskStats(entity) as any);
    }

    async createLocal(context: IContext, entity: ITaskStats): Promise<ITaskStats | null> {
        return super.createLocal(context, new TaskStats(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ITaskStats> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: ITaskStats): Promise<ITaskStats> {
        return super.updateLocal(context, new TaskStats(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ITaskStats = {}): Promise<ITaskStats> {
        return new TaskStats(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskStatsService
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
     * @memberof TaskStatsService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        const res = await this.http.post(`/taskstats`, _data);
        res.data = await this.afterExecuteAction(_context,res?.data);
        return res;
            } catch (error) {
                console.warn(error);
                return new HttpResponse({message:error.message}, {
                    ok: false,
                    status: 500,
                });
            }
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskStatsService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.get(`/taskstats/${_context.taskstats}`);
        res.data = await this.afterExecuteAction(_context,res?.data);
        return res;
            } catch (error) {
                console.warn(error);
                return new HttpResponse({message:error.message}, {
                    ok: false,
                    status: 500,
                });
            }
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskStatsService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/taskstats/getdraft`, _data);
        return res;
            } catch (error) {
                console.warn(error);
                return new HttpResponse({message:error.message}, {
                    ok: false,
                    status: 500,
                });
            }
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskStatsService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.delete(`/taskstats/${_context.taskstats}`);
        return res;
            } catch (error) {
                console.warn(error);
                return new HttpResponse({message:error.message}, {
                    ok: false,
                    status: 500,
                });
            }
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskStatsService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data);
        const res = await this.http.put(`/taskstats/${_context.taskstats}`, _data);
        res.data = await this.afterExecuteAction(_context,res?.data);
        return res;
            } catch (error) {
                console.warn(error);
                return new HttpResponse({message:error.message}, {
                    ok: false,
                    status: 500,
                });
            }
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskStatsService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/taskstats/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
        return res;
            } catch (error) {
                console.warn(error);
                return new HttpResponse({message:error.message}, {
                    ok: false,
                    status: 500,
                });
            }
    }
    /**
     * FetchTaskFinishHuiZong
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskStatsService
     */
    async FetchTaskFinishHuiZong(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/taskstats/fetchtaskfinishhuizong`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
        return res;
            } catch (error) {
                console.warn(error);
                return new HttpResponse({message:error.message}, {
                    ok: false,
                    status: 500,
                });
            }
    }
    /**
     * FetchUserFinishTaskSum
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskStatsService
     */
    async FetchUserFinishTaskSum(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/taskstats/fetchuserfinishtasksum`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
        return res;
            } catch (error) {
                console.warn(error);
                return new HttpResponse({message:error.message}, {
                    ok: false,
                    status: 500,
                });
            }
    }
    /**
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskStatsService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.get(`/taskstats/${_context.taskstats}/select`);
        return res;
            } catch (error) {
                console.warn(error);
                return new HttpResponse({message:error.message}, {
                    ok: false,
                    status: 500,
                });
            }
    }
}
