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
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProStoryService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzprostories/${_context.ibzprostory}/select`);
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
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibzprostories`, _data);
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
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibzprostories/${_context.ibzprostory}`, _data);
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
        return this.http.delete(`/ibzprostories/${_context.ibzprostory}`);
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
        const res = await this.http.get(`/ibzprostories/${_context.ibzprostory}`);
        return res;
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
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibzprostories/getdraft`, _data);
        return res;
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
        return this.http.post(`/ibzprostories/${_context.ibzprostory}/syncfromibiz`, _data);
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
        return this.http.post(`/ibzprostories/fetchdefault`, _data);
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
    public async SyncFromIBIZBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/ibzprostories/syncfromibizbatch`,tempData,isloading);
    }
}
