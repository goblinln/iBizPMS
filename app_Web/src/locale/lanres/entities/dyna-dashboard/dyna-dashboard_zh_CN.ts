import DynaDashboard_zh_CN_Base from './dyna-dashboard_zh_CN_base';

function getLocaleResource(){
    const DynaDashboard_zh_CN_OwnData = {};
    const targetData = Object.assign(DynaDashboard_zh_CN_Base(), DynaDashboard_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;