import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ICompanyStats, CompanyStats } from '../../entities';
import keys from '../../entities/company-stats/company-stats-keys';

/**
 * 公司动态汇总服务对象基类
 *
 * @export
 * @class CompanyStatsBaseService
 * @extends {EntityBaseService}
 */
export class CompanyStatsBaseService extends EntityBaseService<ICompanyStats> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'CompanyStats';
    protected APPDENAMEPLURAL = 'CompanyStats';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/CompanyStats.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'comment';
    protected quickSearchFields = ['comment',];
    protected selectContextParam = {
    };

    newEntity(data: ICompanyStats): CompanyStats {
        return new CompanyStats(data);
    }

    async addLocal(context: IContext, entity: ICompanyStats): Promise<ICompanyStats | null> {
        return this.cache.add(context, new CompanyStats(entity) as any);
    }

    async createLocal(context: IContext, entity: ICompanyStats): Promise<ICompanyStats | null> {
        return super.createLocal(context, new CompanyStats(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ICompanyStats> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: ICompanyStats): Promise<ICompanyStats> {
        return super.updateLocal(context, new CompanyStats(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ICompanyStats = {}): Promise<ICompanyStats> {
        return new CompanyStats(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CompanyStatsService
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
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CompanyStatsService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        const res = await this.http.post(`/companystats`, _data);
        return res;
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CompanyStatsService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/companystats/${_context.companystats}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CompanyStatsService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/companystats/getdraft`, _data);
        return res;
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CompanyStatsService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.delete(`/companystats/${_context.companystats}`);
        return res;
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CompanyStatsService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        const res = await this.http.put(`/companystats/${_context.companystats}`, _data);
        return res;
    }
    /**
     * FetchCompanyDynamicStats
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CompanyStatsService
     */
    async FetchCompanyDynamicStats(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.post(`/companystats/fetchcompanydynamicstats`, _data);
        return res;
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CompanyStatsService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.post(`/companystats/fetchdefault`, _data);
        return res;
    }
    /**
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CompanyStatsService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/companystats/${_context.companystats}/select`);
        return res;
    }
}
