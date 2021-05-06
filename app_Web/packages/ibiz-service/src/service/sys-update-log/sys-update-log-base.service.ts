import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ISysUpdateLog, SysUpdateLog } from '../../entities';
import keys from '../../entities/sys-update-log/sys-update-log-keys';

/**
 * 更新日志服务对象基类
 *
 * @export
 * @class SysUpdateLogBaseService
 * @extends {EntityBaseService}
 */
export class SysUpdateLogBaseService extends EntityBaseService<ISysUpdateLog> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'SysUpdateLog';
    protected APPDENAMEPLURAL = 'SysUpdateLogs';
    protected APPDEKEY = 'sysupdatelogid';
    protected APPDETEXT = 'sysupdatelogname';
    protected quickSearchFields = ['sysupdatelogname',];
    protected selectContextParam = {
    };

    newEntity(data: ISysUpdateLog): SysUpdateLog {
        return new SysUpdateLog(data);
    }

    async addLocal(context: IContext, entity: ISysUpdateLog): Promise<ISysUpdateLog | null> {
        return this.cache.add(context, new SysUpdateLog(entity) as any);
    }

    async createLocal(context: IContext, entity: ISysUpdateLog): Promise<ISysUpdateLog | null> {
        return super.createLocal(context, new SysUpdateLog(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ISysUpdateLog> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: ISysUpdateLog): Promise<ISysUpdateLog> {
        return super.updateLocal(context, new SysUpdateLog(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ISysUpdateLog = {}): Promise<ISysUpdateLog> {
        return new SysUpdateLog(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysUpdateLogService
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
     * @memberof SysUpdateLogService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/sysupdatelogs/${_context.sysupdatelog}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysUpdateLogService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/sysupdatelogs`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysUpdateLogService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/sysupdatelogs/${_context.sysupdatelog}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysUpdateLogService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/sysupdatelogs/${_context.sysupdatelog}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysUpdateLogService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/sysupdatelogs/${_context.sysupdatelog}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysUpdateLogService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/sysupdatelogs/getdraft`, _data);
        return res;
    }
    /**
     * GetLastUpdateInfo
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysUpdateLogService
     */
    async GetLastUpdateInfo(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.put(`/sysupdatelogs/${_context.sysupdatelog}/getlastupdateinfo`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysUpdateLogService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/sysupdatelogs/fetchdefault`, _data);
    }

    /**
     * GetLastUpdateInfoBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SysUpdateLogServiceBase
     */
    public async GetLastUpdateInfoBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/sysupdatelogs/getlastupdateinfobatch`,tempData,isloading);
    }
}
