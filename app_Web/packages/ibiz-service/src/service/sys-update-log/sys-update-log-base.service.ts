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
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/SysUpdateLog.json';
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
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysUpdateLogService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        const res = await this.http.post(`/sysupdatelogs`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        const res = await this.http.get(`/sysupdatelogs/${_context.sysupdatelog}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/sysupdatelogs/getdraft`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        const res = await this.http.put(`/sysupdatelogs/${_context.sysupdatelog}/getlastupdateinfo`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        const res = await this.http.delete(`/sysupdatelogs/${_context.sysupdatelog}`);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
        const res = await this.http.put(`/sysupdatelogs/${_context.sysupdatelog}`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        const res = await this.http.post(`/sysupdatelogs/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        const res = await this.http.get(`/sysupdatelogs/${_context.sysupdatelog}/select`);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
    public async GetLastUpdateInfoBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        const res = await this.http.post(`/sysupdatelogs/getlastupdateinfobatch`,_data);
        return res;
    }
}
