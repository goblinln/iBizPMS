import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIBZTaskTeam, IBZTaskTeam } from '../../entities';
import keys from '../../entities/ibztask-team/ibztask-team-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';
import { SearchFilter } from 'ibiz-core';

/**
 * 任务团队服务对象基类
 *
 * @export
 * @class IBZTaskTeamBaseService
 * @extends {EntityBaseService}
 */
export class IBZTaskTeamBaseService extends EntityBaseService<IIBZTaskTeam> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IBZTaskTeam';
    protected APPDENAMEPLURAL = 'IBZTaskTeams';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'account';
    protected quickSearchFields = ['account',];
    protected selectContextParam = {
        task: 'root',
    };

    newEntity(data: IIBZTaskTeam): IBZTaskTeam {
        return new IBZTaskTeam(data);
    }

    async addLocal(context: IContext, entity: IIBZTaskTeam): Promise<IIBZTaskTeam | null> {
        return this.cache.add(context, new IBZTaskTeam(entity) as any);
    }

    async createLocal(context: IContext, entity: IIBZTaskTeam): Promise<IIBZTaskTeam | null> {
        return super.createLocal(context, new IBZTaskTeam(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIBZTaskTeam> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.root && entity.root !== '') {
            const s = await ___ibz___.gs.getTaskService();
            const data = await s.getLocal2(context, entity.root);
            if (data) {
                entity.root = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIBZTaskTeam): Promise<IIBZTaskTeam> {
        return super.updateLocal(context, new IBZTaskTeam(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIBZTaskTeam = {}): Promise<IIBZTaskTeam> {
        if (_context.task && _context.task !== '') {
            const s = await ___ibz___.gs.getTaskService();
            const data = await s.getLocal2(_context, _context.task);
            if (data) {
                entity.root = data.id;
            }
        }
        return new IBZTaskTeam(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTaskTeamService
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
            const strCond: any[] = ['AND', ['EQ', 'TYPE','task']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('default', cond);
            }
        }
        return this.condCache.get('default');
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
     * @memberof IBZTaskTeamService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const cond: any = this.getDefaultCond();
        return this.searchAppLocal(cond, new SearchFilter(_context, _data));
    }
}
