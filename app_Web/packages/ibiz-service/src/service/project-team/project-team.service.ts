import { Http } from 'ibiz-core';
import { ProjectTeamBaseService } from './project-team-base.service';

/**
 * 项目团队服务
 *
 * @export
 * @class ProjectTeamService
 * @extends {ProjectTeamBaseService}
 */
export class ProjectTeamService extends ProjectTeamBaseService {
    /**
     * Creates an instance of ProjectTeamService.
     * @memberof ProjectTeamService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProjectTeamService')) {
            return ___ibz___.sc.get('ProjectTeamService');
        }
        ___ibz___.sc.set('ProjectTeamService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProjectTeamService}
     * @memberof ProjectTeamService
     */
    static getInstance(): ProjectTeamService {
        if (!___ibz___.sc.has('ProjectTeamService')) {
            new ProjectTeamService();
        }
        return ___ibz___.sc.get('ProjectTeamService');
    }

    /**
     * saveBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectTeamService
     */
    public async saveBatch(context: any = {}, data: any, isloading?: boolean): Promise<any> {
        if (context['project']) {
            return Http.getInstance().post(`/projects/${context['project']}/${this.APPDENAMEPLURAL.toLowerCase()}/savebatch`, data, isloading);
        } 
    }

}
export default ProjectTeamService;
