import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IPlanTempletDetail, PlanTempletDetail } from '../../entities';
import keys from '../../entities/plan-templet-detail/plan-templet-detail-keys';
import { SearchFilter } from 'ibiz-core';

/**
 * 计划模板详情服务对象基类
 *
 * @export
 * @class PlanTempletDetailBaseService
 * @extends {EntityBaseService}
 */
export class PlanTempletDetailBaseService extends EntityBaseService<IPlanTempletDetail> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'PlanTempletDetail';
    protected APPDENAMEPLURAL = 'PlanTempletDetails';
    protected APPDEKEY = 'ibzplantempletdetailid';
    protected APPDETEXT = 'ibzplantempletdetailname';
    protected quickSearchFields = ['ibzplantempletdetailname',];
    protected selectContextParam = {
        ibzplantemplet: 'plantempletid',
    };

    newEntity(data: IPlanTempletDetail): PlanTempletDetail {
        return new PlanTempletDetail(data);
    }

    async addLocal(context: IContext, entity: IPlanTempletDetail): Promise<IPlanTempletDetail | null> {
        return this.cache.add(context, new PlanTempletDetail(entity) as any);
    }

    async createLocal(context: IContext, entity: IPlanTempletDetail): Promise<IPlanTempletDetail | null> {
        return super.createLocal(context, new PlanTempletDetail(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IPlanTempletDetail> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.plantempletid && entity.plantempletid !== '') {
            const s = await ___ibz___.gs.getIbzPlanTempletService();
            const data = await s.getLocal2(context, entity.plantempletid);
            if (data) {
                entity.plantempletid = data.ibzplantempletid;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IPlanTempletDetail): Promise<IPlanTempletDetail> {
        return super.updateLocal(context, new PlanTempletDetail(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IPlanTempletDetail = {}): Promise<IPlanTempletDetail> {
        if (_context.ibzplantemplet && _context.ibzplantemplet !== '') {
            const s = await ___ibz___.gs.getIbzPlanTempletService();
            const data = await s.getLocal2(_context, _context.ibzplantemplet);
            if (data) {
                entity.plantempletid = data.ibzplantempletid;
            }
        }
        return new PlanTempletDetail(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PlanTempletDetailService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PlanTempletDetailService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.searchAppLocal(null, new SearchFilter(_context, _data));
    }
}
