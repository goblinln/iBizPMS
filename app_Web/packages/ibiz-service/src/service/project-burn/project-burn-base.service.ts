import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProjectBurn, ProjectBurn } from '../../entities';
import keys from '../../entities/project-burn/project-burn-keys';

/**
 * burn服务对象基类
 *
 * @export
 * @class ProjectBurnBaseService
 * @extends {EntityBaseService}
 */
export class ProjectBurnBaseService extends EntityBaseService<IProjectBurn> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'ProjectBurn';
    protected APPDENAMEPLURAL = 'ProjectBurns';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'date';
    protected quickSearchFields = ['date',];
    protected selectContextParam = {
        project: 'project',
    };

    newEntity(data: IProjectBurn): ProjectBurn {
        return new ProjectBurn(data);
    }

    async addLocal(context: IContext, entity: IProjectBurn): Promise<IProjectBurn | null> {
        return this.cache.add(context, new ProjectBurn(entity) as any);
    }

    async createLocal(context: IContext, entity: IProjectBurn): Promise<IProjectBurn | null> {
        return super.createLocal(context, new ProjectBurn(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProjectBurn> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.project && entity.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(context, entity.project);
            if (data) {
                entity.project = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IProjectBurn): Promise<IProjectBurn> {
        return super.updateLocal(context, new ProjectBurn(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProjectBurn = {}): Promise<IProjectBurn> {
        if (_context.project && _context.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(_context, _context.project);
            if (data) {
                entity.project = data.id;
            }
        }
        return new ProjectBurn(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBurnService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }
    /**
     * FetchEstimate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBurnService
     */
    async FetchEstimate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/projectburns/fetchestimate`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * ComputeBurn
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectBurnService
     */
    async ComputeBurn(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectburn) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectburns/${_context.projectburn}/computeburn`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * ComputeBurnBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectBurnServiceBase
     */
    public async ComputeBurnBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectburns/computeburnbatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
}
