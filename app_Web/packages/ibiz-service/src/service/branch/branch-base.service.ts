import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IBranch, Branch } from '../../entities';
import keys from '../../entities/branch/branch-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 产品的分支和平台信息服务对象基类
 *
 * @export
 * @class BranchBaseService
 * @extends {EntityBaseService}
 */
export class BranchBaseService extends EntityBaseService<IBranch> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'Branch';
    protected APPDENAMEPLURAL = 'Branches';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
        product: 'product',
    };

    newEntity(data: IBranch): Branch {
        return new Branch(data);
    }

    async addLocal(context: IContext, entity: IBranch): Promise<IBranch | null> {
        return this.cache.add(context, new Branch(entity) as any);
    }

    async createLocal(context: IContext, entity: IBranch): Promise<IBranch | null> {
        return super.createLocal(context, new Branch(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IBranch> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.product && entity.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(context, entity.product);
            if (data) {
                entity.product = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IBranch): Promise<IBranch> {
        return super.updateLocal(context, new Branch(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IBranch = {}): Promise<IBranch> {
        if (_context.product && _context.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(_context, _context.product);
            if (data) {
                entity.product = data.id;
            }
        }
        return new Branch(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BranchService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }

    protected getCurProductCond() {
        if (!this.condCache.has('curProduct')) {
            const strCond: any[] = ['AND', ['OR', ['EQ', 'PRODUCT',{ type: 'WEBCONTEXT', value: 'product'}], ['EQ', 'PRODUCT',{ type: 'DATACONTEXT', value: 'product'}], ['EQ', 'PRODUCT',{ type: 'DATACONTEXT', value: 'products'}]]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('curProduct', cond);
            }
        }
        return this.condCache.get('curProduct');
    }

    protected getDefaultCond() {
        return this.condCache.get('default');
    }

    protected getDefaultBranchCond() {
        if (!this.condCache.has('defaultBranch')) {
            const strCond: any[] = ['AND', ['OR', ['EQ', 'PRODUCT',{ type: 'WEBCONTEXT', value: 'product'}], ['EQ', 'PRODUCT',{ type: 'DATACONTEXT', value: 'product'}]]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('defaultBranch', cond);
            }
        }
        return this.condCache.get('defaultBranch');
    }

    protected getViewCond() {
        return this.condCache.get('view');
    }
}
