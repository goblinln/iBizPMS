import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProjectBug, ProjectBug } from '../../entities';
import keys from '../../entities/project-bug/project-bug-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';
import { GetCurUserConcatLogic } from '../../logic/entity/project-bug/get-cur-user-concat/get-cur-user-concat-logic';

/**
 * Bug服务对象基类
 *
 * @export
 * @class ProjectBugBaseService
 * @extends {EntityBaseService}
 */
export class ProjectBugBaseService extends EntityBaseService<IProjectBug> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'ProjectBug';
    protected APPDENAMEPLURAL = 'ProjectBugs';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'title';
    protected quickSearchFields = ['id','title',];
    protected selectContextParam = {
        project: 'project',
    };

    newEntity(data: IProjectBug): ProjectBug {
        return new ProjectBug(data);
    }

    async addLocal(context: IContext, entity: IProjectBug): Promise<IProjectBug | null> {
        return this.cache.add(context, new ProjectBug(entity) as any);
    }

    async createLocal(context: IContext, entity: IProjectBug): Promise<IProjectBug | null> {
        return super.createLocal(context, new ProjectBug(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProjectBug> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.project && entity.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(context, entity.project);
            if (data) {
                entity.projectname = data.name;
                entity.project = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IProjectBug): Promise<IProjectBug> {
        return super.updateLocal(context, new ProjectBug(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProjectBug = {}): Promise<IProjectBug> {
        if (_context.project && _context.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(_context, _context.project);
            if (data) {
                entity.projectname = data.name;
                entity.project = data.id;
            }
        }
        return new ProjectBug(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBugService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
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
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBugService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/projectbugs/fetchdefault`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBugService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/projectbugs`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBugService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectbug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/projectbugs/${_context.projectbug}`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBugService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectbug) {
            return this.http.delete(`/projects/${_context.project}/projectbugs/${_context.projectbug}`);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBugService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectbug) {
            const res = await this.http.get(`/projects/${_context.project}/projectbugs/${_context.projectbug}`);
            return res;
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBugService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/projectbugs/getdraft`, _data);
            return res;
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Activate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBugService
     */
    async Activate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectbug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/${_context.projectbug}/activate`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * AssignTo
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBugService
     */
    async AssignTo(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectbug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/${_context.projectbug}/assignto`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * BatchUnlinkBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBugService
     */
    async BatchUnlinkBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectbug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/${_context.projectbug}/batchunlinkbug`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * BugFavorites
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBugService
     */
    async BugFavorites(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectbug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/${_context.projectbug}/bugfavorites`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * BugNFavorites
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBugService
     */
    async BugNFavorites(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectbug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/${_context.projectbug}/bugnfavorites`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * BuildBatchUnlinkBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBugService
     */
    async BuildBatchUnlinkBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectbug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/${_context.projectbug}/buildbatchunlinkbug`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * BuildLinkBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBugService
     */
    async BuildLinkBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectbug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/${_context.projectbug}/buildlinkbug`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * BuildUnlinkBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBugService
     */
    async BuildUnlinkBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectbug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/${_context.projectbug}/buildunlinkbug`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Close
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBugService
     */
    async Close(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectbug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/${_context.projectbug}/close`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Confirm
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBugService
     */
    async Confirm(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectbug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/${_context.projectbug}/confirm`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * LinkBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBugService
     */
    async LinkBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectbug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/${_context.projectbug}/linkbug`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * ReleaaseBatchUnlinkBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBugService
     */
    async ReleaaseBatchUnlinkBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectbug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/${_context.projectbug}/releaasebatchunlinkbug`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * ReleaseLinkBugbyBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBugService
     */
    async ReleaseLinkBugbyBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectbug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/${_context.projectbug}/releaselinkbugbybug`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * ReleaseLinkBugbyLeftBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBugService
     */
    async ReleaseLinkBugbyLeftBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectbug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/${_context.projectbug}/releaselinkbugbyleftbug`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * ReleaseUnLinkBugbyLeftBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBugService
     */
    async ReleaseUnLinkBugbyLeftBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectbug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/${_context.projectbug}/releaseunlinkbugbyleftbug`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * ReleaseUnlinkBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBugService
     */
    async ReleaseUnlinkBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectbug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/${_context.projectbug}/releaseunlinkbug`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Resolve
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBugService
     */
    async Resolve(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectbug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/${_context.projectbug}/resolve`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * SendMessage
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBugService
     */
    async SendMessage(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectbug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/${_context.projectbug}/sendmessage`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * SendMsgPreProcess
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBugService
     */
    async SendMsgPreProcess(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectbug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/${_context.projectbug}/sendmsgpreprocess`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * TestScript
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBugService
     */
    async TestScript(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectbug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/${_context.projectbug}/testscript`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * ToStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBugService
     */
    async ToStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectbug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/${_context.projectbug}/tostory`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * UnlinkBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBugService
     */
    async UnlinkBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectbug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/${_context.projectbug}/unlinkbug`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * UpdateStoryVersion
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBugService
     */
    async UpdateStoryVersion(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectbug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/projectbugs/${_context.projectbug}/updatestoryversion`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * GetUserConcat
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBugService
     */
    async GetUserConcat(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const appLogic = new GetCurUserConcatLogic(_context, _data);
        _data = await appLogic.onExecute();
        return new HttpResponse(_data);
    }

    /**
     * ActivateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectBugServiceBase
     */
    public async ActivateBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/activatebatch`,_data);
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
     * @memberof ProjectBugServiceBase
     */
    public async AssignToBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/assigntobatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * BatchUnlinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectBugServiceBase
     */
    public async BatchUnlinkBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/batchunlinkbugbatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * BuildBatchUnlinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectBugServiceBase
     */
    public async BuildBatchUnlinkBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/buildbatchunlinkbugbatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * BuildLinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectBugServiceBase
     */
    public async BuildLinkBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/buildlinkbugbatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * BuildUnlinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectBugServiceBase
     */
    public async BuildUnlinkBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/buildunlinkbugbatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * CloseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectBugServiceBase
     */
    public async CloseBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/closebatch`,_data);
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
     * @memberof ProjectBugServiceBase
     */
    public async ConfirmBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/confirmbatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * LinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectBugServiceBase
     */
    public async LinkBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/linkbugbatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * ReleaaseBatchUnlinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectBugServiceBase
     */
    public async ReleaaseBatchUnlinkBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/releaasebatchunlinkbugbatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * ReleaseLinkBugbyBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectBugServiceBase
     */
    public async ReleaseLinkBugbyBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/releaselinkbugbybugbatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * ReleaseLinkBugbyLeftBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectBugServiceBase
     */
    public async ReleaseLinkBugbyLeftBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/releaselinkbugbyleftbugbatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * ReleaseUnLinkBugbyLeftBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectBugServiceBase
     */
    public async ReleaseUnLinkBugbyLeftBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/releaseunlinkbugbyleftbugbatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * ReleaseUnlinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectBugServiceBase
     */
    public async ReleaseUnlinkBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/releaseunlinkbugbatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * ResolveBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectBugServiceBase
     */
    public async ResolveBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/resolvebatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * SendMessageBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectBugServiceBase
     */
    public async SendMessageBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/sendmessagebatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * SendMsgPreProcessBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectBugServiceBase
     */
    public async SendMsgPreProcessBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/sendmsgpreprocessbatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * ToStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectBugServiceBase
     */
    public async ToStoryBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/tostorybatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * UnlinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectBugServiceBase
     */
    public async UnlinkBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectbugs/unlinkbugbatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
}
