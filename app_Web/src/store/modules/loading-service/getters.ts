import { ViewLoadingService } from "ibiz-vue"

/**
 * 判断指定菜单权限是否存在
 * 
 * @param state 
 */
export const getViewLoadingServicesByRefViewPath = (state: any) => ( srfsessionid: string) => {
    return state.viewLoadingServiceList.filter((service: ViewLoadingService)=>{
        return service.srfsessionid == srfsessionid;
    })
}
