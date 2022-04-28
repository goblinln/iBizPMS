import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IAccountProject, AccountProject } from '../../entities';
import keys from '../../entities/account-project/account-project-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 项目服务对象基类
 *
 * @export
 * @class AccountProjectBaseService
 * @extends {EntityBaseService}
 */
export class AccountProjectBaseService extends EntityBaseService<IAccountProject> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'AccountProject';
    protected APPDENAMEPLURAL = 'AccountProjects';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/AccountProject.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['id','name','code',];
    protected selectContextParam = {
    };

    constructor(opts?: any) {
        super(opts, 'AccountProject');
    }

    newEntity(data: IAccountProject): AccountProject {
        return new AccountProject(data);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IAccountProject> {
        const entity = await super.getLocal(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IAccountProject): Promise<IAccountProject> {
        return super.updateLocal(context, new AccountProject(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IAccountProject = {}): Promise<IAccountProject> {
        return new AccountProject(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof AccountProjectService
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

    protected getBugSelectableProjectListCond() {
        return this.condCache.get('bugSelectableProjectList');
    }

    protected getCurDefaultQueryCond() {
        return this.condCache.get('curDefaultQuery');
    }

    protected getCurDefaultQueryExpCond() {
        return this.condCache.get('curDefaultQueryExp');
    }

    protected getCurPlanProjectCond() {
        return this.condCache.get('curPlanProject');
    }

    protected getCurProductCond() {
        return this.condCache.get('curProduct');
    }

    protected getCurUserCond() {
        return this.condCache.get('curUser');
    }

    protected getCurUserSaCond() {
        return this.condCache.get('curUserSa');
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

    protected getInvolvedProjectCond() {
        return this.condCache.get('involvedProject');
    }

    protected getInvolvedProjectStoryTaskBugCond() {
        return this.condCache.get('involvedProjectStoryTaskBug');
    }

    protected getMyCond() {
        return this.condCache.get('my');
    }

    protected getMyProjectCond() {
        if (!this.condCache.has('myProject')) {
            const strCond: any[] = ['AND', ['EQ', 'DELETED','0']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('myProject', cond);
            }
        }
        return this.condCache.get('myProject');
    }

    protected getOpenByQueryCond() {
        if (!this.condCache.has('openByQuery')) {
            const strCond: any[] = ['AND', ['EQ', 'ACL','private'], ['OR', ['EQ', 'OPENEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'UPDATEBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'PM',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'RD',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'PO',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'QD',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}]]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('openByQuery', cond);
            }
        }
        return this.condCache.get('openByQuery');
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

    protected getProjectTeamCond() {
        return this.condCache.get('projectTeam');
    }

    protected getStoryProjectCond() {
        return this.condCache.get('storyProject');
    }

    protected getUnDoneProjectCond() {
        return this.condCache.get('unDoneProject');
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
     * @memberof AccountProjectService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.sysaccount && _context.accountproject) {
            const res = await this.http.get(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/accountprojects/${encodeURIComponent(_context.accountproject)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        const res = await this.http.get(`/accountprojects/${encodeURIComponent(_context.accountproject)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
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
     * @memberof AccountProjectService
     */
    async FetchAccount(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.sysaccount && true) {
            const res = await this.http.post(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/accountprojects/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        const res = await this.http.post(`/accountprojects/fetchaccount`, _data);
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
     * @memberof AccountProjectService
     */
    async FetchMy(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.sysaccount && true) {
            const res = await this.http.post(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/accountprojects/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        const res = await this.http.post(`/accountprojects/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
}
