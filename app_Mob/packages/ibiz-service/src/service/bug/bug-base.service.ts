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
    protected APPNAME = 'Mob';
    protected APPDENAME = 'Bug';
    protected APPDENAMEPLURAL = 'Bugs';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'title';
    protected quickSearchFields = ['id','title',];
    protected selectContextParam = {
        product: 'product',
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
        if (entity && entity.product && entity.product !== '') {
            const s = await ___ibz___.gs.getProductService();
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
        if (_context.product && _context.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(_context, _context.product);
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
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.bug) {
            return this.http.get(`/products/${_context.product}/bugs/${_context.bug}/select`);
        }
        return this.http.get(`/bugs/${_context.bug}/select`);
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
        if (_context.product && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/bugs`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/bugs`, _data);
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
        if (_context.product && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/bugs/${_context.bug}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/bugs/${_context.bug}`, _data);
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
        if (_context.product && _context.bug) {
            return this.http.delete(`/products/${_context.product}/bugs/${_context.bug}`);
        }
        return this.http.delete(`/bugs/${_context.bug}`);
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
        if (_context.product && _context.bug) {
            const res = await this.http.get(`/products/${_context.product}/bugs/${_context.bug}`);
            return res;
        }
        const res = await this.http.get(`/bugs/${_context.bug}`);
        return res;
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
        if (_context.product && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/bugs/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/bugs/getdraft`, _data);
        return res;
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
        if (_context.product && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/activate`, _data);
        }
        return this.http.post(`/bugs/${_context.bug}/activate`, _data);
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
        if (_context.product && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/assignto`, _data);
        }
        return this.http.post(`/bugs/${_context.bug}/assignto`, _data);
    }
    /**
     * BatchUnlinkBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async BatchUnlinkBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/batchunlinkbug`, _data);
        }
        return this.http.post(`/bugs/${_context.bug}/batchunlinkbug`, _data);
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
        if (_context.product && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/bugfavorites`, _data);
        }
        return this.http.post(`/bugs/${_context.bug}/bugfavorites`, _data);
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
        if (_context.product && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/bugnfavorites`, _data);
        }
        return this.http.post(`/bugs/${_context.bug}/bugnfavorites`, _data);
    }
    /**
     * BuildBatchUnlinkBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async BuildBatchUnlinkBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/buildbatchunlinkbug`, _data);
        }
        return this.http.post(`/bugs/${_context.bug}/buildbatchunlinkbug`, _data);
    }
    /**
     * BuildLinkBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async BuildLinkBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/buildlinkbug`, _data);
        }
        return this.http.post(`/bugs/${_context.bug}/buildlinkbug`, _data);
    }
    /**
     * BuildUnlinkBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async BuildUnlinkBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/buildunlinkbug`, _data);
        }
        return this.http.post(`/bugs/${_context.bug}/buildunlinkbug`, _data);
    }
    /**
     * Close
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async Close(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/close`, _data);
        }
        return this.http.post(`/bugs/${_context.bug}/close`, _data);
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
        if (_context.product && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/confirm`, _data);
        }
        return this.http.post(`/bugs/${_context.bug}/confirm`, _data);
    }
    /**
     * LinkBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async LinkBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/linkbug`, _data);
        }
        return this.http.post(`/bugs/${_context.bug}/linkbug`, _data);
    }
    /**
     * ReleaaseBatchUnlinkBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async ReleaaseBatchUnlinkBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/releaasebatchunlinkbug`, _data);
        }
        return this.http.post(`/bugs/${_context.bug}/releaasebatchunlinkbug`, _data);
    }
    /**
     * ReleaseLinkBugbyBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async ReleaseLinkBugbyBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/releaselinkbugbybug`, _data);
        }
        return this.http.post(`/bugs/${_context.bug}/releaselinkbugbybug`, _data);
    }
    /**
     * ReleaseLinkBugbyLeftBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async ReleaseLinkBugbyLeftBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/releaselinkbugbyleftbug`, _data);
        }
        return this.http.post(`/bugs/${_context.bug}/releaselinkbugbyleftbug`, _data);
    }
    /**
     * ReleaseUnLinkBugbyLeftBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async ReleaseUnLinkBugbyLeftBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/releaseunlinkbugbyleftbug`, _data);
        }
        return this.http.post(`/bugs/${_context.bug}/releaseunlinkbugbyleftbug`, _data);
    }
    /**
     * ReleaseUnlinkBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async ReleaseUnlinkBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/releaseunlinkbug`, _data);
        }
        return this.http.post(`/bugs/${_context.bug}/releaseunlinkbug`, _data);
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
        if (_context.product && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/resolve`, _data);
        }
        return this.http.post(`/bugs/${_context.bug}/resolve`, _data);
    }
    /**
     * SendMessage
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async SendMessage(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/sendmessage`, _data);
        }
        return this.http.post(`/bugs/${_context.bug}/sendmessage`, _data);
    }
    /**
     * SendMsgPreProcess
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async SendMsgPreProcess(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/sendmsgpreprocess`, _data);
        }
        return this.http.post(`/bugs/${_context.bug}/sendmsgpreprocess`, _data);
    }
    /**
     * TestScript
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async TestScript(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/testscript`, _data);
        }
        return this.http.post(`/bugs/${_context.bug}/testscript`, _data);
    }
    /**
     * ToStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async ToStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/tostory`, _data);
        }
        return this.http.post(`/bugs/${_context.bug}/tostory`, _data);
    }
    /**
     * UnlinkBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async UnlinkBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/unlinkbug`, _data);
        }
        return this.http.post(`/bugs/${_context.bug}/unlinkbug`, _data);
    }
    /**
     * UpdateStoryVersion
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async UpdateStoryVersion(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.bug) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/bugs/${_context.bug}/updatestoryversion`, _data);
        }
        return this.http.put(`/bugs/${_context.bug}/updatestoryversion`, _data);
    }
    /**
     * FetchAssignedToMyBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchAssignedToMyBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchassignedtomybug`, _data);
        }
        return this.http.post(`/bugs/fetchassignedtomybug`, _data);
    }
    /**
     * FetchAssignedToMyBugPc
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchAssignedToMyBugPc(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchassignedtomybugpc`, _data);
        }
        return this.http.post(`/bugs/fetchassignedtomybugpc`, _data);
    }
    /**
     * FetchBugsByBuild
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchBugsByBuild(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchbugsbybuild`, _data);
        }
        return this.http.post(`/bugs/fetchbugsbybuild`, _data);
    }
    /**
     * FetchBuildBugs
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchBuildBugs(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchbuildbugs`, _data);
        }
        return this.http.post(`/bugs/fetchbuildbugs`, _data);
    }
    /**
     * FetchBuildLinkResolvedBugs
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchBuildLinkResolvedBugs(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchbuildlinkresolvedbugs`, _data);
        }
        return this.http.post(`/bugs/fetchbuildlinkresolvedbugs`, _data);
    }
    /**
     * FetchBuildOpenBugs
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchBuildOpenBugs(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchbuildopenbugs`, _data);
        }
        return this.http.post(`/bugs/fetchbuildopenbugs`, _data);
    }
    /**
     * FetchBuildProduceBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchBuildProduceBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchbuildproducebug`, _data);
        }
        return this.http.post(`/bugs/fetchbuildproducebug`, _data);
    }
    /**
     * FetchBuildProduceBugModule
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchBuildProduceBugModule(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchbuildproducebugmodule`, _data);
        }
        return this.http.post(`/bugs/fetchbuildproducebugmodule`, _data);
    }
    /**
     * FetchBuildProduceBugModule_Project
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchBuildProduceBugModule_Project(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchbuildproducebugmodule_project`, _data);
        }
        return this.http.post(`/bugs/fetchbuildproducebugmodule_project`, _data);
    }
    /**
     * FetchBuildProduceBugOpenedBy
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchBuildProduceBugOpenedBy(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchbuildproducebugopenedby`, _data);
        }
        return this.http.post(`/bugs/fetchbuildproducebugopenedby`, _data);
    }
    /**
     * FetchBuildProduceBugOpenedBy_Project
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchBuildProduceBugOpenedBy_Project(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchbuildproducebugopenedby_project`, _data);
        }
        return this.http.post(`/bugs/fetchbuildproducebugopenedby_project`, _data);
    }
    /**
     * FetchBuildProduceBugRES
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchBuildProduceBugRES(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchbuildproducebugres`, _data);
        }
        return this.http.post(`/bugs/fetchbuildproducebugres`, _data);
    }
    /**
     * FetchBuildProduceBugRESOLVEDBY
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchBuildProduceBugRESOLVEDBY(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchbuildproducebugresolvedby`, _data);
        }
        return this.http.post(`/bugs/fetchbuildproducebugresolvedby`, _data);
    }
    /**
     * FetchBuildProduceBugRESOLVEDBY_Project
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchBuildProduceBugRESOLVEDBY_Project(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchbuildproducebugresolvedby_project`, _data);
        }
        return this.http.post(`/bugs/fetchbuildproducebugresolvedby_project`, _data);
    }
    /**
     * FetchBuildProduceBugResolution_Project
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchBuildProduceBugResolution_Project(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchbuildproducebugresolution_project`, _data);
        }
        return this.http.post(`/bugs/fetchbuildproducebugresolution_project`, _data);
    }
    /**
     * FetchBuildProduceBugSeverity_Project
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchBuildProduceBugSeverity_Project(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchbuildproducebugseverity_project`, _data);
        }
        return this.http.post(`/bugs/fetchbuildproducebugseverity_project`, _data);
    }
    /**
     * FetchBuildProduceBugStatus_Project
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchBuildProduceBugStatus_Project(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchbuildproducebugstatus_project`, _data);
        }
        return this.http.post(`/bugs/fetchbuildproducebugstatus_project`, _data);
    }
    /**
     * FetchBuildProduceBugType_Project
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchBuildProduceBugType_Project(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchbuildproducebugtype_project`, _data);
        }
        return this.http.post(`/bugs/fetchbuildproducebugtype_project`, _data);
    }
    /**
     * FetchCurUserResolve
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchCurUserResolve(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchcuruserresolve`, _data);
        }
        return this.http.post(`/bugs/fetchcuruserresolve`, _data);
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
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchdefault`, _data);
        }
        return this.http.post(`/bugs/fetchdefault`, _data);
    }
    /**
     * FetchESBulk
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchESBulk(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchesbulk`, _data);
        }
        return this.http.post(`/bugs/fetchesbulk`, _data);
    }
    /**
     * FetchMyAgentBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchMyAgentBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchmyagentbug`, _data);
        }
        return this.http.post(`/bugs/fetchmyagentbug`, _data);
    }
    /**
     * FetchMyCurOpenedBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchMyCurOpenedBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchmycuropenedbug`, _data);
        }
        return this.http.post(`/bugs/fetchmycuropenedbug`, _data);
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
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchmyfavorites`, _data);
        }
        return this.http.post(`/bugs/fetchmyfavorites`, _data);
    }
    /**
     * FetchNotCurPlanLinkBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchNotCurPlanLinkBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchnotcurplanlinkbug`, _data);
        }
        return this.http.post(`/bugs/fetchnotcurplanlinkbug`, _data);
    }
    /**
     * FetchProjectBugs
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchProjectBugs(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchprojectbugs`, _data);
        }
        return this.http.post(`/bugs/fetchprojectbugs`, _data);
    }
    /**
     * FetchReleaseBugs
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchReleaseBugs(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchreleasebugs`, _data);
        }
        return this.http.post(`/bugs/fetchreleasebugs`, _data);
    }
    /**
     * FetchReleaseLeftBugs
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchReleaseLeftBugs(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchreleaseleftbugs`, _data);
        }
        return this.http.post(`/bugs/fetchreleaseleftbugs`, _data);
    }
    /**
     * FetchReleaseLinkableLeftBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchReleaseLinkableLeftBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchreleaselinkableleftbug`, _data);
        }
        return this.http.post(`/bugs/fetchreleaselinkableleftbug`, _data);
    }
    /**
     * FetchReleaseLinkableResolvedBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchReleaseLinkableResolvedBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchreleaselinkableresolvedbug`, _data);
        }
        return this.http.post(`/bugs/fetchreleaselinkableresolvedbug`, _data);
    }
    /**
     * FetchReportBugs
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchReportBugs(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchreportbugs`, _data);
        }
        return this.http.post(`/bugs/fetchreportbugs`, _data);
    }
    /**
     * FetchStoryFormBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchStoryFormBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchstoryformbug`, _data);
        }
        return this.http.post(`/bugs/fetchstoryformbug`, _data);
    }
    /**
     * FetchTaskRelatedBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugService
     */
    async FetchTaskRelatedBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/bugs/fetchtaskrelatedbug`, _data);
        }
        return this.http.post(`/bugs/fetchtaskrelatedbug`, _data);
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
     * ActivateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async ActivateBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/activatebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/bugs/activatebatch`,_data);
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
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/assigntobatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/bugs/assigntobatch`,_data);
    }

    /**
     * BatchUnlinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async BatchUnlinkBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/batchunlinkbugbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/bugs/batchunlinkbugbatch`,_data);
    }

    /**
     * BuildBatchUnlinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async BuildBatchUnlinkBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/buildbatchunlinkbugbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/bugs/buildbatchunlinkbugbatch`,_data);
    }

    /**
     * BuildLinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async BuildLinkBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/buildlinkbugbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/bugs/buildlinkbugbatch`,_data);
    }

    /**
     * BuildUnlinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async BuildUnlinkBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/buildunlinkbugbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/bugs/buildunlinkbugbatch`,_data);
    }

    /**
     * CloseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async CloseBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/closebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/bugs/closebatch`,_data);
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
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/confirmbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/bugs/confirmbatch`,_data);
    }

    /**
     * LinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async LinkBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/linkbugbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/bugs/linkbugbatch`,_data);
    }

    /**
     * ReleaaseBatchUnlinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async ReleaaseBatchUnlinkBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/releaasebatchunlinkbugbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/bugs/releaasebatchunlinkbugbatch`,_data);
    }

    /**
     * ReleaseLinkBugbyBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async ReleaseLinkBugbyBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/releaselinkbugbybugbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/bugs/releaselinkbugbybugbatch`,_data);
    }

    /**
     * ReleaseLinkBugbyLeftBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async ReleaseLinkBugbyLeftBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/releaselinkbugbyleftbugbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/bugs/releaselinkbugbyleftbugbatch`,_data);
    }

    /**
     * ReleaseUnLinkBugbyLeftBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async ReleaseUnLinkBugbyLeftBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/releaseunlinkbugbyleftbugbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/bugs/releaseunlinkbugbyleftbugbatch`,_data);
    }

    /**
     * ReleaseUnlinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async ReleaseUnlinkBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/releaseunlinkbugbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/bugs/releaseunlinkbugbatch`,_data);
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
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/resolvebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/bugs/resolvebatch`,_data);
    }

    /**
     * SendMessageBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async SendMessageBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/sendmessagebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/bugs/sendmessagebatch`,_data);
    }

    /**
     * SendMsgPreProcessBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async SendMsgPreProcessBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/sendmsgpreprocessbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/bugs/sendmsgpreprocessbatch`,_data);
    }

    /**
     * ToStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async ToStoryBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/tostorybatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/bugs/tostorybatch`,_data);
    }

    /**
     * UnlinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BugServiceBase
     */
    public async UnlinkBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/unlinkbugbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/bugs/unlinkbugbatch`,_data);
    }
}
