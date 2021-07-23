import { HttpResponse } from 'ibiz-core';
import { ProjectBaseService } from './project-base.service';

/**
 * 项目服务
 *
 * @export
 * @class ProjectService
 * @extends {ProjectBaseService}
 */
export class ProjectService extends ProjectBaseService {
    /**
     * Creates an instance of ProjectService.
     * @memberof ProjectService
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
     * @param 应用上下文
     * @return {*}  {ProjectService}
     * @memberof ProjectService
     */
    static getInstance(context?: any): ProjectService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}ProjectService` : `ProjectService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new ProjectService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
        /**
     * CancelProjectTop
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
         async CancelProjectTop(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
            let res: any = this.http.post(`/projects/${_context.project}/cancelprojecttop`, _data);
            return res;
        }
      
        /**
         * ProjectTop
         *
         * @param {*} [_context={}]
         * @param {*} [_data = {}]
         * @returns {Promise<HttpResponse>}
         * @memberof ProjectService
         */
        async ProjectTop(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
            let res: any = this.http.post(`/projects/${_context.project}/projecttop`, _data);
            return res;
        }
      
        /**
         * UpdateProjectCycle接口方法
         *
         * @param {*} [context={}]
         * @param {*} [data={}]
         * @param {boolean} [isloading]
         * @returns {Promise<any>}
         * @memberof ProjectService
         */
        public async UpdateProjectCycle(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
            let res: any = { status: 200, data: {} };
            if (!(data && data.begin && data.period)) {
                if (data.end && data.begin) {
                    let begin: Date = new Date(data.begin);
                    let end: Date = new Date(data.end);
                    data.period = Math.floor((end.getTime() - begin.getTime()) / (1000 * 60 * 60 * 24)) + 1;
                } else {
                    return res;
                }
      
            }
            let begin: Date = new Date(data.begin.substring(0, 10));
            let period = parseInt(data.period);
            let days: number = 0;
            let curWeek: number = begin.getDay();
            begin.setDate(begin.getDate() + period - 1);
            for (; period > 0; period--, curWeek++) {
                curWeek = curWeek > 6 ? (curWeek - 7) : curWeek;
                if (curWeek > 0 && curWeek < 6) {
                    days++;
                }
            }
            let year = begin.getFullYear();
            let month = begin.getMonth() + 1;
            let day = begin.getDate();
            Object.assign(res.data, {
                end: `${year}-${month < 10 ? ('0' + month) : month}-${day < 10 ? ('0' + day) : day}`,
                days: days
            });
            return res;
        }
      
        /**
         * UpdateCycle接口方法
         *
         * @param {*} [context={}]
         * @param {*} [data={}]
         * @param {boolean} [isloading]
         * @returns {Promise<any>}
         * @memberof ProjectServiceBase
         */
        public async UpdateCycle(context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
            let res: any = { status: 200, data: {} };
            if (!(data && data.begin && data.end)) {
                return res;
            }
            let begin: Date = new Date(data.begin.substring(0, 10));
            let end: Date = new Date(data.end);
            let period = Math.floor((end.getTime() - begin.getTime()) / (1000 * 60 * 60 * 24)) + 1;
            let days: number = 0;
            let curWeek: number = begin.getDay();
            begin.setDate(begin.getDate() + period);
            for (; period > 0; period--, curWeek++) {
                curWeek = curWeek > 6 ? (curWeek - 7) : curWeek;
                if (curWeek > 0 && curWeek < 6) {
                    days++;
                }
            }
      
            Object.assign(res.data, {
                days: days
            });
            return res;
        }
        
        /**
         * GetDraft接口方法
         *
         * @param {*} [context={}]
         * @param {*} [data={}]
         * @param {boolean} [isloading]
         * @returns {Promise<any>}
         * @memberof ProjectServiceBase
         */
         public async GetDraft(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
            let res:any = await this.http.get(`/projects/getdraft`,isloading);
            if(context.end && context.begin) {
                let begin: Date = new Date(data.begin.substring(0,10));
                let end: Date = new Date(context.end);
                let period = Math.floor((end.getTime() - begin.getTime())/(1000 * 60 * 60 *24)) + 1;
                let days: number = 0;
                let curWeek: number = begin.getDay();
                begin.setDate(begin.getDate() + period);
                for(; period > 0; period--, curWeek++) {
                    curWeek = curWeek > 6 ? (curWeek - 7) : curWeek;
                    if(curWeek > 0 && curWeek < 6) {
                        days++;
                    }
                }
                res.data.days = days;
            }
            if(context.planid) {
                let srfarray: any = [{plans: context.planid,branchs: context.branch,products:context.product}];
                res.data.plans = context.planid;
                res.branchs = context.branch;
                res.products = context.product;
                res.data.srfarray = JSON.stringify(srfarray);
            }
            res.data.project = data.project;
            // this.tempStorage.setItem(context.srfsessionkey+'_tasks',JSON.stringify(res.data.tasks?res.data.tasks:[]));
            return res;
        }
      
}
export default ProjectService;
