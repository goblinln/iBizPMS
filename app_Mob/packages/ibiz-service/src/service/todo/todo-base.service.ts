import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ITodo, Todo } from '../../entities';
import keys from '../../entities/todo/todo-keys';

/**
 * 待办服务对象基类
 *
 * @export
 * @class TodoBaseService
 * @extends {EntityBaseService}
 */
export class TodoBaseService extends EntityBaseService<ITodo> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Mob';
    protected APPDENAME = 'Todo';
    protected APPDENAMEPLURAL = 'Todos';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
    };

    newEntity(data: ITodo): Todo {
        return new Todo(data);
    }

    async addLocal(context: IContext, entity: ITodo): Promise<ITodo | null> {
        return this.cache.add(context, new Todo(entity) as any);
    }

    async createLocal(context: IContext, entity: ITodo): Promise<ITodo | null> {
        return super.createLocal(context, new Todo(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ITodo> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: ITodo): Promise<ITodo> {
        return super.updateLocal(context, new Todo(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ITodo = {}): Promise<ITodo> {
        return new Todo(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TodoService
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
     * @memberof TodoService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/todos/${_context.todo}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TodoService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/todos`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TodoService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/todos/${_context.todo}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TodoService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/todos/${_context.todo}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TodoService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/todos/${_context.todo}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TodoService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/todos/getdraft`, _data);
        return res;
    }
    /**
     * Activate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TodoService
     */
    async Activate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/todos/${_context.todo}/activate`, _data);
    }
    /**
     * AssignTo
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TodoService
     */
    async AssignTo(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/todos/${_context.todo}/assignto`, _data);
    }
    /**
     * Close
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TodoService
     */
    async Close(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/todos/${_context.todo}/close`, _data);
    }
    /**
     * CreateCycle
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TodoService
     */
    async CreateCycle(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/todos/${_context.todo}/createcycle`, _data);
    }
    /**
     * Finish
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TodoService
     */
    async Finish(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/todos/${_context.todo}/finish`, _data);
    }
    /**
     * SendMessage
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TodoService
     */
    async SendMessage(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/todos/${_context.todo}/sendmessage`, _data);
    }
    /**
     * SendMsgPreProcess
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TodoService
     */
    async SendMsgPreProcess(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/todos/${_context.todo}/sendmsgpreprocess`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TodoService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/todos/fetchdefault`, _data);
    }
    /**
     * FetchMyTodo
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TodoService
     */
    async FetchMyTodo(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/todos/fetchmytodo`, _data);
    }
    /**
     * FetchMyTodoPc
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TodoService
     */
    async FetchMyTodoPc(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/todos/fetchmytodopc`, _data);
    }
    /**
     * FetchMyUpcoming
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TodoService
     */
    async FetchMyUpcoming(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/todos/fetchmyupcoming`, _data);
    }
}
