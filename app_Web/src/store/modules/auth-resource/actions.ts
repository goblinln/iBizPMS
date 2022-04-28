/**
 * 提交统一资源数据
 * 
 * @param param0 
 * @param data 
 */
 export const commitAuthData = ({ commit, state }: { commit: any, state: any }, { unires, appmenu, enablepermissionvalid, srfdynainstid }: { unires: Array<any>, appmenu: Array<any>, enablepermissionvalid: boolean, srfdynainstid: string }) => {

  commit('setResourceData', unires && unires.length > 0 ? unires : []);

  commit('setMenuData', appmenu && appmenu.length > 0 ? appmenu : []);

  commit('setEnablePermissionValid', enablepermissionvalid === true);

  commit('setStandDynainstid', srfdynainstid || '');
}