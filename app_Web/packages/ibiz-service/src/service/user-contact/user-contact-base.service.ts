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

    protected getAccountCond() {
        return this.condCache.get('account');
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

    protected getMyCond() {
        return this.condCache.get('my');
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
}
