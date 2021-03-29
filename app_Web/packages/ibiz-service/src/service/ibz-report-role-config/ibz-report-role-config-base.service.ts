import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbzReportRoleConfig, IbzReportRoleConfig } from '../../entities';
import keys from '../../entities/ibz-report-role-config/ibz-report-role-config-keys';

/**
 * 汇报角色配置服务对象基类
 *
 * @export
 * @class IbzReportRoleConfigBaseService
 * @extends {EntityBaseService}
 */
export class IbzReportRoleConfigBaseService extends EntityBaseService<IIbzReportRoleConfig> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IbzReportRoleConfig';
    protected APPDENAMEPLURAL = 'IbzReportRoleConfigs';
    protected APPDEKEY = 'ibzreportroleconfigid';
    protected APPDETEXT = 'ibzreportroleconfigname';
    protected quickSearchFields = ['ibzreportroleconfigname',];
    protected selectContextParam = {
    };

    newEntity(data: IIbzReportRoleConfig): IbzReportRoleConfig {
        return new IbzReportRoleConfig(data);
    }

    async addLocal(context: IContext, entity: IIbzReportRoleConfig): Promise<IIbzReportRoleConfig | null> {
        return this.cache.add(context, new IbzReportRoleConfig(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbzReportRoleConfig): Promise<IIbzReportRoleConfig | null> {
        return super.createLocal(context, new IbzReportRoleConfig(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbzReportRoleConfig> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbzReportRoleConfig): Promise<IIbzReportRoleConfig> {
        return super.updateLocal(context, new IbzReportRoleConfig(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbzReportRoleConfig = {}): Promise<IIbzReportRoleConfig> {
        return new IbzReportRoleConfig(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzReportRoleConfigService
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
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzReportRoleConfigService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzreportroleconfigs/${_context.ibzreportroleconfig}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzReportRoleConfigService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibzreportroleconfigs`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzReportRoleConfigService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibzreportroleconfigs/${_context.ibzreportroleconfig}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzReportRoleConfigService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/ibzreportroleconfigs/${_context.ibzreportroleconfig}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzReportRoleConfigService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/ibzreportroleconfigs/${_context.ibzreportroleconfig}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzReportRoleConfigService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibzreportroleconfigs/getdraft`, _data);
        return res;
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzReportRoleConfigService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzreportroleconfigs/fetchdefault`, _data);
    }
}