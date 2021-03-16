import { SubStoryBase } from './sub-story-base';

/**
 * 需求
 *
 * @export
 * @class SubStory
 * @extends {SubStoryBase}
 * @implements {ISubStory}
 */
export class SubStory extends SubStoryBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof SubStory
     */
    clone(): SubStory {
        return new SubStory(this);
    }
}
export default SubStory;
