import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIBIZProPlugin, IBIZProPlugin } from '../../entities';
import keys from '../../entities/ibizpro-plugin/ibizpro-plugin-keys';

/**
 * 系统插件服务对象基类
 *
 * @export
 * @class IBIZProPluginBaseService
 * @extends {EntityBaseService}
 */
export class IBIZProPluginBaseService extends EntityBaseService<IIBIZProPlugin> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IBIZProPlugin';
    protected APPDENAMEPLURAL = 'IBIZProPlugins';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/IBIZProPlugin.json';
    protected APPDEKEY = 'ibizpropluginid';
    protected APPDETEXT = 'ibizpropluginname';
    protected quickSearchFields = ['ibizpropluginname',];
    protected selectContextParam = {
    };

    newEntity(data: IIBIZProPlugin): IBIZProPlugin {
        return new IBIZProPlugin(data);
    }

    async addLocal(context: IContext, entity: IIBIZProPlugin): Promise<IIBIZProPlugin | null> {
        return this.cache.add(context, new IBIZProPlugin(entity) as any);
    }

    async createLocal(context: IContext, entity: IIBIZProPlugin): Promise<IIBIZProPlugin | null> {
        return super.createLocal(context, new IBIZProPlugin(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIBIZProPlugin> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIBIZProPlugin): Promise<IIBIZProPlugin> {
        return super.updateLocal(context, new IBIZProPlugin(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIBIZProPlugin = {}): Promise<IIBIZProPlugin> {
        return new IBIZProPlugin(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBIZProPluginService
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
     * @memberof IBIZProPluginService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibizproplugins`, _data);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBIZProPluginService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/ibizproplugins/${_context.ibizproplugin}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBIZProPluginService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibizproplugins/getdraft`, _data);
        return res;
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBIZProPluginService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/ibizproplugins/${_context.ibizproplugin}`);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBIZProPluginService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibizproplugins/${_context.ibizproplugin}`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBIZProPluginService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibizproplugins/fetchdefault`, _data);
    }
    /**
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBIZProPluginService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibizproplugins/${_context.ibizproplugin}/select`);
    }
}
