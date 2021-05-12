import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIBZPROJECTTEAM, IBZPROJECTTEAM } from '../../entities';
import keys from '../../entities/ibzprojectteam/ibzprojectteam-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';
import { SearchFilter } from 'ibiz-core';

/**
 * 项目团队服务对象基类
 *
 * @export
 * @class IBZPROJECTTEAMBaseService
 * @extends {EntityBaseService}
 */
export class IBZPROJECTTEAMBaseService extends EntityBaseService<IIBZPROJECTTEAM> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Mob';
    protected APPDENAME = 'IBZPROJECTTEAM';
    protected APPDENAMEPLURAL = 'IBZPROJECTTEAMs';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'account';
    protected quickSearchFields = ['account',];
    protected selectContextParam = {
        project: 'root',
    };

    newEntity(data: IIBZPROJECTTEAM): IBZPROJECTTEAM {
        return new IBZPROJECTTEAM(data);
    }

    async addLocal(context: IContext, entity: IIBZPROJECTTEAM): Promise<IIBZPROJECTTEAM | null> {
        return this.cache.add(context, new IBZPROJECTTEAM(entity) as any);
    }

    async createLocal(context: IContext, entity: IIBZPROJECTTEAM): Promise<IIBZPROJECTTEAM | null> {
        return super.createLocal(context, new IBZPROJECTTEAM(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIBZPROJECTTEAM> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.root && entity.root !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(context, entity.root);
            if (data) {
                entity.projectname = data.name;
                entity.root = data.id;
                entity.project = data;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIBZPROJECTTEAM): Promise<IIBZPROJECTTEAM> {
        return super.updateLocal(context, new IBZPROJECTTEAM(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIBZPROJECTTEAM = {}): Promise<IIBZPROJECTTEAM> {
        if (_context.project && _context.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(_context, _context.project);
            if (data) {
                entity.projectname = data.name;
                entity.root = data.id;
                entity.project = data;
            }
        }
        return new IBZPROJECTTEAM(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZPROJECTTEAMService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }

    protected getDefaultCond() {
        if (!this.condCache.has('default')) {
            const strCond: any[] = ['AND', ['EQ', 'TYPE','project']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('default', cond);
            }
        }
        return this.condCache.get('default');
    }

    protected getProjectTeamPmCond() {
        return this.condCache.get('projectTeamPm');
    }

    protected getRowEditDefaultCond() {
        return this.condCache.get('rowEditDefault');
    }

    protected getTaskCntEstimateConsumedLeftCond() {
        return this.condCache.get('taskCntEstimateConsumedLeft');
    }

    protected getViewCond() {
        if (!this.condCache.has('view')) {
            const strCond: any[] = ['AND', ['EQ', 'TYPE','project']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('view', cond);
            }
        }
        return this.condCache.get('view');
    }
    /**
     * GetUserRole
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZPROJECTTEAMService
     */
    async GetUserRole(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        throw new Error('自定义实体行为「GetUserRole」需要重写实现');
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZPROJECTTEAMService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const cond: any = this.getDefaultCond();
        return this.searchAppLocal(cond, new SearchFilter(_context, _data));
    }
    /**
     * FetchProjectTeamPm
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZPROJECTTEAMService
     */
    async FetchProjectTeamPm(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const cond: any = this.getProjectTeamPmCond();
        return this.searchAppLocal(cond, new SearchFilter(_context, _data));
    }
    /**
     * FetchRowEditDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZPROJECTTEAMService
     */
    async FetchRowEditDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const cond: any = this.getRowEditDefaultCond();
        return this.searchAppLocal(cond, new SearchFilter(_context, _data));
    }
    /**
     * FetchTaskCntEstimateConsumedLeft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZPROJECTTEAMService
     */
    async FetchTaskCntEstimateConsumedLeft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const cond: any = this.getTaskCntEstimateConsumedLeftCond();
        return this.searchAppLocal(cond, new SearchFilter(_context, _data));
    }
}
