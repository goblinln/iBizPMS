import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ITodo, Todo } from '../../entities';
import keys from '../../entities/todo/todo-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

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
    protected APPNAME = 'Web';
    protected APPDENAME = 'Todo';
    protected APPDENAMEPLURAL = 'Todos';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/Todo.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['id','todosn','name',];
    protected selectContextParam = {
    };

    constructor(opts?: any) {
        super(opts, 'Todo');
    }

    newEntity(data: ITodo): Todo {
        return new Todo(data);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ITodo> {
        const entity = await super.getLocal(context, srfKey);
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

    protected getAccountCond() {
        return this.condCache.get('account');
    }

    protected getDefaultCond() {
        return this.condCache.get('default');
    }

    protected getMyCond() {
        return this.condCache.get('my');
    }

    protected getMyCreateTodoCond() {
        if (!this.condCache.has('myCreateTodo')) {
            const strCond: any[] = ['AND', ['OR', ['EQ', 'ASSIGNEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'FINISHEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'UPDATEBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'CLOSEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'ACCOUNT',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}]]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('myCreateTodo', cond);
            }
        }
        return this.condCache.get('myCreateTodo');
    }

    protected getMyTodoCond() {
        return this.condCache.get('myTodo');
    }

    protected getMyTodoPcCond() {
        return this.condCache.get('myTodoPc');
    }

    protected getMyUpcomingCond() {
        return this.condCache.get('myUpcoming');
    }

    protected getViewCond() {
        return this.condCache.get('view');
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
        try {
        if (_context.sysaccount && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/sysaccounts/${_context.sysaccount}/todos`, _data);
            return res;
        }
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        const res = await this.http.post(`/todos`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.sysaccount && _context.todo) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Finish');
            const res = await this.http.post(`/sysaccounts/${_context.sysaccount}/todos/${_context.todo}/finish`, _data);
            return res;
        }
        const res = await this.http.post(`/todos/${_context.todo}/finish`, _data);
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
     * @memberof TodoService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.sysaccount && _context.todo) {
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/todos/${_context.todo}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        const res = await this.http.get(`/todos/${_context.todo}`);
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
     * @memberof TodoService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.sysaccount && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/todos/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/todos/getdraft`, _data);
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
     * @memberof TodoService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.sysaccount && _context.todo) {
            const res = await this.http.delete(`/sysaccounts/${_context.sysaccount}/todos/${_context.todo}`);
            return res;
        }
        const res = await this.http.delete(`/todos/${_context.todo}`);
        return res;
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
     * @memberof TodoService
     */
    async Start(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.sysaccount && _context.todo) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Start');
            const res = await this.http.post(`/sysaccounts/${_context.sysaccount}/todos/${_context.todo}/start`, _data);
            return res;
        }
        const res = await this.http.post(`/todos/${_context.todo}/start`, _data);
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
     * @memberof TodoService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.sysaccount && _context.todo) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/sysaccounts/${_context.sysaccount}/todos/${_context.todo}`, _data);
            return res;
        }
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
        const res = await this.http.put(`/todos/${_context.todo}`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchAccount
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TodoService
     */
    async FetchAccount(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.sysaccount && true) {
            const res = await this.http.post(`/sysaccounts/${_context.sysaccount}/todos/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        const res = await this.http.post(`/todos/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchMy
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TodoService
     */
    async FetchMy(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.sysaccount && true) {
            const res = await this.http.post(`/sysaccounts/${_context.sysaccount}/todos/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        const res = await this.http.post(`/todos/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }

    /**
     * FinishBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TodoServiceBase
     */
    public async FinishBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.sysaccount && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/sysaccounts/${_context.sysaccount}/todos/finishbatch`,_data);
            return res;
        }
        _data = await this.obtainMinor(_context, _data);
        const res = await this.http.post(`/todos/finishbatch`,_data);
        return res;
    }

    /**
     * StartBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TodoServiceBase
     */
    public async StartBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.sysaccount && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/sysaccounts/${_context.sysaccount}/todos/startbatch`,_data);
            return res;
        }
        _data = await this.obtainMinor(_context, _data);
        const res = await this.http.post(`/todos/startbatch`,_data);
        return res;
    }
}
