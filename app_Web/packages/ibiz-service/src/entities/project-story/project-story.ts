import { ProjectStoryBase } from './project-story-base';

/**
 * 需求
 *
 * @export
 * @class ProjectStory
 * @extends {ProjectStoryBase}
 * @implements {IProjectStory}
 */
export class ProjectStory extends ProjectStoryBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProjectStory
     */
    clone(): ProjectStory {
        return new ProjectStory(this);
    }
}
export default ProjectStory;
