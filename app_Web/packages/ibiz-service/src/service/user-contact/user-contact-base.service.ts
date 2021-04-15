import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IUserContact, UserContact } from '../../entities';
import keys from '../../entities/user-contact/user-contact-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 用户联系方式服务对象基类
 *
 * @export
 * @class UserContactBaseService
 * @extends {EntityBaseService}
 */
export class UserContactBaseService extends EntityBaseService<IUserContact> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'UserContact';
    protected APPDENAMEPLURAL = 'UserContacts';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'listname';
    protected quickSearchFields = ['listname',];
    protected selectContextParam = {
    };

    newEntity(data: IUserContact): UserContact {
        return new UserContact(data);
    }

    async addLocal(context: IContext, entity: IUserContact): Promise<IUserContact | null> {
        return this.cache.add(context, new UserContact(entity) as any);
    }

    async createLocal(context: IContext, entity: IUserContact): Promise<IUserContact | null> {
        return super.createLocal(context, new UserContact(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IUserContact> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IUserContact): Promise<IUserContact> {
        return super.updateLocal(context, new UserContact(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IUserContact = {}): Promise<IUserContact> {
        return new UserContact(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserContactService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }

    protected getCurUSERCONTACTCond() {
        if (!this.condCache.has('curUSERCONTACT')) {
            const strCond: any[] = ['AND', ['EQ', 'ACCOUNT',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('curUSERCONTACT', cond);
            }
        }
        return this.condCache.get('curUSERCONTACT');
    }

    protected getDefaultCond() {
        return this.condCache.get('default');
    }

    protected getMyUSERCONTACTCond() {
        if (!this.condCache.has('myUSERCONTACT')) {
            const strCond: any[] = ['AND', ['EQ', 'ACCOUNT',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('myUSERCONTACT', cond);
            }
        }
        return this.condCache.get('myUSERCONTACT');
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
     * @memberof UserContactService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/usercontacts/${_context.usercontact}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserContactService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/usercontacts`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserContactService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/usercontacts/${_context.usercontact}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserContactService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/usercontacts/${_context.usercontact}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserContactService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/usercontacts/${_context.usercontact}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserContactService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/usercontacts/getdraft`, _data);
        return res;
    }
    /**
     * FetchCurUSERCONTACT
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserContactService
     */
    async FetchCurUSERCONTACT(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/usercontacts/fetchcurusercontact`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserContactService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/usercontacts/fetchdefault`, _data);
    }
    /**
     * FetchMyUSERCONTACT
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserContactService
     */
    async FetchMyUSERCONTACT(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/usercontacts/fetchmyusercontact`, _data);
    }
}