import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IAccountProduct, AccountProduct } from '../../entities';
import keys from '../../entities/account-product/account-product-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';
import { CancelProductTopLogic } from '../../logic/entity/account-product/cancel-product-top/cancel-product-top-logic';
import { ProductTopLogic } from '../../logic/entity/account-product/product-top/product-top-logic';

/**
 * 产品服务对象基类
 *
 * @export
 * @class AccountProductBaseService
 * @extends {EntityBaseService}
 */
export class AccountProductBaseService extends EntityBaseService<IAccountProduct> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'AccountProduct';
    protected APPDENAMEPLURAL = 'AccountProducts';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name','code','productsn',];
    protected selectContextParam = {
    };

    newEntity(data: IAccountProduct): AccountProduct {
        return new AccountProduct(data);
    }

    async addLocal(context: IContext, entity: IAccountProduct): Promise<IAccountProduct | null> {
        return this.cache.add(context, new AccountProduct(entity) as any);
    }

    async createLocal(context: IContext, entity: IAccountProduct): Promise<IAccountProduct | null> {
        return super.createLocal(context, new AccountProduct(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IAccountProduct> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IAccountProduct): Promise<IAccountProduct> {
        return super.updateLocal(context, new AccountProduct(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IAccountProduct = {}): Promise<IAccountProduct> {
        return new AccountProduct(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof AccountProductService
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

    protected getAllListCond() {
        if (!this.condCache.has('allList')) {
            const strCond: any[] = ['AND', ['OR', ['NOTEQ', 'STATUS','closed']]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('allList', cond);
            }
        }
        return this.condCache.get('allList');
    }

    protected getAllProductCond() {
        return this.condCache.get('allProduct');
    }

    protected getCheckNameOrCodeCond() {
        return this.condCache.get('checkNameOrCode');
    }

    protected getCurDefaultCond() {
        return this.condCache.get('curDefault');
    }

    protected getCurProjectCond() {
        return this.condCache.get('curProject');
    }

    protected getCurUerCond() {
        if (!this.condCache.has('curUer')) {
            const strCond: any[] = ['AND', ['OR', ['EQ', 'ACL','open']]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('curUer', cond);
            }
        }
        return this.condCache.get('curUer');
    }

    protected getDefaultCond() {
        return this.condCache.get('default');
    }

    protected getDeveloperQueryCond() {
        if (!this.condCache.has('developerQuery')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('developerQuery', cond);
            }
        }
        return this.condCache.get('developerQuery');
    }

    protected getESBulkCond() {
        return this.condCache.get('eSBulk');
    }

    protected getMyCond() {
        return this.condCache.get('my');
    }

    protected getOpenQueryCond() {
        if (!this.condCache.has('openQuery')) {
            const strCond: any[] = ['AND', ['EQ', 'ACL','open']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('openQuery', cond);
            }
        }
        return this.condCache.get('openQuery');
    }

    protected getProductManagerQueryCond() {
        if (!this.condCache.has('productManagerQuery')) {
            const strCond: any[] = ['AND', ['AND', ['EQ', 'ACL','private'], ['OR', ['EQ', 'UPDATEBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'CREATEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'QD',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'RD',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'PO',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}]]]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('productManagerQuery', cond);
            }
        }
        return this.condCache.get('productManagerQuery');
    }

    protected getProductPMCond() {
        return this.condCache.get('productPM');
    }

    protected getProductTeamCond() {
        return this.condCache.get('productTeam');
    }

    protected getSimpleCond() {
        return this.condCache.get('simple');
    }

    protected getStoryCurProjectCond() {
        return this.condCache.get('storyCurProject');
    }

    protected getViewCond() {
        return this.condCache.get('view');
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof AccountProductService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && _context.accountproduct) {
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/accountproducts/${_context.accountproduct}`);
            return res;
        }
        const res = await this.http.get(`/accountproducts/${_context.accountproduct}`);
        return res;
    }
    /**
     * FetchAccount
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof AccountProductService
     */
    async FetchAccount(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/accountproducts/fetchaccount`, _data);
        }
        return this.http.post(`/accountproducts/fetchaccount`, _data);
    }
    /**
     * FetchMy
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof AccountProductService
     */
    async FetchMy(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/accountproducts/fetchmy`, _data);
        }
        return this.http.post(`/accountproducts/fetchmy`, _data);
    }
}
