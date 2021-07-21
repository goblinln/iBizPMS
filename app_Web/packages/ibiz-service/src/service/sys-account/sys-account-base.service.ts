import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ISysAccount, SysAccount } from '../../entities';
import keys from '../../entities/sys-account/sys-account-keys';

/**
 * 系统用户服务对象基类
 *
 * @export
 * @class SysAccountBaseService
 * @extends {EntityBaseService}
 */
export class SysAccountBaseService extends EntityBaseService<ISysAccount> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'SysAccount';
    protected APPDENAMEPLURAL = 'SysAccounts';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/SysAccount.json';
    protected APPDEKEY = 'userid';
    protected APPDETEXT = 'personname';
    protected quickSearchFields = ['personname',];
    protected selectContextParam = {
    };

    newEntity(data: ISysAccount): SysAccount {
        return new SysAccount(data);
    }

    async addLocal(context: IContext, entity: ISysAccount): Promise<ISysAccount | null> {
        return this.cache.add(context, new SysAccount(entity) as any);
    }

    async createLocal(context: IContext, entity: ISysAccount): Promise<ISysAccount | null> {
        return super.createLocal(context, new SysAccount(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ISysAccount> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: ISysAccount): Promise<ISysAccount> {
        return super.updateLocal(context, new SysAccount(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ISysAccount = {}): Promise<ISysAccount> {
        return new SysAccount(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysAccountService
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
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysAccountService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.get(`/sysaccounts/${_context.sysaccount}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * CountMyContribution
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysAccountService
     */
    async CountMyContribution(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/sysaccounts/countmycontribution`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'CountMyContribution');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * CountMyWork
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysAccountService
     */
    async CountMyWork(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/sysaccounts/countmywork`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'CountMyWork');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
}
