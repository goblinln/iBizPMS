import { StoryStageBase } from './story-stage-base';

/**
 * 需求阶段
 *
 * @export
 * @class StoryStage
 * @extends {StoryStageBase}
 * @implements {IStoryStage}
 */
export class StoryStage extends StoryStageBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof StoryStage
     */
    clone(): StoryStage {
        return new StoryStage(this);
    }
}
export default StoryStage;
