import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbztaskteam, Ibztaskteam } from '../../entities';
import keys from '../../entities/ibztaskteam/ibztaskteam-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';
import { SearchFilter } from 'ibiz-core';

/**
 * 任务团队服务对象基类
 *
 * @export
 * @class IbztaskteamBaseService
 * @extends {EntityBaseService}
 */
export class IbztaskteamBaseService extends EntityBaseService<IIbztaskteam> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Mob';
    protected APPDENAME = 'Ibztaskteam';
    protected APPDENAMEPLURAL = 'ibztaskteams';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Mob/PSAPPDATAENTITIES/ibztaskteam.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'account';
    protected quickSearchFields = ['account',];
    protected selectContextParam = {
        task: 'root',
    };

    newEntity(data: IIbztaskteam): Ibztaskteam {
        return new Ibztaskteam(data);
    }

    async addLocal(context: IContext, entity: IIbztaskteam): Promise<IIbztaskteam | null> {
        return this.cache.add(context, new Ibztaskteam(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbztaskteam): Promise<IIbztaskteam | null> {
        return super.createLocal(context, new Ibztaskteam(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbztaskteam> {
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

    async updateLocal(context: IContext, entity: IIbztaskteam): Promise<IIbztaskteam> {
        return super.updateLocal(context, new Ibztaskteam(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbztaskteam = {}): Promise<IIbztaskteam> {
        if (_context.task && _context.task !== '') {
            const s = await ___ibz___.gs.getTaskService();
            const data = await s.getLocal2(_context, _context.task);
            if (data) {
                entity.root = data.id;
            }
        }
        return new Ibztaskteam(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbztaskteamService
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
     * @memberof IbztaskteamService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const cond: any = this.getDefaultCond();
        return this.searchAppLocal(cond, new SearchFilter(_context, _data));
    }
}
