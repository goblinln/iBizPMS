import { StorySpecBase } from './story-spec-base';

/**
 * 需求描述
 *
 * @export
 * @class StorySpec
 * @extends {StorySpecBase}
 * @implements {IStorySpec}
 */
export class StorySpec extends StorySpecBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof StorySpec
     */
    clone(): StorySpec {
        return new StorySpec(this);
    }
}
export default StorySpec;
