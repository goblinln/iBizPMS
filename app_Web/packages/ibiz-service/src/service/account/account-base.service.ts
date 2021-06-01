import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IAccount, Account } from '../../entities';
import keys from '../../entities/account/account-keys';

/**
 * 系统用户服务对象基类
 *
 * @export
 * @class AccountBaseService
 * @extends {EntityBaseService}
 */
export class AccountBaseService extends EntityBaseService<IAccount> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'Account';
    protected APPDENAMEPLURAL = 'Accounts';
    protected APPDEKEY = 'userid';
    protected APPDETEXT = 'personname';
    protected quickSearchFields = ['personname',];
    protected selectContextParam = {
    };

    newEntity(data: IAccount): Account {
        return new Account(data);
    }

    async addLocal(context: IContext, entity: IAccount): Promise<IAccount | null> {
        return this.cache.add(context, new Account(entity) as any);
    }

    async createLocal(context: IContext, entity: IAccount): Promise<IAccount | null> {
        return super.createLocal(context, new Account(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IAccount> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IAccount): Promise<IAccount> {
        return super.updateLocal(context, new Account(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IAccount = {}): Promise<IAccount> {
        return new Account(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof AccountService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }
}
