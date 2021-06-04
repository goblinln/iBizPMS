import { ProjectDailyBase } from './project-daily-base';

/**
 * 项目日报
 *
 * @export
 * @class ProjectDaily
 * @extends {ProjectDailyBase}
 * @implements {IProjectDaily}
 */
export class ProjectDaily extends ProjectDailyBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProjectDaily
     */
    clone(): ProjectDaily {
        return new ProjectDaily(this);
    }
}
export default ProjectDaily;
