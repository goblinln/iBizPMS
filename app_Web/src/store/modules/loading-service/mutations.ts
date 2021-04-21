import { ViewLoadingService } from 'ibiz-vue';

/**
 * 设置统一资源数据
 * 
 * @param state 
 * @param resourceArray
 */
export const addViewLoadingService = (state: any, viewLoadingService: ViewLoadingService) => {
    if(viewLoadingService){
        return;
    }
    state.viewLoadingServiceList.push(viewLoadingService);
}
