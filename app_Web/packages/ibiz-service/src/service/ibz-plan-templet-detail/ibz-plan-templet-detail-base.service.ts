import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbzPlanTempletDetail, IbzPlanTempletDetail } from '../../entities';
import keys from '../../entities/ibz-plan-templet-detail/ibz-plan-templet-detail-keys';

/**
 * 计划模板详情服务对象基类
 *
 * @export
 * @class IbzPlanTempletDetailBaseService
 * @extends {EntityBaseService}
 */
export class IbzPlanTempletDetailBaseService extends EntityBaseService<IIbzPlanTempletDetail> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IbzPlanTempletDetail';
    protected APPDENAMEPLURAL = 'IbzPlanTempletDetails';
    protected APPDEKEY = 'ibzplantempletdetailid';
    protected APPDETEXT = 'ibzplantempletdetailname';
    protected quickSearchFields = ['ibzplantempletdetailname',];
    protected selectContextParam = {
    };

    newEntity(data: IIbzPlanTempletDetail): IbzPlanTempletDetail {
        return new IbzPlanTempletDetail(data);
    }

    async addLocal(context: IContext, entity: IIbzPlanTempletDetail): Promise<IIbzPlanTempletDetail | null> {
        return this.cache.add(context, new IbzPlanTempletDetail(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbzPlanTempletDetail): Promise<IIbzPlanTempletDetail | null> {
        return super.createLocal(context, new IbzPlanTempletDetail(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbzPlanTempletDetail> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbzPlanTempletDetail): Promise<IIbzPlanTempletDetail> {
        return super.updateLocal(context, new IbzPlanTempletDetail(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbzPlanTempletDetail = {}): Promise<IIbzPlanTempletDetail> {
        return new IbzPlanTempletDetail(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzPlanTempletDetailService
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
