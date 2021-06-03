import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IBug, Bug } from '../../entities';
import keys from '../../entities/bug/bug-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';
import { GetCurUserConcatLogic } from '../../logic/entity/bug/get-cur-user-concat/get-cur-user-concat-logic';

/**
 * Bug服务对象基类
 *
 * @export
 * @class BugBaseService
 * @extends {EntityBaseService}
 */
export class BugBaseService extends EntityBaseService<IBug> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'Bug';
    protected APPDENAMEPLURAL = 'Bugs';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'title';
    protected quickSearchFields = ['id','title',];
    protected selectContextParam = {
        project: 'project',
        test: 'product',
    };

    newEntity(data: IBug): Bug {
        return new Bug(data);
    }

    async addLocal(context: IContext, entity: IBug): Promise<IBug | null> {
        return this.cache.add(context, new Bug(entity) as any);
    }

    async createLocal(context: IContext, entity: IBug): Promise<IBug | null> {
        return super.createLocal(context, new Bug(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IBug> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.project && entity.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(context, entity.project);
            if (data) {
                entity.projectname = data.name;
                entity.project = data.id;
            }
        }
        if (entity && entity.product && entity.product !== '') {
            const s = await ___ibz___.gs.getTestService();
            const data = await s.getLocal2(context, entity.product);
            if (data) {
                entity.productname = data.name;
                entity.product = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IBug): Promise<IBug> {
        return super.updateLocal(context, new Bug(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IBug = {}): Promise<IBug> {
        if (_context.project && _context.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(_context, _context.project);
            if (data) {
                entity.projectname = data.name;
                entity.project = data.id;
            }
        }
        if (_context.test && _context.test !== '') {
            const s = await ___ibz___.gs.getTestService();
            const data = await s.getLocal2(_context, _context.test);
            if (data) {
                entity.productname = data.name;
                entity.product = data.id;
            }
        }
        return new Bug(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
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

    protected getAssignedToMyBugCond() {
        if (!this.condCache.has('assignedToMyBug')) {
            const strCond: any[] = ['AND', ['EQ', 'ASSIGNEDTO',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('assignedToMyBug', cond);
            }
        }
        return this.condCache.get('assignedToMyBug');
    }

    protected getAssignedToMyBugPcCond() {
        if (!this.condCache.has('assignedToMyBugPc')) {
            const strCond: any[] = ['AND', ['EQ', 'ASSIGNEDTO',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('assignedToMyBugPc', cond);
            }
        }
        return this.condCache.get('assignedToMyBugPc');
    }

    protected getBuildBugsCond() {
        return this.condCache.get('buildBugs');
    }

    protected getBuildLinkResolvedBugsCond() {
        if (!this.condCache.has('buildLinkResolvedBugs')) {
            const strCond: any[] = ['AND', ['NOTEQ', 'STATUS','closed'], ['EQ', 'PROJECT',{ type: 'WEBCONTEXT', value: 'srfparentkey'}], ['EQ', 'STATUS','resolved']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('buildLinkResolvedBugs', cond);
            }
        }
        return this.condCache.get('buildLinkResolvedBugs');
    }

    protected getBuildOpenBugsCond() {
        return this.condCache.get('buildOpenBugs');
    }

    protected getBuildProduceBugCond() {
        if (!this.condCache.has('buildProduceBug')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('buildProduceBug', cond);
            }
        }
        return this.condCache.get('buildProduceBug');
    }

    protected getBuildProduceBugModuleCond() {
        if (!this.condCache.has('buildProduceBugModule')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('buildProduceBugModule', cond);
            }
        }
        return this.condCache.get('buildProduceBugModule');
    }

    protected getBuildProduceBugModule_ProjectCond() {
        if (!this.condCache.has('buildProduceBugModule_Project')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('buildProduceBugModule_Project', cond);
            }
        }
        return this.condCache.get('buildProduceBugModule_Project');
    }

    protected getBuildProduceBugOpenedByCond() {
        if (!this.condCache.has('buildProduceBugOpenedBy')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('buildProduceBugOpenedBy', cond);
            }
        }
        return this.condCache.get('buildProduceBugOpenedBy');
    }

    protected getBuildProduceBugOpenedBy_ProjectCond() {
        if (!this.condCache.has('buildProduceBugOpenedBy_Project')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('buildProduceBugOpenedBy_Project', cond);
            }
        }
        return this.condCache.get('buildProduceBugOpenedBy_Project');
    }

    protected getBuildProduceBugRESCond() {
        if (!this.condCache.has('buildProduceBugRES')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('buildProduceBugRES', cond);
            }
        }
        return this.condCache.get('buildProduceBugRES');
    }

    protected getBuildProduceBugRESOLVEDBYCond() {
        if (!this.condCache.has('buildProduceBugRESOLVEDBY')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('buildProduceBugRESOLVEDBY', cond);
            }
        }
        return this.condCache.get('buildProduceBugRESOLVEDBY');
    }

    protected getBuildProduceBugRESOLVEDBY_ProjectCond() {
        if (!this.condCache.has('buildProduceBugRESOLVEDBY_Project')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('buildProduceBugRESOLVEDBY_Project', cond);
            }
        }
        return this.condCache.get('buildProduceBugRESOLVEDBY_Project');
    }

    protected getBuildProduceBugResolution_ProjectCond() {
        if (!this.condCache.has('buildProduceBugResolution_Project')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('buildProduceBugResolution_Project', cond);
            }
        }
        return this.condCache.get('buildProduceBugResolution_Project');
    }

    protected getBuildProduceBugSeverity_ProjectCond() {
        if (!this.condCache.has('buildProduceBugSeverity_Project')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('buildProduceBugSeverity_Project', cond);
            }
        }
        return this.condCache.get('buildProduceBugSeverity_Project');
    }

    protected getBuildProduceBugStatus_ProjectCond() {
        if (!this.condCache.has('buildProduceBugStatus_Project')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('buildProduceBugStatus_Project', cond);
            }
        }
        return this.condCache.get('buildProduceBugStatus_Project');
    }

    protected getBuildProduceBugType_ProjectCond() {
        if (!this.condCache.has('buildProduceBugType_Project')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('buildProduceBugType_Project', cond);
            }
        }
        return this.condCache.get('buildProduceBugType_Project');
    }

    protected getCurUserResolveCond() {
        if (!this.condCache.has('curUserResolve')) {
            const strCond: any[] = ['AND', ['EQ', 'RESOLVEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('curUserResolve', cond);
            }
        }
        return this.condCache.get('curUserResolve');
    }

    protected getDefaultCond() {
        return this.condCache.get('default');
    }

    protected getESBulkCond() {
        return this.condCache.get('eSBulk');
    }

    protected getMyCond() {
        return this.condCache.get('my');
    }

    protected getMyAgentBugCond() {
        return this.condCache.get('myAgentBug');
    }

    protected getMyCreateOrPartakeCond() {
        if (!this.condCache.has('myCreateOrPartake')) {
            const strCond: any[] = ['AND', ['OR', ['EQ', 'OPENEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'CLOSEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'RESOLVEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'LASTEDITEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}]]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('myCreateOrPartake', cond);
            }
        }
        return this.condCache.get('myCreateOrPartake');
    }

    protected getMyCurOpenedBugCond() {
        if (!this.condCache.has('myCurOpenedBug')) {
            const strCond: any[] = ['AND', ['EQ', 'OPENEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('myCurOpenedBug', cond);
            }
        }
        return this.condCache.get('myCurOpenedBug');
    }

    protected getMyFavoritesCond() {
        return this.condCache.get('myFavorites');
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

    protected getNotCurPlanLinkBugCond() {
        return this.condCache.get('notCurPlanLinkBug');
    }

    protected getReleaseBugsCond() {
        return this.condCache.get('releaseBugs');
    }

    protected getReleaseLeftBugsCond() {
        return this.condCache.get('releaseLeftBugs');
    }

    protected getReleaseLinkableLeftBugCond() {
        if (!this.condCache.has('releaseLinkableLeftBug')) {
            const strCond: any[] = ['AND', ['EQ', 'PRODUCT',{ type: 'DATACONTEXT', value: 'product'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('releaseLinkableLeftBug', cond);
            }
        }
        return this.condCache.get('releaseLinkableLeftBug');
    }

    protected getReleaseLinkableResolvedBugCond() {
        if (!this.condCache.has('releaseLinkableResolvedBug')) {
            const strCond: any[] = ['AND', ['EQ', 'PRODUCT',{ type: 'DATACONTEXT', value: 'product'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('releaseLinkableResolvedBug', cond);
            }
        }
        return this.condCache.get('releaseLinkableResolvedBug');
    }

    protected getReportBugsCond() {
        return this.condCache.get('reportBugs');
    }

    protected getSelectBugByBuildCond() {
        if (!this.condCache.has('selectBugByBuild')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('selectBugByBuild', cond);
            }
        }
        return this.condCache.get('selectBugByBuild');
    }

    protected getSelectBugsByProjectCond() {
        return this.condCache.get('selectBugsByProject');
    }

    protected getSimpleCond() {
        return this.condCache.get('simple');
    }

    protected getStoryFormBugCond() {
        if (!this.condCache.has('storyFormBug')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('storyFormBug', cond);
            }
        }
        return this.condCache.get('storyFormBug');
    }

    protected getTaskBugCond() {
        return this.condCache.get('taskBug');
    }

    protected getViewCond() {
        return this.condCache.get('view');
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && _context.project && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/bugs/getdraft`, _data);
            return res;
        }
        if (_context.test && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/tests/${_context.test}/bugs/getdraft`, _data);
            return res;
        }
        if (_context.project && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/bugs/getdraft`, _data);
            return res;
        }
        if (_context.sysaccount && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/bugs/getdraft`, _data);
            return res;
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && _context.project && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/bugs/fetchdefault`, _data);
        }
        if (_context.test && true) {
            return this.http.post(`/tests/${_context.test}/bugs/fetchdefault`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/bugs/fetchdefault`, _data);
        }
        if (_context.sysaccount && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/bugs/fetchdefault`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchAccount
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchAccount(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && _context.project && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/bugs/fetchaccount`, _data);
        }
        if (_context.test && true) {
            return this.http.post(`/tests/${_context.test}/bugs/fetchaccount`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/bugs/fetchaccount`, _data);
        }
        if (_context.sysaccount && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/bugs/fetchaccount`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Resolve
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async Resolve(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && _context.project && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/bugs/${_context.bug}/resolve`, _data);
        }
        if (_context.test && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tests/${_context.test}/bugs/${_context.bug}/resolve`, _data);
        }
        if (_context.project && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/resolve`, _data);
        }
        if (_context.sysaccount && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/sysaccounts/${_context.sysaccount}/bugs/${_context.bug}/resolve`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Activate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async Activate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && _context.project && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/bugs/${_context.bug}/activate`, _data);
        }
        if (_context.test && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tests/${_context.test}/bugs/${_context.bug}/activate`, _data);
        }
        if (_context.project && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/activate`, _data);
        }
        if (_context.sysaccount && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/sysaccounts/${_context.sysaccount}/bugs/${_context.bug}/activate`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchProductBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchProductBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && _context.project && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/bugs/fetchproductbug`, _data);
        }
        if (_context.test && true) {
            return this.http.post(`/tests/${_context.test}/bugs/fetchproductbug`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/bugs/fetchproductbug`, _data);
        }
        if (_context.sysaccount && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/bugs/fetchproductbug`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && _context.project && _context.bug) {
            return this.http.delete(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/bugs/${_context.bug}`);
        }
        if (_context.test && _context.bug) {
            return this.http.delete(`/tests/${_context.test}/bugs/${_context.bug}`);
        }
        if (_context.project && _context.bug) {
            return this.http.delete(`/projects/${_context.project}/bugs/${_context.bug}`);
        }
        if (_context.sysaccount && _context.bug) {
            return this.http.delete(`/sysaccounts/${_context.sysaccount}/bugs/${_context.bug}`);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchMyFavorites
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchMyFavorites(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && _context.project && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/bugs/fetchmyfavorites`, _data);
        }
        if (_context.test && true) {
            return this.http.post(`/tests/${_context.test}/bugs/fetchmyfavorites`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/bugs/fetchmyfavorites`, _data);
        }
        if (_context.sysaccount && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/bugs/fetchmyfavorites`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && _context.project && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/bugs/${_context.bug}`, _data);
        }
        if (_context.test && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/tests/${_context.test}/bugs/${_context.bug}`, _data);
        }
        if (_context.project && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/bugs/${_context.bug}`, _data);
        }
        if (_context.sysaccount && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/sysaccounts/${_context.sysaccount}/bugs/${_context.bug}`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && _context.project && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/bugs`, _data);
        }
        if (_context.test && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/tests/${_context.test}/bugs`, _data);
        }
        if (_context.project && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/bugs`, _data);
        }
        if (_context.sysaccount && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/sysaccounts/${_context.sysaccount}/bugs`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * BugNFavorites
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async BugNFavorites(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && _context.project && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/bugs/${_context.bug}/bugnfavorites`, _data);
        }
        if (_context.test && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tests/${_context.test}/bugs/${_context.bug}/bugnfavorites`, _data);
        }
        if (_context.project && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/bugnfavorites`, _data);
        }
        if (_context.sysaccount && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/sysaccounts/${_context.sysaccount}/bugs/${_context.bug}/bugnfavorites`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && _context.project && _context.bug) {
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/bugs/${_context.bug}`);
            return res;
        }
        if (_context.test && _context.bug) {
            const res = await this.http.get(`/tests/${_context.test}/bugs/${_context.bug}`);
            return res;
        }
        if (_context.project && _context.bug) {
            const res = await this.http.get(`/projects/${_context.project}/bugs/${_context.bug}`);
            return res;
        }
        if (_context.sysaccount && _context.bug) {
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/bugs/${_context.bug}`);
            return res;
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * BugFavorites
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async BugFavorites(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && _context.project && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/bugs/${_context.bug}/bugfavorites`, _data);
        }
        if (_context.test && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tests/${_context.test}/bugs/${_context.bug}/bugfavorites`, _data);
        }
        if (_context.project && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/bugfavorites`, _data);
        }
        if (_context.sysaccount && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/sysaccounts/${_context.sysaccount}/bugs/${_context.bug}/bugfavorites`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Confirm
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async Confirm(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && _context.project && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/bugs/${_context.bug}/confirm`, _data);
        }
        if (_context.test && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tests/${_context.test}/bugs/${_context.bug}/confirm`, _data);
        }
        if (_context.project && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/confirm`, _data);
        }
        if (_context.sysaccount && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/sysaccounts/${_context.sysaccount}/bugs/${_context.bug}/confirm`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * AssignTo
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async AssignTo(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && _context.project && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/bugs/${_context.bug}/assignto`, _data);
        }
        if (_context.test && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tests/${_context.test}/bugs/${_context.bug}/assignto`, _data);
        }
        if (_context.project && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/assignto`, _data);
        }
        if (_context.sysaccount && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/sysaccounts/${_context.sysaccount}/bugs/${_context.bug}/assignto`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchProjectBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchProjectBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && _context.project && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/bugs/fetchprojectbug`, _data);
        }
        if (_context.test && true) {
            return this.http.post(`/tests/${_context.test}/bugs/fetchprojectbug`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/bugs/fetchprojectbug`, _data);
        }
        if (_context.sysaccount && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/bugs/fetchprojectbug`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchMy
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchMy(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && _context.project && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/bugs/fetchmy`, _data);
        }
        if (_context.test && true) {
            return this.http.post(`/tests/${_context.test}/bugs/fetchmy`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/bugs/fetchmy`, _data);
        }
        if (_context.sysaccount && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/bugs/fetchmy`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * GetUserConcat
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async GetUserConcat(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const appLogic = new GetCurUserConcatLogic(_context, _data);
        _data = await appLogic.onExecute();
        return new HttpResponse(_data);
    }

    /**
     * ResolveBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async ResolveBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.sysaccount && _context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/bugs/resolvebatch`,_data);
        }
        if(_context.test && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tests/${_context.test}/bugs/resolvebatch`,_data);
        }
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/bugs/resolvebatch`,_data);
        }
        if(_context.sysaccount && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/sysaccounts/${_context.sysaccount}/bugs/resolvebatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * ActivateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async ActivateBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.sysaccount && _context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/bugs/activatebatch`,_data);
        }
        if(_context.test && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tests/${_context.test}/bugs/activatebatch`,_data);
        }
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/bugs/activatebatch`,_data);
        }
        if(_context.sysaccount && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/sysaccounts/${_context.sysaccount}/bugs/activatebatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * ConfirmBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async ConfirmBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.sysaccount && _context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/bugs/confirmbatch`,_data);
        }
        if(_context.test && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tests/${_context.test}/bugs/confirmbatch`,_data);
        }
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/bugs/confirmbatch`,_data);
        }
        if(_context.sysaccount && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/sysaccounts/${_context.sysaccount}/bugs/confirmbatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * AssignToBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async AssignToBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.sysaccount && _context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/bugs/assigntobatch`,_data);
        }
        if(_context.test && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tests/${_context.test}/bugs/assigntobatch`,_data);
        }
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/bugs/assigntobatch`,_data);
        }
        if(_context.sysaccount && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/sysaccounts/${_context.sysaccount}/bugs/assigntobatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
}
