import { LoadingServiceBase } from "./loading-service-base";


/**
 * 视图加载服务
 *
 * @export
 * @class ViewLoadingService
 * @extends {LoadingServiceBase}
 */
export class ViewLoadingService extends LoadingServiceBase {
    /**
     * 主视图sessionid
     *
     * @type {string[]}
     * @memberof ViewLoadingService
     */
     public srfsessionid: string = '';    
}