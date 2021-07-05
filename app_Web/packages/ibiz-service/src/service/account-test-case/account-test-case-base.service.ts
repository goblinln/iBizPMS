import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IAccountTestCase, AccountTestCase } from '../../entities';
import keys from '../../entities/account-test-case/account-test-case-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 测试用例服务对象基类
 *
 * @export
 * @class AccountTestCaseBaseService
 * @extends {EntityBaseService}
 */
export class AccountTestCaseBaseService extends EntityBaseService<IAccountTestCase> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'AccountTestCase';
    protected APPDENAMEPLURAL = 'AccountTestCases';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/AccountTestCase.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'title';
    protected quickSearchFields = ['title',];
    protected selectContextParam = {
    };

    newEntity(data: IAccountTestCase): AccountTestCase {
        return new AccountTestCase(data);
    }

    async addLocal(context: IContext, entity: IAccountTestCase): Promise<IAccountTestCase | null> {
        return this.cache.add(context, new AccountTestCase(entity) as any);
    }

    async createLocal(context: IContext, entity: IAccountTestCase): Promise<IAccountTestCase | null> {
        return super.createLocal(context, new AccountTestCase(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IAccountTestCase> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IAccountTestCase): Promise<IAccountTestCase> {
        return super.updateLocal(context, new AccountTestCase(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IAccountTestCase = {}): Promise<IAccountTestCase> {
        return new AccountTestCase(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof AccountTestCaseService
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

    protected getBatchNewCond() {
        if (!this.condCache.has('batchNew')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('batchNew', cond);
            }
        }
        return this.condCache.get('batchNew');
    }

    protected getCurOpenedCaseCond() {
        if (!this.condCache.has('curOpenedCase')) {
            const strCond: any[] = ['AND', ['EQ', 'OPENEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('curOpenedCase', cond);
            }
        }
        return this.condCache.get('curOpenedCase');
    }

    protected getCurSuiteCond() {
        return this.condCache.get('curSuite');
    }

    protected getCurTestTaskCond() {
        return this.condCache.get('curTestTask');
    }

    protected getDefaultCond() {
        return this.condCache.get('default');
    }

    protected getESBulkCond() {
        return this.condCache.get('eSBulk');
    }

    protected getModuleRePortCaseCond() {
        if (!this.condCache.has('moduleRePortCase')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('moduleRePortCase', cond);
            }
        }
        return this.condCache.get('moduleRePortCase');
    }

    protected getModuleRePortCaseEntryCond() {
        if (!this.condCache.has('moduleRePortCaseEntry')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('moduleRePortCaseEntry', cond);
            }
        }
        return this.condCache.get('moduleRePortCaseEntry');
    }

    protected getModuleRePortCase_ProjectCond() {
        if (!this.condCache.has('moduleRePortCase_Project')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('moduleRePortCase_Project', cond);
            }
        }
        return this.condCache.get('moduleRePortCase_Project');
    }

    protected getMyCond() {
        return this.condCache.get('my');
    }

    protected getMyCreateOrUpdateCond() {
        if (!this.condCache.has('myCreateOrUpdate')) {
            const strCond: any[] = ['AND', ['OR', ['EQ', 'OPENEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'REVIEWEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'LASTEDITEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}]]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('myCreateOrUpdate', cond);
            }
        }
        return this.condCache.get('myCreateOrUpdate');
    }

    protected getMyFavoriteCond() {
        if (!this.condCache.has('myFavorite')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('myFavorite', cond);
            }
        }
        return this.condCache.get('myFavorite');
    }

    protected getMyReProductCond() {
        if (!this.condCache.has('myReProduct')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('myReProduct', cond);
            }
        }
        return this.condCache.get('myReProduct');
    }

    protected getNotCurTestSuiteCond() {
        return this.condCache.get('notCurTestSuite');
    }

    protected getNotCurTestTaskCond() {
        return this.condCache.get('notCurTestTask');
    }

    protected getNotCurTestTaskProjectCond() {
        return this.condCache.get('notCurTestTaskProject');
    }

    protected getRePortCaseCond() {
        if (!this.condCache.has('rePortCase')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('rePortCase', cond);
            }
        }
        return this.condCache.get('rePortCase');
    }

    protected getRePortCaseEntryCond() {
        if (!this.condCache.has('rePortCaseEntry')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('rePortCaseEntry', cond);
            }
        }
        return this.condCache.get('rePortCaseEntry');
    }

    protected getRePortCase_ProjectCond() {
        if (!this.condCache.has('rePortCase_Project')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('rePortCase_Project', cond);
            }
        }
        return this.condCache.get('rePortCase_Project');
    }

    protected getRunERRePortCaseCond() {
        if (!this.condCache.has('runERRePortCase')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('runERRePortCase', cond);
            }
        }
        return this.condCache.get('runERRePortCase');
    }

    protected getRunERRePortCaseEntryCond() {
        if (!this.condCache.has('runERRePortCaseEntry')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('runERRePortCaseEntry', cond);
            }
        }
        return this.condCache.get('runERRePortCaseEntry');
    }

    protected getRunERRePortCase_ProjectCond() {
        if (!this.condCache.has('runERRePortCase_Project')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('runERRePortCase_Project', cond);
            }
        }
        return this.condCache.get('runERRePortCase_Project');
    }

    protected getRunRePortCaseCond() {
        if (!this.condCache.has('runRePortCase')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('runRePortCase', cond);
            }
        }
        return this.condCache.get('runRePortCase');
    }

    protected getRunRePortCaseEntryCond() {
        if (!this.condCache.has('runRePortCaseEntry')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('runRePortCaseEntry', cond);
            }
        }
        return this.condCache.get('runRePortCaseEntry');
    }

    protected getRunRePortCase_ProjectCond() {
        if (!this.condCache.has('runRePortCase_Project')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('runRePortCase_Project', cond);
            }
        }
        return this.condCache.get('runRePortCase_Project');
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
     * @memberof AccountTestCaseService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && _context.accounttestcase) {
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/accounttestcases/${_context.accounttestcase}`);
            return res;
        }
        const res = await this.http.get(`/accounttestcases/${_context.accounttestcase}`);
        return res;
    }
    /**
     * FetchAccount
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof AccountTestCaseService
     */
    async FetchAccount(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/accounttestcases/fetchaccount`, _data);
        }
        return this.http.post(`/accounttestcases/fetchaccount`, _data);
    }
    /**
     * FetchMy
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof AccountTestCaseService
     */
    async FetchMy(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/accounttestcases/fetchmy`, _data);
        }
        return this.http.post(`/accounttestcases/fetchmy`, _data);
    }
    /**
     * FetchMyFavorite
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof AccountTestCaseService
     */
    async FetchMyFavorite(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/accounttestcases/fetchmyfavorite`, _data);
        }
        return this.http.post(`/accounttestcases/fetchmyfavorite`, _data);
    }
}
