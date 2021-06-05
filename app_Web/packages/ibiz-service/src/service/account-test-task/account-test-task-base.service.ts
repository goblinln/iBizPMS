import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IAccountTestTask, AccountTestTask } from '../../entities';
import keys from '../../entities/account-test-task/account-test-task-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';
import { GetCurUserConcatLogic } from '../../logic/entity/account-test-task/get-cur-user-concat/get-cur-user-concat-logic';

/**
 * 测试版本服务对象基类
 *
 * @export
 * @class AccountTestTaskBaseService
 * @extends {EntityBaseService}
 */
export class AccountTestTaskBaseService extends EntityBaseService<IAccountTestTask> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'AccountTestTask';
    protected APPDENAMEPLURAL = 'AccountTestTasks';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
    };

    newEntity(data: IAccountTestTask): AccountTestTask {
        return new AccountTestTask(data);
    }

    async addLocal(context: IContext, entity: IAccountTestTask): Promise<IAccountTestTask | null> {
        return this.cache.add(context, new AccountTestTask(entity) as any);
    }

    async createLocal(context: IContext, entity: IAccountTestTask): Promise<IAccountTestTask | null> {
        return super.createLocal(context, new AccountTestTask(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IAccountTestTask> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IAccountTestTask): Promise<IAccountTestTask> {
        return super.updateLocal(context, new AccountTestTask(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IAccountTestTask = {}): Promise<IAccountTestTask> {
        return new AccountTestTask(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof AccountTestTaskService
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
     * FetchAccount
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof AccountTestTaskService
     */
    async FetchAccount(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/accounttesttasks/fetchaccount`, _data);
        }
        return this.http.post(`/accounttesttasks/fetchaccount`, _data);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof AccountTestTaskService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && _context.accounttesttask) {
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/accounttesttasks/${_context.accounttesttask}`);
            return res;
        }
        const res = await this.http.get(`/accounttesttasks/${_context.accounttesttask}`);
        return res;
    }
    /**
     * FetchMy
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof AccountTestTaskService
     */
    async FetchMy(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/accounttesttasks/fetchmy`, _data);
        }
        return this.http.post(`/accounttesttasks/fetchmy`, _data);
    }
    /**
     * GetUserConcat
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof AccountTestTaskService
     */
    async GetUserConcat(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const appLogic = new GetCurUserConcatLogic(_context, _data);
        _data = await appLogic.onExecute();
        return new HttpResponse(_data);
    }
}
