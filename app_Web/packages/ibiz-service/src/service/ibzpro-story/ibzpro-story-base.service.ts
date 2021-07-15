import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIBZProStory, IBZProStory } from '../../entities';
import keys from '../../entities/ibzpro-story/ibzpro-story-keys';

/**
 * 需求服务对象基类
 *
 * @export
 * @class IBZProStoryBaseService
 * @extends {EntityBaseService}
 */
export class IBZProStoryBaseService extends EntityBaseService<IIBZProStory> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IBZProStory';
    protected APPDENAMEPLURAL = 'IBZProStories';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/IBZProStory.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'title';
    protected quickSearchFields = ['title',];
    protected selectContextParam = {
    };

    newEntity(data: IIBZProStory): IBZProStory {
        return new IBZProStory(data);
    }

    async addLocal(context: IContext, entity: IIBZProStory): Promise<IIBZProStory | null> {
        return this.cache.add(context, new IBZProStory(entity) as any);
    }

    async createLocal(context: IContext, entity: IIBZProStory): Promise<IIBZProStory | null> {
        return super.createLocal(context, new IBZProStory(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIBZProStory> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIBZProStory): Promise<IIBZProStory> {
        return super.updateLocal(context, new IBZProStory(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIBZProStory = {}): Promise<IIBZProStory> {
        return new IBZProStory(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProStoryService
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
     * @memberof IBZProStoryService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        const res = await this.http.post(`/ibzprostories`, _data);
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
     * @memberof IBZProStoryService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.get(`/ibzprostories/${_context.ibzprostory}`);
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
     * @memberof IBZProStoryService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibzprostories/getdraft`, _data);
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
     * @memberof IBZProStoryService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.delete(`/ibzprostories/${_context.ibzprostory}`);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * SyncFromIBIZ
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProStoryService
     */
    async SyncFromIBIZ(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/ibzprostories/${_context.ibzprostory}/syncfromibiz`, _data);
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
     * @memberof IBZProStoryService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data);
        const res = await this.http.put(`/ibzprostories/${_context.ibzprostory}`, _data);
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
     * @memberof IBZProStoryService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/ibzprostories/fetchdefault`, _data);
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
     * @memberof IBZProStoryService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.get(`/ibzprostories/${_context.ibzprostory}/select`);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }

    /**
     * SyncFromIBIZBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZProStoryServiceBase
     */
    public async SyncFromIBIZBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        const res = await this.http.post(`/ibzprostories/syncfromibizbatch`,_data);
        return res;
    }
}
