import { IBZProStoryBase } from './ibzpro-story-base';

/**
 * 需求
 *
 * @export
 * @class IBZProStory
 * @extends {IBZProStoryBase}
 * @implements {IIBZProStory}
 */
export class IBZProStory extends IBZProStoryBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IBZProStory
     */
    clone(): IBZProStory {
        return new IBZProStory(this);
    }
}
export default IBZProStory;
