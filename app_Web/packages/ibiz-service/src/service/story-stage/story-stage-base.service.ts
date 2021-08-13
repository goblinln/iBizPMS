import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IStoryStage, StoryStage } from '../../entities';
import keys from '../../entities/story-stage/story-stage-keys';

/**
 * 需求阶段服务对象基类
 *
 * @export
 * @class StoryStageBaseService
 * @extends {EntityBaseService}
 */
export class StoryStageBaseService extends EntityBaseService<IStoryStage> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'StoryStage';
    protected APPDENAMEPLURAL = 'StoryStages';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/StoryStage.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'story';
    protected quickSearchFields = ['story',];
    protected selectContextParam = {
    };

    constructor(opts?: any) {
        super(opts, 'StoryStage', 1);
    }

    newEntity(data: IStoryStage): StoryStage {
        return new StoryStage(data);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IStoryStage> {
        const entity = await super.getLocal(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IStoryStage): Promise<IStoryStage> {
        return super.updateLocal(context, new StoryStage(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IStoryStage = {}): Promise<IStoryStage> {
        return new StoryStage(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StoryStageService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }
}
