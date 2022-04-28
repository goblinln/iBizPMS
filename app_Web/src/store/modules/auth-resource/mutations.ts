/**
 * 设置统一资源数据
 * 
 * @param state 
 * @param resourceArray
 */
export const setResourceData = (state: any, resourceArray:Array<any>) => {
    if(!resourceArray){
        return;
    }
    state.resourceData = resourceArray;
}

/**
 * 设置菜单数据
 * 
 * @param state 
 * @param resourceArray
 */
export const setMenuData = (state: any, menuArray:Array<any>) => {
    if(!menuArray){
        return;
    }
    state.menuData = menuArray;
}

/**
 * 设置是否开启权限认证
 * 
 * @param state 
 * @param resourceArray
 */
export const setEnablePermissionValid = (state: any, enablepermissionvalid:boolean) => {
    state.enablePermissionValid = enablepermissionvalid;
}

/**
 * 设置标准模型实例标识
 * 
 * @param state 
 * @param srfdynainstid
 */
 export const setStandDynainstid = (state: any, srfdynainstid:string) => {
    state.srfdynainstid = srfdynainstid;
}

/**
 * 设置实体权限数据
 * 
 * @param state 
 * @param {key:string,value:any}
 */
 export const setSrfappdeData = (state: any, {key,value}:{key:string,value:any}) => {
    state.srfappdeData[key] = value;
}
