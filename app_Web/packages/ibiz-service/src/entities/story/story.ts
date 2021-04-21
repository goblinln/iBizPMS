import { StoryBase } from './story-base';

/**
 * 需求
 *
 * @export
 * @class Story
 * @extends {StoryBase}
 * @implements {IStory}
 */
export class Story extends StoryBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof Story
     */
    clone(): Story {
        return new Story(this);
    }
}
export default Story;
