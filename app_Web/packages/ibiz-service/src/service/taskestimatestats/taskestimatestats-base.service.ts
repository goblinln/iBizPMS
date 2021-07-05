import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ITaskestimatestats, Taskestimatestats } from '../../entities';
import keys from '../../entities/taskestimatestats/taskestimatestats-keys';

/**
 * 任务工时统计服务对象基类
 *
 * @export
 * @class TaskestimatestatsBaseService
 * @extends {EntityBaseService}
 */
export class TaskestimatestatsBaseService extends EntityBaseService<ITaskestimatestats> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'Taskestimatestats';
    protected APPDENAMEPLURAL = 'taskestimatestats';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/taskestimatestats.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'account';
    protected quickSearchFields = ['account',];
    protected selectContextParam = {
    };

    newEntity(data: ITaskestimatestats): Taskestimatestats {
        return new Taskestimatestats(data);
    }

    async addLocal(context: IContext, entity: ITaskestimatestats): Promise<ITaskestimatestats | null> {
        return this.cache.add(context, new Taskestimatestats(entity) as any);
    }

    async createLocal(context: IContext, entity: ITaskestimatestats): Promise<ITaskestimatestats | null> {
        return super.createLocal(context, new Taskestimatestats(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ITaskestimatestats> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: ITaskestimatestats): Promise<ITaskestimatestats> {
        return super.updateLocal(context, new Taskestimatestats(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ITaskestimatestats = {}): Promise<ITaskestimatestats> {
        return new Taskestimatestats(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskestimatestatsService
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
     * @memberof TaskestimatestatsService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/taskestimatestats`, _data);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskestimatestatsService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/taskestimatestats/${_context.taskestimatestats}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskestimatestatsService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/taskestimatestats/getdraft`, _data);
        return res;
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskestimatestatsService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/taskestimatestats/${_context.taskestimatestats}`);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskestimatestatsService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/taskestimatestats/${_context.taskestimatestats}`, _data);
    }
    /**
     * FetchActionMonth
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskestimatestatsService
     */
    async FetchActionMonth(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/taskestimatestats/fetchactionmonth`, _data);
    }
    /**
     * FetchActionYear
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskestimatestatsService
     */
    async FetchActionYear(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/taskestimatestats/fetchactionyear`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskestimatestatsService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/taskestimatestats/fetchdefault`, _data);
    }
    /**
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskestimatestatsService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/taskestimatestats/${_context.taskestimatestats}/select`);
    }
}
