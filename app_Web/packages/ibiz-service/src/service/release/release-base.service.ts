import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IRelease, Release } from '../../entities';
import keys from '../../entities/release/release-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 发布服务对象基类
 *
 * @export
 * @class ReleaseBaseService
 * @extends {EntityBaseService}
 */
export class ReleaseBaseService extends EntityBaseService<IRelease> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'Release';
    protected APPDENAMEPLURAL = 'Releases';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
        product: 'product',
    };

    newEntity(data: IRelease): Release {
        return new Release(data);
    }

    async addLocal(context: IContext, entity: IRelease): Promise<IRelease | null> {
        return this.cache.add(context, new Release(entity) as any);
    }

    async createLocal(context: IContext, entity: IRelease): Promise<IRelease | null> {
        return super.createLocal(context, new Release(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IRelease> {
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

    async updateLocal(context: IContext, entity: IRelease): Promise<IRelease> {
        return super.updateLocal(context, new Release(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IRelease = {}): Promise<IRelease> {
        if (_context.product && _context.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(_context, _context.product);
            if (data) {
                entity.productname = data.name;
                entity.product = data.id;
            }
        }
        return new Release(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ReleaseService
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
        return this.condCache.get('default');
    }

    protected getGetListCond() {
        if (!this.condCache.has('getList')) {
            const strCond: any[] = ['AND', ['EQ', 'PRODUCT',{ type: 'DATACONTEXT', value: 'product'}], ['EQ', 'STATUS',{ type: 'DATACONTEXT', value: 'status'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('getList', cond);
            }
        }
        return this.condCache.get('getList');
    }

    protected getReportReleaseCond() {
        if (!this.condCache.has('reportRelease')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('reportRelease', cond);
            }
        }
        return this.condCache.get('reportRelease');
    }

    protected getViewCond() {
        return this.condCache.get('view');
    }
}
