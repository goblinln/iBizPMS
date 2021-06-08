import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbzproProductUserTask, IbzproProductUserTask } from '../../entities';
import keys from '../../entities/ibzpro-product-user-task/ibzpro-product-user-task-keys';

/**
 * 产品汇报用户任务服务对象基类
 *
 * @export
 * @class IbzproProductUserTaskBaseService
 * @extends {EntityBaseService}
 */
export class IbzproProductUserTaskBaseService extends EntityBaseService<IIbzproProductUserTask> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IbzproProductUserTask';
    protected APPDENAMEPLURAL = 'IbzproProductUserTasks';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'id';
    protected quickSearchFields = ['id',];
    protected selectContextParam = {
    };

    newEntity(data: IIbzproProductUserTask): IbzproProductUserTask {
        return new IbzproProductUserTask(data);
    }

    async addLocal(context: IContext, entity: IIbzproProductUserTask): Promise<IIbzproProductUserTask | null> {
        return this.cache.add(context, new IbzproProductUserTask(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbzproProductUserTask): Promise<IIbzproProductUserTask | null> {
        return super.createLocal(context, new IbzproProductUserTask(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbzproProductUserTask> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbzproProductUserTask): Promise<IIbzproProductUserTask> {
        return super.updateLocal(context, new IbzproProductUserTask(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbzproProductUserTask = {}): Promise<IIbzproProductUserTask> {
        return new IbzproProductUserTask(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzproProductUserTaskService
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
     * @memberof IbzproProductUserTaskService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibzproproductusertasks`, _data);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzproProductUserTaskService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/ibzproproductusertasks/${_context.ibzproproductusertask}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzproProductUserTaskService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibzproproductusertasks/getdraft`, _data);
        return res;
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzproProductUserTaskService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/ibzproproductusertasks/${_context.ibzproproductusertask}`);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzproProductUserTaskService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibzproproductusertasks/${_context.ibzproproductusertask}`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzproProductUserTaskService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzproproductusertasks/fetchdefault`, _data);
    }
    /**
     * FetchProductDailyUserTaskStats
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzproProductUserTaskService
     */
    async FetchProductDailyUserTaskStats(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzproproductusertasks/fetchproductdailyusertaskstats`, _data);
    }
    /**
     * FetchProductMonthlyUserTaskStats
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzproProductUserTaskService
     */
    async FetchProductMonthlyUserTaskStats(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzproproductusertasks/fetchproductmonthlyusertaskstats`, _data);
    }
    /**
     * FetchProductWeeklyUserTaskStats
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzproProductUserTaskService
     */
    async FetchProductWeeklyUserTaskStats(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzproproductusertasks/fetchproductweeklyusertaskstats`, _data);
    }
    /**
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzproProductUserTaskService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzproproductusertasks/${_context.ibzproproductusertask}/select`);
    }
}
