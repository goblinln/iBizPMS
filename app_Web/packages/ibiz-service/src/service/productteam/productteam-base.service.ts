import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IPRODUCTTEAM, PRODUCTTEAM } from '../../entities';
import keys from '../../entities/productteam/productteam-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 产品团队服务对象基类
 *
 * @export
 * @class PRODUCTTEAMBaseService
 * @extends {EntityBaseService}
 */
export class PRODUCTTEAMBaseService extends EntityBaseService<IPRODUCTTEAM> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'PRODUCTTEAM';
    protected APPDENAMEPLURAL = 'PRODUCTTEAMs';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'account';
    protected quickSearchFields = ['account',];
    protected selectContextParam = {
        product: 'root',
    };

    newEntity(data: IPRODUCTTEAM): PRODUCTTEAM {
        return new PRODUCTTEAM(data);
    }

    async addLocal(context: IContext, entity: IPRODUCTTEAM): Promise<IPRODUCTTEAM | null> {
        return this.cache.add(context, new PRODUCTTEAM(entity) as any);
    }

    async createLocal(context: IContext, entity: IPRODUCTTEAM): Promise<IPRODUCTTEAM | null> {
        return super.createLocal(context, new PRODUCTTEAM(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IPRODUCTTEAM> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.root && entity.root !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(context, entity.root);
            if (data) {
                entity.root = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IPRODUCTTEAM): Promise<IPRODUCTTEAM> {
        return super.updateLocal(context, new PRODUCTTEAM(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IPRODUCTTEAM = {}): Promise<IPRODUCTTEAM> {
        if (_context.product && _context.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(_context, _context.product);
            if (data) {
                entity.root = data.id;
            }
        }
        return new PRODUCTTEAM(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PRODUCTTEAMService
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
            const strCond: any[] = ['AND', ['EQ', 'TYPE','product']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('default', cond);
            }
        }
        return this.condCache.get('default');
    }

    protected getProductTeamInfoCond() {
        return this.condCache.get('productTeamInfo');
    }

    protected getProjectAppCond() {
        if (!this.condCache.has('projectApp')) {
            const strCond: any[] = ['AND', ['EQ', 'ROOT',{ type: 'WEBCONTEXT', value: 'product'}], ['EQ', 'TYPE','product']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('projectApp', cond);
            }
        }
        return this.condCache.get('projectApp');
    }

    protected getRowEditDefaultProductTeamCond() {
        return this.condCache.get('rowEditDefaultProductTeam');
    }

    protected getViewCond() {
        return this.condCache.get('view');
    }
}
