import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IDynaDashboard, DynaDashboard } from '../../entities';
import keys from '../../entities/dyna-dashboard/dyna-dashboard-keys';

/**
 * 动态数据看板服务对象基类
 *
 * @export
 * @class DynaDashboardBaseService
 * @extends {EntityBaseService}
 */
export class DynaDashboardBaseService extends EntityBaseService<IDynaDashboard> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'DynaDashboard';
    protected APPDENAMEPLURAL = 'DynaDashboards';
    protected APPDEKEY = 'dynadashboardid';
    protected APPDETEXT = 'dynadashboardname';
    protected quickSearchFields = ['dynadashboardname',];
    protected selectContextParam = {
    };

    newEntity(data: IDynaDashboard): DynaDashboard {
        return new DynaDashboard(data);
    }

    async addLocal(context: IContext, entity: IDynaDashboard): Promise<IDynaDashboard | null> {
        return this.cache.add(context, new DynaDashboard(entity) as any);
    }

    async createLocal(context: IContext, entity: IDynaDashboard): Promise<IDynaDashboard | null> {
        return super.createLocal(context, new DynaDashboard(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IDynaDashboard> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IDynaDashboard): Promise<IDynaDashboard> {
        return super.updateLocal(context, new DynaDashboard(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IDynaDashboard = {}): Promise<IDynaDashboard> {
        return new DynaDashboard(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DynaDashboardService
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
