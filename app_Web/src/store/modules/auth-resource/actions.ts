/**
 * 提交统一资源数据
 * 
 * @param param0 
 * @param data 
 */
 export const commitAuthData = ({ commit, state }: { commit: any, state: any }, { unires, appmenu, enablepermissionvalid, srfdynainstid }: { unires: Array<any>, appmenu: Array<any>, enablepermissionvalid: boolean, srfdynainstid: string }) => {
    if (unires && unires.length > 0) {
        commit('setResourceData', unires);
    }
    if (appmenu && appmenu.length > 0) {
        commit('setMenuData', appmenu);
    }
    if (enablepermissionvalid === true || enablepermissionvalid === false) {
        commit('setEnablePermissionValid', enablepermissionvalid);
    }
    if (srfdynainstid) {
        commit('setStandDynainstid', srfdynainstid);
    }
}