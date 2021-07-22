import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IReportSummary, ReportSummary } from '../../entities';
import keys from '../../entities/report-summary/report-summary-keys';

/**
 * 汇报汇总服务对象基类
 *
 * @export
 * @class ReportSummaryBaseService
 * @extends {EntityBaseService}
 */
export class ReportSummaryBaseService extends EntityBaseService<IReportSummary> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Mob';
    protected APPDENAME = 'ReportSummary';
    protected APPDENAMEPLURAL = 'ReportSummaries';
    protected APPDEKEY = 'ibzdailyid';
    protected APPDETEXT = 'ibzdailyname';
    protected quickSearchFields = ['ibzdailyname',];
    protected selectContextParam = {
    };

    newEntity(data: IReportSummary): ReportSummary {
        return new ReportSummary(data);
    }

    async addLocal(context: IContext, entity: IReportSummary): Promise<IReportSummary | null> {
        return this.cache.add(context, new ReportSummary(entity) as any);
    }

    async createLocal(context: IContext, entity: IReportSummary): Promise<IReportSummary | null> {
        return super.createLocal(context, new ReportSummary(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IReportSummary> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IReportSummary): Promise<IReportSummary> {
        return super.updateLocal(context, new ReportSummary(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IReportSummary = {}): Promise<IReportSummary> {
        return new ReportSummary(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ReportSummaryService
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
     * FetchAll
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ReportSummaryService
     */
    async FetchAll(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/reportsummaries/fetchall`, _data);
    }
}
