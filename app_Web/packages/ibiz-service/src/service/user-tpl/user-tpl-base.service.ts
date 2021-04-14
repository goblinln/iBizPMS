import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IUserTpl, UserTpl } from '../../entities';
import keys from '../../entities/user-tpl/user-tpl-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 用户模板服务对象基类
 *
 * @export
 * @class UserTplBaseService
 * @extends {EntityBaseService}
 */
export class UserTplBaseService extends EntityBaseService<IUserTpl> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'UserTpl';
    protected APPDENAMEPLURAL = 'UserTpls';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'title';
    protected quickSearchFields = ['title',];
    protected selectContextParam = {
    };

    newEntity(data: IUserTpl): UserTpl {
        return new UserTpl(data);
    }

    async addLocal(context: IContext, entity: IUserTpl): Promise<IUserTpl | null> {
        return this.cache.add(context, new UserTpl(entity) as any);
    }

    async createLocal(context: IContext, entity: IUserTpl): Promise<IUserTpl | null> {
        return super.createLocal(context, new UserTpl(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IUserTpl> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IUserTpl): Promise<IUserTpl> {
        return super.updateLocal(context, new UserTpl(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IUserTpl = {}): Promise<IUserTpl> {
        return new UserTpl(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserTplService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }

    protected getDefaultCond() {
        if (!this.condCache.has('default')) {
            const strCond: any[] = ['AND', ['OR', ['EQ', 'PUBLIC','1'], ['EQ', 'ACCOUNT',{ type: 'WEBCONTEXT', value: 'account'}]], ['EQ', 'TYPE',{ type: 'WEBCONTEXT', value: 'type'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('default', cond);
            }
        }
        return this.condCache.get('default');
    }

    protected getMyUserTplCond() {
        if (!this.condCache.has('myUserTpl')) {
            const strCond: any[] = ['AND', ['EQ', 'ACCOUNT',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('myUserTpl', cond);
            }
        }
        return this.condCache.get('myUserTpl');
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
     * @memberof UserTplService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/usertpls/${_context.usertpl}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserTplService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/usertpls`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserTplService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/usertpls/${_context.usertpl}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserTplService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/usertpls/${_context.usertpl}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserTplService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/usertpls/${_context.usertpl}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserTplService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/usertpls/getdraft`, _data);
        return res;
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserTplService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/usertpls/fetchdefault`, _data);
    }
    /**
     * FetchMyUserTpl
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserTplService
     */
    async FetchMyUserTpl(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/usertpls/fetchmyusertpl`, _data);
    }
}