import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IStorySpec, StorySpec } from '../../entities';
import keys from '../../entities/story-spec/story-spec-keys';

/**
 * 需求描述服务对象基类
 *
 * @export
 * @class StorySpecBaseService
 * @extends {EntityBaseService}
 */
export class StorySpecBaseService extends EntityBaseService<IStorySpec> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'StorySpec';
    protected APPDENAMEPLURAL = 'StorySpecs';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/StorySpec.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'title';
    protected quickSearchFields = ['title',];
    protected selectContextParam = {
        story: 'story',
    };

    constructor(opts?: any) {
        super(opts, 'StorySpec');
    }

    newEntity(data: IStorySpec): StorySpec {
        return new StorySpec(data);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IStorySpec> {
        const entity = await super.getLocal(context, srfKey);
        if (entity && entity.story && entity.story !== '') {
            const s = await ___ibz___.gs.getStoryService();
            const data = await s.getLocal2(context, entity.story);
            if (data) {
                entity.story = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IStorySpec): Promise<IStorySpec> {
        return super.updateLocal(context, new StorySpec(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IStorySpec = {}): Promise<IStorySpec> {
        if (_context.story && _context.story !== '') {
            const s = await ___ibz___.gs.getStoryService();
            const data = await s.getLocal2(_context, _context.story);
            if (data) {
                entity.story = data.id;
            }
        }
        return new StorySpec(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StorySpecService
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
     * FetchVersion
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StorySpecService
     */
    async FetchVersion(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.story && true) {
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/stories/${_context.story}/storyspecs/fetchversion`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchVersion');
            return res;
        }
        if (_context.project && _context.story && true) {
            const res = await this.http.post(`/projects/${_context.project}/stories/${_context.story}/storyspecs/fetchversion`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchVersion');
            return res;
        }
        if (_context.product && _context.story && true) {
            const res = await this.http.post(`/products/${_context.product}/stories/${_context.story}/storyspecs/fetchversion`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchVersion');
            return res;
        }
    this.log.warn([`[StorySpec]>>>[FetchVersion函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
}
