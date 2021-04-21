import { ProjectStatsBase } from './project-stats-base';

/**
 * 项目统计
 *
 * @export
 * @class ProjectStats
 * @extends {ProjectStatsBase}
 * @implements {IProjectStats}
 */
export class ProjectStats extends ProjectStatsBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProjectStats
     */
    clone(): ProjectStats {
        return new ProjectStats(this);
    }
}
export default ProjectStats;
