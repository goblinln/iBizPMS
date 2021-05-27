import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IBurn, Burn } from '../../entities';
import keys from '../../entities/burn/burn-keys';

/**
 * burn服务对象基类
 *
 * @export
 * @class BurnBaseService
 * @extends {EntityBaseService}
 */
export class BurnBaseService extends EntityBaseService<IBurn> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'Burn';
    protected APPDENAMEPLURAL = 'Burns';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'date';
    protected quickSearchFields = ['date',];
    protected selectContextParam = {
        project: 'project',
    };

    newEntity(data: IBurn): Burn {
        return new Burn(data);
    }

    async addLocal(context: IContext, entity: IBurn): Promise<IBurn | null> {
        return this.cache.add(context, new Burn(entity) as any);
    }

    async createLocal(context: IContext, entity: IBurn): Promise<IBurn | null> {
        return super.createLocal(context, new Burn(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IBurn> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.project && entity.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(context, entity.project);
            if (data) {
                entity.project = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IBurn): Promise<IBurn> {
        return super.updateLocal(context, new Burn(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IBurn = {}): Promise<IBurn> {
        if (_context.project && _context.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(_context, _context.project);
            if (data) {
                entity.project = data.id;
            }
        }
        return new Burn(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BurnService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }
}
