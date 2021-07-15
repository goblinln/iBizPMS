import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ISysUpdateFeatures, SysUpdateFeatures } from '../../entities';
import keys from '../../entities/sys-update-features/sys-update-features-keys';

/**
 * 系统更新功能服务对象基类
 *
 * @export
 * @class SysUpdateFeaturesBaseService
 * @extends {EntityBaseService}
 */
export class SysUpdateFeaturesBaseService extends EntityBaseService<ISysUpdateFeatures> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'SysUpdateFeatures';
    protected APPDENAMEPLURAL = 'SysUpdateFeatures';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/SysUpdateFeatures.json';
    protected APPDEKEY = 'sysupdatefeaturesid';
    protected APPDETEXT = 'sysupdatefeaturesname';
    protected quickSearchFields = ['sysupdatefeaturesname',];
    protected selectContextParam = {
        sysupdatelog: 'sysupdatelogid',
    };

    newEntity(data: ISysUpdateFeatures): SysUpdateFeatures {
        return new SysUpdateFeatures(data);
    }

    async addLocal(context: IContext, entity: ISysUpdateFeatures): Promise<ISysUpdateFeatures | null> {
        return this.cache.add(context, new SysUpdateFeatures(entity) as any);
    }

    async createLocal(context: IContext, entity: ISysUpdateFeatures): Promise<ISysUpdateFeatures | null> {
        return super.createLocal(context, new SysUpdateFeatures(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ISysUpdateFeatures> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.sysupdatelogid && entity.sysupdatelogid !== '') {
            const s = await ___ibz___.gs.getSysUpdateLogService();
            const data = await s.getLocal2(context, entity.sysupdatelogid);
            if (data) {
                entity.sysupdatelogname = data.sysupdatelogname;
                entity.sysupdatelogid = data.sysupdatelogid;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: ISysUpdateFeatures): Promise<ISysUpdateFeatures> {
        return super.updateLocal(context, new SysUpdateFeatures(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ISysUpdateFeatures = {}): Promise<ISysUpdateFeatures> {
        if (_context.sysupdatelog && _context.sysupdatelog !== '') {
            const s = await ___ibz___.gs.getSysUpdateLogService();
            const data = await s.getLocal2(_context, _context.sysupdatelog);
            if (data) {
                entity.sysupdatelogname = data.sysupdatelogname;
                entity.sysupdatelogid = data.sysupdatelogid;
            }
        }
        return new SysUpdateFeatures(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysUpdateFeaturesService
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
     * @memberof SysUpdateFeaturesService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.sysupdatelog && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/sysupdatelogs/${_context.sysupdatelog}/sysupdatefeatures`, _data);
        res.data = await this.afterExecuteAction(_context,res?.data);
            return res;
        }
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        const res = await this.http.post(`/sysupdatefeatures`, _data);
        res.data = await this.afterExecuteAction(_context,res?.data);
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
     * @memberof SysUpdateFeaturesService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.sysupdatelog && _context.sysupdatefeatures) {
            const res = await this.http.get(`/sysupdatelogs/${_context.sysupdatelog}/sysupdatefeatures/${_context.sysupdatefeatures}`);
        res.data = await this.afterExecuteAction(_context,res?.data);
            return res;
        }
        const res = await this.http.get(`/sysupdatefeatures/${_context.sysupdatefeatures}`);
        res.data = await this.afterExecuteAction(_context,res?.data);
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
     * @memberof SysUpdateFeaturesService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.sysupdatelog && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/sysupdatelogs/${_context.sysupdatelog}/sysupdatefeatures/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/sysupdatefeatures/getdraft`, _data);
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
     * @memberof SysUpdateFeaturesService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.sysupdatelog && _context.sysupdatefeatures) {
            const res = await this.http.delete(`/sysupdatelogs/${_context.sysupdatelog}/sysupdatefeatures/${_context.sysupdatefeatures}`);
            return res;
        }
        const res = await this.http.delete(`/sysupdatefeatures/${_context.sysupdatefeatures}`);
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
     * @memberof SysUpdateFeaturesService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.sysupdatelog && _context.sysupdatefeatures) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data);
            const res = await this.http.put(`/sysupdatelogs/${_context.sysupdatelog}/sysupdatefeatures/${_context.sysupdatefeatures}`, _data);
        res.data = await this.afterExecuteAction(_context,res?.data);
            return res;
        }
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data);
        const res = await this.http.put(`/sysupdatefeatures/${_context.sysupdatefeatures}`, _data);
        res.data = await this.afterExecuteAction(_context,res?.data);
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
     * @memberof SysUpdateFeaturesService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.sysupdatelog && true) {
            const res = await this.http.post(`/sysupdatelogs/${_context.sysupdatelog}/sysupdatefeatures/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        const res = await this.http.post(`/sysupdatefeatures/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
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
     * @memberof SysUpdateFeaturesService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.sysupdatelog && _context.sysupdatefeatures) {
            const res = await this.http.get(`/sysupdatelogs/${_context.sysupdatelog}/sysupdatefeatures/${_context.sysupdatefeatures}/select`);
            return res;
        }
        const res = await this.http.get(`/sysupdatefeatures/${_context.sysupdatefeatures}/select`);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
}
