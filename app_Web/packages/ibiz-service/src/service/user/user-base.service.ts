import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IUser, User } from '../../entities';
import keys from '../../entities/user/user-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 用户服务对象基类
 *
 * @export
 * @class UserBaseService
 * @extends {EntityBaseService}
 */
export class UserBaseService extends EntityBaseService<IUser> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'User';
    protected APPDENAMEPLURAL = 'Users';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'realname';
    protected quickSearchFields = ['realname',];
    protected selectContextParam = {
    };

    newEntity(data: IUser): User {
        return new User(data);
    }

    async addLocal(context: IContext, entity: IUser): Promise<IUser | null> {
        return this.cache.add(context, new User(entity) as any);
    }

    async createLocal(context: IContext, entity: IUser): Promise<IUser | null> {
        return super.createLocal(context, new User(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IUser> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IUser): Promise<IUser> {
        return super.updateLocal(context, new User(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IUser = {}): Promise<IUser> {
        return new User(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }

    protected getBugUserCond() {
        return this.condCache.get('bugUser');
    }

    protected getDefaultCond() {
        return this.condCache.get('default');
    }

    protected getGetByLoginCond() {
        if (!this.condCache.has('getByLogin')) {
            const strCond: any[] = ['AND', ['OR', ['EQ', 'ACCOUNT','account'], ['EQ', 'MOBILE','mobile'], ['EQ', 'EMAIL','email'], ['EQ', 'COMMITER','commiter']]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('getByLogin', cond);
            }
        }
        return this.condCache.get('getByLogin');
    }

    protected getProjectTeamMCond() {
        if (!this.condCache.has('projectTeamM')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('projectTeamM', cond);
            }
        }
        return this.condCache.get('projectTeamM');
    }

    protected getProjectTeamUserCond() {
        return this.condCache.get('projectTeamUser');
    }

    protected getProjectTeamUserTaskCond() {
        return this.condCache.get('projectTeamUserTask');
    }

    protected getTaskTeamCond() {
        return this.condCache.get('taskTeam');
    }

    protected getViewCond() {
        return this.condCache.get('view');
    }
    /**
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/users/${_context.user}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/users`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/users/${_context.user}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/users/${_context.user}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/users/${_context.user}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/users/getdraft`, _data);
        return res;
    }
    /**
     * GetByCommiter
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserService
     */
    async GetByCommiter(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/users/${_context.user}/getbycommiter`);
        return res;
    }
    /**
     * SyncAccount
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserService
     */
    async SyncAccount(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/users/${_context.user}/syncaccount`, _data);
    }
    /**
     * FetchBugUser
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserService
     */
    async FetchBugUser(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/users/fetchbuguser`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/users/fetchdefault`, _data);
    }
    /**
     * FetchGetByCommiter
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserService
     */
    async FetchGetByCommiter(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/users/fetchgetbycommiter`, _data);
    }
    /**
     * FetchProjectTeamM
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserService
     */
    async FetchProjectTeamM(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/users/fetchprojectteamm`, _data);
    }
    /**
     * FetchProjectTeamUser
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserService
     */
    async FetchProjectTeamUser(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/users/fetchprojectteamuser`, _data);
    }
    /**
     * FetchProjectTeamUserTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserService
     */
    async FetchProjectTeamUserTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/users/fetchprojectteamusertask`, _data);
    }
    /**
     * FetchTaskTeam
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserService
     */
    async FetchTaskTeam(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/users/fetchtaskteam`, _data);
    }
}