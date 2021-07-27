import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IDynaFilter, DynaFilter } from '../../entities';
import keys from '../../entities/dyna-filter/dyna-filter-keys';

/**
 * 动态搜索栏服务对象基类
 *
 * @export
 * @class DynaFilterBaseService
 * @extends {EntityBaseService}
 */
export class DynaFilterBaseService extends EntityBaseService<IDynaFilter> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'DynaFilter';
    protected APPDENAMEPLURAL = 'DynaFilters';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/DynaFilter.json';
    protected APPDEKEY = 'dynafilterid';
    protected APPDETEXT = 'dynafiltername';
    protected quickSearchFields = ['dynafiltername',];
    protected selectContextParam = {
    };

    constructor(opts?: any) {
        super(opts, 'DynaFilter');
    }

    newEntity(data: IDynaFilter): DynaFilter {
        return new DynaFilter(data);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IDynaFilter> {
        const entity = await super.getLocal(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IDynaFilter): Promise<IDynaFilter> {
        return super.updateLocal(context, new DynaFilter(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IDynaFilter = {}): Promise<IDynaFilter> {
        return new DynaFilter(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DynaFilterService
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
