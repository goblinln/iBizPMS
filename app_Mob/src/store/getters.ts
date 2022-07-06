/**
 * 获取代码表对象
 * 
 * @param state 
 */
export const getCodeList = (state: any) => (srfkey: string) => {
    return state.codelists.find((_codelist: any) => Object.is(_codelist.srfkey, srfkey));
}

/**
 * 获取代码表
 * 
 * @param state 
 */
export const getCodeListItems = (state: any) => (srfkey: string) => {
    let items: any[] = [];
    const codelist = state.codelists.find((_codelist: any) => Object.is(_codelist.srfkey, srfkey));
    if (!codelist) {
        console.log(`----${srfkey}----代码表不存在`);
    } else {
        items = [...codelist.items];
    }
    return items;
}

/**
 * 获取应用数据
 * 
 * @param state 
 */
export const getAppData = (state: any) => () => {
    return state.appdata;
}

/**
 * 获取本地应用数据
 * 
 * @param state 
 */
export const getLocalData = (state: any) => () => {
    return state.localdata;
}

/**
 * 获取导航标签页面
 * 
 * @param state 
 */
export const getPage = (state: any) => (arg: any) => {
    let page: any = null;
    if (isNaN(arg)) {
        const index = state.pageTagList.findIndex((page: any) => Object.is(page.fullPath, arg));
        if (index >= 0) {
            page = state.pageTagList[index];
        }
    } else {
        page = state.pageTagList[arg];
    }
    return page;
}

/**
 * 获取第三方应用名称
 * 
 * @param state 
 */
export const getThirdPartyName = (state: any) =>() => {
    return state.thirdPartyName;
}


/**
 * 获取 z-index 
 * 
 * @param state 
 */
export const getZIndex = (state: any) => () => {
    return state.zIndex;
}
/**
 * 获取搜索表单状态
 * 
 * @param state 
 * @param val 
 */
export const getSearchformStatus = (state: any) => {
    return state.searchformStatus;
  }

/** 获取单位数据
  * 
  * @param state 
  */
 export const getOrgData = (state: any) => (srfkey: string) => {
     let orgData = state.orgDataMap[srfkey];
     return orgData;
 }
 
 /**
  * 获取部门数据
  * 
  * @param state 
  */
 export const getDepData = (state: any) => (srfkey: string) => {
   let depData = state.depDataMap[srfkey];
   return depData;
 }

/**
 * 获取部门成员
 * 
 * @param state 
 */
export const getDepartmentPersonnel = (state: any) => () => {
  return state.departmentPersonnel;
}

/**
 * 获取全局数据
 * 
 * @param state 
 */
export const getAppGlobal = (state: any) => () => {
  return state.appGlobal;
}

/**
 * 获取顶层路由数据
 * 
 * @param state 
 */
export const getRouteViewGlobal = (state: any) => (key:string) => {
  return state.routeViewGlobal[key];
}

/**
 * 获取顶层视图
 * 
 * @param state 
 */
export const getView = (state: any) => (key:string) => {
  return state.appViews[key];
}