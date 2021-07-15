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
    constructor(opts?: any) {
        const { context: context, tag: cacheKey } = opts;
        super(context);
        if (___ibz___.sc.has(cacheKey)) {
            return ___ibz___.sc.get(cacheKey);
        }
        ___ibz___.sc.set(cacheKey, this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProjectTeamService}
     * @memberof ProjectTeamService
     */
	static getInstance(context?: any): ProjectTeamService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}ProjectTeamService` : `ProjectTeamService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new ProjectTeamService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
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
